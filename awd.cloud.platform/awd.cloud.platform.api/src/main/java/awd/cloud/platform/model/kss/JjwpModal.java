package awd.cloud.platform.model.kss;

import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.JjwpsljlModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JjwpModal implements Serializable{

	private JbxxModel jbxxEntity;
	private JjwpsljlModel jjwpsljlModel;

	public JjwpsljlModel getJjwpsljlModel() {
		return jjwpsljlModel;
	}

	public void setJjwpsljlModel(JjwpsljlModel jjwpsljlModel) {
		this.jjwpsljlModel = jjwpsljlModel;
	}

	public JbxxModel getJbxxEntity() {
		return jbxxEntity;
	}

	public void setJbxxEntity(JbxxModel jbxxEntity) {
		this.jbxxEntity = jbxxEntity;
	}


}