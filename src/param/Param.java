package param;

public class Param {
	
	//#text file, byte order mark
	public final static byte [] BOM = {(byte)0xEF,(byte)0xBB,(byte)0xBF};
	
	public final static String SETTING_FILE_NAME = "sysProp.propertis";
	public final static String [][] IMAGE_KEYS= new String[][]{{"x","y","width","heigh"},{"1","1","200","200"}};
	public final static String TARGET_PAHT = "targetPath";
	public final static String SAVE_PAHT = "savePath";
}
