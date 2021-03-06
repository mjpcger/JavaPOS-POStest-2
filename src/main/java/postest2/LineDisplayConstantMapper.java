package postest2;

public class LineDisplayConstantMapper extends CommonConstantMapper {
	
	
	//###################################################################
	//#### Line Display Constants
	//###################################################################
	
	/////////////////////////////////////////////////////////////////////
	// "CapBlink" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapBlink)
	public static final ConstantConverter DISP_CB_NOBLINK      = new ConstantConverter(0, "DISP_CB_NOBLINK");
	@BelongingProperty(PropertyNames.getCapBlink)
	public static final ConstantConverter DISP_CB_BLINKALL     = new ConstantConverter(1, "DISP_CB_BLINKALL");
	@BelongingProperty(PropertyNames.getCapBlink)
	public static final ConstantConverter DISP_CB_BLINKEACH    = new ConstantConverter(2, "DISP_CB_BLINKEACH");
	
	/////////////////////////////////////////////////////////////////////
	// "CapCharacterSet" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter DISP_CCS_NUMERIC     = new ConstantConverter(0, "DISP_CCS_NUMERIC");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter DISP_CCS_ALPHA       = new ConstantConverter(1, "DISP_CCS_ALPHA");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter DISP_CCS_ASCII       = new ConstantConverter(998, "DISP_CCS_ASCII");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter DISP_CCS_KANA        = new ConstantConverter(10, "DISP_CCS_KANA");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter DISP_CCS_KANJI       = new ConstantConverter(11, "DISP_CCS_KANJI");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter DISP_CCS_UNICODE     = new ConstantConverter(997, "DISP_CCS_UNICODE");
	
	
	/////////////////////////////////////////////////////////////////////
	// "CapCursorType" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapCursorType)
	public static final ConstantConverter DISP_CCT_NONE        = new ConstantConverter(0x00000000, "DISP_CCT_NONE");
	@BelongingProperty(PropertyNames.getCapCursorType)
	public static final ConstantConverter DISP_CCT_FIXED       = new ConstantConverter(0x00000001, "DISP_CCT_FIXED");
	@BelongingProperty(PropertyNames.getCapCursorType)
	public static final ConstantConverter DISP_CCT_BLOCK       = new ConstantConverter(0x00000002, "DISP_CCT_BLOCK");
	@BelongingProperty(PropertyNames.getCapCursorType)
	public static final ConstantConverter DISP_CCT_HALFBLOCK   = new ConstantConverter(0x00000004, "DISP_CCT_HALFBLOCK");
	@BelongingProperty(PropertyNames.getCapCursorType)
	public static final ConstantConverter DISP_CCT_UNDERLINE   = new ConstantConverter(0x00000008, "DISP_CCT_UNDERLINE");
	@BelongingProperty(PropertyNames.getCapCursorType)
	public static final ConstantConverter DISP_CCT_REVERSE     = new ConstantConverter(0x00000010, "DISP_CCT_REVERSE");
	@BelongingProperty(PropertyNames.getCapCursorType)
	public static final ConstantConverter DISP_CCT_OTHER       = new ConstantConverter(0x00000020, "DISP_CCT_OTHER");
	
