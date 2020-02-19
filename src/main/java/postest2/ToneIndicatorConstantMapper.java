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

		if (ToneIndicatorConstantMapper.TONE_MT_NONE.getConstant().equals(constant)) {
			return ToneIndicatorConstantMapper.TONE_MT_NONE.getContantNumber();
		}
		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
