package com.hpf.ExcelUtil;


/**
 * 
 *
 * @createTime: 2014-8-25 上午10:01:07
 * @author: <a href="mailto:xiaokunpeng014@haopinfeng.com">肖坤鹏</a>
 * @version: 0.1
 * @changesSum: 
 * 
 */
public class ExcelException extends RuntimeException {
	
	/** Comment for <code>serialVersionUID</code> */
	private static final long	serialVersionUID	= -1325769410348659900L;
	
	public ExcelException() {
		super();
	}
	
	public ExcelException(String message) {
		super(message);
	}
	
	public ExcelException(String message, Throwable cause) {
		super(message, cause);
	}
}