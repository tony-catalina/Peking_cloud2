/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

public interface BjjlDao  extends Dao{

    //流水牌>重点人员>帮教人员
    int getBjjlCount(@Param(value = "jsbh")String jsbh);

}
