package postest2;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class BelongingPropertyChecker {

	public static ArrayList<String> invokeThis(IMapWrapper mapper, String methodName) {

		IMapWrapper imw = mapper.getTheClass();

		// get all Fields from the Class
		Field[] fields = null;
		try {
			fields = Class.forName(imw.getClass().getName()).getFields();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<String> stringConstants = new ArrayList<String>();

		for (Field field : fields) {

			if (field.isAnnotationPresent(BelongingProperty.class)) {
				BelongingProperty belongingProperty = field.getAnnotation(BelongingProperty.class);
				PropertyNames names = belongingProperty.value();

				if (mapper instanceof RFIDScannerConstantMapper) {
					if (methodName.equals("getProtocolMask")) {
						methodName = "getCapMultipleProtocols";
					}
				}

				if (mapper instanceof POSPowerConstantMapper) {
					if (methodName.equals("getUPSChargeState")) {
						methodName = "getCapUPSChargeState";
					}
				}

				if (mapper instanceof PointCardRWConstantMapper) {
					if (methodName.equals("getCapTracksToWrite")) {
						methodName = "getCapTracksToRead";
					}
				}

				if (mapper instanceof ElectronicJournalConstantMapper) {
					if (methodName.equals("getStation")) {
						methodName = "getCapStation";
					}
				}

				if (mapper instanceof MSRConstantMapper) {
					if (methodName.equals("getCapWritableTracks") || methodName.equals("getTracksToWrite")) {
						methodName = "getTracksToRead";
					}

					if (methodName.equals("getDataEncryptionAlgorithm")) {
						methodName = "getCapDataEncryption";
					}
				}

				if (mapper instanceof POSPrinterConstantMapper) {
					if (methodName.equals("getCapJrnColor") || methodName.equals("getCapRecColor")
							|| methodName.equals("getCapSlpColor")) {
						methodName = "getCapColor";
					}

					if (methodName.equals("getCapJrnCartridgeSensor")
							|| methodName.equals("getCapRecCartridgeSensor")
							|| methodName.equals("getCapSlpCartridgeSensor")
							|| methodName.equals("getJrnCartridgeState")
							|| methodName.equals("getRecCartridgeState")
							|| methodName.equals("getSlpCartridgeState")) {
						methodName = "getCapCartridge";
					}

					if (methodName.equals("getCapRecRuledLine") || methodName.equals("getCapSlpRuledLine")) {
						methodName = "getCapRuledLine";
					}

					if (methodName.equals("getErrorStation") || methodName.equals("getPageModeStation")) {
						methodName = "getPTRStation";
					}
				}

				if (methodName.equals(names.toString())) {
					if (ConstantConverter.class.isAssignableFrom(field.getType())) {
						ConstantConverter c = null;
						try {
							c = (ConstantConverter) field.get(mapper.getClass());
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
						stringConstants.add(c.getConstant());
					}
				}
			}
		}
		return stringConstants;
	}
}
