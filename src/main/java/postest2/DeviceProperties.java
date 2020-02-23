package postest2;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jpos.BaseJposControl;
import jpos.JposException;

public class DeviceProperties {
	public static enum ByteConversion {
		Hexadecimal,
		Ascii,
		Decoded,
		Length
	}

	public static class ConversionData {
		String Encoding;
		ByteConversion Conversion;
		int MaxLen;
	}

	static private Map<BaseJposControl, ConversionData> dataMap = new HashMap<>();

	static public String getEncoding(BaseJposControl control) {
		ConversionData cvd = dataMap.get(control);
		return cvd == null ? null : cvd.Encoding;
	}

	static public void setEncoding(BaseJposControl control, String encoding) {
		ConversionData cvd = prepareConversionData(control);
		cvd.Encoding = encoding;
	}

	static public ByteConversion getConversion(BaseJposControl control) {
		ConversionData cvd = dataMap.get(control);
		return cvd == null ? ByteConversion.Ascii : cvd.Conversion;
	}

	static public void setConversion(BaseJposControl control, ByteConversion conversion) {
		ConversionData cvd = prepareConversionData(control);
		cvd.Conversion = conversion;
	}

	static public int getMaxLen(BaseJposControl control) {
		ConversionData cvd = dataMap.get(control);
		return cvd == null ? 120 : cvd.MaxLen;
	}

	static public void setMaxLen(BaseJposControl control, int maxlen) {
		ConversionData cvd = prepareConversionData(control);
		cvd.MaxLen = maxlen;
	}

	private static ConversionData prepareConversionData(BaseJposControl control) {
		ConversionData cvd = dataMap.get(control);
		if (cvd == null) {
			dataMap.put(control, cvd = new ConversionData());
			cvd.Encoding = null;
			cvd.Conversion = ByteConversion.Ascii;
			cvd.MaxLen = 120;
		}
		return cvd;
	}

	static String stringOf(Object o, BaseJposControl control) {
		int MaxLen = getMaxLen(control);
		ByteConversion Conversion = getConversion(control);
		String Encoding = getEncoding(control);
		if (o == null)
			return "[null]";
		if (o instanceof byte[]) {
			byte[] data = (byte[]) o;
			String result = "";
			switch (Conversion) {
				case Hexadecimal: {
					for (byte b : data) {
						result += new String(new char[] {
								"0123456789ABCDEF".charAt((b & 0xf0) >>4),
								"0123456789ABCDEF".charAt(b & 0xf)
						});
						if (result.length() >= MaxLen - 1)
							break;
					}
					return result;
				}
				case Ascii: {
					for (byte b : data) {
						if ((b & 0xff) >= 0x20 && (b & 0xff) < 0x7f && b != (byte) '\\' && b != (byte) ']') {
							result += new String(new char[]{(char)(b & 0xff)});
						} else if (b == (byte)'\\' || b == (byte) ']') {
							result += "\\" + new String(new byte[]{b});
						} else {
							result += "\\x" + new String(new char[] {
									"0123456789ABCDEF".charAt((b & 0xf0) >> 4),
									"0123456789ABCDEF".charAt(b & 0xf)
							});
						}
						if (result.length() >= MaxLen - 1)
							break;
					}
					return result;
				}
				case Decoded: {
					String value = new String(data);
					try {
						if (Encoding != null)
							value = new String(data, Encoding);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return value.length() <= MaxLen ? value : value.substring(0, MaxLen);
				}
				case Length: {
					return "[" + data.length + " Byte]";
				}
			}
		}
		return o.toString();
	}

	public static String getPropertyValue(BaseJposControl object, IMapWrapper mapper, String getter) {
		try {
			for (Method method : Class.forName(object.getClass().getName()).getMethods()) {
				if (method.getName().equals(getter)) {
					Object obj = method.invoke(object);
					for (String value : BelongingPropertyChecker.invokeThis(mapper, getter)) {
						if (obj.equals(Class.forName(mapper.getClass().getName())
								.getMethod("getConstantNumberFromString", String.class).invoke(mapper, value))) {
							return value;
						}
					}
					return stringOf(obj, object);
				}
			}
		} catch (Exception e) {}
		return null;
	}

	public static String getProperties(BaseJposControl object, IMapWrapper objectMap) throws JposException {

		String properties = "";

		try {
			Method[] methods = Class.forName(object.getClass().getName()).getMethods();

			for (Method method : methods) {
				if (isGetter(method)) {
					String methodName = method.getName();
					try {
						if (method.getReturnType().equals(Integer.TYPE)) {
							if (objectMap != null) {
								ArrayList<String> al = BelongingPropertyChecker.invokeThis(objectMap, methodName);
								if (!al.isEmpty()) {
									int rightValue = (Integer) method.invoke(object, null);
									properties += method.getName().substring(3) + ": ";
									for (String value : al) {
										int temp = (Integer) Class.forName(objectMap.getClass().getName())
												.getMethod("getConstantNumberFromString", String.class)
												.invoke(objectMap, value);
										if (rightValue == temp) {
											properties += value;
											break;
										}
									}
									properties += "\n";
								} else {
									properties += method.getName().substring(3) + ": " + (Integer) method.invoke(object, null);
									properties += "\n";
								}
							}
						} else {
							properties += method.getName().substring(3) + ": " + stringOf(method.invoke(object), object);
							properties += "\n";
						} // end if return type
					} catch (InvocationTargetException e) {
						if (e.getTargetException() instanceof JposException) {
							properties += method.getName().substring(3) + ": [No access]\n";
						} else {
							e.printStackTrace();
							break;
						}
					}
				} // end if is getter
			} // end for

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return properties;
	}

	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}

}
