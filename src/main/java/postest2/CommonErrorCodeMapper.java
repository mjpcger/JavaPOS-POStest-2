package postest2;

import jpos.JposConst;

/**
 * Mapper for JPOS error codes
 */
public class CommonErrorCodeMapper extends ErrorCodeMapper {
    static public CommonErrorCodeMapper Mapper = new CommonErrorCodeMapper();

    private CommonErrorCodeMapper() {
        super();
        Mappings = new Object[]{
                JposConst.JPOS_E_CLOSED, "JPOS_E_CLOSED",
                JposConst.JPOS_E_CLAIMED, "JPOS_E_CLAIMED",
                JposConst.JPOS_E_NOTCLAIMED, "JPOS_E_NOTCLAIMED",
                JposConst.JPOS_E_NOSERVICE, "JPOS_E_NOSERVICE",
                JposConst.JPOS_E_DISABLED, "JPOS_E_DISABLED",
                JposConst.JPOS_E_ILLEGAL, "JPOS_E_ILLEGAL",
                JposConst.JPOS_E_NOHARDWARE, "JPOS_E_NOHARDWARE",
                JposConst.JPOS_E_OFFLINE, "JPOS_E_OFFLINE",
                JposConst.JPOS_E_NOEXIST, "JPOS_E_NOEXIST",
                JposConst.JPOS_E_EXISTS, "JPOS_E_EXISTS",
                JposConst.JPOS_E_FAILURE, "JPOS_E_FAILURE",
                JposConst.JPOS_E_TIMEOUT, "JPOS_E_TIMEOUT",
                JposConst.JPOS_E_BUSY, "JPOS_E_BUSY",
                JposConst.JPOS_E_EXTENDED, "JPOS_E_EXTENDED",
                JposConst.JPOS_E_DEPRECATED, "JPOS_E_DEPRECATED"
        };
    }
}
