package postest2;

public class POSPowerConstantMapper extends CommonConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "CapUPSChargeState" Capability and "UPSChargeState" Property
	// Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapUPSChargeState)
	public static final ConstantConverter PWR_UPS_FULL = new ConstantConverter(0x00000001, "PWR_UPS_FULL");
	@BelongingProperty(PropertyNames.getCapUPSChargeState)
	public static final ConstantConverter PWR_UPS_WARNING = new ConstantConverter(0x00000002,
			"PWR_UPS_WARNING");
	@BelongingProperty(PropertyNames.getCapUPSChargeState)
	public static final ConstantConverter PWR_UPS_LOW = new ConstantConverter(0x00000004, "PWR_UPS_LOW");
	@BelongingProperty(PropertyNames.getCapUPSChargeState)
	public static final ConstantConverter PWR_UPS_CRITICAL = new ConstantConverter(0x00000008,
			"PWR_UPS_CRITICAL");

	// ///////////////////////////////////////////////////////////////////
	// "restartPOS", "standbyPOS", "suspendPOS" Methods:
	// "reason" Parameter Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter PWR_REASON_REQUEST = new ConstantConverter(1, "PWR_REASON_REQUEST");
	public static final ConstantConverter PWR_REASON_ALLOW = new ConstantConverter(2, "PWR_REASON_ALLOW");
	public static final ConstantConverter PWR_REASON_DENY = new ConstantConverter(3, "PWR_REASON_DENY");

	// ///////////////////////////////////////////////////////////////////
	// "PowerSource" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getPowerSource)
	public static final ConstantConverter PWR_SOURCE_NA = new ConstantConverter(1, "PWR_SOURCE_NA");
	@BelongingProperty(PropertyNames.getPowerSource)
	public static final ConstantConverter PWR_SOURCE_AC = new ConstantConverter(2, "PWR_SOURCE_AC");
	@BelongingProperty(PropertyNames.getPowerSource)
	public static final ConstantConverter PWR_SOURCE_BATTERY = new ConstantConverter(3, "PWR_SOURCE_BATTERY");
	@BelongingProperty(PropertyNames.getPowerSource)
	public static final ConstantConverter PWR_SOURCE_BACKUP = new ConstantConverter(4, "PWR_SOURCE_BACKUP");

	public static int getConstantNumberFromString(String constant) {
		
		if (PWR_SOURCE_NA.getConstant().equals(constant)) {
			return PWR_SOURCE_NA.getContantNumber();
		}

		if (PWR_SOURCE_AC.getConstant().equals(constant)) {
			return PWR_SOURCE_AC.getContantNumber();
		}

		if (PWR_SOURCE_BATTERY.getConstant().equals(constant)) {
			return PWR_SOURCE_BATTERY.getContantNumber();
		}

		if (PWR_SOURCE_BACKUP.getConstant().equals(constant)) {
			return PWR_SOURCE_BACKUP.getContantNumber();
		}

		if (PWR_UPS_FULL.getConstant().equals(constant)) {
			return PWR_UPS_FULL.getContantNumber();
		}

		if (PWR_UPS_WARNING.getConstant().equals(constant)) {
			return PWR_UPS_WARNING.getContantNumber();
		}

		if (PWR_UPS_LOW.getConstant().equals(constant)) {
			return PWR_UPS_LOW.getContantNumber();
		}

		if (PWR_UPS_CRITICAL.getConstant().equals(constant)) {
			return PWR_UPS_CRITICAL.getContantNumber();
		}

		if (PWR_REASON_REQUEST.getConstant().equals(constant)) {
			return PWR_REASON_REQUEST.getContantNumber();
		}

		if (PWR_REASON_ALLOW.getConstant().equals(constant)) {
			return PWR_REASON_ALLOW.getContantNumber();
		}

		if (PWR_REASON_DENY.getConstant().equals(constant)) {
			return PWR_REASON_DENY.getContantNumber();
		}

		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
