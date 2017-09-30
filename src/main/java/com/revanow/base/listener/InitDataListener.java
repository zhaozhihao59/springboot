package com.revanow.base.listener;

import java.util.TimeZone;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.revanow.base.constant.SystemConstant;
import com.revanow.base.util.Utils;

/**
 * 系统初始化操作
 * @creator     zhangqiang
 * @create-time Jun 21, 2012   4:44:43 PM
 * @version 0.1
 */
@Component
public class InitDataListener implements ApplicationListener<ContextRefreshedEvent> {

	private Log log = LogFactory.getLog(InitDataListener.class);
	
	@Autowired(required = false)
	private ServletContext servletContent;
	
	//上下文路径
	@Value("${context.path}")
	private String ctxPath;
	//系统版本
	@Value("${dev.version}")
	private String sysVersion;
	//开发模式
	@Value("${dev.mode}")
	private String devMode;
	@Value("${project.name}")
	private String projectName;
	@Value("${reduce.url}")
	private String reduceUrl;
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		log.info("====== 系统数据初始化开始======");
		
		Utils.hostUrl = reduceUrl;
		Utils.init();
		//初始化时间区域
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		if (servletContent != null) {
			// 初始化网站基本信息
			servletContent.setAttribute(SystemConstant.SYS_VERSION, sysVersion);
			servletContent.setAttribute(SystemConstant.DEV_MODE, devMode);
			servletContent.setAttribute(SystemConstant.CONTEXT_PATH, ctxPath);
			servletContent.setAttribute(SystemConstant.PROJECT_NAME, projectName);
		}
		
		log.info("====== 系统数据初始化完毕 ======");
	}

}
