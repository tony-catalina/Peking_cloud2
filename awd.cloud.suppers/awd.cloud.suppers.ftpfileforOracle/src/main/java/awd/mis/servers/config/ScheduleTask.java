package awd.mis.servers.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import awd.mis.servers.service.OracleService;

@Component
public class ScheduleTask {
	@Autowired
	private OracleService oracleService;
	
    @Scheduled(cron = "0 0/1 * * * ?")//没分钟执行一次
    private void configureTasks() {
    	oracleService.readFtpFileToEntity();
        System.err.println("这是定时任务，执行 readFtpFileToEntity() 方法 ，目前时间: " + LocalDateTime.now());
    }
}