package postest2;

public class CoinAcceptorConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "EndDeposit" Method Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter CACC_DEPOSIT_COMPLETE = new ConstantConverter(11,
			"BACC_DEPOSIT_COMPLETE");

	// ///////////////////////////////////////////////////////////////////
	// "PauseDeposit" Method Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter CACC_DEPOSIT_PAUSE = new ConstantConverter(11, "BACC_DEPOSIT_PAUSE");
	public static final ConstantConverter CACC_DEPOSIT_RESTART = new ConstantConverter(12,
			"BACC_DEPOSIT_RESTART");

	// ///////////////////////////////////////////////////////////////////
	// "FullStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getFullStatus)
	public static final ConstantConverter CACC_STATUS_OK = new ConstantConverter(0, "CACC_STATUS_OK");
	@BelongingProperty(PropertyNames.getFullStatus)
	public static final ConstantConverter CACC_STATUS_FULL = new ConstantConverter(21, "CACC_STATUS_FULL");
	@BelongingProperty(PropertyNames.getFullStatus)
	public static final ConstantConverter CACC_STATUS_NEARFULL = new ConstantConverter(22,
			"CACC_STATUS_NEARFULL");
	@BelongingProperty(PropertyNames.getFullStatus)
	public static final ConstantConverter CACC_STATUS_FULLOK = new ConstantConverter(23, "CACC_STATUS_FULLOK");
	@BelongingProperty(PropertyNames.getFullStatus)
	public static final ConstantConverter CACC_STATUS_JAM = new ConstantConverter(31, "CACC_STATUS_JAM");
	@BelongingProperty(PropertyNames.getFullStatus)
	public static final ConstantConverter CACC_STATUS_JAMOK = new ConstantConverter(32, "CACC_STATUS_JAMOK");

	// ///////////////////////////////////////////////////////////////////
	// "DepositStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter CACC_STATUS_DEPOSIT_START = new ConstantConverter(1, "CACC_STATUS_DEPOSIT_START");
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter CACC_STATUS_DEPOSIT_END = new ConstantConverter(2, "CACC_STATUS_DEPOSIT_END");
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter CACC_STATUS_DEPOSIT_COUNT = new ConstantConverter(4, "CACC_STATUS_DEPOSIT_COUNT");
	@BelongingProperty(PropertyNames.getDepositStatus)
	public static final ConstantConverter CACC_STATUS_DEPOSIT_JAM = new ConstantConverter(5, "CACC_STATUS_DEPOSIT_JAM");
	
	
	public static int getConstantNumberFromString(String constant) {
		
		if (CoinAcceptorConstantMapper.CACC_STATUS_DEPOSIT_START.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_STATUS_DEPOSIT_START.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_STATUS_DEPOSIT_END.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_STATUS_DEPOSIT_END.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_STATUS_DEPOSIT_COUNT.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_STATUS_DEPOSIT_COUNT.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_STATUS_DEPOSIT_JAM.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_STATUS_DEPOSIT_JAM.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_STATUS_OK.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_STATUS_OK.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_STATUS_FULL.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_STATUS_FULL.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_STATUS_NEARFULL.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_STATUS_NEARFULL.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_STATUS_FULLOK.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_STATUS_FULLOK.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_STATUS_JAM.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_STATUS_JAM.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_STATUS_JAMOK.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_STATUS_JAMOK.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_DEPOSIT_COMPLETE.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_DEPOSIT_COMPLETE.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_DEPOSIT_PAUSE.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_DEPOSIT_PAUSE.getContantNumber();
		}

		if (CoinAcceptorConstantMapper.CACC_DEPOSIT_RESTART.getConstant().equals(constant)) {
			return CoinAcceptorConstantMapper.CACC_DEPOSIT_RESTART.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
