package awd.cloud.suppers.interfaces.service.interfaces.imp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.cloud.suppers.interfaces.api.ManagerApi;
import awd.cloud.suppers.interfaces.dao.UserinfoDao;
import awd.cloud.suppers.interfaces.entity.UserinfoEntity;
import awd.cloud.suppers.interfaces.service.interfaces.UserinfoService;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import awd.cloud.suppers.interfaces.utils.StringUtils;
import awd.cloud.suppers.interfaces.utils.TermType;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
@Service("userinfoService")
public class SimpleUserinfoService extends GenericEntityService<UserinfoEntity,String>implements UserinfoService{

        @Autowired
        private UserinfoDao userinfoDao;

        @Autowired
        private ManagerApi managerApi;
        
        @Override
        protected IDGenerator<String> getIDGenerator(UserinfoEntity entity){
                String jsbh="999999999";
                return()->{
                return getSEQUID(jsbh);
            };
        }
        
        @Override
        public UserinfoDao getDao(){
                return userinfoDao;
        }
        @Override
        @UseDataSource("write_ds")
        public int deleteByPk(String pk){
            return super.deleteByPk(pk);
        }

        @Override
        @UseDataSource("write_ds")
        public String getSEQUID(String jsbh){
        	return super.getSEQUID(jsbh);
        }

        @Override
        @UseDataSource("write_ds")
        public String insert(UserinfoEntity entity){
        	return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(UserinfoEntity entity){
        	return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<UserinfoEntity> selectByPk(List<String> id){
        	return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public UserinfoEntity selectByPk(String pk){
        	return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<UserinfoEntity> selectPager(Entity arg0){
        	return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(UserinfoEntity entity){
        	return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<UserinfoEntity> data){
        	return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,UserinfoEntity entity){
        	return super.updateByPk(pk,entity);
        }

		@Override
		public List<UserinfoEntity> getUserByType() {
			QueryParam param = new QueryParam();
			param.setPaging(false);
			param.and("usertype", TermType.in, "8,9")
				.and("state", "R2");
			ResponseMessage<awd.cloud.suppers.interfaces.utils.PagerResult<UserinfoEntity>> re = managerApi.queryUser(param);
			List<UserinfoEntity> list = new ArrayList<UserinfoEntity>();
			
			if (StringUtils.isNotBlank(re)) {
				list = re.getResult().getData();
			}
			return list;
		}
		
		@Override
		public int setUserinfoFromManager() {
			//先清空原本的userinfo表
			userinfoDao.deleteTable();
			//在从manager获取userinfo数据
			List<UserinfoEntity> list = getUserByType();
			list.forEach(entity -> {
				insert(entity);
			});
			return list.size();
		}
}
