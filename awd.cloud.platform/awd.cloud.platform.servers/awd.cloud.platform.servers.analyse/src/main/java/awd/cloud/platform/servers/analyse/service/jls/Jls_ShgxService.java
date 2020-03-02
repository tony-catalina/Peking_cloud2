package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_ShgxDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Jls_ShgxService
{

    @Autowired
    private Jls_ShgxDao jls_shgxDao;

    /**
     * 查询社会关系数量
     * @param starttime
     * @param endtime
     * @return
     */
    @UseDataSource("jls_ds")
    public List<AnalysisJlsResultVO> queryjlsShgxFx(String starttime, String endtime){
        return jls_shgxDao.queryjlsShgxFx(starttime, endtime);
    }
}
