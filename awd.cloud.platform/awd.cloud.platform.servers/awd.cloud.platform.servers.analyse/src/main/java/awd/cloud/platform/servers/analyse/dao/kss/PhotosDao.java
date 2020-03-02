/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

public interface PhotosDao extends Dao {

    //根据人员编号和监所编号获取照片
    public String getRyPhoto(@Param(value = "rybh")String rybh,@Param(value = "jsbh")String jsbh);

}
