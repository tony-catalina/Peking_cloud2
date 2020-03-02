package awd.cloud.platform.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import awd.bj.kss.model.MjjbxxModel;
import awd.cloud.platform.utils.Result;

@Service
public interface AppMjjbxxService {

    public Result<?> saveMjxxAndPicture(MjjbxxModel mjjbxxEntity, MultipartFile policePhoto);

    public Result<?> updateMjxxAndPicture(MjjbxxModel mjjbxxEntity, MultipartFile policePhoto);

    public void getPicByMjid(String id, HttpServletResponse response);
}
