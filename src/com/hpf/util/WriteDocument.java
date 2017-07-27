package com.hpf.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteDocument {
	
	public static void writeByBufferedReader(String savefilepath, String doccontent) {  
        try {  
        	//内容
            String content = doccontent;  
            
            //创建文件
            File file = new File(savefilepath);  
            if (!file.exists()) {  
                file.createNewFile();  
            }  
            
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
             
            BufferedWriter bw = new BufferedWriter(write);  
            bw.write(content);  
            bw.flush();  
            bw.close();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } 
}
