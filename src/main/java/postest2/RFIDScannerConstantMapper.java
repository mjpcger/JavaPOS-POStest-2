package postest2;

public class RFIDScannerConstantMapper extends CommonConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "CapMultipleProtocols", "CurrentTagProtocol", and
	// "ProtocolMask" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getProtocolMask)
	public static final ConstantConverter RFID_PR_EPC0 = new ConstantConverter(0x00000001, "RFID_PR_EPC0");
	@BelongingProperty(PropertyNames.getProtocolMask)
	public static final ConstantConverter RFID_PR_0PLUS = new ConstantConverter(0x00000002, "RFID_PR_0PLUS");
	@BelongingProperty(PropertyNames.getProtocolMask)
	public static final ConstantConverter RFID_PR_EPC1 = new ConstantConverter(0x00000004, "RFID_PR_EPC1");
	@BelongingProperty(PropertyNames.getProtocolMask)
	public static final ConstantConverter RFID_PR_EPC1G2 = new ConstantConverter(0x00000008, "RFID_PR_EPC1G2");
	@BelongingProperty(PropertyNames.getProtocolMask)
	public static final ConstantConverter RFID_PR_EPC2 = new ConstantConverter(0x00000010, "RFID_PR_EPC2");
	@BelongingProperty(PropertyNames.getProtocolMask)
	public static final ConstantConverter RFID_PR_ISO14443A = new ConstantConverter(0x00001000,
			"RFID_PR_ISO14443A");
	@BelongingProperty(PropertyNames.getProtocolMask)
	public static final ConstantConverter RFID_PR_ISO14443B = new ConstantConverter(0x00002000,
			"RFID_PR_ISO14443B");
	@BelongingProperty(PropertyNames.getProtocolMask)
	public static final ConstantConverter RFID_PR_ISO15693 = new ConstantConverter(0x00003000,
			"RFID_PR_ISO15693");
	@BelongingProperty(PropertyNames.getProtocolMask)
	public static final ConstantConverter RFID_PR_ISO180006B = new ConstantConverter(0x00004000,
			"RFID_PR_ISO180006B");
	@BelongingProperty(PropertyNames.getProtocolMask)
	public static final ConstantConverter RFID_PR_OTHER = new ConstantConverter(0x01000000, "RFID_PR_OTHER");
	@BelongingProperty(PropertyNames.getProtocolMask)
	public static final ConstantConverter RFID_PR_ALL = new ConstantConverter(0x40000000, "RFID_PR_ALL");

	// ///////////////////////////////////////////////////////////////////
	// "readTags" and "startReadTags" Methods: "Cmd" Parameter Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter RFID_RT_ID = new ConstantConverter(0x10, "RFID_RT_ID");
	public static final ConstantConverter RFID_RT_FULLUSERDATA = new ConstantConverter(0x01,
			"RFID_RT_FULLUSERDATA");
	public static final ConstantConverter RFID_RT_PARTIALUSERDATA = new ConstantConverter(0x02,
			"RFID_RT_PARTIALUSERDATA");
	public static final ConstantConverter RFID_RT_ID_FULLUSERDATA = new ConstantConverter(0x11,
			"RFID_RT_ID_FULLUSERDATA");
	public static final ConstantConverter RFID_RT_ID_PARTIALUSERDATA = new ConstantConverter(0x12,
			"RFID_RT_ID_PARTIALUSERDATA");

	// ///////////////////////////////////////////////////////////////////
	// "CapWriteTag" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapWriteTag)
	public static final ConstantConverter RFID_CWT_NONE = new ConstantConverter(0, "RFID_CWT_NONE");
	@BelongingProperty(PropertyNames.getCapWriteTag)
	public static final ConstantConverter RFID_CWT_ID = new ConstantConverter(1, "RFID_CWT_ID");
	@BelongingProperty(PropertyNames.getCapWriteTag)
	public static final ConstantConverter RFID_CWT_USERDATA = new ConstantConverter(2, "RFID_CWT_USERDATA");
	@BelongingProperty(PropertyNames.getCapWriteTag)
	public static final ConstantConverter RFID_CWT_ALL = new ConstantConverter(3, "RFID_CWT_ALL");

	
	public static int getConstantNumberFromString(String constant) {
		
		if (RFID_CWT_NONE.getConstant().equals(constant)) {
			return RFID_CWT_NONE.getContantNumber();
		}

		if (RFID_CWT_ID.getConstant().equals(constant)) {
			return RFID_CWT_ID.getContantNumber();
		}

		if (RFID_CWT_USERDATA.getConstant().equals(constant)) {
			return RFID_CWT_USERDATA.getContantNumber();
		}

		if (RFID_CWT_ALL.getConstant().equals(constant)) {
			return RFID_CWT_ALL.getContantNumber();
		}

		if (RFID_PR_EPC0.getConstant().equals(constant)) {
			return RFID_PR_EPC0.getContantNumber();
		}

		if (RFID_PR_0PLUS.getConstant().equals(constant)) {
			return RFID_PR_0PLUS.getContantNumber();
		}

		if (RFID_PR_EPC1.getConstant().equals(constant)) {
			return RFID_PR_EPC1.getContantNumber();
		}

		if (RFID_PR_EPC1G2.getConstant().equals(constant)) {
			return RFID_PR_EPC1G2.getContantNumber();
		}

		if (RFID_PR_EPC2.getConstant().equals(constant)) {
			return RFID_PR_EPC2.getContantNumber();
		}

		if (RFID_PR_ISO14443A.getConstant().equals(constant)) {
			return RFID_PR_ISO14443A.getContantNumber();
		}

		if (RFID_PR_ISO14443B.getConstant().equals(constant)) {
			return RFID_PR_ISO14443B.getContantNumber();
		}

		if (RFID_PR_ISO15693.getConstant().equals(constant)) {
			return RFID_PR_ISO15693.getContantNumber();
		}

		if (RFID_PR_ISO180006B.getConstant().equals(constant)) {
			return RFID_PR_ISO180006B.getContantNumber();
		}

		if (RFID_PR_OTHER.getConstant().equals(constant)) {
			return RFID_PR_OTHER.getContantNumber();
		}

		if (RFID_PR_ALL.getConstant().equals(constant)) {
			return RFID_PR_ALL.getContantNumber();
		}

		if (RFID_RT_ID.getConstant().equals(constant)) {
			return RFID_RT_ID.getContantNumber();
		}

		if (RFID_RT_FULLUSERDATA.getConstant().equals(constant)) {
			return RFID_RT_FULLUSERDATA.getContantNumber();
		}

		if (RFID_RT_PARTIALUSERDATA.getConstant().equals(constant)) {
			return RFID_RT_PARTIALUSERDATA.getContantNumber();
		}

		if (RFID_RT_ID_FULLUSERDATA.getConstant().equals(constant)) {
			return RFID_RT_ID_FULLUSERDATA.getContantNumber();
		}

		if (RFID_RT_ID_PARTIALUSERDATA.getConstant().equals(constant)) {
			return RFID_RT_ID_PARTIALUSERDATA.getContantNumber();
		}

		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