	// Added in Release 1.8
	@BelongingProperty(PropertyNames.getCapCursorType)
	public static final ConstantConverter DISP_CCT_BLINK       = new ConstantConverter(  0x00000040, "DISP_CCT_BLINK");
	
	
	/////////////////////////////////////////////////////////////////////
	// "CapReadBack" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapReadBack)
	public static final ConstantConverter DISP_CRB_NONE        = new ConstantConverter(  0x00000000, "DISP_CRB_NONE");
	@BelongingProperty(PropertyNames.getCapReadBack)
	public static final ConstantConverter DISP_CRB_SINGLE      = new ConstantConverter(  0x00000001, "DISP_CRB_SINGLE");
	
	
	/////////////////////////////////////////////////////////////////////
	// "CapReverse" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapReverse)
	public static final ConstantConverter DISP_CR_NONE          = new ConstantConverter(  0x00000000, "DISP_CR_NONE");
	@BelongingProperty(PropertyNames.getCapReverse)
	public static final ConstantConverter DISP_CR_REVERSEALL    = new ConstantConverter(  0x00000001, "DISP_CR_REVERSEALL");
	@BelongingProperty(PropertyNames.getCapReverse)
	public static final ConstantConverter DISP_CR_REVERSEEACH   = new ConstantConverter(  0x00000002, "DISP_CR_REVERSEEACH");
	
	
	/////////////////////////////////////////////////////////////////////
	// "CharacterSet" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCharacterSet)
	public static final ConstantConverter DISP_CS_UNICODE      = new ConstantConverter(997, "DISP_CS_UNICODE");
	@BelongingProperty(PropertyNames.getCharacterSet)
	public static final ConstantConverter DISP_CS_ASCII        = new ConstantConverter(998, "DISP_CS_ASCII");
	@BelongingProperty(PropertyNames.getCharacterSet)
	public static final ConstantConverter DISP_CS_ANSI         = new ConstantConverter(999, "DISP_CS_ANSI");
	
	
	/////////////////////////////////////////////////////////////////////
	// "CursorType" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCursorType)
	public static final ConstantConverter DISP_CT_NONE        = new ConstantConverter(  0, "DISP_CT_NONE");
	@BelongingProperty(PropertyNames.getCursorType)
	public static final ConstantConverter DISP_CT_FIXED       = new ConstantConverter(  1, "DISP_CT_FIXED");
	@BelongingProperty(PropertyNames.getCursorType)
	public static final ConstantConverter DISP_CT_BLOCK       = new ConstantConverter(  2, "DISP_CT_BLOCK");
	@BelongingProperty(PropertyNames.getCursorType)
	public static final ConstantConverter DISP_CT_HALFBLOCK   = new ConstantConverter(  3, "DISP_CT_HALFBLOCK");
	@BelongingProperty(PropertyNames.getCursorType)
	public static final ConstantConverter DISP_CT_UNDERLINE   = new ConstantConverter(  4, "DISP_CT_UNDERLINE");
	@BelongingProperty(PropertyNames.getCursorType)
	public static final ConstantConverter DISP_CT_REVERSE     = new ConstantConverter(  5, "DISP_CT_REVERSE");
	@BelongingProperty(PropertyNames.getCursorType)
	public static final ConstantConverter DISP_CT_OTHER       = new ConstantConverter(  6, "DISP_CT_OTHER");
	
