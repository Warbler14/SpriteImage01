package core;

import org.apache.log4j.*;

import param.Param;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;



public class FileWriter {
	
	static Logger logger = Logger.getLogger(FileReader.class);
	
	public FileWriter(){}
	
	public boolean write( String content , String fileDirs , String fileName ){
		boolean status = false;
		
		FileOutputStream out = null;
		BufferedInputStream bis = null;
		long datasize = 0;
		
		File fileDir = new File( fileDirs );
		
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		
		File file = new File( fileDirs + "\\" +  fileName );
		
		try{
			
			
			out = new FileOutputStream( file );
			bis = new BufferedInputStream( new ByteArrayInputStream( content.getBytes("UTF-8")) );
			
			byte[] buffer = new byte[1024];
			int read = 0 ;
			
			out.write(Param.BOM);
			
			while( (read = bis.read(buffer)) > 0 ){
				out.write(buffer, 0, read);
				datasize += read;
			}
			
			status = true;
			
		}catch(Exception e){
			logger.error( e.getMessage() );
		}finally {
			
			logger.info( "file_size : " + datasize + " bytes" );
			
			try{
				if( bis != null ) bis.close();
				if( out != null ) out.close();
			}catch( Exception e){
				logger.error( e.getMessage() );
			}
			
		}
		
		return status;
	}
	
}
