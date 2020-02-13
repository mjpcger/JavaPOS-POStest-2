package postest2;

public class FiscalPrinterConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "AdjustmentType" arguments in diverse methods
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter FPTR_AT_AMOUNT_DISCOUNT = new ConstantConverter(1,
			"FPTR_AT_AMOUNT_DISCOUNT");
	public static final ConstantConverter FPTR_AT_AMOUNT_SURCHARGE = new ConstantConverter(2,
			"FPTR_AT_AMOUNT_SURCHARGE");
	public static final ConstantConverter FPTR_AT_PERCENTAGE_DISCOUNT = new ConstantConverter(3,
			"FPTR_AT_PERCENTAGE_DISCOUNT");
	public static final ConstantConverter FPTR_AT_PERCENTAGE_SURCHARGE = new ConstantConverter(4,
			"FPTR_AT_PERCENTAGE_SURCHARGE");
	public static final ConstantConverter FPTR_AT_COUPON_AMOUNT_DISCOUNT = new ConstantConverter(5,
			"FPTR_AT_COUPON_AMOUNT_DISCOUNT"); // 1.11
	public static final ConstantConverter FPTR_AT_COUPON_PERCENTAGE_DISCOUNT = new ConstantConverter(6,
			"FPTR_AT_COUPON_PERCENTAGE_DISCOUNT"); // 1.11

	// ///////////////////////////////////////////////////////////////////
	// "ActualCurrency" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_BRC = new ConstantConverter(1, "FPTR_AC_BRC");
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_BGL = new ConstantConverter(2, "FPTR_AC_BGL");
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_EUR = new ConstantConverter(3, "FPTR_AC_EUR");
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_GRD = new ConstantConverter(4, "FPTR_AC_GRD");
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_HUF = new ConstantConverter(5, "FPTR_AC_HUF");
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_ITL = new ConstantConverter(6, "FPTR_AC_ITL");
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_PLZ = new ConstantConverter(7, "FPTR_AC_PLZ");
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_ROL = new ConstantConverter(8, "FPTR_AC_ROL");
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_RUR = new ConstantConverter(9, "FPTR_AC_RUR");
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_TRL = new ConstantConverter(10, "FPTR_AC_TRL");
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_CZK = new ConstantConverter(11, "FPTR_AC_CZK");
	@BelongingProperty(PropertyNames.getActualCurrency)
	public static final ConstantConverter FPTR_AC_UAH = new ConstantConverter(12, "FPTR_AC_UAH");

	// ///////////////////////////////////////////////////////////////////
	// "ReportType" argument in "PrintReport" method
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter FPTR_RT_ORDINAL = new ConstantConverter(1, "FPTR_RT_ORDINAL");
	public static final ConstantConverter FPTR_RT_DATE = new ConstantConverter(2, "FPTR_RT_DATE");
	public static final ConstantConverter FPTR_RT_EOD_ORDINAL = new ConstantConverter(3,
			"FPTR_RT_EOD_ORDINAL");

	// ///////////////////////////////////////////////////////////////////
	// Fiscal Printer Station Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter FPTR_S_JOURNAL = new ConstantConverter(1, "FPTR_S_JOURNAL");
	public static final ConstantConverter FPTR_S_RECEIPT = new ConstantConverter(2, "FPTR_S_RECEIPT");
	public static final ConstantConverter FPTR_S_SLIP = new ConstantConverter(4, "FPTR_S_SLIP");


	// ///////////////////////////////////////////////////////////////////
	// "GetTotalizer" Method Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter FPTR_GT_GROSS = new ConstantConverter(1, "FPTR_GT_GROSS");
	public static final ConstantConverter FPTR_GT_NET = new ConstantConverter(2, "FPTR_GT_NET");
	public static final ConstantConverter FPTR_GT_DISCOUNT = new ConstantConverter(3, "FPTR_GT_DISCOUNT");
	public static final ConstantConverter FPTR_GT_DISCOUNT_VOID = new ConstantConverter(4, "FPTR_GT_DISCOUNT_VOID");
	public static final ConstantConverter FPTR_GT_ITEM = new ConstantConverter(5, "FPTR_GT_ITEM");
	public static final ConstantConverter FPTR_GT_ITEM_VOID = new ConstantConverter(6, "FPTR_GT_ITEM_VOID");
	public static final ConstantConverter FPTR_GT_NOT_PAID = new ConstantConverter(7, "FPTR_GT_NOT_PAID");
	public static final ConstantConverter FPTR_GT_REFUND = new ConstantConverter(8, "FPTR_GT_REFUND");
	public static final ConstantConverter FPTR_GT_REFUND_VOID = new ConstantConverter(9, "FPTR_GT_REFUND_VOID");
	public static final ConstantConverter FPTR_GT_SUBTOTAL_DISCOUNT = new ConstantConverter(10, "FPTR_GT_SUBTOTAL_DISCOUNT");
	public static final ConstantConverter FPTR_GT_SUBTOTAL_DISCOUNT_VOID = new ConstantConverter(11, "FPTR_GT_SUBTOTAL_DISCOUNT_VOID");
	public static final ConstantConverter FPTR_GT_SUBTOTAL_SURCHARGES = new ConstantConverter(12, "FPTR_GT_SUBTOTAL_SURCHARGES");
	public static final ConstantConverter FPTR_GT_SUBTOTAL_SURCHARGES_VOID = new ConstantConverter(13, "FPTR_GT_SUBTOTAL_SURCHARGES_VOID");
	public static final ConstantConverter FPTR_GT_SURCHARGE = new ConstantConverter(14, "FPTR_GT_SURCHARGE");
	public static final ConstantConverter FPTR_GT_SURCHARGE_VOID = new ConstantConverter(15, "FPTR_GT_SURCHARGE_VOID");
	public static final ConstantConverter FPTR_GT_VAT = new ConstantConverter(16, "FPTR_GT_VAT");
	public static final ConstantConverter FPTR_GT_VAT_CATEGORY = new ConstantConverter(17, "FPTR_GT_VAT_CATEGORY");

	// ///////////////////////////////////////////////////////////////////
	// "GetData"  Method Constants for dataItem
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter FPTR_GD_CURRENT_TOTAL = new ConstantConverter(1, "FPTR_GD_CURRENT_TOTAL");
	public static final ConstantConverter FPTR_GD_DAILY_TOTAL = new ConstantConverter(2, "FPTR_GD_DAILY_TOTAL");
	public static final ConstantConverter FPTR_GD_RECEIPT_NUMBER = new ConstantConverter(3, "FPTR_GD_RECEIPT_NUMBER");
	public static final ConstantConverter FPTR_GD_REFUND = new ConstantConverter(4, "FPTR_GD_REFUND");
	public static final ConstantConverter FPTR_GD_NOT_PAID = new ConstantConverter(5, "FPTR_GD_NOT_PAID");
	public static final ConstantConverter FPTR_GD_MID_VOID = new ConstantConverter(6, "FPTR_GD_MID_VOID");
	public static final ConstantConverter FPTR_GD_Z_REPORT = new ConstantConverter(7, "FPTR_GD_Z_REPORT");
	public static final ConstantConverter FPTR_GD_GRAND_TOTAL = new ConstantConverter(8, "FPTR_GD_GRAND_TOTAL");
	public static final ConstantConverter FPTR_GD_PRINTER_ID = new ConstantConverter(9, "FPTR_GD_PRINTER_ID");
	public static final ConstantConverter FPTR_GD_FIRMWARE = new ConstantConverter(10, "FPTR_GD_FIRMWARE");
	public static final ConstantConverter FPTR_GD_RESTART = new ConstantConverter(11, "FPTR_GD_RESTART");
	public static final ConstantConverter FPTR_GD_REFUND_VOID = new ConstantConverter(12, "FPTR_GD_REFUND_VOID");
	public static final ConstantConverter FPTR_GD_NUMB_CONFIG_BLOCK = new ConstantConverter(13, "FPTR_GD_NUMB_CONFIG_BLOCK");
	public static final ConstantConverter FPTR_GD_NUMB_CURRENCY_BLOCK = new ConstantConverter(14, "FPTR_GD_NUMB_CURRENCY_BLOCK");
	public static final ConstantConverter FPTR_GD_NUMB_HDR_BLOCK = new ConstantConverter(15, "FPTR_GD_NUMB_HDR_BLOCK");
	public static final ConstantConverter FPTR_GD_NUMB_RESET_BLOCK = new ConstantConverter(16, "FPTR_GD_NUMB_RESET_BLOCK");
	public static final ConstantConverter FPTR_GD_NUMB_VAT_BLOCK = new ConstantConverter(17, "FPTR_GD_NUMB_VAT_BLOCK");
	public static final ConstantConverter FPTR_GD_FISCAL_DOC = new ConstantConverter(18, "FPTR_GD_FISCAL_DOC");
	public static final ConstantConverter FPTR_GD_FISCAL_DOC_VOID = new ConstantConverter(19, "FPTR_GD_FISCAL_DOC_VOID");
	public static final ConstantConverter FPTR_GD_FISCAL_REC = new ConstantConverter(20, "FPTR_GD_FISCAL_REC");
	public static final ConstantConverter FPTR_GD_FISCAL_REC_VOID = new ConstantConverter(21, "FPTR_GD_FISCAL_REC_VOID");
	public static final ConstantConverter FPTR_GD_NONFISCAL_DOC = new ConstantConverter(22, "FPTR_GD_NONFISCAL_DOC");
	public static final ConstantConverter FPTR_GD_NONFISCAL_DOC_VOID = new ConstantConverter(23, "FPTR_GD_NONFISCAL_DOC_VOID");
	public static final ConstantConverter FPTR_GD_NONFISCAL_REC = new ConstantConverter(24, "FPTR_GD_NONFISCAL_REC");
	public static final ConstantConverter FPTR_GD_SIMP_INVOICE = new ConstantConverter(25, "FPTR_GD_SIMP_INVOICE");
	public static final ConstantConverter FPTR_GD_TENDER = new ConstantConverter(26, "FPTR_GD_TENDER");
	public static final ConstantConverter FPTR_GD_LINECOUNT = new ConstantConverter(27, "FPTR_GD_LINECOUNT");
	public static final ConstantConverter FPTR_GD_DESCRIPTION_LENGTH = new ConstantConverter(28, "FPTR_GD_DESCRIPTION_LENGTH");

	// ///////////////////////////////////////////////////////////////////
	// "GetData"  Method Constants for optArgs
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter FPTR_PDL_CASH = new ConstantConverter(1, "FPTR_PDL_CASH");
	public static final ConstantConverter FPTR_PDL_CHEQUE = new ConstantConverter(2, "FPTR_PDL_CHEQUE");
	public static final ConstantConverter FPTR_PDL_CHITTY = new ConstantConverter(3, "FPTR_PDL_CHITTY");
	public static final ConstantConverter FPTR_PDL_COUPON = new ConstantConverter(4, "FPTR_PDL_COUPON");
	public static final ConstantConverter FPTR_PDL_CURRENCY = new ConstantConverter(5, "FPTR_PDL_CURRENCY");
	public static final ConstantConverter FPTR_PDL_DRIVEN_OFF = new ConstantConverter(6, "FPTR_PDL_DRIVEN_OFF");
	public static final ConstantConverter FPTR_PDL_EFT_IMPRINTER = new ConstantConverter(7, "FPTR_PDL_EFT_IMPRINTER");
	public static final ConstantConverter FPTR_PDL_EFT_TERMINAL = new ConstantConverter(8, "FPTR_PDL_EFT_TERMINAL");
	public static final ConstantConverter FPTR_PDL_TERMINAL_IMPRINTER = new ConstantConverter(9, "FPTR_PDL_TERMINAL_IMPRINTER");
	public static final ConstantConverter FPTR_PDL_FREE_GIFT = new ConstantConverter(10, "FPTR_PDL_FREE_GIFT");
	public static final ConstantConverter FPTR_PDL_GIRO = new ConstantConverter(11, "FPTR_PDL_GIRO");
	public static final ConstantConverter FPTR_PDL_HOME = new ConstantConverter(12, "FPTR_PDL_HOME");
	public static final ConstantConverter FPTR_PDL_IMPRINTER_WITH_ISSUER = new ConstantConverter(13, "FPTR_PDL_IMPRINTER_WITH_ISSUER");
	public static final ConstantConverter FPTR_PDL_LOCAL_ACCOUNT = new ConstantConverter(14, "FPTR_PDL_LOCAL_ACCOUNT");
	public static final ConstantConverter FPTR_PDL_LOCAL_ACCOUNT_CARD = new ConstantConverter(15, "FPTR_PDL_LOCAL_ACCOUNT_CARD");
	public static final ConstantConverter FPTR_PDL_PAY_CARD = new ConstantConverter(16, "FPTR_PDL_PAY_CARD");
	public static final ConstantConverter FPTR_PDL_PAY_CARD_MANUAL = new ConstantConverter(17, "FPTR_PDL_PAY_CARD_MANUAL");
	public static final ConstantConverter FPTR_PDL_PREPAY = new ConstantConverter(18, "FPTR_PDL_PREPAY");
	public static final ConstantConverter FPTR_PDL_PUMP_TEST = new ConstantConverter(19, "FPTR_PDL_PUMP_TEST");
	public static final ConstantConverter FPTR_PDL_SHORT_CREDIT = new ConstantConverter(20, "FPTR_PDL_SHORT_CREDIT");
	public static final ConstantConverter FPTR_PDL_STAFF = new ConstantConverter(21, "FPTR_PDL_STAFF");
	public static final ConstantConverter FPTR_PDL_VOUCHER = new ConstantConverter(22, "FPTR_PDL_VOUCHER");
	public static final ConstantConverter FPTR_LC_ITEM = new ConstantConverter(1, "FPTR_LC_ITEM");
	public static final ConstantConverter FPTR_LC_ITEM_VOID = new ConstantConverter(2, "FPTR_LC_ITEM_VOID");
	public static final ConstantConverter FPTR_LC_DISCOUNT = new ConstantConverter(3, "FPTR_LC_DISCOUNT");
	public static final ConstantConverter FPTR_LC_DISCOUNT_VOID = new ConstantConverter(4, "FPTR_LC_DISCOUNT_VOID");
	public static final ConstantConverter FPTR_LC_SURCHARGE = new ConstantConverter(5, "FPTR_LC_SURCHARGE");
	public static final ConstantConverter FPTR_LC_SURCHARGE_VOID = new ConstantConverter(6, "FPTR_LC_SURCHARGE_VOID");
	public static final ConstantConverter FPTR_LC_REFUND = new ConstantConverter(7, "FPTR_LC_REFUND");
	public static final ConstantConverter FPTR_LC_REFUND_VOID = new ConstantConverter(8, "FPTR_LC_REFUND_VOID");
	public static final ConstantConverter FPTR_LC_SUBTOTAL_DISCOUNT = new ConstantConverter(9, "FPTR_LC_SUBTOTAL_DISCOUNT");
	public static final ConstantConverter FPTR_LC_SUBTOTAL_DISCOUNT_VOID = new ConstantConverter(10, "FPTR_LC_SUBTOTAL_DISCOUNT_VOID");
	public static final ConstantConverter FPTR_LC_SUBTOTAL_SURCHARGE = new ConstantConverter(11, "FPTR_LC_SUBTOTAL_SURCHARGE");
	public static final ConstantConverter FPTR_LC_SUBTOTAL_SURCHARGE_VOID = new ConstantConverter(12, "FPTR_LC_SUBTOTAL_SURCHARGE_VOID");
	public static final ConstantConverter FPTR_LC_COMMENT = new ConstantConverter(13, "FPTR_LC_COMMENT");
	public static final ConstantConverter FPTR_LC_SUBTOTAL = new ConstantConverter(14, "FPTR_LC_SUBTOTAL");
	public static final ConstantConverter FPTR_LC_TOTAL = new ConstantConverter(15, "FPTR_LC_TOTAL");
	public static final ConstantConverter FPTR_DL_ITEM = new ConstantConverter(1, "FPTR_DL_ITEM");
	public static final ConstantConverter FPTR_DL_ITEM_ADJUSTMENT = new ConstantConverter(2, "FPTR_DL_ITEM_ADJUSTMENT");
	public static final ConstantConverter FPTR_DL_ITEM_FUEL = new ConstantConverter(3, "FPTR_DL_ITEM_FUEL");
	public static final ConstantConverter FPTR_DL_ITEM_FUEL_VOID = new ConstantConverter(4, "FPTR_DL_ITEM_FUEL_VOID");
	public static final ConstantConverter FPTR_DL_NOT_PAID = new ConstantConverter(5, "FPTR_DL_NOT_PAID");
	public static final ConstantConverter FPTR_DL_PACKAGE_ADJUSTMENT = new ConstantConverter(6, "FPTR_DL_PACKAGE_ADJUSTMENT");
	public static final ConstantConverter FPTR_DL_REFUND = new ConstantConverter(7, "FPTR_DL_REFUND");
	public static final ConstantConverter FPTR_DL_REFUND_VOID = new ConstantConverter(8, "FPTR_DL_REFUND_VOID");
	public static final ConstantConverter FPTR_DL_SUBTOTAL_ADJUSTMENT = new ConstantConverter(9, "FPTR_DL_SUBTOTAL_ADJUSTMENT");
	public static final ConstantConverter FPTR_DL_TOTAL = new ConstantConverter(10, "FPTR_DL_TOTAL");
	public static final ConstantConverter FPTR_DL_VOID = new ConstantConverter(11, "FPTR_DL_VOID");
	public static final ConstantConverter FPTR_DL_VOID_ITEM = new ConstantConverter(12, "FPTR_DL_VOID_ITEM");

	// ///////////////////////////////////////////////////////////////////
	// "ContractorId" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getContractorId)
	public static final ConstantConverter FPTR_CID_FIRST = new ConstantConverter(1, "FPTR_CID_FIRST");
	@BelongingProperty(PropertyNames.getContractorId)
	public static final ConstantConverter FPTR_CID_SECOND = new ConstantConverter(2, "FPTR_CID_SECOND");
	@BelongingProperty(PropertyNames.getContractorId)
	public static final ConstantConverter FPTR_CID_SINGLE = new ConstantConverter(3, "FPTR_CID_SINGLE");

	// ///////////////////////////////////////////////////////////////////
	// "CountryCode" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_BRAZIL = new ConstantConverter(0x00000001, "FPTR_CC_BRAZIL");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_GREECE = new ConstantConverter(0x00000002, "FPTR_CC_GREECE");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_HUNGARY = new ConstantConverter(0x00000004, "FPTR_CC_HUNGARY");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_ITALY = new ConstantConverter(0x00000008, "FPTR_CC_ITALY");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_POLAND = new ConstantConverter(0x00000010, "FPTR_CC_POLAND");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_TURKEY = new ConstantConverter(0x00000020, "FPTR_CC_TURKEY");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_RUSSIA = new ConstantConverter(0x00000040, "FPTR_CC_RUSSIA");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_BULGARIA = new ConstantConverter(0x00000080, "FPTR_CC_BULGARIA");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_ROMANIA = new ConstantConverter(0x00000100, "FPTR_CC_ROMANIA");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_CZECH_REPUBLIC = new ConstantConverter(0x00000200, "FPTR_CC_CZECH_REPUBLIC");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_UKRAINE = new ConstantConverter(0x00000400, "FPTR_CC_UKRAINE");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter FPTR_CC_OTHER = new ConstantConverter(0x40000000, "FPTR_CC_OTHER");

	// ///////////////////////////////////////////////////////////////////
	// "DateType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getDateType)
	public static final ConstantConverter FPTR_DT_CONF = new ConstantConverter(1, "FPTR_DT_CONF");
	@BelongingProperty(PropertyNames.getDateType)
	public static final ConstantConverter FPTR_DT_EOD = new ConstantConverter(2, "FPTR_DT_EOD");
	@BelongingProperty(PropertyNames.getDateType)
	public static final ConstantConverter FPTR_DT_RESET = new ConstantConverter(3, "FPTR_DT_RESET");
	@BelongingProperty(PropertyNames.getDateType)
	public static final ConstantConverter FPTR_DT_RTC = new ConstantConverter(4, "FPTR_DT_RTC");
	@BelongingProperty(PropertyNames.getDateType)
	public static final ConstantConverter FPTR_DT_VAT = new ConstantConverter(5, "FPTR_DT_VAT");
	@BelongingProperty(PropertyNames.getDateType)
	public static final ConstantConverter FPTR_DT_START = new ConstantConverter(6, "FPTR_DT_START");

	// ///////////////////////////////////////////////////////////////////
	// "FiscalReceiptStation" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getFiscalReceiptStation)
	public static final ConstantConverter FPTR_RS_RECEIPT =  new ConstantConverter(1, "FPTR_RS_RECEIPT");
	@BelongingProperty(PropertyNames.getFiscalReceiptStation)
	public static final ConstantConverter FPTR_RS_SLIP =  new ConstantConverter(2, "FPTR_RS_SLIP");

	// ///////////////////////////////////////////////////////////////////
	// "FiscalReceiptType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getFiscalReceiptType)
	public static final ConstantConverter FPTR_RT_CASH_IN = new ConstantConverter(1, "FPTR_RT_CASH_IN");
	@BelongingProperty(PropertyNames.getFiscalReceiptType)
	public static final ConstantConverter FPTR_RT_CASH_OUT = new ConstantConverter(2, "FPTR_RT_CASH_OUT");
	@BelongingProperty(PropertyNames.getFiscalReceiptType)
	public static final ConstantConverter FPTR_RT_GENERIC = new ConstantConverter(3, "FPTR_RT_GENERIC");
	@BelongingProperty(PropertyNames.getFiscalReceiptType)
	public static final ConstantConverter FPTR_RT_SALES = new ConstantConverter(4, "FPTR_RT_SALES");
	@BelongingProperty(PropertyNames.getFiscalReceiptType)
	public static final ConstantConverter FPTR_RT_SERVICE = new ConstantConverter(5, "FPTR_RT_SERVICE");
	@BelongingProperty(PropertyNames.getFiscalReceiptType)
	public static final ConstantConverter FPTR_RT_SIMPLE_INVOICE = new ConstantConverter(6, "FPTR_RT_SIMPLE_INVOICE");
	@BelongingProperty(PropertyNames.getFiscalReceiptType)
	public static final ConstantConverter FPTR_RT_REFUND = new ConstantConverter(7, "FPTR_RT_REFUND");

	// ///////////////////////////////////////////////////////////////////
	// "MessageType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_ADVANCE = new ConstantConverter(1, "FPTR_MT_ADVANCE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_ADVANCE_PAID = new ConstantConverter(2, "FPTR_MT_ADVANCE_PAID");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_AMOUNT_TO_BE_PAID = new ConstantConverter(3, "FPTR_MT_AMOUNT_TO_BE_PAID");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_AMOUNT_TO_BE_PAID_BACK = new ConstantConverter(4, "FPTR_MT_AMOUNT_TO_BE_PAID_BACK");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CARD = new ConstantConverter(5, "FPTR_MT_CARD");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CARD_NUMBER = new ConstantConverter(6, "FPTR_MT_CARD_NUMBER");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CARD_TYPE = new ConstantConverter(7, "FPTR_MT_CARD_TYPE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CASH = new ConstantConverter(8, "FPTR_MT_CASH");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CASHIER = new ConstantConverter(9, "FPTR_MT_CASHIER");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CASH_REGISTER_NUMBER = new ConstantConverter(10, "FPTR_MT_CASH_REGISTER_NUMBER");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CHANGE = new ConstantConverter(11, "FPTR_MT_CHANGE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CHEQUE = new ConstantConverter(12, "FPTR_MT_CHEQUE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CLIENT_NUMBER = new ConstantConverter(13, "FPTR_MT_CLIENT_NUMBER");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CLIENT_SIGNATURE = new ConstantConverter(14, "FPTR_MT_CLIENT_SIGNATURE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_COUNTER_STATE = new ConstantConverter(15, "FPTR_MT_COUNTER_STATE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CREDIT_CARD = new ConstantConverter(16, "FPTR_MT_CREDIT_CARD");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CURRENCY = new ConstantConverter(17, "FPTR_MT_CURRENCY");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_CURRENCY_VALUE = new ConstantConverter(18, "FPTR_MT_CURRENCY_VALUE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_DEPOSIT = new ConstantConverter(19, "FPTR_MT_DEPOSIT");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_DEPOSIT_RETURNED = new ConstantConverter(20, "FPTR_MT_DEPOSIT_RETURNED");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_DOT_LINE = new ConstantConverter(21, "FPTR_MT_DOT_LINE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_DRIVER_NUMB = new ConstantConverter(22, "FPTR_MT_DRIVER_NUMB");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_EMPTY_LINE = new ConstantConverter(23, "FPTR_MT_EMPTY_LINE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_FREE_TEXT = new ConstantConverter(24, "FPTR_MT_FREE_TEXT");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_FREE_TEXT_WITH_DAY_LIMIT = new ConstantConverter(25, "FPTR_MT_FREE_TEXT_WITH_DAY_LIMIT");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_GIVEN_DISCOUNT = new ConstantConverter(26, "FPTR_MT_GIVEN_DISCOUNT");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_LOCAL_CREDIT = new ConstantConverter(27, "FPTR_MT_LOCAL_CREDIT");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_MILEAGE_KM = new ConstantConverter(28, "FPTR_MT_MILEAGE_KM");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_NOTE = new ConstantConverter(29, "FPTR_MT_NOTE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_PAID = new ConstantConverter(30, "FPTR_MT_PAID");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_PAY_IN = new ConstantConverter(31, "FPTR_MT_PAY_IN");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_POINT_GRANTED = new ConstantConverter(32, "FPTR_MT_POINT_GRANTED");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_POINTS_BONUS = new ConstantConverter(33, "FPTR_MT_POINTS_BONUS");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_POINTS_RECEIPT = new ConstantConverter(34, "FPTR_MT_POINTS_RECEIPT");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_POINTS_TOTAL = new ConstantConverter(35, "FPTR_MT_POINTS_TOTAL");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_PROFITED = new ConstantConverter(36, "FPTR_MT_PROFITED");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_RATE = new ConstantConverter(37, "FPTR_MT_RATE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_REGISTER_NUMB = new ConstantConverter(38, "FPTR_MT_REGISTER_NUMB");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_SHIFT_NUMBER = new ConstantConverter(39, "FPTR_MT_SHIFT_NUMBER");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_STATE_OF_AN_ACCOUNT = new ConstantConverter(40, "FPTR_MT_STATE_OF_AN_ACCOUNT");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_SUBSCRIPTION = new ConstantConverter(41, "FPTR_MT_SUBSCRIPTION");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_TABLE = new ConstantConverter(42, "FPTR_MT_TABLE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_THANK_YOU_FOR_LOYALTY = new ConstantConverter(43, "FPTR_MT_THANK_YOU_FOR_LOYALTY");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_TRANSACTION_NUMB = new ConstantConverter(44, "FPTR_MT_TRANSACTION_NUMB");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_VALID_TO = new ConstantConverter(45, "FPTR_MT_VALID_TO");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_VOUCHER = new ConstantConverter(46, "FPTR_MT_VOUCHER");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_VOUCHER_PAID = new ConstantConverter(47, "FPTR_MT_VOUCHER_PAID");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_VOUCHER_VALUE = new ConstantConverter(48, "FPTR_MT_VOUCHER_VALUE");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_WITH_DISCOUNT = new ConstantConverter(49, "FPTR_MT_WITH_DISCOUNT");
	@BelongingProperty(PropertyNames.getMessageType)
	public static final ConstantConverter FPTR_MT_WITHOUT_UPLIFT = new ConstantConverter(50, "FPTR_MT_WITHOUT_UPLIFT");

	// ///////////////////////////////////////////////////////////////////
	// "ErrorState", "PrinterState" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getPrinterState)
	public static final ConstantConverter FPTR_PS_MONITOR = new ConstantConverter(1, "FPTR_PS_MONITOR");
	@BelongingProperty(PropertyNames.getPrinterState)
	public static final ConstantConverter FPTR_PS_FISCAL_RECEIPT = new ConstantConverter(2, "FPTR_PS_FISCAL_RECEIPT");
	@BelongingProperty(PropertyNames.getPrinterState)
	public static final ConstantConverter FPTR_PS_FISCAL_RECEIPT_TOTAL = new ConstantConverter(3, "FPTR_PS_FISCAL_RECEIPT_TOTAL");
	@BelongingProperty(PropertyNames.getPrinterState)
	public static final ConstantConverter FPTR_PS_FISCAL_RECEIPT_ENDING = new ConstantConverter(4, "FPTR_PS_FISCAL_RECEIPT_ENDING");
	@BelongingProperty(PropertyNames.getPrinterState)
	public static final ConstantConverter FPTR_PS_FISCAL_DOCUMENT = new ConstantConverter(5, "FPTR_PS_FISCAL_DOCUMENT");
	@BelongingProperty(PropertyNames.getPrinterState)
	public static final ConstantConverter FPTR_PS_FIXED_OUTPUT = new ConstantConverter(6, "FPTR_PS_FIXED_OUTPUT");
	@BelongingProperty(PropertyNames.getPrinterState)
	public static final ConstantConverter FPTR_PS_ITEM_LIST = new ConstantConverter(7, "FPTR_PS_ITEM_LIST");
	@BelongingProperty(PropertyNames.getPrinterState)
	public static final ConstantConverter FPTR_PS_LOCKED = new ConstantConverter(8, "FPTR_PS_LOCKED");
	@BelongingProperty(PropertyNames.getPrinterState)
	public static final ConstantConverter FPTR_PS_NONFISCAL = new ConstantConverter(9, "FPTR_PS_NONFISCAL");
	@BelongingProperty(PropertyNames.getPrinterState)
	public static final ConstantConverter FPTR_PS_REPORT = new ConstantConverter(10, "FPTR_PS_REPORT");

	// ///////////////////////////////////////////////////////////////////
	// "SlipSelection" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getSlipSelection)
	public static final ConstantConverter FPTR_SS_FULL_LENGTH = new ConstantConverter(1, "FPTR_SS_FULL_LENGTH");
	@BelongingProperty(PropertyNames.getSlipSelection)
	public static final ConstantConverter FPTR_SS_VALIDATION = new ConstantConverter(2, "FPTR_SS_VALIDATION");

	// ///////////////////////////////////////////////////////////////////
	// "TotalizerType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getTotalizerType)
	public static final ConstantConverter FPTR_TT_DOCUMENT = new ConstantConverter(1, "FPTR_TT_DOCUMENT");
	@BelongingProperty(PropertyNames.getTotalizerType)
	public static final ConstantConverter FPTR_TT_DAY = new ConstantConverter(2, "FPTR_TT_DAY");
	@BelongingProperty(PropertyNames.getTotalizerType)
	public static final ConstantConverter FPTR_TT_RECEIPT = new ConstantConverter(3, "FPTR_TT_RECEIPT");
	@BelongingProperty(PropertyNames.getTotalizerType)
	public static final ConstantConverter FPTR_TT_GRAND = new ConstantConverter(4, "FPTR_TT_GRAND");

	// ///////////////////////////////////////////////////////////////////
	// "ErrorLevel" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getErrorLevel)
	public static final ConstantConverter FPTR_EL_NONE =  new ConstantConverter(1, "FPTR_EL_NONE");
	@BelongingProperty(PropertyNames.getErrorLevel)
	public static final ConstantConverter FPTR_EL_RECOVERABLE =  new ConstantConverter(2, "FPTR_EL_RECOVERABLE");
	@BelongingProperty(PropertyNames.getErrorLevel)
	public static final ConstantConverter FPTR_EL_FATAL =  new ConstantConverter(3, "FPTR_EL_FATAL");
	@BelongingProperty(PropertyNames.getErrorLevel)
	public static final ConstantConverter FPTR_EL_BLOCKED =  new ConstantConverter(4, "FPTR_EL_BLOCKED");
	
	
	public static int getConstantNumberFromString(String constant) {
		
		if (FiscalPrinterConstantMapper.FPTR_EL_NONE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_EL_NONE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_EL_RECOVERABLE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_EL_RECOVERABLE.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_EL_FATAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_EL_FATAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_EL_BLOCKED.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_EL_BLOCKED.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_TT_DOCUMENT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_TT_DOCUMENT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_TT_DAY.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_TT_DAY.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_TT_RECEIPT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_TT_RECEIPT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_TT_GRAND.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_TT_GRAND.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_SS_FULL_LENGTH.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_SS_FULL_LENGTH.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_SS_VALIDATION.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_SS_VALIDATION.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_PS_MONITOR.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PS_MONITOR.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PS_FISCAL_RECEIPT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PS_FISCAL_RECEIPT.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_PS_FISCAL_RECEIPT_TOTAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PS_FISCAL_RECEIPT_TOTAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PS_FISCAL_RECEIPT_ENDING.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PS_FISCAL_RECEIPT_ENDING.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PS_FISCAL_DOCUMENT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PS_FISCAL_DOCUMENT.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_PS_FIXED_OUTPUT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PS_FIXED_OUTPUT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PS_ITEM_LIST.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PS_ITEM_LIST.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_PS_LOCKED.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PS_LOCKED.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PS_NONFISCAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PS_NONFISCAL.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_PS_REPORT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PS_REPORT.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_ADVANCE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_ADVANCE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_ADVANCE_PAID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_ADVANCE_PAID.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_AMOUNT_TO_BE_PAID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_AMOUNT_TO_BE_PAID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_AMOUNT_TO_BE_PAID_BACK.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_AMOUNT_TO_BE_PAID_BACK.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_CARD.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CARD.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_CARD_NUMBER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CARD_NUMBER.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_CARD_TYPE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CARD_TYPE.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_CASH.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CASH.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_CASHIER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CASHIER.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_CASH_REGISTER_NUMBER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CASH_REGISTER_NUMBER.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_CHANGE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CHANGE.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_CHEQUE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CHEQUE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_CLIENT_NUMBER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CLIENT_NUMBER.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_CLIENT_SIGNATURE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CLIENT_SIGNATURE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_COUNTER_STATE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_COUNTER_STATE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_CREDIT_CARD.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CREDIT_CARD.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_CURRENCY.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CURRENCY.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_CURRENCY_VALUE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_CURRENCY_VALUE.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_DEPOSIT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_DEPOSIT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_DEPOSIT_RETURNED.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_DEPOSIT_RETURNED.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_DOT_LINE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_DOT_LINE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_DRIVER_NUMB.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_DRIVER_NUMB.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_EMPTY_LINE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_EMPTY_LINE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_FREE_TEXT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_FREE_TEXT.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_FREE_TEXT_WITH_DAY_LIMIT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_FREE_TEXT_WITH_DAY_LIMIT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_GIVEN_DISCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_GIVEN_DISCOUNT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_LOCAL_CREDIT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_LOCAL_CREDIT.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_MILEAGE_KM.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_MILEAGE_KM.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_NOTE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_NOTE.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_PAID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_PAID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_PAY_IN.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_PAY_IN.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_POINT_GRANTED.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_POINT_GRANTED.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_POINTS_BONUS.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_POINTS_BONUS.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_POINTS_RECEIPT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_POINTS_RECEIPT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_POINTS_TOTAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_POINTS_TOTAL.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_PROFITED.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_PROFITED.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_RATE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_RATE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_REGISTER_NUMB.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_REGISTER_NUMB.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_SHIFT_NUMBER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_SHIFT_NUMBER.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_STATE_OF_AN_ACCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_STATE_OF_AN_ACCOUNT.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_SUBSCRIPTION.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_SUBSCRIPTION.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_TABLE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_TABLE.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_THANK_YOU_FOR_LOYALTY.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_THANK_YOU_FOR_LOYALTY.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_MT_TRANSACTION_NUMB.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_TRANSACTION_NUMB.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_VALID_TO.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_VALID_TO.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_VOUCHER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_VOUCHER.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_VOUCHER_PAID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_VOUCHER_PAID.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_VOUCHER_VALUE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_VOUCHER_VALUE.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_WITH_DISCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_WITH_DISCOUNT.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_MT_WITHOUT_UPLIFT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_MT_WITHOUT_UPLIFT.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_RT_CASH_IN.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RT_CASH_IN.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_RT_CASH_OUT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RT_CASH_OUT.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_RT_GENERIC.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RT_GENERIC.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_RT_SALES.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RT_SALES.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_RT_SERVICE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RT_SERVICE.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_RT_SIMPLE_INVOICE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RT_SIMPLE_INVOICE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_RT_REFUND.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RT_REFUND.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_RS_RECEIPT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RS_RECEIPT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_RS_SLIP.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RS_SLIP.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_DT_CONF.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DT_CONF.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DT_EOD.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DT_EOD.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DT_RESET.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DT_RESET.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_DT_RTC.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DT_RTC.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DT_VAT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DT_VAT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DT_START.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DT_START.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_CC_BRAZIL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_BRAZIL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_CC_GREECE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_GREECE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_CC_HUNGARY.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_HUNGARY.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_CC_ITALY.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_ITALY.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_CC_POLAND.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_POLAND.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_CC_TURKEY.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_TURKEY.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_CC_RUSSIA.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_RUSSIA.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_CC_BULGARIA.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_BULGARIA.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_CC_ROMANIA.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_ROMANIA.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_CC_CZECH_REPUBLIC.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_CZECH_REPUBLIC.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_CC_UKRAINE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_UKRAINE.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_CC_OTHER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CC_OTHER.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_CID_FIRST.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CID_FIRST.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_CID_SECOND.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CID_SECOND.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_CID_SINGLE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_CID_SINGLE.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_DISCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_DISCOUNT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_SURCHARGE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_SURCHARGE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AT_COUPON_AMOUNT_DISCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AT_COUPON_AMOUNT_DISCOUNT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AT_COUPON_PERCENTAGE_DISCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AT_COUPON_PERCENTAGE_DISCOUNT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_BRC.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_BRC.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_BGL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_BGL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_EUR.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_EUR.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_GRD.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_GRD.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_HUF.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_HUF.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_ITL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_ITL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_PLZ.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_PLZ.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_ROL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_ROL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_RUR.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_RUR.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_TRL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_TRL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_CZK.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_CZK.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_AC_UAH.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_AC_UAH.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_RT_ORDINAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RT_ORDINAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_RT_DATE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RT_DATE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_RT_EOD_ORDINAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_RT_EOD_ORDINAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_S_JOURNAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_S_JOURNAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_S_RECEIPT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_S_RECEIPT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_S_SLIP.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_S_SLIP.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_GT_GROSS.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_GROSS.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_NET.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_NET.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_ITEM.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_ITEM.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_ITEM_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_ITEM_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_NOT_PAID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_NOT_PAID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_REFUND.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_REFUND.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_REFUND_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_REFUND_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT_VOID.getContantNumber();
		}
		
		if (FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_VAT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_VAT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GT_VAT_CATEGORY.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GT_VAT_CATEGORY.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_CURRENT_TOTAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_CURRENT_TOTAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_DAILY_TOTAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_DAILY_TOTAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_RECEIPT_NUMBER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_RECEIPT_NUMBER.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_REFUND.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_REFUND.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_NOT_PAID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_NOT_PAID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_MID_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_MID_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_Z_REPORT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_Z_REPORT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_GRAND_TOTAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_GRAND_TOTAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_PRINTER_ID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_PRINTER_ID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_FIRMWARE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_FIRMWARE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_RESTART.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_RESTART.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_REFUND_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_REFUND_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_NUMB_CONFIG_BLOCK.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_NUMB_CONFIG_BLOCK.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_NUMB_CURRENCY_BLOCK.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_NUMB_CURRENCY_BLOCK.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_NUMB_HDR_BLOCK.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_NUMB_HDR_BLOCK.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_NUMB_RESET_BLOCK.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_NUMB_RESET_BLOCK.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_NUMB_VAT_BLOCK.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_NUMB_VAT_BLOCK.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_FISCAL_DOC.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_FISCAL_DOC.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_FISCAL_DOC_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_FISCAL_DOC_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_FISCAL_REC.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_FISCAL_REC.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_FISCAL_REC_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_FISCAL_REC_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_NONFISCAL_DOC.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_NONFISCAL_DOC.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_NONFISCAL_DOC_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_NONFISCAL_DOC_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_NONFISCAL_REC.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_NONFISCAL_REC.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_SIMP_INVOICE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_SIMP_INVOICE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_TENDER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_TENDER.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_LINECOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_LINECOUNT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_GD_DESCRIPTION_LENGTH.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_GD_DESCRIPTION_LENGTH.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_CASH.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_CASH.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_CHEQUE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_CHEQUE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_CHITTY.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_CHITTY.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_COUPON.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_COUPON.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_CURRENCY.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_CURRENCY.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_DRIVEN_OFF.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_DRIVEN_OFF.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_EFT_IMPRINTER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_EFT_IMPRINTER.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_EFT_TERMINAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_EFT_TERMINAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_TERMINAL_IMPRINTER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_TERMINAL_IMPRINTER.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_FREE_GIFT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_FREE_GIFT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_GIRO.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_GIRO.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_HOME.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_HOME.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_IMPRINTER_WITH_ISSUER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_IMPRINTER_WITH_ISSUER.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_LOCAL_ACCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_LOCAL_ACCOUNT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_LOCAL_ACCOUNT_CARD.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_LOCAL_ACCOUNT_CARD.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_PAY_CARD.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_PAY_CARD.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_PAY_CARD_MANUAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_PAY_CARD_MANUAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_PREPAY.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_PREPAY.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_PUMP_TEST.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_PUMP_TEST.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_SHORT_CREDIT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_SHORT_CREDIT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_STAFF.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_STAFF.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_PDL_VOUCHER.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_PDL_VOUCHER.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_ITEM.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_ITEM.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_ITEM_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_ITEM_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_DISCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_DISCOUNT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_DISCOUNT_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_DISCOUNT_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_SURCHARGE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_SURCHARGE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_SURCHARGE_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_SURCHARGE_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_REFUND.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_REFUND.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_REFUND_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_REFUND_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_DISCOUNT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_DISCOUNT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_DISCOUNT_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_DISCOUNT_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_SURCHARGE.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_SURCHARGE.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_SURCHARGE_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL_SURCHARGE_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_COMMENT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_COMMENT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_SUBTOTAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_LC_TOTAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_LC_TOTAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_ITEM.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_ITEM.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_ITEM_ADJUSTMENT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_ITEM_ADJUSTMENT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_ITEM_FUEL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_ITEM_FUEL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_ITEM_FUEL_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_ITEM_FUEL_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_NOT_PAID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_NOT_PAID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_PACKAGE_ADJUSTMENT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_PACKAGE_ADJUSTMENT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_REFUND.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_REFUND.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_REFUND_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_REFUND_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_SUBTOTAL_ADJUSTMENT.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_SUBTOTAL_ADJUSTMENT.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_TOTAL.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_TOTAL.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_VOID.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_VOID.getContantNumber();
		}

		if (FiscalPrinterConstantMapper.FPTR_DL_VOID_ITEM.getConstant().equals(constant)) {
			return FiscalPrinterConstantMapper.FPTR_DL_VOID_ITEM.getContantNumber();
		}

		return Integer.parseInt(constant);
	}


	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
