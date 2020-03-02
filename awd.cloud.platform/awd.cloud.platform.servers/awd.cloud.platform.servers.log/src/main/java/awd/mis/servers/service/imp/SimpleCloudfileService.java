/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http:www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.dao.CloudfileDao;
import awd.mis.servers.entity.CloudfileEntity;
import awd.mis.servers.service.CloudfileService;
import awd.mis.servers.utils.FastDFSClient;
import awd.mis.servers.utils.FastDFSFile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("cloudfileService")
public class SimpleCloudfileService extends GenericEntityService<CloudfileEntity, String> implements CloudfileService {

    @Autowired
    private CloudfileDao cloudfileDao;

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(CloudfileEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public CloudfileDao getDao() {
        return cloudfileDao;
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String saveOrUpdate(CloudfileEntity fileEntity, FastDFSFile fastfile) {

        String[] fileAbsolutePath = {};
        String path = "";
        try {
            fileAbsolutePath = FastDFSClient.upload(fastfile);  //upload to fastdfs
            if (fileAbsolutePath != null && fileAbsolutePath.length > 0) {
                path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
                fileEntity.setGroupname(fileAbsolutePath[0]);
                fileEntity.setRemotefilename(fileAbsolutePath[1]);
                fileEntity.setFilename(path);
                this.saveOrUpdate(fileEntity);
            } else {
                logger.error("upload file failed,please upload again!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;

    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int deleteByPk(String pk) {
        CloudfileEntity file = selectByPk(pk);
        if ("1".equals(file.getIsdir())) {
//			判断目录下是否有文件
            QueryParamEntity param = new QueryParamEntity();
            param.and("dir", TermType.eq, file.getDir())
                    .and("isdir", TermType.eq, "0")
                    .and("scbz", TermType.eq, "0")
                    .and("jsbh", TermType.eq, file.getJsbh());
            List<CloudfileEntity> files = select(param);
            if (files != null && files.size() > 0) {
                return 0;
            } else {
                return super.deleteByPk(pk);
            }
        } else {
            try {
                FastDFSClient.deleteFile(file.getGroupname(), file.getRemotefilename());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.deleteByPk(pk);
        }

    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String getSEQUID(String jsbh) {
        return super.getSEQUID(jsbh);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String insert(CloudfileEntity entity) {
        entity.setCreatetime(Calendar.getInstance().getTime());
        return super.insert(entity);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String saveOrUpdate(CloudfileEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<CloudfileEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public CloudfileEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<CloudfileEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected int updateByPk(CloudfileEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(List<CloudfileEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(String pk, CloudfileEntity entity) {
        entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk, entity);
    }

    @Override
    public String mkdir(String jsbh, String userid, String fdir, String dir) {
        QueryParamEntity param = new QueryParamEntity();
        if (StringUtils.isNullOrEmpty(fdir)) {
            param.and("dir", TermType.eq, dir);
        } else {
            param.and("dir", TermType.eq, fdir + "/" + dir);
        }
        param.and("jsbh", TermType.eq, jsbh)
                .and("creator", TermType.eq, userid);
        List<CloudfileEntity> list = select(param);
        if (list != null && list.size() > 0) {
            return "已经存在该目录";
        } else {
            CloudfileEntity entity = new CloudfileEntity();
            entity.setJsbh(jsbh);
            if (StringUtils.isNullOrEmpty(fdir)) {
                fdir = "";
                entity.setFdir(fdir);
                entity.setDir(dir);
            } else {
                entity.setFdir(fdir);
                entity.setDir(fdir + "/" + dir.substring(dir.lastIndexOf("/") + 1, dir.length()));
            }
            entity.setFileicon("dir.png");
            entity.setFilename(dir.substring(dir.lastIndexOf("/") + 1, dir.length()));
            entity.setScbz("0");
            entity.setShare("0");
            entity.setIsdir("1");
            entity.setCreator(userid);
            entity.setCreatetime(Calendar.getInstance().getTime());
            entity.setUpdator(dir.substring(dir.lastIndexOf("/") + 1, dir.length()));
            return super.saveOrUpdate(entity);
        }

    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String uploadByte(FastDFSFile fastfile) {

        String[] fileAbsolutePath = {};
        String path = "";
        try {
            fileAbsolutePath = FastDFSClient.upload(fastfile);  //upload to fastdfs
            if (fileAbsolutePath != null && fileAbsolutePath.length > 0) {
                path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
            } else {
                logger.error("upload file failed,please upload again!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;

    }


}
