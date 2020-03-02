/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/ESearch")
@AccessLogger("高级搜索")
@Api(tags = "awd-elasticsearch", description = "提供高级搜索功能")
public class ElasticSearchController {

	private static Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);	
	

}
