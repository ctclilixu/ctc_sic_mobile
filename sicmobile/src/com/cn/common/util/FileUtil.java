package com.cn.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @name FileUtil.java
 * @author Frank
 * @time 2014-12-22上午12:50:29
 * @version 1.0
 */
public class FileUtil {

	private static final Logger log = LogManager.getLogger(FileUtil.class);
	
	/**
	 * 文件拷贝
	 * @param oldFile
	 * @param targetFile
	 * @throws Exception 
	 */
	public static void copyFile(String oldPath, String targetPath, String filename) {
		try {
			File oldFile = new File(oldPath);
			if(!oldFile.exists()) {
				log.warn("oldPath=[" + oldPath + "] is not exist.");
				return;
			}
			File targetdir = new File(targetPath);
			//判断目标目录是否存在
			if(!targetdir.exists()) {
				targetdir.mkdir();
			}
			int byteread = 0;
			InputStream inStream = new FileInputStream(oldPath);
			FileOutputStream fs = new FileOutputStream(targetPath + filename);
			byte[] buffer = new byte[1024];
			while ((byteread = inStream.read(buffer)) != -1) { 
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
		} catch(Exception e) {
			log.info("copyFile error:" + e);
		}
	}
	
	/**
	 * 删除文件
	 * @param filename
	 * @param path
	 */
	public static void deleteFile(String filename, String path) {
		File file = new File(path + filename);
		if(file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * 上传文件到指定目录，返回新的文件名
	 * @param file 文件对象
	 * @param path 文件保存路径
	 * @param name 文件名
	 * @return
	 */
	public static String uploadFile(File file, String path, String name) {
		String filename = "";
		if(file != null) {
			File pathFile = new File(path);
			//判断目录是否存在，不存在则创建目录
			if(!pathFile.exists()) {
				pathFile.mkdir();
			}
			//新的文件名
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String d = sdf.format(date);
			String uuid = UUID.randomUUID().toString();
			uuid = uuid.substring(uuid.length() - 6, uuid.length());
			filename = d + uuid + name.substring(name.lastIndexOf("."), name.length());
			log.info(file.getName());
			
			//将文件保存到指定路径下
			InputStream is = null;
			OutputStream out = null;
			try {
				// 获取上传的图片
				is = new BufferedInputStream(new FileInputStream(file), 1024);
				// 指明上传的路径
				out = new BufferedOutputStream(
						new FileOutputStream(path + filename), 1024);
				byte[] bu = new byte[1024];
				while (is.read(bu) > 0) {
					out.write(bu);
				}
				out.close();
				is.close();
			} catch (FileNotFoundException e) {
				log.info("uploadFile error:" + e);
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filename;
	}
	
    /** 
     *  根据路径删除指定的目录或文件，无论存在与否 
     *@param sPath  要删除的目录或文件 
     *@return 删除成功返回 true，否则返回 false。 
     */  
    public static boolean DeleteFolder(String sPath) {  
        boolean flag = false;  
        File file = new File(sPath);  
        // 判断目录或文件是否存在  
        if (!file.exists()) {  // 不存在返回 false  
            return flag;  
        } else {  
            // 判断是否为文件  
            if (file.isFile()) {  // 为文件时调用删除文件方法  
                return deleteFile(sPath);  
            } else {  // 为目录时调用删除目录方法  
                return deleteDirectory(sPath);  
            }  
        }  
    }  
	
    /** 
     * 删除单个文件 
     * @param   sPath    被删除文件的文件名 
     * @return 单个文件删除成功返回true，否则返回false 
     */  
    public static boolean deleteFile(String sPath) {  
        boolean flag = false;  
        File file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }  
	
    /** 
     * 删除目录（文件夹）以及目录下的文件 
     * @param   sPath 被删除目录的文件路径 
     * @return  目录删除成功返回true，否则返回false 
     */  
    public static boolean deleteDirectory(String sPath) {  
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
        if (!sPath.endsWith(File.separator)) {  
            sPath = sPath + File.separator;  
        }  
        File dirFile = new File(sPath);  
        //如果dir对应的文件不存在，或者不是一个目录，则退出  
        if (!dirFile.exists() || !dirFile.isDirectory()) {  
            return false;  
        }  
        boolean flag = true;  
        //删除文件夹下的所有文件(包括子目录)  
        File[] files = dirFile.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            //删除子文件  
            if (files[i].isFile()) {  
                flag = deleteFile(files[i].getAbsolutePath());  
                if (!flag) break;  
            } //删除子目录  
            else {  
                flag = deleteDirectory(files[i].getAbsolutePath());  
                if (!flag) break;  
            }  
        }  
        if (!flag) return false;  
        //删除当前目录  
        if (dirFile.delete()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
    
    /** 
     * 解压文件
     * @param   inputGzipFilePath 输入压缩文件路径
     * @param   outputFilePath 输出解压后的文件路径
     * @return  目录删除成功返回true，否则返回false 
     */ 
    public static boolean decompressFile(String inputGzipFilePath,String outputFilePath){
        byte[] buffer = new byte[1024];
        try {
			GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(inputGzipFilePath));
	        FileOutputStream out = new FileOutputStream(outputFilePath);
	        int len;
	        while ((len = gzis.read(buffer)) > 0) {
	        	out.write(buffer, 0, len);
	        }
	        gzis.close();
	    	out.close();
	    	log.info("解压"+inputGzipFilePath+"到"+outputFilePath+",OK!");
	    	return true;
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			return false;
		} catch (IOException e) {
			log.error(e.getMessage());
			return false;
		}
    }
	
	public static void main(String[] args) {
		String name = "aaaa.jpg";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String d = sdf.format(date);
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 6, uuid.length());
		String filename = d + uuid + name.substring(name.lastIndexOf("."), name.length());
		System.out.println(filename);
	}
}
