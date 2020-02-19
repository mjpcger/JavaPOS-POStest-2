package postest2;

public class CoinDispenserConstantMapper extends CommonConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "DispenserStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getDispenserStatus)
	public static final ConstantConverter COIN_STATUS_OK = new ConstantConverter(1, "COIN_STATUS_OK");
	@BelongingProperty(PropertyNames.getDispenserStatus)
	public static final ConstantConverter COIN_STATUS_EMPTY = new ConstantConverter(2, "COIN_STATUS_EMPTY");
	@BelongingProperty(PropertyNames.getDispenserStatus)
	public static final ConstantConverter COIN_STATUS_NEAREMPTY = new ConstantConverter(3,
			"COIN_STATUS_NEAREMPTY");
	@BelongingProperty(PropertyNames.getDispenserStatus)
	public static final ConstantConverter COIN_STATUS_JAM = new ConstantConverter(4, "COIN_STATUS_JAM");

	/**
	 * Get Constant Number from String - Needed because ComboBoxes just hold the
	 * String and not the Object
	 *
	 * @param constant
	 * @return
	 */
	public static int getConstantNumberFromString(String constant){
		if(CoinDispenserConstantMapper.COIN_STATUS_OK.getConstant().equals(constant)) {
			return CoinDispenserConstantMapper.COIN_STATUS_OK.getContantNumber();
		}

		if(CoinDispenserConstantMapper.COIN_STATUS_NEAREMPTY.getConstant().equals(constant)) {
			return CoinDispenserConstantMapper.COIN_STATUS_NEAREMPTY.getContantNumber();
		}

		if(CoinDispenserConstantMapper.COIN_STATUS_EMPTY.getConstant().equals(constant)) {
			return CoinDispenserConstantMapper.COIN_STATUS_EMPTY.getContantNumber();
		}

		if(CoinDispenserConstantMapper.COIN_STATUS_JAM.getConstant().equals(constant)) {
			return CoinDispenserConstantMapper.COIN_STATUS_JAM.getContantNumber();
		}

		if(CoinDispenserConstantMapper.COIN_STATUS_OK.getConstant().equals(constant)) {
			return CoinDispenserConstantMapper.COIN_STATUS_OK.getContantNumber();
		}

		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
