package awd.mis.servers.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import awd.mis.servers.service.MysqlService;
import awd.mis.servers.service.PublicService;

@Component
public class ScheduleTask {
	@Autowired
	private MysqlService mysqlService;
	@Autowired
	private PublicService publicService;
	
    @Scheduled(cron = "0 0/1 * * * ?")
    private void configureTasks() {
    	mysqlService.readFtpFileToEntity();
    	publicService.readFtpFileToEntity();
        System.err.println("这是定时任务，执行 readFtpFileToEntity() 方法 ，目前时间: " + LocalDateTime.now());
    }
}