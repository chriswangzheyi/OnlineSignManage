package com.hpf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class test {
	
	public static void main(String[] args) {
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("Hello", "World");
		m.put("Apple", "3.14");
		m.put("Another", "Element");
		

		List<String> list = new ArrayList<String>(m.values());

		System.out.println(list);
		
	

}

}