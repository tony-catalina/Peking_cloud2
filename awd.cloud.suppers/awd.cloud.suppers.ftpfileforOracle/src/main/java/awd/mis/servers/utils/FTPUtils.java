package awd.mis.servers.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FTPUtils {
	
	@Value("${awd.ftp.hostip}")
	private String hostip;
	
	@Value("${awd.ftp.port}")
	private String port;
	
	@Value("${awd.ftp.username}")
	private String username;

	@Value("${awd.ftp.password}")
	private String password;
	
	private static FTPClient ftpClient;
    
	public FTPClient getFtp() {
		if (ftpClient == null) {
			ftpClient = new FTPClient();
		}
		return ftpClient;
	}
	
	
	public String getHostip() {
		return hostip;
	}


	public String getPort() {
		return port;
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	/**
     * 初始化ftp服务器
     */
    public void initFtpClient() {
        ftpClient = getFtp();
        
        ftpClient.setControlEncoding("utf-8");
        try {
            System.err.println("connecting...ftp server:"+ hostip +":"+ port); 
            ftpClient.connect(hostip, Integer.parseInt(port)); //连接ftp服务器
            
            ftpClient.login(username,password); //登录ftp服务器
            
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if(!FTPReply.isPositiveCompletion(replyCode)){
                System.err.println("connect failed...ftp server:"+ hostip +":"+ port); 
                ftpClient.enterLocalPassiveMode(); 
            }else {
            	System.err.println("connect successful...ftp server:"+ hostip +":"+ port); 
			}
            
        }catch (MalformedURLException e) { 
           e.printStackTrace(); 
        }catch (IOException e) { 
           e.printStackTrace(); 
        } 
    }
    
    /**
    * 上传文件
    * @param pathname ftp服务保存地址
    * @param fileName 上传到ftp的文件名
    * @param originfilename 待上传文件的名称（绝对地址） * 
    * @return
    * 
    */
    public boolean uploadFile( String pathname, String fileName,String originfilename){
    	System.err.println("ready to upload FTP !");
        boolean flag = false;
        InputStream inputStream = null;
        try{
            System.err.println("begin to upload file");
            //把文件转化为流
            inputStream = new FileInputStream(new File(originfilename));
            //初始化ftp
            //initFtpClient();
            //设置编码
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //文件需要保存的路径
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.err.println("file upload success !");
        }catch (Exception e) {
            System.err.println("file upload fail !");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            } 
        }
        return true;
    }
    /**
     * 上传文件
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param inputStream 输入文件流 
     * @return
     */
    public boolean uploadFile( String pathname, String fileName,InputStream inputStream){
        boolean flag = false;
        try{
            System.err.println("begin to upload file");
            //initFtpClient();
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.err.println("file upload success !");
        }catch (Exception e) {
            System.err.println("file upload fail !");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            } 
        }
        return true;
    }
    //改变目录路径
     public boolean changeWorkingDirectory(String directory) {
            boolean flag = true;
            try {
                flag = ftpClient.changeWorkingDirectory(directory);
                if (flag) {
                  System.err.println("entry folder :" + directory + " success !");

                } else {
                    System.err.println("entry folder :" + directory + " fail ! create this folder !");
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return flag;
        }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        System.err.println("create folder [" + subDirectory + "] failed !");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    //判断ftp服务器文件是否存在    
    public boolean existFile(String path) throws IOException {
            boolean flag = false;
            FTPFile[] ftpFileArr = ftpClient.listFiles(path);
            if (ftpFileArr.length > 0) {
                flag = true;
            }
            return flag;
        }
    //创建目录
    public static boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.err.println("create folder: " + dir + " successs !");

            } else {
                System.err.println("create folder: " + dir + " fail !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    /** * 下载文件 * 
    * @param pathname FTP服务器文件目录 * 
    * @param filename 文件名称 * 
    * @param localpath 下载后的文件路径 * 
    * @return */
    public boolean downloadFile(String pathname, String filename, String localpath){ 
        boolean flag = false; 
        OutputStream os=null;
        try { 
            System.err.println("begin to download file !");
            //initFtpClient();
            //切换FTP目录 
            ftpClient.changeWorkingDirectory(pathname); 
            FTPFile[] ftpFiles = ftpClient.listFiles(); 
            for(FTPFile file : ftpFiles){ 
                if(filename.equalsIgnoreCase(file.getName())){ 
                    File localFile = new File(localpath + "/" + file.getName()); 
                    os = new FileOutputStream(localFile); 
                    ftpClient.retrieveFile(file.getName(), os); 
                    os.close(); 
                } 
            } 
            ftpClient.logout(); 
            flag = true; 
            System.err.println("download file success !");
        } catch (Exception e) { 
            System.err.println("download file failed !");
            e.printStackTrace(); 
        } finally{ 
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            } 
        } 
        return flag; 
    }
    
    /** * 删除文件 * 
    * @param pathname FTP服务器保存目录 * 
    * @param filename 要删除的文件名称 * 
    * @return */ 
    public boolean deleteFile(String pathname, String filename){ 
        boolean flag = false; 
        try { 
            System.err.println("begin to delete file !");
            //initFtpClient();
            //切换FTP目录 
            ftpClient.changeWorkingDirectory(pathname); 
            ftpClient.dele(filename); 
            ftpClient.logout();
            flag = true; 
            System.err.println("delete file success !");
        } catch (Exception e) { 
            System.err.println("delete file failed !");
            e.printStackTrace(); 
        } finally {
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
        }
        return flag; 
    }
    
    
    /**
     * 读取ftp服务器上的文件，换换成 string 
     * @param remoteFilePath
     * @return
     */
    public String readFtpFile(String remoteFilePath) {
    	String temp = "";
    	InputStream inputStreamin = null;
    	InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        StringBuffer strbuftmp = new StringBuffer("");
    	try {
    		//initFtpClient();
    		inputStreamin = ftpClient.retrieveFileStream(remoteFilePath);
    		inputStreamReader = new InputStreamReader(inputStreamin);
            bufferedReader = new BufferedReader(inputStreamReader);
            String tmp = "";
            while ((tmp = bufferedReader.readLine()) != null) {
            	strbuftmp.append(tmp + "\n");
            }
            temp = strbuftmp.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bufferedReader.close();
				inputStreamReader.close();
				inputStreamin.close();
	    		//ftpClient.changeWorkingDirectory(remoteFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	return temp;
	}
}
