package com.hpf.util;

import java.io.File;

public class DeleteFile {
	
	public static boolean deleteFile(String PathAndfileName){
		   File file = new File(PathAndfileName);
		   if (file.isFile() && file.exists()) {
		  file.delete();//"删除单个文件"+name+"成功！"
		  return true;
		   }//"删除单个文件"+name+"失败！"
		   return false;
		  }

}
