package postest2;

public class MICRConstantMapper extends CommonConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "CheckType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCheckType)
	public static final ConstantConverter MICR_CT_PERSONAL = new ConstantConverter(1, "MICR_CT_PERSONAL");
	@BelongingProperty(PropertyNames.getCheckType)
	public static final ConstantConverter MICR_CT_BUSINESS = new ConstantConverter(2, "MICR_CT_BUSINESS");
	@BelongingProperty(PropertyNames.getCheckType)
	public static final ConstantConverter MICR_CT_UNKNOWN = new ConstantConverter(99, "MICR_CT_UNKNOWN");

	// ///////////////////////////////////////////////////////////////////
	// "CountryCode" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter MICR_CC_USA = new ConstantConverter(1, "MICR_CC_USA");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter MICR_CC_CANADA = new ConstantConverter(2, "MICR_CC_CANADA");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter MICR_CC_MEXICO = new ConstantConverter(3, "MICR_CC_MEXICO");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter MICR_CC_CMC7 = new ConstantConverter(4, "MICR_CC_CMC7");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter MICR_CC_UNKNOWN = new ConstantConverter(99, "MICR_CC_UNKNOWN");
	

	public static int getConstantNumberFromString(String constant) {
		
		if (MICR_CC_USA.getConstant().equals(constant)) {
			return MICR_CC_USA.getContantNumber();
		}

		if (MICR_CC_CANADA.getConstant().equals(constant)) {
			return MICR_CC_CANADA.getContantNumber();
		}

		if (MICR_CC_MEXICO.getConstant().equals(constant)) {
			return MICR_CC_MEXICO.getContantNumber();
		}
		
		if (MICR_CC_CMC7.getConstant().equals(constant)) {
			return MICR_CC_CMC7.getContantNumber();
		}

		if (MICR_CC_UNKNOWN.getConstant().equals(constant)) {
			return MICR_CC_UNKNOWN.getContantNumber();
		}

		if (MICR_CT_PERSONAL.getConstant().equals(constant)) {
			return MICR_CT_PERSONAL.getContantNumber();
		}

		if (MICR_CT_BUSINESS.getConstant().equals(constant)) {
			return MICR_CT_BUSINESS.getContantNumber();
		}

		if (MICR_CT_UNKNOWN.getConstant().equals(constant)) {
			return MICR_CT_UNKNOWN.getContantNumber();
		}

		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
