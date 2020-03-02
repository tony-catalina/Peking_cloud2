package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.PhotosDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Kss_PhotosService {

    @Autowired
    private PhotosDao photosDao;

    @UseDataSource("kss_ds")
    public String getRyPhoto(String rybh,String jsbh){

        return photosDao.getRyPhoto(rybh,jsbh);
    }
}
