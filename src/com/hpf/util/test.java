package com.hpf.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class test {

	public static void main(String[] args) {
	
		
		
	    String jsonStr = "[{'id':'11','parentId':'root','refObj':"
	    		+ "{'existType':'exist','deptType':'emp','treeNodeType':'dept'}}]";
        List<JSONObject> list = JSON.parseArray(jsonStr, JSONObject.class);
        for (JSONObject object : list) {
            System.out.println(object.getJSONObject("refObj").getString("id"));

	}
        
	}

}
