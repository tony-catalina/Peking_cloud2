package awd.mis.desktop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.FileModel;

@Service("fileservice")
public class FileService {

	public List<FileModel> getfile(User currentUser) {
		List<FileModel> list=new ArrayList<>();
		return list;
	}


	

}
