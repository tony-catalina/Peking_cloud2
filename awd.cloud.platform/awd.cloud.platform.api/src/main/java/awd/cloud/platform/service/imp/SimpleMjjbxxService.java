package awd.cloud.platform.service.imp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thoughtworks.xstream.core.util.Base64Encoder;

import awd.bj.kss.model.MjjbxxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.AppMjjbxxService;
import awd.cloud.platform.utils.Result;
import awd.cloud.platform.utils.ResultUtils;
import awd.framework.common.utils.StringUtils;

@Service
public class SimpleMjjbxxService implements AppMjjbxxService {

    @Autowired
    private KssServerApis KssServerApis;

    @Override
    public Result<?> saveMjxxAndPicture(MjjbxxModel mjjbxxEntity,
                                        MultipartFile policePhoto) {
        try {
            String enable = this.checkPicture(policePhoto);
            String pictureByteStr = null;

            if (!StringUtils.isNullOrEmpty(enable)) {
                return ResultUtils.error(100, enable);
            }
            if (policePhoto != null) {
                pictureByteStr = this.fileTobase64(policePhoto);
            }
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("MjjbxxEntity", mjjbxxEntity);
            params.put("pictureByteStr", pictureByteStr);
            return ResultUtils.success(KssServerApis.saveMjxxAndPicture(params));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultUtils.error(Result.ERR_SAVE, "保存失败");
        }
    }


    /**
     * 更新
     */
    @Override
    public Result<?> updateMjxxAndPicture(MjjbxxModel mjjbxxEntity,
                                          MultipartFile policePhoto) {
        try {
            String enable = this.checkPicture(policePhoto);
            String pictureByteStr = null;

            if (!StringUtils.isNullOrEmpty(enable)) {
                return ResultUtils.error(100, enable);
            }
            if (policePhoto != null) {
                pictureByteStr = this.fileTobase64(policePhoto);
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("MjjbxxEntity", mjjbxxEntity);
            params.put("pictureByteStr", pictureByteStr);
            return ResultUtils.success(KssServerApis.updateMjxxAndPicture(params));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultUtils.error(Result.ERR_UPDATE, "更新失败");
        }
    }


    @Override
    public void getPicByMjid(String mjid, HttpServletResponse response) {
        Base64Encoder base = new Base64Encoder();
        OutputStream outputSream = null;
        String bytes = KssServerApis.getPicByMjid(mjid);
        if (bytes != null) {
            byte[] pictureByte = base.decode(bytes);
            try {
                outputSream = response.getOutputStream();
                outputSream.write(pictureByte);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (outputSream != null) {
                        outputSream.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    /**
     * 检测照片是否合法
     *
     * @param policePhoto 照片名称
     * @return
     */
    public String checkPicture(MultipartFile policePhoto) {
        if (StringUtils.isNullOrEmpty(policePhoto.getOriginalFilename())) {
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
