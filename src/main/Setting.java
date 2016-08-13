package main;

import java.io.File;

import org.apache.log4j.Logger;

import core.FileWriter;
import param.Param;

public class Setting {
	static Logger logger = Logger.getLogger(Setting.class);

	public static void loadSettingFile(){
		
		String rootPath = System.getProperty("user.dir");
		logger.info(rootPath);

		String filePath = System.getProperty("user.dir");

		try{
			
			File file = new File( filePath + "\\" + Param.SETTING_FILE_NAME );
			if( !file.exists() ){
				
				//default setting file writeS
				logger.info("Create default setting file, path : " + filePath );
				
				FileWriter writer = new FileWriter();
				
				writer.write( buildDefaultSettingFileContent( System.getProperty("user.dir")  ) 
						    , filePath
						    , "\\" + Param.SETTING_FILE_NAME );
			}
			
		}catch(Exception e){ 
			logger.error( e.getMessage() );
		}
				
	}
	
	private static String buildDefaultSettingFileContent( String  path ){
		String settings = "# SpriteImage setting property\r\n";
		String path0 = System.getProperty("user.dir").toString().replaceAll("\\\\", "/");
						
		settings += Param.TARGET_PAHT + "=" + path0 + "\r\n";
		settings += Param.SAVE_PAHT + "=" + path0 + "\r\n";
				
		settings += Param.IMAGE_KEYS[0][0] + "=" + Param.IMAGE_KEYS[1][0] + "\r\n";
		settings += Param.IMAGE_KEYS[0][1] + "=" + Param.IMAGE_KEYS[1][1] + "\r\n";
		settings += Param.IMAGE_KEYS[0][2] + "=" + Param.IMAGE_KEYS[1][2] + "\r\n";
		settings += Param.IMAGE_KEYS[0][3] + "=" + Param.IMAGE_KEYS[1][3] ;
		
		return settings;
	}
	
}
