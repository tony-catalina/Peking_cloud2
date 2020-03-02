package awd.cloud.platform.model.kss;

import awd.bj.kss.model.EmdjModel;
import awd.cloud.platform.model.Model;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class EmdjModelDO implements Model {

    private String lcid;
    
    private String jsbh;

    private EmdjModel emdjEntity;

    private JbxxModelDO jbxxEntity;

    public EmdjModel getEmdjEntity() {
        return emdjEntity;
    }


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


	public void setEmdjEntity(EmdjModel emdjEntity) {
        this.emdjEntity = emdjEntity;
    }

    public JbxxModelDO getJbxxModel() {
        return jbxxEntity;
    }

    public void setJbxxModel(JbxxModelDO jbxxEntity) {
        this.jbxxEntity = jbxxEntity;
    }


}
