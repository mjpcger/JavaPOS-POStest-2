package postest2;

public class CheckScannerConstantMapper extends CommonConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "Color" Capability Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getColor)
	public static final ConstantConverter CHK_CL_MONO = new ConstantConverter(1, "CHK_CL_MONO");
	@BelongingProperty(PropertyNames.getColor)
	public static final ConstantConverter CHK_CL_GRAYSCALE = new ConstantConverter(2, "CHK_CL_GRAYSCALE");
	@BelongingProperty(PropertyNames.getColor)
	public static final ConstantConverter CHK_CL_16 = new ConstantConverter(3, "CHK_CL_16");
	@BelongingProperty(PropertyNames.getColor)
	public static final ConstantConverter CHK_CL_256 = new ConstantConverter(4, "CHK_CL_256");
	@BelongingProperty(PropertyNames.getColor)
	public static final ConstantConverter CHK_CL_FULL = new ConstantConverter(5, "CHK_CL_FULL");

	// ///////////////////////////////////////////////////////////////////
	// "CapColor" Capability Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter CHK_CCL_MONO = new ConstantConverter(0x00000001, "CHK_CCL_MONO");
	public static final ConstantConverter CHK_CCL_GRAYSCALE = new ConstantConverter(0x00000002, "CHK_CCL_GRAYSCALE");
	public static final ConstantConverter CHK_CCL_16 = new ConstantConverter(0x00000004, "CHK_CCL_16");
	public static final ConstantConverter CHK_CCL_256 = new ConstantConverter(0x00000008, "CHK_CCL_256");
	public static final ConstantConverter CHK_CCL_FULL = new ConstantConverter(0x00000010, "CHK_CCL_FULL");

	// ///////////////////////////////////////////////////////////////////
	// "ImageFormat" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getImageFormat)
	public static final ConstantConverter CHK_IF_NATIVE = new ConstantConverter(1, "CHK_IF_NATIVE");
	@BelongingProperty(PropertyNames.getImageFormat)
	public static final ConstantConverter CHK_IF_TIFF = new ConstantConverter(2, "CHK_IF_TIFF");
	@BelongingProperty(PropertyNames.getImageFormat)
	public static final ConstantConverter CHK_IF_BMP = new ConstantConverter(3, "CHK_IF_BMP");
	@BelongingProperty(PropertyNames.getImageFormat)
	public static final ConstantConverter CHK_IF_JPEG = new ConstantConverter(4, "CHK_IF_JPEG");
	@BelongingProperty(PropertyNames.getImageFormat)
	public static final ConstantConverter CHK_IF_GIF = new ConstantConverter(5, "CHK_IF_GIF");

	// ///////////////////////////////////////////////////////////////////
	// "CapImageFormat" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapImageFormat)
	public static final ConstantConverter CHK_CIF_NATIVE = new ConstantConverter(1, "CHK_CIF_NATIVE");
	@BelongingProperty(PropertyNames.getCapImageFormat)
	public static final ConstantConverter CHK_CIF_TIFF = new ConstantConverter(2, "CHK_CIF_TIFF");
	@BelongingProperty(PropertyNames.getCapImageFormat)
	public static final ConstantConverter CHK_CIF_BMP = new ConstantConverter(3, "CHK_CIF_BMP");
	@BelongingProperty(PropertyNames.getCapImageFormat)
	public static final ConstantConverter CHK_CIF_JPEG = new ConstantConverter(4, "CHK_CIF_JPEG");
	@BelongingProperty(PropertyNames.getCapImageFormat)
	public static final ConstantConverter CHK_CIF_GIF = new ConstantConverter(5, "CHK_CIF_GIF");

	// ///////////////////////////////////////////////////////////////////
	// "ImageMemoryStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getImageMemoryStatus)
	public static final ConstantConverter CHK_IMS_EMPTY = new ConstantConverter(1, "CHK_IMS_EMPTY");
	@BelongingProperty(PropertyNames.getImageMemoryStatus)
	public static final ConstantConverter CHK_IMS_OK = new ConstantConverter(2, "CHK_IMS_OK");
	@BelongingProperty(PropertyNames.getImageMemoryStatus)
	public static final ConstantConverter CHK_IMS_FULL = new ConstantConverter(3, "CHK_IMS_FULL");

	// ///////////////////////////////////////////////////////////////////
	// "MapMode" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter CHK_MM_DOTS = new ConstantConverter(1, "CHK_MM_DOTS");
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter CHK_MM_TWIPS = new ConstantConverter(2, "CHK_MM_TWIPS");
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter CHK_MM_ENGLISH = new ConstantConverter(3, "CHK_MM_ENGLISH");
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter CHK_MM_METRIC = new ConstantConverter(4, "CHK_MM_METRIC");

	// ///////////////////////////////////////////////////////////////////
	// "clearImage" Method Constants:
	// ///////////////////////////////////////////////////////////////////

	// "by" Parameter
	public static final ConstantConverter CHK_CLR_ALL = new ConstantConverter(1, "CHK_CLR_ALL");
	public static final ConstantConverter CHK_CLR_BY_FILEID = new ConstantConverter(2, "CHK_CLR_BY_FILEID");
	public static final ConstantConverter CHK_CLR_BY_FILEINDEX = new ConstantConverter(3,
			"CHK_CLR_BY_FILEINDEX");
	public static final ConstantConverter CHK_CLR_BY_IMAGETAGDATA = new ConstantConverter(4,
			"CHK_CLR_BY_IMAGETAGDATA");

	// ///////////////////////////////////////////////////////////////////
	// "defineCropArea" Method Constants:
	// ///////////////////////////////////////////////////////////////////

	// "cropAreaID" Parameter or index number
	public static final ConstantConverter CHK_CROP_AREA_ENTIRE_IMAGE = new ConstantConverter(-1,
			"CHK_CROP_AREA_ENTIRE_IMAGE");
	public static final ConstantConverter CHK_CROP_AREA_RESET_ALL = new ConstantConverter(-2,
			"CHK_CROP_AREA_RESET_ALL");

	// "cx" Parameter or integer width
	public static final ConstantConverter CHK_CROP_AREA_RIGHT = new ConstantConverter(-1,
			"CHK_CROP_AREA_RIGHT");

	// "cy" Parameter or integer height
	public static final ConstantConverter CHK_CROP_AREA_BOTTOM = new ConstantConverter(-1,
			"CHK_CROP_AREA_BOTTOM");

	// ///////////////////////////////////////////////////////////////////
	// "retrieveMemory" Method Constants:
	// ///////////////////////////////////////////////////////////////////

	// "by" Parameter
	public static final ConstantConverter CHK_LOCATE_BY_FILEID = new ConstantConverter(1,
			"CHK_LOCATE_BY_FILEID");
	public static final ConstantConverter CHK_LOCATE_BY_FILEINDEX = new ConstantConverter(2,
			"CHK_LOCATE_BY_FILEINDEX");
	public static final ConstantConverter CHK_LOCATE_BY_IMAGETAGDATA = new ConstantConverter(3,
			"CHK_LOCATE_BY_IMAGETAGDATA");

	public static int getConstantNumberFromString(String constant) {

		if (CheckScannerConstantMapper.CHK_CL_16.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CL_16.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CL_256.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CL_256.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CL_FULL.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CL_FULL.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CL_GRAYSCALE.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CL_GRAYSCALE.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CL_MONO.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CL_MONO.getContantNumber();
		}
		
		if (CheckScannerConstantMapper.CHK_CCL_16.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CCL_16.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CCL_256.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CCL_256.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CCL_FULL.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CCL_FULL.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CCL_GRAYSCALE.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CCL_GRAYSCALE.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CCL_MONO.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CCL_MONO.getContantNumber();
		}
		
		if (CheckScannerConstantMapper.CHK_CLR_ALL.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CLR_ALL.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CLR_BY_FILEID.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CLR_BY_FILEID.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CLR_BY_FILEINDEX.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CLR_BY_FILEINDEX.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CLR_BY_IMAGETAGDATA.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CLR_BY_IMAGETAGDATA.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CROP_AREA_BOTTOM.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CROP_AREA_BOTTOM.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CROP_AREA_ENTIRE_IMAGE.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CROP_AREA_ENTIRE_IMAGE.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CROP_AREA_RESET_ALL.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CROP_AREA_RESET_ALL.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CROP_AREA_RIGHT.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CROP_AREA_RIGHT.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_IF_BMP.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_IF_BMP.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_IF_GIF.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_IF_GIF.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_IF_JPEG.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_IF_JPEG.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_IF_NATIVE.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_IF_NATIVE.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_IF_TIFF.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_IF_TIFF.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_IMS_EMPTY.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_IMS_EMPTY.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_IMS_FULL.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_IMS_FULL.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_IMS_OK.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_IMS_OK.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_LOCATE_BY_FILEID.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_LOCATE_BY_FILEID.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_LOCATE_BY_FILEINDEX.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_LOCATE_BY_FILEINDEX.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_LOCATE_BY_IMAGETAGDATA.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_LOCATE_BY_IMAGETAGDATA.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_MM_DOTS.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_MM_DOTS.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_MM_ENGLISH.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_MM_ENGLISH.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_MM_METRIC.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_MM_METRIC.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_MM_TWIPS.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_MM_TWIPS.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CIF_BMP.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CIF_BMP.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CIF_GIF.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CIF_GIF.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CIF_JPEG.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CIF_JPEG.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CIF_NATIVE.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CIF_NATIVE.getContantNumber();
		}

		if (CheckScannerConstantMapper.CHK_CIF_TIFF.getConstant().equals(constant)) {
			return CheckScannerConstantMapper.CHK_CIF_TIFF.getContantNumber();
		}

		return CommonConstantMapper.getConstantNumberFromString(constant);
	}
}
