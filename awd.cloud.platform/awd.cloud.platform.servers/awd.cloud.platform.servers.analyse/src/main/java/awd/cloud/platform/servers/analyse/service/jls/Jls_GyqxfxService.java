package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JbxxDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2020-02-10 13:07
 * Description：<描述>
 */
@Service
public class Jls_GyqxfxService {

    @Autowired
    private Jls_JbxxDao jlsJbxxDao;

    /**
     * 查询关押期限数量
     * @param starttime
     * @param endtime
     * @return
     */
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> queryGyqxfxList(String starttime, String endtime){
        List<Map<String,Object>> result = jlsJbxxDao.queryGyqxNum(starttime, endtime);
        for (Map<String,Object> map : result ){
            String dz = (String) map.get("dz");
            map.put("dzString", CacheUtils.get().getDictionarys("XZQH",dz));
        }
        return result;
    }
}
