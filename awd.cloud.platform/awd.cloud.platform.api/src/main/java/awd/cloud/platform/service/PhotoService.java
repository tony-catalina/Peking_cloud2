package awd.cloud.platform.service;

import awd.bj.kss.model.PhotosModel;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
	public ResponseMessage<String> savePhoto(PhotosModel photosEntity, MultipartFile photo);
}
