package com.cn.common.util;

/**
 * 常量类
 * @name Constants.java
 * @author Frank
 * @time 2013-9-24下午8:57:14
 * @version 1.0
 */
public class Constants {
	
	//普通用户
	public final static String ROLE_CODE_NORMAL = "normal";
	//管理员
	public final static String ROLE_CODE_ADMIN = "admin";
	
	//数据状态：有效
	public final static int STATUS_NORMAL = 1;
	//数据状态：无效
	public final static int STATUS_DEL = 0;
	
	/**
	 * 当前用户角色对应的资源列表
	 */
	public final static String SESSION_RESOURCE_LIST = "resource_list";
	
	/**
	 * 当前用户角色对应的资源MAP（拦截器用）
	 */
	public final static String SESSION_RESOURCE_MAP = "resource_map";
	
	/**
	 * 验证码
	 */
	public final static String SESSION_RANDOM = "random";
	
	/**
	 * 是否登录
	 */
	public final static String SESSION_ISLOGIN = "is_login";
	
	/**
	 * 已登录
	 */
	public final static String SESSION_FLAG_IS_LOGIN = "1";
	
	/**
	 * 未登录
	 */
	public final static String SESSION_FLAG_NOT_LOGIN = "0";
	
	/**
	 * 登录ID
	 */
	public final static String SESSION_USER_ID = "user_id";
	
	/**
	 * 用户所属
	 */
	public final static String SESSION_BELONGTO = "belongto";

	/**
	 * 用户名
	 */
	public final static String SESSION_USER_NAME = "user_name";
	
	/**
	 * 登录时间
	 */
	public final static String SESSION_LOGIN_TIME = "login_time";
}
