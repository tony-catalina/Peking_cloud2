package awd.mis.desktop.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description 文件下载
 * @Author WS
 * @Date 2019-05-27 11:41
 */
public class DownloadFileUtil {

    public static final String separator = File.separator;

    static Logger logger = LoggerFactory.getLogger(DownloadFileUtil.class);

    /**
     * 下载样表
     *
     * @param urlStr
     * @return
     */
    public static ResponseEntity<InputStreamResource> download(String urlStr) {

        ResponseEntity<InputStreamResource> response = null;
        try {
            URL url = new URL(urlStr);
            String fileName = urlStr.substring(urlStr.lastIndexOf("/"), urlStr.length());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //得到输入流
            InputStream inputStream = conn.getInputStream();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition",
                    "attachment; filename="
                            + fileName);
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            response = ResponseEntity.ok().headers(headers)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(inputStream));
        } catch (FileNotFoundException e1) {
            logger.error("找不到指定的文件", e1);
        } catch (IOException e) {
            logger.error("获取不到文件流", e);
        }
        return response;
    }

    public static void main(String[] args) {
        String url ="http://192.168.4.109:8080/storagegroup/M00/00/09/wKgEbVzrciCAQIq1AAGQANrkxEQ596.xls";
        download(url);
    }
}