	// Added in Release 1.8
	@BelongingProperty(PropertyNames.getCursorType)
	public static final ConstantConverter DISP_CT_BLINK       = new ConstantConverter(  0x10000000, "DISP_CT_BLINK");
	
	
	/////////////////////////////////////////////////////////////////////
	// "MarqueeType" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getMarqueeType)
	public static final ConstantConverter DISP_MT_NONE         = new ConstantConverter(0, "DISP_MT_NONE");
	@BelongingProperty(PropertyNames.getMarqueeType)
	public static final ConstantConverter DISP_MT_UP           = new ConstantConverter(1, "DISP_MT_UP");
	@BelongingProperty(PropertyNames.getMarqueeType)
	public static final ConstantConverter DISP_MT_DOWN         = new ConstantConverter(2, "DISP_MT_DOWN");
	@BelongingProperty(PropertyNames.getMarqueeType)
	public static final ConstantConverter DISP_MT_LEFT         = new ConstantConverter(3, "DISP_MT_LEFT");
	@BelongingProperty(PropertyNames.getMarqueeType)
	public static final ConstantConverter DISP_MT_RIGHT        = new ConstantConverter(4, "DISP_MT_RIGHT");
	@BelongingProperty(PropertyNames.getMarqueeType)
	public static final ConstantConverter DISP_MT_INIT         = new ConstantConverter(5, "DISP_MT_INIT");
	
	
	/////////////////////////////////////////////////////////////////////
	// "MarqueeFormat" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getMarqueeFormat)
	public static final ConstantConverter DISP_MF_WALK         = new ConstantConverter(0, "DISP_MF_WALK");
	@BelongingProperty(PropertyNames.getMarqueeFormat)
	public static final ConstantConverter DISP_MF_PLACE        = new ConstantConverter(1, "DISP_MF_PLACE");
	
	
	/////////////////////////////////////////////////////////////////////
	// "DefineGlyph" Method: "GlyphType" Parameter Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_GT_SINGLE       = new ConstantConverter(1, "DISP_GT_SINGLE");
	
	
	/////////////////////////////////////////////////////////////////////
	// "DisplayText" Method: "Attribute" Property Constants
	// "DisplayTextAt" Method: "Attribute" Property Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_DT_NORMAL        = new ConstantConverter(0, "DISP_DT_NORMAL");
	public static final ConstantConverter DISP_DT_BLINK         = new ConstantConverter(1, "DISP_DT_BLINK");
	public static final ConstantConverter DISP_DT_REVERSE       = new ConstantConverter(2, "DISP_DT_REVERSE");
	public static final ConstantConverter DISP_DT_BLINK_REVERSE = new ConstantConverter(3, "DISP_DT_BLINK_REVERSE");
	
	
	/////////////////////////////////////////////////////////////////////
	// "ScrollText" Method: "Direction" Parameter Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_ST_UP           = new ConstantConverter(1, "DISP_ST_UP");
	public static final ConstantConverter DISP_ST_DOWN         = new ConstantConverter(2, "DISP_ST_DOWN");
	public static final ConstantConverter DISP_ST_LEFT         = new ConstantConverter(3, "DISP_ST_LEFT");
	public static final ConstantConverter DISP_ST_RIGHT        = new ConstantConverter(4, "DISP_ST_RIGHT");
	
	
	/////////////////////////////////////////////////////////////////////
	// "SetDescriptor" Method: "Attribute" Parameter Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_SD_OFF          = new ConstantConverter(0, "DISP_SD_OFF");
	public static final ConstantConverter DISP_SD_ON           = new ConstantConverter(1, "DISP_SD_ON");
	public static final ConstantConverter DISP_SD_BLINK        = new ConstantConverter(2, "DISP_SD_BLINK");
	
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	// The following were added in Release 1.7
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	// "displayBitmap" and "setBitmap" Method Constants:
	/////////////////////////////////////////////////////////////////////
	
	//   "Width" Parameter
	
	public static final ConstantConverter DISP_BM_ASIS          = new ConstantConverter(-11, "DISP_BM_ASIS");
	
	//   "AlignmentX" Parameter
	
	public static final ConstantConverter DISP_BM_LEFT          = new ConstantConverter(-1, "DISP_BM_LEFT");
	public static final ConstantConverter DISP_BM_CENTER        = new ConstantConverter(-2, "DISP_BM_CENTER");
	public static final ConstantConverter DISP_BM_RIGHT         = new ConstantConverter(-3, "DISP_BM_RIGHT");
	
	//   "AlignmentY" Parameter
	
	public static final ConstantConverter DISP_BM_TOP           = new ConstantConverter(-1, "DISP_BM_TOP");
	public static final ConstantConverter DISP_BM_BOTTOM        = new ConstantConverter(-3, "DISP_BM_BOTTOM");
	
