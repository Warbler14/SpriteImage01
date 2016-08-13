package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Util {
	static Logger logger = Logger.getLogger(Util.class);
	
	private static Properties getPropertyObject( String propFile ){
		Properties properties = new Properties();
		
		try {
            properties.load(new FileInputStream(propFile));	//file : "project.properties" 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            properties = null;
        } catch (IOException e) {
            e.printStackTrace();
            properties = null;
        }
		
		return properties;
	}
	
	public static String getProperty(String propFile, String key){
		return getPropertyObject(propFile).getProperty(key);
	}
	
	public static HashMap<String, String> getProperty(String propFile, String [] keys){
		HashMap<String, String> resultMap = null;
		
		if( keys.length > 0 ){
			resultMap = new HashMap<String, String>();			
			Properties properties = getPropertyObject(propFile);
			
			for(int i=0, j=keys.length ; i<j ; i++ ){
				resultMap.put( keys[i], properties.getProperty(keys[i]) );
			}
			
		}
		
		return resultMap;
	}
	
	public static int [] getPropertyArray(String propFile, String [] keys ){
		int [] resultArray = null;
		
		if( keys.length > 0 ){
			try{
				resultArray = new int[keys.length];			
				Properties properties = getPropertyObject(propFile);
				
				for(int i=0, j=keys.length ; i<j ; i++ ){
					resultArray[i] = Integer.parseInt( properties.getProperty(keys[i]) );
				}
				
			}catch( NumberFormatException e ){
				logger.error( e );
				resultArray = null;
			}
		}
		
		return resultArray;
	}
	
	public static void mkDir(String dirPath){
		File dir = new File(dirPath);		
		if(!dir.exists()) dir.mkdirs();
	}
	
	public static ArrayList<String[]> searchFiles( String path , String fileExt){
		
		ArrayList<String[]> reslutList = null;
		
		File dirFile = new File(path);
		File []fileList = dirFile.listFiles();
		
		if( fileList != null ){
			reslutList = new ArrayList<String[]>();

			for(File tempFile : fileList) {
				if(tempFile.isFile()) {
					String tempPath = tempFile.getParent();
					String tempFileName = tempFile.getName();
					String tempFileNameExt = tempFileName.substring(tempFileName.lastIndexOf(".")+1, tempFileName.length() );
					
					if( fileExt.equals(  tempFileNameExt )){
						logger.debug("Path="+tempPath);
						logger.debug("FileName="+tempFileName);
						logger.debug("tempFileNameExt="+tempFileNameExt);
						
						reslutList.add( new String[]{ tempPath, tempFileName } );
					}
					
				}
			}//end for
		}
		
		return reslutList;
	}
	
	public static String getNow(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date d = new Date();		
		return format.format(d);		
	}
}
