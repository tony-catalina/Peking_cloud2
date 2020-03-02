package awd.cloud.platform.model.kss;

import awd.bj.kss.model.JstzModel;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import awd.cloud.platform.utils.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public  class JstzModelDO implements Model {
    
        private String lcid;
    
        private JstzModel jstzEntity;
    
        private JbxxModelDO jbxxEntity;
    
        public JstzModel getJstzEntity() {
            return jstzEntity;
        }
    
        public void setJstzEntity(JstzModel jstzEntity) {
            this.jstzEntity = jstzEntity;
        }
    
        public JbxxModelDO getJbxxEntity() {
            return jbxxEntity;
        }
    
        public void setJbxxEntity(JbxxModelDO jbxxEntity) {
            this.jbxxEntity = jbxxEntity;
        }
    
        public String getLcid() {
            return lcid;
        }
    
        public void setLcid(String lcid) {
            this.lcid = lcid;
        }
    
    
    }