	/**
	 * Get Constant Number from String - Needed because ComboBoxes just hold the
	 * String and not the Object
	 * 
	 * @param constant
	 * @return
	 */
	public static int getConstantNumberFromString(String constant){
		
		if(DISP_CB_NOBLINK.getConstant().equals(constant)) {
			return DISP_CB_NOBLINK.getContantNumber();
		}
		
		if(DISP_CB_BLINKALL.getConstant().equals(constant)) {
			return DISP_CB_BLINKALL.getContantNumber();
		}
		
		if(DISP_CB_BLINKEACH.getConstant().equals(constant)) {
			return DISP_CB_BLINKEACH.getContantNumber();
		}
		
		if(DISP_CCS_NUMERIC.getConstant().equals(constant)) {
			return DISP_CCS_NUMERIC.getContantNumber();
		}
		
		if(DISP_CCS_ALPHA.getConstant().equals(constant)) {
			return DISP_CCS_ALPHA.getContantNumber();
		}
		
		if(DISP_CCS_ASCII.getConstant().equals(constant)) {
			return DISP_CCS_ASCII.getContantNumber();
		}
		
		if(DISP_CCS_KANA.getConstant().equals(constant)) {
			return DISP_CCS_KANA.getContantNumber();
		}
		
		if(DISP_CCS_KANJI.getConstant().equals(constant)) {
			return DISP_CCS_KANJI.getContantNumber();
		}
		
		if(DISP_CCS_UNICODE.getConstant().equals(constant)) {
			return DISP_CCS_UNICODE.getContantNumber();
		}
		
		if(DISP_CCT_NONE.getConstant().equals(constant)) {
			return DISP_CCT_NONE.getContantNumber();
		}
		
		if(DISP_CCT_FIXED.getConstant().equals(constant)) {
			return DISP_CCT_FIXED.getContantNumber();
		}
		
		if(DISP_CCT_BLOCK.getConstant().equals(constant)) {
			return DISP_CCT_BLOCK.getContantNumber();
		}
		
		if(DISP_CCT_HALFBLOCK.getConstant().equals(constant)) {
			return DISP_CCT_HALFBLOCK.getContantNumber();
		}
		
		if(DISP_CCT_UNDERLINE.getConstant().equals(constant)) {
			return DISP_CCT_UNDERLINE.getContantNumber();
		}
		
		if(DISP_CCT_REVERSE.getConstant().equals(constant)) {
			return DISP_CCT_REVERSE.getContantNumber();
		}
		
		if(DISP_CCT_OTHER.getConstant().equals(constant)) {
			return DISP_CCT_OTHER.getContantNumber();
		}
		
		if(DISP_CCT_BLINK.getConstant().equals(constant)) {
			return DISP_CCT_BLINK.getContantNumber();
		}
		
		if(DISP_CRB_NONE.getConstant().equals(constant)) {
			return DISP_CRB_NONE.getContantNumber();
		}
		
		if(DISP_CRB_SINGLE.getConstant().equals(constant)) {
			return DISP_CRB_SINGLE.getContantNumber();
		}
		
		if(DISP_CR_NONE.getConstant().equals(constant)) {
			return DISP_CR_NONE.getContantNumber();
		}
		
		if(DISP_CR_REVERSEALL.getConstant().equals(constant)) {
			return DISP_CR_REVERSEALL.getContantNumber();
		}
		
		if(DISP_CR_REVERSEEACH.getConstant().equals(constant)) {
			return DISP_CR_REVERSEEACH.getContantNumber();
		}
		
		if(DISP_CS_UNICODE.getConstant().equals(constant)) {
			return DISP_CS_UNICODE.getContantNumber();
		}
		
		if(DISP_CS_ASCII.getConstant().equals(constant)) {
			return DISP_CS_ASCII.getContantNumber();
		}
		
		if(DISP_CS_ANSI.getConstant().equals(constant)) {
			return DISP_CS_ANSI.getContantNumber();
		}
		
		if(DISP_CT_NONE.getConstant().equals(constant)) {
			return DISP_CT_NONE.getContantNumber();
		}
		
		if(DISP_CT_FIXED.getConstant().equals(constant)) {
			return DISP_CT_FIXED.getContantNumber();
		}
		
		if(DISP_CT_BLOCK.getConstant().equals(constant)) {
			return DISP_CT_BLOCK.getContantNumber();
		}
		
		if(DISP_CT_HALFBLOCK.getConstant().equals(constant)) {
			return DISP_CT_HALFBLOCK.getContantNumber();
		}
		
		if(DISP_CT_UNDERLINE.getConstant().equals(constant)) {
			return DISP_CT_UNDERLINE.getContantNumber();
		}
		
		if(DISP_CT_REVERSE.getConstant().equals(constant)) {
			return DISP_CT_REVERSE.getContantNumber();
		}
		
		if(DISP_CT_OTHER.getConstant().equals(constant)) {
			return DISP_CT_OTHER.getContantNumber();
		}
		
		if(DISP_CT_BLINK.getConstant().equals(constant)) {
			return DISP_CT_BLINK.getContantNumber();
		}
		
		if(DISP_MT_NONE.getConstant().equals(constant)) {
			return DISP_MT_NONE.getContantNumber();
		}
		
		if(DISP_MT_UP.getConstant().equals(constant)) {
			return DISP_MT_UP.getContantNumber();
		}
		
		if(DISP_MT_DOWN.getConstant().equals(constant)) {
			return DISP_MT_DOWN.getContantNumber();
		}
		
		if(DISP_MT_LEFT.getConstant().equals(constant)) {
			return DISP_MT_LEFT.getContantNumber();
		}
		
		if(DISP_MT_RIGHT.getConstant().equals(constant)) {
			return DISP_MT_RIGHT.getContantNumber();
		}
		
		if(DISP_MT_INIT.getConstant().equals(constant)) {
			return DISP_MT_INIT.getContantNumber();
		}
		
		if(DISP_MF_WALK.getConstant().equals(constant)) {
			return DISP_MF_WALK.getContantNumber();
		}
		
		if(DISP_MF_PLACE.getConstant().equals(constant)) {
			return DISP_MF_PLACE.getContantNumber();
		}
		
		if(DISP_GT_SINGLE.getConstant().equals(constant)) {
			return DISP_GT_SINGLE.getContantNumber();
		}
		
		if(DISP_DT_NORMAL.getConstant().equals(constant)) {
			return DISP_DT_NORMAL.getContantNumber();
		}
		
		if(DISP_DT_BLINK.getConstant().equals(constant)) {
			return DISP_DT_BLINK.getContantNumber();
		}
		
		if(DISP_DT_REVERSE.getConstant().equals(constant)) {
			return DISP_DT_REVERSE.getContantNumber();
		}
		
		if(DISP_DT_BLINK_REVERSE.getConstant().equals(constant)) {
			return DISP_DT_BLINK_REVERSE.getContantNumber();
		}
		
		if(DISP_ST_UP.getConstant().equals(constant)) {
			return DISP_ST_UP.getContantNumber();
		}
		
		if(DISP_ST_DOWN.getConstant().equals(constant)) {
			return DISP_ST_DOWN.getContantNumber();
		}
		
		if(DISP_ST_LEFT.getConstant().equals(constant)) {
			return DISP_ST_LEFT.getContantNumber();
		}
		
		if(DISP_ST_RIGHT.getConstant().equals(constant)) {
			return DISP_ST_RIGHT.getContantNumber();
		}
		
		if(DISP_SD_OFF.getConstant().equals(constant)) {
			return DISP_SD_OFF.getContantNumber();
		}
		
		if(DISP_SD_ON.getConstant().equals(constant)) {
			return DISP_SD_ON.getContantNumber();
		}
		
		if(DISP_SD_BLINK.getConstant().equals(constant)) {
			return DISP_SD_BLINK.getContantNumber();
		}
		
		if(DISP_BM_ASIS.getConstant().equals(constant)) {
			return DISP_BM_ASIS.getContantNumber();
		}
		
		if(DISP_BM_LEFT.getConstant().equals(constant)) {
			return DISP_BM_LEFT.getContantNumber();
		}
		
		if(DISP_BM_CENTER.getConstant().equals(constant)) {
			return DISP_BM_CENTER.getContantNumber();
		}
		
		if(DISP_BM_RIGHT.getConstant().equals(constant)) {
			return DISP_BM_RIGHT.getContantNumber();
		}
		
		if(DISP_BM_TOP.getConstant().equals(constant)) {
			return DISP_BM_TOP.getContantNumber();
		}
		
		if(DISP_BM_BOTTOM.getConstant().equals(constant)) {
			return DISP_BM_BOTTOM.getContantNumber();
		}

		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
