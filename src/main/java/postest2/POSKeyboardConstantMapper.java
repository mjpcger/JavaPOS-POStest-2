package postest2;

public class POSKeyboardConstantMapper extends CommonConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "EventTypes" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getEventTypes)
	public static final ConstantConverter KBD_ET_DOWN = new ConstantConverter(1, "KBD_ET_DOWN");
	@BelongingProperty(PropertyNames.getEventTypes)
	public static final ConstantConverter KBD_ET_DOWN_UP = new ConstantConverter(2, "KBD_ET_DOWN_UP");

	// ///////////////////////////////////////////////////////////////////
	// "POSKeyEventType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter KBD_KET_KEYDOWN = new ConstantConverter(1, "KBD_KET_KEYDOWN");
	public static final ConstantConverter KBD_KET_KEYUP = new ConstantConverter(2, "KBD_KET_KEYUP");

	
	public static int getConstantNumberFromString(String constant) {

		if (KBD_ET_DOWN.getConstant().equals(constant)) {
			return KBD_ET_DOWN.getContantNumber();
		}
		
		if (KBD_ET_DOWN_UP.getConstant().equals(constant)) {
			return KBD_ET_DOWN_UP.getContantNumber();
		}
		
		if (KBD_KET_KEYDOWN.getConstant().equals(constant)) {
			return KBD_KET_KEYDOWN.getContantNumber();
		}
		
		if (KBD_KET_KEYUP.getConstant().equals(constant)) {
			return KBD_KET_KEYUP.getContantNumber();
		}

		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
