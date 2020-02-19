package postest2;

public class CashChangerConstantMapper extends CommonConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "DeviceStatus" and "FullStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter CHAN_STATUS_OK = new ConstantConverter(0, "STATUS_OK");
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter CHAN_STATUS_EMPTY = new ConstantConverter(11, "STATUS_EMPTY");
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter CHAN_STATUS_NEAREMPTY = new ConstantConverter(12,
			"STATUS_NEAREMPTY");
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter CHAN_STATUS_FULL = new ConstantConverter(21, "STATUS_FULL");
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter CHAN_STATUS_NEARFULL = new ConstantConverter(22, "STATUS_NEARFULL");
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter CHAN_STATUS_JAM = new ConstantConverter(31, "STATUS_JAM");

	// ///////////////////////////////////////////////////////////////////
	// "DepositStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter CHAN_STATUS_DEPOSIT_START = new ConstantConverter(1,
			"STATUS_DEPOSIT_START");
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter CHAN_STATUS_DEPOSIT_END = new ConstantConverter(2,
			"STATUS_DEPOSIT_END");
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter CHAN_STATUS_DEPOSIT_COUNT = new ConstantConverter(4,
			"STATUS_DEPOSIT_COUNT");
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter CHAN_STATUS_DEPOSIT_JAM = new ConstantConverter(5,
			"STATUS_DEPOSIT_JAM");

	public static int getConstantNumberFromString(String constant) {

		if (CashChangerConstantMapper.CHAN_STATUS_OK.getConstant().equals(constant)) {
			return CashChangerConstantMapper.CHAN_STATUS_OK.getContantNumber();
		}

		if (CashChangerConstantMapper.CHAN_STATUS_EMPTY.getConstant().equals(constant)) {
			return CashChangerConstantMapper.CHAN_STATUS_EMPTY.getContantNumber();
		}

		if (CashChangerConstantMapper.CHAN_STATUS_NEAREMPTY.getConstant().equals(constant)) {
			return CashChangerConstantMapper.CHAN_STATUS_NEAREMPTY.getContantNumber();
		}

		if (CashChangerConstantMapper.CHAN_STATUS_FULL.getConstant().equals(constant)) {
			return CashChangerConstantMapper.CHAN_STATUS_FULL.getContantNumber();
		}

		if (CashChangerConstantMapper.CHAN_STATUS_NEARFULL.getConstant().equals(constant)) {
			return CashChangerConstantMapper.CHAN_STATUS_NEARFULL.getContantNumber();
		}

		if (CashChangerConstantMapper.CHAN_STATUS_JAM.getConstant().equals(constant)) {
			return CashChangerConstantMapper.CHAN_STATUS_JAM.getContantNumber();
		}

		if (CashChangerConstantMapper.CHAN_STATUS_DEPOSIT_START.getConstant().equals(constant)) {
			return CashChangerConstantMapper.CHAN_STATUS_DEPOSIT_START.getContantNumber();
		}

		if (CashChangerConstantMapper.CHAN_STATUS_DEPOSIT_END.getConstant().equals(constant)) {
			return CashChangerConstantMapper.CHAN_STATUS_DEPOSIT_END.getContantNumber();
		}

		if (CashChangerConstantMapper.CHAN_STATUS_DEPOSIT_COUNT.getConstant().equals(constant)) {
			return CashChangerConstantMapper.CHAN_STATUS_DEPOSIT_COUNT.getContantNumber();
		}

		if (CashChangerConstantMapper.CHAN_STATUS_DEPOSIT_JAM.getConstant().equals(constant)) {
			return CashChangerConstantMapper.CHAN_STATUS_DEPOSIT_JAM.getContantNumber();
		}

		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
