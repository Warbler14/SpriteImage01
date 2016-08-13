package core;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
public class SpriteImage {
	static Logger logger = Logger.getLogger(SpriteImage.class);
	
    int x,y,width,height;
    BufferedImage bigImg;
    
    public SpriteImage(int x,int y,int width,int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public BufferedImage getSpliteImage( String fileName ){
    	BufferedImage splitImage = null;
    	
        try {
        	
        	File file = new File( fileName );
        	
        	if( file.exists() ){
        		splitImage = new BufferedImage(x,y,BufferedImage.TYPE_INT_ARGB);

                bigImg = ImageIO.read( file );
                
                splitImage = bigImg.getSubimage( x, y, width, height);
                
        	}
        	            
        } catch (Exception e) {
            e.printStackTrace();
            splitImage = null;
        }
        
        return splitImage;
    }
    
    
    public void makeImage(String targetPath , String targetfileName, String savePath , String saveName , String imageExt ){
    	
		BufferedImage splitImage = this.getSpliteImage( targetPath + "/" + targetfileName); //"C:/TEMP/test/test.jpg"
		
		if( splitImage != null ){
			
			try{
				
				String fileName = savePath + saveName;					
				Util.mkDir(savePath);				
				File file = new File( fileName );  
				ImageIO.write(splitImage, imageExt, file);  
				
				printResult( file.exists(), " " + fileName );
				
			}catch(Exception e){
				logger.info(">> " + e);
			}
			
			
		}else{
			printResult( false, "" );
		}
		
    }
    
    public static void printResult( boolean check, String message ){
		
		if(check){
			logger.info(  message + " create OK");
		}else{
			logger.info(  message + " create FAIL");
		}
	}
 
}