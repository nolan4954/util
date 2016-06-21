package com.best.web.htyt.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件管理工具类
 * @author bl00064
 *
 */
public class FileManageUtil {
	/**
	 * 删除目录下的所有文件
	 */	
	public static void cleanFileDir(String fileDir){
		File dir = new File(fileDir);
		if (!dir.exists()){				
			dir.mkdir();
		}				
		// 删除目录下的文件 
		//如果fileDir不以文件分隔符结尾，自动添加文件分隔符     
        if(!fileDir.endsWith(File.separator)){     
        	fileDir = fileDir+File.separator;     
        }  
        File dirFile = new File(fileDir);   
        //如果dir对应的文件不存在，或者不是一个目录，则退出     
        if(dirFile.exists() && dirFile.isDirectory()){     
        	 //删除文件夹下的所有文件(包括子目录)     
            File[] files = dirFile.listFiles(); 
            for(int i=0;i<files.length;i++){  
            	//删除子文件     
                if(files[i].isFile() &&  files[i].exists()){    
                	files[i].delete();     
                }
            }
        }
	}
	/**
     * 删除指定文件
     */ 
    public static void cleanFile(File file){
       if(file.exists()){
           file.delete();
       }
    }
	/**
	 * 删除不包含文件的目录下
	 */
	public static void deleteEmptyDirectory(String fileDir) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!fileDir.endsWith(File.separator)) {  
	    	fileDir = fileDir + File.separator;  
	    }  
	    File dirFile = new File(fileDir);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if(dirFile.exists() && dirFile.isDirectory()){ 
	    	  //删除文件夹下的所有文件(包括子目录)  
		    File[] files = dirFile.listFiles();  
		    if( files.length == 0){
		    	 //删除当前目录  
		    	dirFile.delete();
		    }
	    }  
	  
	} 
	/**
	 * 生成一个随机的文件名
	 * @param prefix 文件名前缀
	 * @return
	 */
	public static String getFileName(String prefix){
		String fileName = "";
		DateFormat ymdhmsFormat = new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		String now = ymdhmsFormat.format(today);
		fileName = prefix + "_" + now.toString() + "_" + today.getTime() + ".xls";
		return fileName;
	}
	/**
	 * 保存导入的文件到磁盘
	 * @param fileBytes 上传文件的字节
	 * @param fileName 保存文件的名字
	 * @return 返回保存的文件的File对象
	 */
	public static File saveImportedFile(byte[] fileBytes,String fileName) {
		 
		try{
			String USER_DIRECTORY=System.getProperty("user.dir");//返回用户当前所在的目录   
			String  fileDir  =  USER_DIRECTORY+File.separator + "import";
			File dir = new File(fileDir);
			if (!dir.exists()){				
				dir.mkdir();
			}				
			 fileName = dir.getPath() + File.separator + fileName;
		 
			File importFile = new File(fileName);
			if (importFile.exists()){
				importFile.delete();
			}
			//将该文件保存到磁盘目录
			FileOutputStream fileOutStream = new FileOutputStream(importFile);
			fileOutStream.write(fileBytes);
			fileOutStream.close();
			
			return importFile;
		}catch(IOException e){
			return null;
		}
	}
	/**
	 * 保存导入的文件到磁盘, 文件名字随机生成，前缀为"upload_"，保存目录为"user.dir"/import
	 * @param fileBytes 上传文件的字节
	 * @return 返回保存的文件的File对象
	 */
	public static File saveImportedFile(byte[] fileBytes) {
		String fileName = getFileName("upload_");
		return saveImportedFile(fileBytes, fileName);
	}
	/**
	 * 创建一个目录
	 * @param fileName
	 * @return
	 */
	public static boolean makeDirectory(String fileName) {
		File file = new File(fileName);
		if (!file.exists()){				
			file.mkdir();
		}	
		return makeDirectory(file);
	}
	/**
	 * 创建一个目录
	 * @param file
	 * @return
	 */
	public static boolean makeDirectory(File file) {
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
		return parent.mkdirs();
		}
		return false;
	}
	/**
	 * 删除一个文件
	 * @param filePath 文件全路径
	 */
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()){				
			file.delete();
		}	
	}
}
