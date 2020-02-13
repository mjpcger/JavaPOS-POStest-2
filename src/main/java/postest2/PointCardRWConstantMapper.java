package postest2;

public class PointCardRWConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "MapMode" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter PCRW_MM_DOTS = new ConstantConverter(1, "PPAD_TRANS_DEBIT");
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter PCRW_MM_TWIPS = new ConstantConverter(2, "PCRW_MM_TWIPS");
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter PCRW_MM_ENGLISH = new ConstantConverter(3, "PCRW_MM_ENGLISH");
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter PCRW_MM_METRIC = new ConstantConverter(4, "PCRW_MM_METRIC");

	// ///////////////////////////////////////////////////////////////////
	// "RotatePrint" Method: "Rotation" Parameter Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter PCRW_RP_NORMAL = new ConstantConverter(0x0001, "PCRW_RP_NORMAL");
	// In drafts of spec, but not in final - should never have been defined.
	// public static final int PCRW_RP_NORMAL_ASYNC = 0x0002;
	public static final ConstantConverter PCRW_RP_RIGHT90 = new ConstantConverter(0x0101, "PCRW_RP_RIGHT90");
	public static final ConstantConverter PCRW_RP_LEFT90 = new ConstantConverter(0x0102, "PCRW_RP_LEFT90");
	public static final ConstantConverter PCRW_RP_ROTATE180 = new ConstantConverter(0x0103,
			"PCRW_RP_ROTATE180");

	// ///////////////////////////////////////////////////////////////////
	// CapTrackToRead and TrackToWrite Property constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapTracksToRead)
	public static final ConstantConverter PCRW_TRACK1 = new ConstantConverter(0x0001, "PCRW_TRACK1");
	@BelongingProperty(PropertyNames.getCapTracksToRead)
	public static final ConstantConverter PCRW_TRACK2 = new ConstantConverter(0x0002, "PCRW_TRACK2");
	@BelongingProperty(PropertyNames.getCapTracksToRead)
	public static final ConstantConverter PCRW_TRACK3 = new ConstantConverter(0x0004, "PCRW_TRACK3");
	@BelongingProperty(PropertyNames.getCapTracksToRead)
	public static final ConstantConverter PCRW_TRACK4 = new ConstantConverter(0x0008, "PCRW_TRACK4");
	@BelongingProperty(PropertyNames.getCapTracksToRead)
	public static final ConstantConverter PCRW_TRACK5 = new ConstantConverter(0x0010, "PCRW_TRACK5");
	@BelongingProperty(PropertyNames.getCapTracksToRead)
	public static final ConstantConverter PCRW_TRACK6 = new ConstantConverter(0x0020, "PCRW_TRACK6");

	// ///////////////////////////////////////////////////////////////////
	// "CardState" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCardState)
	public static final ConstantConverter PCRW_STATE_NOCARD = new ConstantConverter(1, "PCRW_STATE_NOCARD");
	@BelongingProperty(PropertyNames.getCardState)
	public static final ConstantConverter PCRW_STATE_REMAINING = new ConstantConverter(2,
			"PCRW_STATE_REMAINING");
	@BelongingProperty(PropertyNames.getCardState)
	public static final ConstantConverter PCRW_STATE_INRW = new ConstantConverter(3, "PCRW_STATE_INRW");

	// ///////////////////////////////////////////////////////////////////
	// "CapCharacterSet" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter PCRW_CCS_ALPHA = new ConstantConverter(1, "PCRW_CCS_ALPHA");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter PCRW_CCS_ASCII = new ConstantConverter(998, "PCRW_CCS_ASCII");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter PCRW_CCS_KANA = new ConstantConverter(10, "PCRW_CCS_KANA");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter PCRW_CCS_KANJI = new ConstantConverter(11, "PCRW_CCS_KANJI");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter PCRW_CCS_UNICODE = new ConstantConverter(997, "PCRW_CCS_UNICODE");

	// ///////////////////////////////////////////////////////////////////
	// "CharacterSet" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCharacterSet)
	public static final ConstantConverter PCRW_CS_UNICODE = new ConstantConverter(997, "PCRW_CS_UNICODE");
	@BelongingProperty(PropertyNames.getCharacterSet)
	public static final ConstantConverter PCRW_CS_ASCII = new ConstantConverter(998, "PCRW_CS_ASCII");
	@BelongingProperty(PropertyNames.getCharacterSet)
	public static final ConstantConverter PCRW_CS_ANSI = new ConstantConverter(999, "PCRW_CS_ANSI");

	
	public static int getConstantNumberFromString(String constant) {
		
		if (PointCardRWConstantMapper.PCRW_CS_ASCII.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_CS_ASCII.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_CS_ANSI.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_CS_ANSI.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_CS_UNICODE.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_CS_UNICODE.getContantNumber();
		}
		
		if (PointCardRWConstantMapper.PCRW_CCS_ALPHA.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_CCS_ALPHA.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_CCS_ASCII.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_CCS_ASCII.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_CCS_KANA.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_CCS_KANA.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_CCS_KANJI.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_CCS_KANJI.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_CCS_UNICODE.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_CCS_UNICODE.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_STATE_NOCARD.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_STATE_NOCARD.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_STATE_REMAINING.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_STATE_REMAINING.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_STATE_INRW.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_STATE_INRW.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_TRACK1.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_TRACK1.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_TRACK2.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_TRACK2.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_TRACK3.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_TRACK3.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_TRACK4.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_TRACK4.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_TRACK5.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_TRACK5.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_TRACK6.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_TRACK6.getContantNumber();
		}
		// //////////////

		if (PointCardRWConstantMapper.PCRW_MM_DOTS.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_MM_DOTS.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_MM_TWIPS.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_MM_TWIPS.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_MM_ENGLISH.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_MM_ENGLISH.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_MM_METRIC.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_MM_METRIC.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_RP_NORMAL.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_RP_NORMAL.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_RP_RIGHT90.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_RP_RIGHT90.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_RP_LEFT90.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_RP_LEFT90.getContantNumber();
		}

		if (PointCardRWConstantMapper.PCRW_RP_ROTATE180.getConstant().equals(constant)) {
			return PointCardRWConstantMapper.PCRW_RP_ROTATE180.getContantNumber();
		}

		return Integer.parseInt(constant);
	}


	@Override
	public IMapWrapper getTheClass() {
		return this;
	}
	
}
