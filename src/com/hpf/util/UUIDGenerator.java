package com.hpf.util;

import java.util.UUID;

public class UUIDGenerator {


	public static String UUIDGenerator(){	
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
		
	
}
