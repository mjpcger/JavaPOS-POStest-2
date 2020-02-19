package postest2;

import jpos.JposConst;

public class CommonConstantMapper implements IMapWrapper {

	/////////////////////////////////////////////////////////////////////
	// "CapPowerReporting" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapPowerReporting)
	public static final ConstantConverter JPOS_PR_NONE = new ConstantConverter(JposConst.JPOS_PR_NONE, "JPOS_PR_NONE");
	@BelongingProperty(PropertyNames.getCapPowerReporting)
	public static final ConstantConverter JPOS_PR_STANDARD = new ConstantConverter(JposConst.JPOS_PR_STANDARD, "JPOS_PR_STANDARD");
	@BelongingProperty(PropertyNames.getCapPowerReporting)
	public static final ConstantConverter JPOS_PR_ADVANCED = new ConstantConverter(JposConst.JPOS_PR_ADVANCED, "JPOS_PR_ADVANCED");

	/////////////////////////////////////////////////////////////////////
    // "CheckHealth" Method: "Level" Parameter Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter JPOS_CH_INTERNAL     = new ConstantConverter(JposConst.JPOS_CH_INTERNAL, "JPOS_CH_INTERNAL");
    public static final ConstantConverter JPOS_CH_EXTERNAL     = new ConstantConverter(JposConst.JPOS_CH_EXTERNAL, "JPOS_CH_EXTERNAL");
    public static final ConstantConverter JPOS_CH_INTERACTIVE  = new ConstantConverter(JposConst.JPOS_CH_INTERACTIVE, "JPOS_CH_INTERACTIVE");

	/////////////////////////////////////////////////////////////////////
	// "PowerNotify" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getPowerNotify)
	public static final ConstantConverter JPOS_PN_DISABLED = new ConstantConverter(JposConst.JPOS_PN_DISABLED, "JPOS_PN_DISABLED");
	@BelongingProperty(PropertyNames.getPowerNotify)
	public static final ConstantConverter JPOS_PN_ENABLED = new ConstantConverter(JposConst.JPOS_PN_ENABLED, "JPOS_PN_ENABLED");

	/////////////////////////////////////////////////////////////////////
	// "PowerState" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getPowerState)
	public static final ConstantConverter JPOS_PS_UNKNOWN = new ConstantConverter(JposConst.JPOS_PS_UNKNOWN, "JPOS_PS_UNKNOWN");
	@BelongingProperty(PropertyNames.getPowerState)
	public static final ConstantConverter JPOS_PS_ONLINE = new ConstantConverter(JposConst.JPOS_PS_ONLINE, "JPOS_PS_ONLINE");
	@BelongingProperty(PropertyNames.getPowerState)
	public static final ConstantConverter JPOS_PS_OFF = new ConstantConverter(JposConst.JPOS_PS_OFF, "JPOS_PS_OFF");
	@BelongingProperty(PropertyNames.getPowerState)
	public static final ConstantConverter JPOS_PS_OFFLINE = new ConstantConverter(JposConst.JPOS_PS_OFFLINE, "JPOS_PS_OFFLINE");
	@BelongingProperty(PropertyNames.getPowerState)
	public static final ConstantConverter JPOS_PS_OFF_OFFLINE = new ConstantConverter(JposConst.JPOS_PS_OFF_OFFLINE, "JPOS_PS_OFF_OFFLINE");

	/////////////////////////////////////////////////////////////////////
	// "State" Property Constants
	/////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getState)
	public static final ConstantConverter JPOS_S_CLOSED = new ConstantConverter(JposConst.JPOS_S_CLOSED, "JPOS_S_CLOSED");
	@BelongingProperty(PropertyNames.getState)
	public static final ConstantConverter JPOS_S_IDLE = new ConstantConverter(JposConst.JPOS_S_IDLE, "JPOS_S_IDLE");
	@BelongingProperty(PropertyNames.getState)
	public static final ConstantConverter JPOS_S_BUSY = new ConstantConverter(JposConst.JPOS_S_BUSY, "JPOS_S_BUSY");
	@BelongingProperty(PropertyNames.getState)
	public static final ConstantConverter JPOS_S_ERROR = new ConstantConverter(JposConst.JPOS_S_ERROR, "JPOS_S_ERROR");


    public static int getConstantNumberFromString(String constant){

		if(CommonConstantMapper.JPOS_PR_NONE.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_PR_NONE.getContantNumber();
		}

		if(CommonConstantMapper.JPOS_PR_STANDARD.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_PR_STANDARD.getContantNumber();
		}

		if(CommonConstantMapper.JPOS_PR_ADVANCED.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_PR_ADVANCED.getContantNumber();
		}

		if(CommonConstantMapper.JPOS_CH_INTERNAL.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_CH_INTERNAL.getContantNumber();
		}
		
		if(CommonConstantMapper.JPOS_CH_EXTERNAL.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_CH_EXTERNAL.getContantNumber();
		}

		if(CommonConstantMapper.JPOS_CH_INTERACTIVE.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_CH_INTERACTIVE.getContantNumber();
		}


		if(CommonConstantMapper.JPOS_PN_DISABLED.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_PN_DISABLED.getContantNumber();
		}

		if(CommonConstantMapper.JPOS_PN_ENABLED.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_PN_ENABLED.getContantNumber();
		}

		if(CommonConstantMapper.JPOS_PS_UNKNOWN.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_PS_UNKNOWN.getContantNumber();
		}

		if(CommonConstantMapper.JPOS_PS_ONLINE.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_PS_ONLINE.getContantNumber();
		}

		if(CommonConstantMapper.JPOS_PS_OFF.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_PS_OFF.getContantNumber();
		}

		if(CommonConstantMapper.JPOS_PS_OFFLINE.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_PS_OFFLINE.getContantNumber();
		}

		if(CommonConstantMapper.JPOS_PS_OFF_OFFLINE.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_PS_OFF_OFFLINE.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}
}
