package awd.cloud.platform.model.kss;

import awd.framework.common.model.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

public class Kss_FwdjModel implements Model {
    private String licd;

    private Map<String,Object>  map;

    public String getLicd() {
        return licd;
    }

    public void setLicd(String licd) {
        this.licd = licd;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
