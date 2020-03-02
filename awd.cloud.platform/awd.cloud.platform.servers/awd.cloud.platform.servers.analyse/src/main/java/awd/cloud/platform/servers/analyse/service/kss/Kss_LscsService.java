package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.LscsDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_LscsService {
    @Autowired
    private LscsDao lscsDao;

    /**
     * 临时出所昨日出所
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> lscsZrcs(String jsbh) {
        return lscsDao.lscsZrcs(jsbh);
    }

    /**
     * 临时出所昨日出所未归
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> lscsZrcswg(String jsbh) {
        return lscsDao.lscsZrcswg(jsbh);
    }

    /**
     * 临时出所今日出所
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> lscsJrcs(String jsbh) {
        return lscsDao.lscsJrcs(jsbh);
    }

    /**
     * 临时出所今日出所未归
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> lscsJrcswg(String jsbh) {
        return lscsDao.lscsJrcswg(jsbh);
    }


    /**
     * 临时出所本周出所
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> lscsBzcs(String jsbh) {
        return lscsDao.lscsBzcs(jsbh);
    }

    /**
     * 临时出所本周出所未归
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> lscsBzcswg(String jsbh) {
        return lscsDao.lscsBzcswg(jsbh);
    }

    /**
     * 临时出所本月出所
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> lscsBycs(String jsbh) {
        return lscsDao.lscsBycs(jsbh);
    }

    /**
     * 临时出所本月出所未归
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> lscsBycswg(String jsbh) {
        return lscsDao.lscsBycswg(jsbh);
    }

	public List<Map<String, Object>> getLscsCount(String jsbh) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * 流水牌>实时状态>离所探视
     * @param jsbh
     * @return
     */
    @UseDataSource("kss_ds")
	public int getLscsCountByField (String jsbh,String field,String value){

        return lscsDao.getLscsCountByField(jsbh,field,value);
    }
    @UseDataSource("kss_ds")
    public int getLscsCountByCsyyWithJs(String jsbh, String jsh,String value) {
        return lscsDao.getLscsCountByCsyyWithJs(jsbh,jsh,value);
    }
}
