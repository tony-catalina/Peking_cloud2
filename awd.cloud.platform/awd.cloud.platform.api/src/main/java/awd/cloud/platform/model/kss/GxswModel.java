package awd.cloud.platform.model.kss;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.bj.base.model.Model;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GxswModel implements Model {
    Map<String,Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
