package postest2;

public class ElectronicJournalConstantMapper extends CommonConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "CapStation", "Station" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapStation)
	public static final ConstantConverter EJ_S_RECEIPT = new ConstantConverter(0x00000001, "EJ_S_RECEIPT");
	@BelongingProperty(PropertyNames.getCapStation)
	public static final ConstantConverter EJ_S_SLIP = new ConstantConverter(0x00000002, "EJ_S_SLIP");
	@BelongingProperty(PropertyNames.getCapStation)
	public static final ConstantConverter EJ_S_JOURNAL = new ConstantConverter(0x00000004, "EJ_S_JOURNAL");

	// ///////////////////////////////////////////////////////////////////
	// "retrieveCurrentMarker" Method, "markerType" Parameter Constants
	// "retrieveMarker" Method, "markerType" Parameter Constants
	// "retrieveMarkerByDateTime" Method, "markerType" Parameter Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter EJ_MT_SESSION_BEG = new ConstantConverter(1, "EJ_MT_SESSION_BEG");
	public static final ConstantConverter EJ_MT_SESSION_END = new ConstantConverter(2, "EJ_MT_SESSION_END");
	public static final ConstantConverter EJ_MT_DOCUMENT = new ConstantConverter(3, "EJ_MT_DOCUMENT");
	public static final ConstantConverter EJ_MT_HEAD = new ConstantConverter(4, "EJ_MT_HEAD");
	public static final ConstantConverter EJ_MT_TAIL = new ConstantConverter(5, "EJ_MT_TAIL");

	public static int getConstantNumberFromString(String constant) {

		if (EJ_S_RECEIPT.getConstant().equals(constant)) {
			return EJ_S_RECEIPT.getContantNumber();
		}

		if (EJ_S_SLIP.getConstant().equals(constant)) {
			return EJ_S_SLIP.getContantNumber();
		}

		if (EJ_S_JOURNAL.getConstant().equals(constant)) {
			return EJ_S_JOURNAL.getContantNumber();
		}

		if (EJ_MT_SESSION_BEG.getConstant().equals(constant)) {
			return EJ_MT_SESSION_BEG.getContantNumber();
		}

		if (EJ_MT_SESSION_END.getConstant().equals(constant)) {
			return EJ_MT_SESSION_END.getContantNumber();
		}

		if (EJ_MT_DOCUMENT.getConstant().equals(constant)) {
			return EJ_MT_DOCUMENT.getContantNumber();
		}

		if (EJ_MT_HEAD.getConstant().equals(constant)) {
			return EJ_MT_HEAD.getContantNumber();
		}

		if (EJ_MT_TAIL.getConstant().equals(constant)) {
			return EJ_MT_TAIL.getContantNumber();
		}

		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
