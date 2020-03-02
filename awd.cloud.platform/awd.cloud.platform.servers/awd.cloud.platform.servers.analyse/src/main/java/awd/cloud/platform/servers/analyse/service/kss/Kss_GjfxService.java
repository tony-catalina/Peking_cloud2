package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JbxxDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_GjfxService {
    @Autowired
    private JbxxDao jbxxDao;

    /**
     *  查询国籍分析数量
     * @param jyrq
     * @param starttime
     * @param endtime
     * @return
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> queryGjfxList(String jyrq, String starttime, String endtime){
        return jbxxDao.queryGjNum(jyrq, starttime, endtime);
    }

    /**
     *  上位机国籍分析
     * @param jyrq
     * @param starttime
     * @param endtime
     * @return
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> queryGjfx(String jyrq, String starttime, String endtime){
        List<Map<String,Object>> result = jbxxDao.queryGjfxNum(jyrq, starttime, endtime);
        for (Map<String,Object> map : result ){
            String dz = (String) map.get("dz");
            map.put("dzString", CacheUtils.get().getDictionarys("XZQH",dz));
        }
        return result;
    }
}
