/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

public interface WgsjclDao extends Dao {

//	<!-- 违规事件分析 -->
	List<Map<String,Object>> wgqkfxCount();

    LinkedList<Map<String,Object>> getBzwgrs(@Param("jsbh") String jsbh,@Param("jsh") String jsh,@Param("bzkssj") String bzkssj,@Param("bzjssj") String bzjssj);

    LinkedList<Map<String, Object>> getBzjswgsj(@Param("jsbh") String jsbh,@Param("jsh") String jsh,@Param("bzkssj") String bzkssj,@Param("bzjssj") String bzjssj);
}
