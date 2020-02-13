package postest2;

import jpos.CATConst;

/**
 *
 */
public class CATErrorCodeMapper extends ErrorCodeMapper {
    static public CATErrorCodeMapper Mapper = new CATErrorCodeMapper();

    private CATErrorCodeMapper() {
        super();
        Mappings = new Object[]{
                CATConst.JPOS_ECAT_CENTERERROR , "JPOS_ECAT_CENTERERROR",
                CATConst.JPOS_ECAT_COMMANDERROR , "JPOS_ECAT_COMMANDERROR",
                CATConst.JPOS_ECAT_RESET , "JPOS_ECAT_RESET",
                CATConst.JPOS_ECAT_COMMUNICATIONERROR , "JPOS_ECAT_COMMUNICATIONERROR",
                CATConst.JPOS_ECAT_DAILYLOGOVERFLOW , "JPOS_ECAT_DAILYLOGOVERFLOW",
                CATConst.JPOS_ECAT_DEFICIENT , "JPOS_ECAT_DEFICIENT",
                CATConst.JPOS_ECAT_OVERDEPOSIT , "JPOS_ECAT_OVERDEPOSIT"
        };
    }
}
