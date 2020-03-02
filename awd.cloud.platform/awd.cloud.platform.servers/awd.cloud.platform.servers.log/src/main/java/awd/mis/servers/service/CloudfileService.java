/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.CloudfileEntity;
import awd.mis.servers.utils.FastDFSFile;

public interface CloudfileService extends CrudService<CloudfileEntity, String> {

	String saveOrUpdate(CloudfileEntity fileEntity, FastDFSFile fastfile);

	String mkdir(String jsbh,String userid, String fdir, String dir);

	String uploadByte( FastDFSFile fastfile);

}
