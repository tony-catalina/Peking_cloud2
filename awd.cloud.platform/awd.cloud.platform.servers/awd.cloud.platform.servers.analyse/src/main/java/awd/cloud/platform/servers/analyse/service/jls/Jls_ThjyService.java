package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_ThjyDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Jls_ThjyService
{
    @Autowired
    private Jls_ThjyDao jls_thjyDao;

    /**
     * 查询社会关系数量
     * @param starttime
     * @param endtime
     * @return
     */
    @UseDataSource("jls_ds")
    public List<AnalysisJlsResultVO> queryjlsThjyFx(String starttime, String endtime){
        return jls_thjyDao.queryjlsThjyFx(starttime, endtime);
    }
}
