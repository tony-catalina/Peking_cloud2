package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JbxxDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_GyqxfxService {
    @Autowired
    private JbxxDao jbxxDao;

    /**
     * 查询关押期限数量
     * @param starttime
     * @param endtime
     * @return
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> queryGyqxfxList(String starttime, String endtime){
        List<Map<String,Object>> result = jbxxDao.queryGyqxNum(starttime, endtime);
        System.err.println("查询出来了，结果为===="+ JSON.toJSONString(result));
        for (Map<String,Object> map:result){
            String dz = (String) map.get("dz");
            map.put("dzString", CacheUtils.get().getDictionarys("XZQH",dz));
            System.err.println("获取缓存完成");
        }
        return result;
    }
}
