package com.hpf.test;

import java.io.File;
import java.io.IOException;

public class test3 {
	
	 /**   
	   * 删除单个文件   
	   * @param   fileName    被删除文件的文件名   
	   * @return 单个文件删除成功返回true,否则返回false   
	   */   
	  public static boolean deleteFile(String fileName){
	   File file = new File(fileName);
	   if (file.isFile() && file.exists()) {
	  file.delete();//"删除单个文件"+name+"成功！"
	  return true;
	   }//"删除单个文件"+name+"失败！"
	   return false;
	  }
	  
	  public static void main(String[] args) {
		  deleteFile("E://text.txt");
		
	}
	  

}
