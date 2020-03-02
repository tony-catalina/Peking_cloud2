package awd.cloud.platform.tasks.controller;

import java.util.List;

import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import awd.cloud.platform.tasks.model.Message;
import awd.cloud.platform.tasks.quartz.job.ScheduleJob;
import awd.cloud.platform.tasks.quartz.service.ScheduleJobService;
import awd.framework.common.utils.OpenAPI;


@RestController
@RequestMapping("/api")
public class RestfulController {

	private static Logger logger = LoggerFactory.getLogger(RestfulController.class);

	@Autowired 
	private ScheduleJobService scheduleJobService;
	
	@GetMapping("/metaData")
	public Object metaData() throws SchedulerException{
		SchedulerMetaData metaData = scheduleJobService.getMetaData();
		return metaData;
	}
	
	@GetMapping("/getAllJobs")
	public Object getAllJobs() throws SchedulerException{
		List<ScheduleJob> jobList = scheduleJobService.getAllJobList(); 
		return jobList;
	}
	
	@GetMapping("/getRunningJobs")
	public Object getRunningJobs() throws SchedulerException{
		List<ScheduleJob> jobList = scheduleJobService.getRunningJobList();
		return jobList;
	}
	
	@RequestMapping(value = "/pauseJob", method = { RequestMethod.POST})
	public Object pauseJob(ScheduleJob job){
		logger.info("params, job = {}", job);
		Message message = Message.failure();
		try {
			scheduleJobService.pauseJob(job);
			message = Message.success();
		} catch (Exception e) {
			message.setMsg(e.getMessage());
			logger.error("pauseJob ex:", e);
		}
		return message;
	}
	
	@RequestMapping(value = "/resumeJob", method = { RequestMethod.POST})
	public Object resumeJob(ScheduleJob job){
		logger.info("params, job = {}", job);
		Message message = Message.failure();
		try {
			scheduleJobService.resumeJob(job);
			message = Message.success();
		} catch (Exception e) {
			message.setMsg(e.getMessage());
			logger.error("resumeJob ex:", e);
		}
		return message;
	}
	
	
	@RequestMapping(value = "/deleteJob", method = { RequestMethod.POST})
	public Object deleteJob(ScheduleJob job){
		logger.info("params, job = {}", job);
		Message message = Message.failure();
		try {
			scheduleJobService.deleteJob(job);
			message = Message.success();
		} catch (Exception e) {
			message.setMsg(e.getMessage());
			logger.error("deleteJob ex:", e);
		}
		return message;
	}
	
	@RequestMapping(value = "/runJob", method = { RequestMethod.POST})
	public Object runJob(ScheduleJob job){
		logger.info("params, job = {}", job);
		Message message = Message.failure();
		try {
			scheduleJobService.runJobOnce(job);
			message = Message.success();
		} catch (Exception e) {
			message.setMsg(e.getMessage());
			logger.error("runJob ex:", e);
		}
		return message;
	}
	
	
	@RequestMapping(value = "/saveOrUpdate", method = { RequestMethod.POST})
	public Object saveOrupdate(ScheduleJob job){
		logger.info("params, job = {}", job);
		Message message = Message.failure();
		try {
			scheduleJobService.saveOrupdate(job);
			message = Message.success();
		} catch (Exception e) {
			message.setMsg(e.getMessage());
			logger.error("updateCron ex:", e);
		}
		return message;
	}
	

}
