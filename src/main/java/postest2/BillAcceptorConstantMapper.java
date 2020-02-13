package postest2;

public class BillAcceptorConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "DepositStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter BACC_STATUS_DEPOSIT_START = new ConstantConverter(1,
			"STATUS_DEPOSIT_START");
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter BACC_STATUS_DEPOSIT_END = new ConstantConverter(2,
			"STATUS_DEPOSIT_END");
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter BACC_STATUS_DEPOSIT_COUNT = new ConstantConverter(4,
			"STATUS_DEPOSIT_COUNT");
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter BACC_STATUS_DEPOSIT_JAM = new ConstantConverter(5,
			"STATUS_DEPOSIT_JAM");

	// ///////////////////////////////////////////////////////////////////
	// "FullStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getFullStatus)
	public static final ConstantConverter BACC_STATUS_OK = new ConstantConverter(0, "BACC_STATUS_OK");
	@BelongingProperty(PropertyNames.getFullStatus)
	public static final ConstantConverter BACC_STATUS_FULL = new ConstantConverter(21, "BACC_STATUS_FULL");
	@BelongingProperty(PropertyNames.getFullStatus)
	public static final ConstantConverter BACC_STATUS_NEARFULL = new ConstantConverter(22,
			"BACC_STATUS_NEARFULL");

	// ///////////////////////////////////////////////////////////////////
	// "EndDeposit" Method Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter BACC_DEPOSIT_COMPLETE = new ConstantConverter(11,
			"BACC_DEPOSIT_COMPLETE");

	// ///////////////////////////////////////////////////////////////////
	// "PauseDeposit" Method Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter BACC_DEPOSIT_PAUSE = new ConstantConverter(11, "BACC_DEPOSIT_PAUSE");
	public static final ConstantConverter BACC_DEPOSIT_RESTART = new ConstantConverter(12,
			"BACC_DEPOSIT_RESTART");

	public static int getConstantNumberFromString(String constant) {

		if (BillAcceptorConstantMapper.BACC_STATUS_OK.getConstant().equals(constant)) {
			return BillAcceptorConstantMapper.BACC_STATUS_OK.getContantNumber();
		}

		if (BillAcceptorConstantMapper.BACC_STATUS_FULL.getConstant().equals(constant)) {
			return BillAcceptorConstantMapper.BACC_STATUS_FULL.getContantNumber();
		}

		if (BillAcceptorConstantMapper.BACC_STATUS_NEARFULL.getConstant().equals(constant)) {
			return BillAcceptorConstantMapper.BACC_STATUS_NEARFULL.getContantNumber();
		}

		if (BillAcceptorConstantMapper.BACC_DEPOSIT_COMPLETE.getConstant().equals(constant)) {
			return BillAcceptorConstantMapper.BACC_DEPOSIT_COMPLETE.getContantNumber();
		}

		if (BillAcceptorConstantMapper.BACC_DEPOSIT_PAUSE.getConstant().equals(constant)) {
			return BillAcceptorConstantMapper.BACC_DEPOSIT_PAUSE.getContantNumber();
		}

		if (BillAcceptorConstantMapper.BACC_DEPOSIT_RESTART.getConstant().equals(constant)) {
			return BillAcceptorConstantMapper.BACC_DEPOSIT_RESTART.getContantNumber();
		}

		if (BillAcceptorConstantMapper.BACC_STATUS_DEPOSIT_START.getConstant().equals(constant)) {
			return BillAcceptorConstantMapper.BACC_STATUS_DEPOSIT_START.getContantNumber();
		}

		if (BillAcceptorConstantMapper.BACC_STATUS_DEPOSIT_END.getConstant().equals(constant)) {
			return BillAcceptorConstantMapper.BACC_STATUS_DEPOSIT_END.getContantNumber();
		}

		if (BillAcceptorConstantMapper.BACC_STATUS_DEPOSIT_COUNT.getConstant().equals(constant)) {
			return BillAcceptorConstantMapper.BACC_STATUS_DEPOSIT_COUNT.getContantNumber();
		}

		if (BillAcceptorConstantMapper.BACC_STATUS_DEPOSIT_JAM.getConstant().equals(constant)) {
			return BillAcceptorConstantMapper.BACC_STATUS_DEPOSIT_JAM.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
