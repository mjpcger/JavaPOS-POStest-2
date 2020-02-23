package postest2;

public class KeylockConstantMapper extends CommonConstantMapper {
	
	// ///////////////////////////////////////////////////////////////////
	// "KeyPosition" Property Constants
	// "WaitForKeylockChange" Method: "KeyPosition" Parameter
	// "StatusUpdateEvent" Event: "status" Parameter
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter LOCK_KP_ANY = new ConstantConverter(0, "LOCK_KP_ANY"); // WaitForKeylockChange Only
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter LOCK_KP_ELECTRONIC = new ConstantConverter(0, "LOCK_KP_ELECTRONIC"); // StatusUpdateEvent Only
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter LOCK_KP_LOCK = new ConstantConverter(1, "LOCK_KP_LOCK");
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter LOCK_KP_NORM = new ConstantConverter(2, "LOCK_KP_NORM");
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter LOCK_KP_SUPR = new ConstantConverter(3, "LOCK_KP_SUPR");

	// ///////////////////////////////////////////////////////////////////
	// "CapKeylockType" Property Constants Added in 1.11
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapKeylockType)
	public static final ConstantConverter LOCK_KT_STANDARD = new ConstantConverter(1, "LOCK_KT_STANDARD");
	@BelongingProperty(PropertyNames.getCapKeylockType)
	public static final ConstantConverter LOCK_KT_ELECTRONIC = new ConstantConverter(2, "LOCK_KT_ELECTRONIC");

	public static int getConstantNumberFromString(String constant){
		
		if(LOCK_KT_STANDARD.getConstant().equals(constant)) {
			return LOCK_KT_STANDARD.getContantNumber();
		}

		if(LOCK_KT_ELECTRONIC.getConstant().equals(constant)) {
			return LOCK_KT_ELECTRONIC.getContantNumber();
		}

		if(LOCK_KP_ANY.getConstant().equals(constant)) {
			return LOCK_KP_ANY.getContantNumber();
		}

		if(LOCK_KP_ELECTRONIC.getConstant().equals(constant)) {
			return LOCK_KP_ELECTRONIC.getContantNumber();
		}

		if(LOCK_KP_LOCK.getConstant().equals(constant)) {
			return LOCK_KP_LOCK.getContantNumber();
		}

		if(LOCK_KP_NORM.getConstant().equals(constant)) {
			return LOCK_KP_NORM.getContantNumber();
		}

		if(LOCK_KP_SUPR.getConstant().equals(constant)) {
			return LOCK_KP_SUPR.getContantNumber();
		}

		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
