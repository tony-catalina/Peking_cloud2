package awd.mis.servers.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class SetFastdfsValueUtil {
	
	@Value("${tracker_server.url}")
    private String tracker_server_url;
	
	public void setFastdfsValueUtil() throws IOException{
		System.err.println("开始进入 setFastdfsValueUtil方法===");
		
		InputStream inputStream=null;
        try{
        	
        	//inputStream = this.getClass().getResourceAsStream("/fdfs_client.conf");
        	ClassPathResource resource = new ClassPathResource("/fdfs_client.conf");
        	inputStream = resource.getInputStream();
            Properties p = new Properties();
            p.load(inputStream);
            System.err.println("tracker_server==="+p.getProperty("tracker_server"));
            System.err.println("tracker_server_url==="+tracker_server_url);
            p.setProperty("tracker_server", tracker_server_url);
            
            System.err.println("tracker_server_url最终："+p.getProperty("tracker_server"));
        }catch(Exception ex){
            System.err.println(ex.toString());
        }finally{//不管是否出现异常，都要确保关闭以释放资源
            try{
            	inputStream.close();
            }catch(IOException ioEx){
                System.err.println(ioEx.toString());
            }
        }
		
	}
	
}
