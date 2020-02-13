package postest2;

public class CommonConstantMapper {

	 /////////////////////////////////////////////////////////////////////
    // "CheckHealth" Method: "Level" Parameter Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter JPOS_CH_INTERNAL     = new ConstantConverter(1, "JPOS_CH_INTERNAL");
    public static final ConstantConverter JPOS_CH_EXTERNAL     = new ConstantConverter(2, "JPOS_CH_EXTERNAL");
    public static final ConstantConverter JPOS_CH_INTERACTIVE  = new ConstantConverter(3, "JPOS_CH_INTERACTIVE");


    public static int getConstantNumberFromString(String constant){

		if(CommonConstantMapper.JPOS_CH_INTERNAL.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_CH_INTERNAL.getContantNumber();
		}
		
		if(CommonConstantMapper.JPOS_CH_EXTERNAL.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_CH_EXTERNAL.getContantNumber();
		}

		if(CommonConstantMapper.JPOS_CH_INTERACTIVE.getConstant().equals(constant)) {
			return CommonConstantMapper.JPOS_CH_INTERACTIVE.getContantNumber();
		}
		
		
		return Integer.parseInt(constant);
	}
    
    
}
