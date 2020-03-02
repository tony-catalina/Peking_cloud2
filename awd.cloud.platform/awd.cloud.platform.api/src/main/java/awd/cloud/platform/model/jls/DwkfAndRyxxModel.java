package awd.cloud.platform.model.jls;

import awd.bj.base.model.Model;
import awd.bj.jls.model.DwkfModel;
import awd.bj.jls.model.DwkfRyxxModel;

import java.util.List;

public class DwkfAndRyxxModel implements Model{
	private DwkfModel dwkfModel;
	
	private List<DwkfRyxxModel> dwList;

	public DwkfModel getDwkfModel() {
		return dwkfModel;
	}

	public void setDwkfModel(DwkfModel dwkfModel) {
		this.dwkfModel = dwkfModel;
	}

	public List<DwkfRyxxModel> getDwList() {
		return dwList;
	}

	public void setDwList(List<DwkfRyxxModel> dwList) {
		this.dwList = dwList;
	}
	
	
}
