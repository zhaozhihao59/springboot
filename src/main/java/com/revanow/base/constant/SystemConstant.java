package com.revanow.base.constant;


/**
 * 系统常量
 * @author zhangqiang
 *
 */
public interface SystemConstant {

	/** 后台管理系统admin账号 */
	String ADMIN_USER = "admin";
	/** admin用户的ID值 */
	Long ADMIN_ID = 1L;
	/** 系统版本号 */
	String SYS_VERSION = "sysVersion";
	/** 系统配置ID值 */
	Long APP_CONFIG_ID = 1L;
	/** 主管权限ID */
	Long MANAGER_ID = 18L;
	/** 上下文路径 */
	String CONTEXT_PATH = "ctxPath";
	/** 开发模式 */
	String DEV_MODE = "devMode";
	/** 默认处理数据的每页条数 */
	Integer DEFAULT_PROCESS_DATA_PAGE_SIZE = 500;
	/** 查询角色前5条用户信息 */
	Integer SEARCH_ROLE_USER_COUNT = 5;
	/** 默认密码 */
	String DEFAULT_PASSWORD = "abc123";
	/** 为空的字符串默认值 */
	String DEFAULT_EMPTY_STR = "-";
	/** 逗号 */
	String COMMA_STR = ",";
	/** 为空的日期默认值 */
	String DEFAULT_EMPTY_DATE = "1900.01.01";
	/** 为空的整数默认值 */
	Long DEFAULT_EMPTY_LONG = 0L;
	/** 为空的double默认值 */
	Double DEFAULT_EMPTY_DOUBLE = 0d;
	/** 小数点保留3位 */
	Integer SCALE_3 = 3;
	/** 数据清洗角色名称 */
	String CLEAN_DATA_ROLE_NAME = "数据清洗员";
	/** 项目名称*/
	String PROJECT_NAME = "projectName";
	
}
