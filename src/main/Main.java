package main;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import core.SpriteImage;
import core.Util;
import param.Param;

public class Main {
	static Logger logger = Logger.getLogger(Main.class);
	
	private static void init(){
		Setting.loadSettingFile();
	}

	public static void main(String args[]){
		init();
		
		String property =  System.getProperty("user.dir") + "\\" + Param.SETTING_FILE_NAME;
				
		String [] keys = Param.IMAGE_KEYS[0];
		int [] iset = Util.getPropertyArray(property, keys );
		logger.info( "x : " + iset[0] );
		logger.info( "y : " + iset[1] );
		logger.info( "width : " + iset[2] );
		logger.info( "height : " + iset[3] );
		
		String targetPath = Util.getProperty( property, Param.TARGET_PAHT );
		String savePath = Util.getProperty( property, Param.SAVE_PAHT );
		logger.info("target path : " + targetPath);
		logger.info("save path : " + savePath);
		
		SpriteImage si = new SpriteImage( iset[0], iset[1], iset[2], iset[3]);
				
		ArrayList<String[]> fileIntoList = Util.searchFiles( targetPath , "jpg");
		
		String now = Util.getNow();
		
		for( int i= 0, j = fileIntoList.size() ; i < j ; i++ ){
			String[] fileInfo = fileIntoList.get(i);
			
			si.makeImage(fileInfo[0], fileInfo[1], savePath, "/" + now + "_" + i + ".jpg", "jpg");
		}
		
	}//end main
	
	
	
	
	
	
	

	
	
}
