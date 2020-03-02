package awd.cloud.platform.service.imp;

import awd.bj.kss.model.PhotosModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PhotoService;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
import awd.cloud.platform.utils.PagerResult;
import awd.framework.common.utils.StringUtils;
import com.thoughtworks.xstream.core.util.Base64Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
public class SimplePhotoService implements PhotoService {
	
	@Autowired
	private KssServerApis kssServerApis;

	@Override
	public ResponseMessage<String> savePhoto(PhotosModel photosEntity, MultipartFile photo) {
		String photoString = null;
		try {
            String enable = this.checkPicture(photo);

            if (!StringUtils.isNullOrEmpty(enable)) {
                return ResponseMessage.error(enable);
            }
            
            Base64Encoder base = new Base64Encoder();
    		if(photo != null) {
    			photoString = this.fileTobase64(photo);
    			photosEntity.setPhoto(base.decode(photoString));
    		}
    		QueryParam param = new QueryParam();
    		param.and("jsbh", TermType.eq, photosEntity.getJsbh());
    		param.and("rybh", TermType.eq, photosEntity.getRybh());
    		param.and("type", TermType.eq, photosEntity.getType());
    		ResponseMessage<PagerResult<PhotosModel>> photosModel = kssServerApis.photosList(param);
    		if(photosModel.getResult().getTotal()>0) {
    			if(!StringUtils.isNullOrEmpty(photosEntity.getPhotoUrl())) {
    				 return kssServerApis.photosUpdate(photosModel.getResult().getData().get(0).getId(), photosEntity);
    			}else {
    				return ResponseMessage.ok("保存成功");
    			}
    		}else {
    			 return kssServerApis.savePhotos(photosEntity);
    		}
		}catch (Exception ex) {
            ex.printStackTrace();
            return ResponseMessage.error("保存失败");
        }
	}
	
	/**
     * 检测照片是否合法
     *
     * @param policePhoto 照片名称
     * @return
     */
    public String checkPicture(MultipartFile policePhoto) {
        if (StringUtils.isNullOrEmpty(policePhoto)) {
            return null;
        }
        String fileName = policePhoto.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
        if (!extension.equals("JPG")
                && !extension.equals("PNG")
                && !extension.equals("JPEG")
                && !extension.equals("BMP")) {
            return "文件格式不正确!";
        }
        if (policePhoto.getSize() > 3 * 1024 * 1024) {
            return "文件过大!";
        }
        return null;
    }

    /**
     * 将MultipartFile文件 base64编码
     *
     * @param policePhoto
     * @return
     */
    public String fileTobase64(MultipartFile policePhoto) {
        if (StringUtils.isNullOrEmpty(policePhoto.getOriginalFilename())) {
            return null;
        }
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = (InputStream) policePhoto.getInputStream();
            bos = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int n = -1;
            while ((n = is.read(buffer)) != -1) {
                bos.write(buffer, 0, n);
            }
            Base64Encoder base = new Base64Encoder();
            return base.encode(bos.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


}
