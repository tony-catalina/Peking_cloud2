/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.DocumentfileEntity;
import awd.mis.servers.utils.FastDFSFile;

public interface DocumentfileService extends CrudService<DocumentfileEntity, String> {

    String saveOrUpdate(DocumentfileEntity documentfileEntity, FastDFSFile fastDFSFile);
}
