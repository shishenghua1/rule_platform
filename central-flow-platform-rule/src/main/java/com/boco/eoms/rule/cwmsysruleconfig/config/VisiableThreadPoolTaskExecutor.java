package com.boco.eoms.rule.cwmsysruleconfig.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 查看线程信息配置
 * @author chenjianghe
 *
 */
public class VisiableThreadPoolTaskExecutor extends ThreadPoolTaskExecutor{
	
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);
	
	private void showThreadPoolInfo(String prefix){
		
		ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
		
		if(null == threadPoolExecutor) {
			return;
		}
		
		logger.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
				this.getThreadNamePrefix(),
				prefix,
				threadPoolExecutor.getTaskCount(),
				threadPoolExecutor.getCompletedTaskCount(),
				threadPoolExecutor.getActiveCount(),
				threadPoolExecutor.getQueue().size());
	}
}
