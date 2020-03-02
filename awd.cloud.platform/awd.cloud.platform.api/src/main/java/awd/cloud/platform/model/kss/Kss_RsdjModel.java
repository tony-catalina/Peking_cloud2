package awd.cloud.platform.model.kss;
import java.util.List;

import awd.cloud.platform.model.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.PhotosModel;
import awd.bj.kss.model.ShgxModel;
public class Kss_RsdjModel implements Model {
    private String lcid;

    private String jsbh;

    private JbxxModel jbxxEntity;

    private List<ShgxModel> shgxEntities;

    private List<PhotosModel> photosEntities;

    public String getLcid() {
        return lcid;
    }

    public void setLcid(String lcid) {
        this.lcid = lcid;
    }

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public JbxxModel getJbxxEntity() {
        return jbxxEntity;
    }

    public void setJbxxEntity(JbxxModel jbxxEntity) {
        this.jbxxEntity = jbxxEntity;
    }

    public List<ShgxModel> getShgxEntities() {
        return shgxEntities;
    }

    public void setShgxEntities(List<ShgxModel> shgxEntities) {
        this.shgxEntities = shgxEntities;
    }

    public List<PhotosModel> getPhotosEntities() {
        return photosEntities;
    }

    public void setPhotosEntities(List<PhotosModel> photosEntities) {
        this.photosEntities = photosEntities;
    }
}
