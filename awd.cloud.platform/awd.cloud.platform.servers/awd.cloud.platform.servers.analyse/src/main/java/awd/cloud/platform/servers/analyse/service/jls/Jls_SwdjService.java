package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_SwdjDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Jls_SwdjService {
    @Autowired
    private Jls_SwdjDao jls_SwdjDao;

    @UseDataSource("jls_ds")
    public Map<String, Object> swdjYwdt(String starttime, String endtime, String jsbh) {
        System.out.println("调用业务动态----startTime--Dao--"+starttime);
        System.out.println("调用业务动态----endTime--Dao--"+endtime);
        Map<String, Object> swdj = jls_SwdjDao.swdj(starttime ,endtime ,jsbh);
        return swdj;
    }

}
