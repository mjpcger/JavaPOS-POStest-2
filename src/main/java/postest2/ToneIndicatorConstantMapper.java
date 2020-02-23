package postest2;

public class ToneIndicatorConstantMapper extends CommonConstantMapper {
	// ###################################################################
	// #### Tone Indicator Constants
	// ###################################################################

	// ///////////////////////////////////////////////////////////////////
	// "MelodyType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getMelodyType)
	public static final ConstantConverter TONE_MT_NONE = new ConstantConverter(0, "TONE_MT_NONE");

	public static int getConstantNumberFromString(String constant) {

		if (TONE_MT_NONE.getConstant().equals(constant)) {
			return TONE_MT_NONE.getContantNumber();
		}
		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
