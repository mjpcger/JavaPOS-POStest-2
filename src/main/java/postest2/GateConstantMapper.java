package postest2;

public class GateConstantMapper extends CommonConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "GateStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getGetStatus)
	public static final ConstantConverter GATE_GS_CLOSED = new ConstantConverter(1, "GATE_GS_CLOSED");
	@BelongingProperty(PropertyNames.getGetStatus)
	public static final ConstantConverter GATE_GS_OPEN = new ConstantConverter(2, "GATE_GS_OPEN");
	@BelongingProperty(PropertyNames.getGetStatus)
	public static final ConstantConverter GATE_GS_BLOCKED = new ConstantConverter(3, "GATE_GS_BLOCKED");
	@BelongingProperty(PropertyNames.getGetStatus)
	public static final ConstantConverter GATE_GS_MALFUNCTION = new ConstantConverter(4, "GATE_GS_MALFUNCTION");

	public static int getConstantNumberFromString(String constant) {

		if (GATE_GS_CLOSED.getConstant().equals(constant)) {
			return GATE_GS_CLOSED.getContantNumber();
		}

		if (GATE_GS_OPEN.getConstant().equals(constant)) {
			return GATE_GS_OPEN.getContantNumber();
		}

		if (GATE_GS_BLOCKED.getConstant().equals(constant)) {
			return GATE_GS_BLOCKED.getContantNumber();
		}

		if (GATE_GS_MALFUNCTION.getConstant().equals(constant)) {
			return GATE_GS_MALFUNCTION.getContantNumber();
		}

		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
