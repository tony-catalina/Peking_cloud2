package awd.mis.servers.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

@Component
public class FileUtil {

	
	@Value("${awd.ftp.folderpath.mysqlFolder}")
	private String mysqlHomeFolder;
	@Value("${awd.ftp.folderpath.oracleFolder}")
	private String oracleHomeFolder;
	@Value("${awd.ftp.folderpath.publicFolder}")
	private String publicFolder;
	
	@Autowired
	private FTPUtils fTPUtils;
	
	public String makeDir() {
		//String msg1 = makeMysqlDir();
		//String msg2 = makeOracleDir();
		String msg1 = createDir(mysqlHomeFolder);
		String msg2 = createDir(oracleHomeFolder);
		String msg3 = createDir(publicFolder);
		try {
			fTPUtils.initFtpClient();
			fTPUtils.CreateDirecroty(mysqlHomeFolder);
			fTPUtils.initFtpClient();
			fTPUtils.CreateDirecroty(oracleHomeFolder);
			fTPUtils.initFtpClient();
			fTPUtils.CreateDirecroty(publicFolder);
		} catch (IOException e) {
			//e.printStackTrace();
			String msg = "create FTP server folder : " + 
					mysqlHomeFolder + " or " + 
					oracleHomeFolder + " or " + 
					publicFolder + " is failed !";
			System.err.println(msg);
		}
		return "msg1:"+msg1+", msg2:"+msg2+", msg3:"+msg3;
	}
	
	/**
	 * 创建 mysql 数据库获取的数据文件存放目录
	 */
	public String makeMysqlDir() {
		//获取根目录
		//ClassPathResource resource = new ClassPathResource("/");
		//创建文件夹：
		File mysql = new File(mysqlHomeFolder);
		System.err.println("absolutePath-mysqlHomeFolder:"+mysql.getAbsolutePath());
		String msg = "";
		if (mysql.exists()) {
			if (mysql.isDirectory()) {
				msg = "root dir "+mysqlHomeFolder+" is exist";
			} else {
				msg = "exist same name folder,could not create folder: "+mysqlHomeFolder;
			}
		} else {
		    msg = "dir " + mysqlHomeFolder + " is not exists, create it ...";
		    boolean mysqlFlag = mysql.mkdir();
		    if (mysqlFlag) {
		    	msg = "create root dir : "+mysqlHomeFolder+" folder is successful !";
		    }else {
		    	msg = "create root dir : "+mysqlHomeFolder+" folder is failed !";
		    }
		}
		System.err.println(msg);
		return msg;
	}
	
	/**
	 * 创建oracle 数据库同步过来的文件存放目录
	 */
	public String makeOracleDir() {
		//创建文件夹：
		File oracle = new File(oracleHomeFolder);
		System.err.println("absolutePath-oracleHomeFolder:"+oracle.getAbsolutePath());
		String msg = "";
		if (oracle.exists()) {
			if (oracle.isDirectory()) {
				msg = "root dir: "+ oracleHomeFolder +" is exist.";
			} else {
				msg = "exist same name folder,could not create folder: "+ oracleHomeFolder;
			}
		} else {
			msg = "dir not exists, create it ...";
			boolean oracleFlag = oracle.mkdir();
			if (oracleFlag) {
				msg = "create root dir : "+ oracleHomeFolder +" folder is successful !";
		    }else {
		    	msg = "create root dir : "+ oracleHomeFolder +" folder is fil failed !";
		    }
		}
		System.err.println(msg);
		return msg;
	}
	
	public String createDir(String path) {
		String msg = "";
		File folder = new File(path);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				msg = "root dir: "+ path +" is exist.";
			} else {
				msg = "exist same name folder,could not create folder: "+ path;
			}
		} else {
			boolean folderFlag = false;
			if (path.indexOf("/") != -1 || path.indexOf("|") != -1 || path.indexOf("\\") != -1 ) {
				folderFlag = folder.mkdirs();
			}else {
				folderFlag = folder.mkdir();
			}
			if (folderFlag) {
				msg = "create root dir : "+ path +" folder is successful !";
		    }else {
		    	msg = "create root dir : "+ path +" folder is fil failed !";
		    }
		}
		System.err.println("createDir msg is :"+msg);
		return msg;
	}
	
	/**
	 * 创建从 mysql数据库 获取的数据 转成文件 
     * @param fileName 文件名
     * @param fileContent     文本文件内容
     * @return
     */
    public Map<String, Object> createMysqlFile(String fileName, String fileContent) {
    	
    	//String targetPath = oracleHomeFolder;
    	String targetPath = mysqlHomeFolder;
    	boolean flag = false;
    	String filePath = "";
    	Map<String, Object> map = Maps.newHashMap();
        try {
            filePath = targetPath + "/" + fileName;
            System.err.println("createMysqlFile...filePath is : "+filePath);
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            String strContent = fileContent;
            myFile.println(strContent);
            myFile.close();
            resultFile.close();
            fTPUtils.initFtpClient();
            flag = fTPUtils.uploadFile(targetPath, fileName, filePath);
        } catch (Exception e) {
            System.err.println("create folder "+ targetPath +" with error !");
        }finally {
        	map.put("flag", flag);
        	map.put("path", filePath);
		}
        return map;
    }
	
	/**
	 * 获取存在ftp中的oracle 数据库的 json 文件
     * @param filePathAndName 文本文件完整绝对路径及文件名
     * @param fileContent     文本文件内容
     * @return
     */
	public String downLoadFileByFtp() {
		String sourcePath = oracleHomeFolder;	//这个是ftp服务器的 源文件目录
		String targetPath = oracleHomeFolder;	//这个时候本地下载的 目标目录。这里我应该是一样的
		String localFilePath = "";
		List<String> jsonNameList = new ArrayList<String>();
        try {
            fTPUtils.initFtpClient();
            FTPClient ftpClient = fTPUtils.getFtp();
            
            FTPFile[] ftpFileArr = ftpClient.listFiles(sourcePath);
            for (FTPFile ftpFile : ftpFileArr) {
            	String name = ftpFile.getName();
            	if (!ftpFile.isDirectory()) {
            		jsonNameList.add(name);
				}
			}
            if (jsonNameList.size() > 0) {
            	Collections.sort(jsonNameList);
            	Collections.reverse(jsonNameList);
				
            	String lastFileName = jsonNameList.get(0);
            	System.err.println("lastFileName is : "+lastFileName);
            	/**
            	 * 用这个文件路径 看看在目标目录中 存不存在该文件，
            	 * 	不存在就下载，
            	 * 	存在的话就不继续后续文件的读取操作了
            	 */
            	File localFile = new File(targetPath + "/" + lastFileName); 
            	if (!localFile.exists()) {
            		System.err.println("download file : "+lastFileName);
            		//下载文件到本地
            		boolean flag = fTPUtils.downloadFile(sourcePath,lastFileName,targetPath);
            		if (flag) {
            			//获取文件下载的地址
            			localFilePath = targetPath + "/" + lastFileName;
					}
				}else {
					System.err.println("this file named "+ lastFileName +" is exist at your filesystem");
				}
			}
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("downLoadOracleFile with error !");
        }
        return localFilePath;
    }
    
	
	/**
	 * 从ftp服务器的对应的mysql文件夹中获取 已经上传的。json数据，并读取，
	 * 然后把这些数据全部移动到一个新的文件夹中，等待下一次的读取周期
	 * @param <T>
	 */
	public List<String> getJsonObjList() {
		String sourcePath = oracleHomeFolder;	//这个是ftp服务器的 源文件目录，准备从这里读取文件
		/*获取当前时间，准备根据当前时间在新建一个文件夹，用来存放这个读取后期内的文件*/
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式,因为我打算用来当做文件夹的名称，所谓设置成这个格式
		String newFolderName = dateFormat.format(new Date());
		String newRemote = sourcePath + "/" + newFolderName;
		/*新建一个ftp文件夹*/
		fTPUtils.initFtpClient();
		FTPClient ftpClient = fTPUtils.getFtp();
		List<String> jsonList = new ArrayList<String>();	//定义一个数组用来存放java对象
		try {
			fTPUtils.CreateDirecroty(newRemote);
			//ftpClient.changeToParentDirectory();
			//System.err.println("ftpBoolen=="+ftpBoolen);
			FTPFile[] ftpFileArr = ftpClient.listFiles(sourcePath);	//获取父级文件夹类的文件
			for (FTPFile ftpFile : ftpFileArr) {
				String name = ftpFile.getName();
				String tmpJson = "";
				if (!ftpFile.isDirectory()) {
					String oldSourcePath = sourcePath + "/" + name;
					String newFilePath = newRemote + "/" + name;
					//fTPUtils.initFtpClient();
					tmpJson = fTPUtils.readFtpFile(oldSourcePath);
					jsonList.add(tmpJson);
					ftpClient.completePendingCommand();
					ftpClient.deleteFile(newFilePath);	//删除目标位置的文件，防止吧文件移动过去因为已存在无法移动
					ftpClient.rename(oldSourcePath, newFilePath);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonList;
	}
	
	/**
	 * 从ftp服务器的对应的public文件夹中获取 已经上传的。json数据，并读取，
	 * 然后把这些数据全部移动到一个新的文件夹中，等待下一次的读取周期
	 * @param <T>
	 */
	public List<String> getPublicObjList() {
		String sourcePath = publicFolder;	//这个是ftp服务器的 源文件目录，准备从这里读取文件
		/*获取当前时间，准备根据当前时间在新建一个文件夹，用来存放这个读取后期内的文件*/
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式,因为我打算用来当做文件夹的名称，所谓设置成这个格式
		String newFolderName = dateFormat.format(new Date());
		String newRemote = sourcePath + "/" + newFolderName;
		/*新建一个ftp文件夹*/
		fTPUtils.initFtpClient();
		FTPClient ftpClient = fTPUtils.getFtp();
		List<String> jsonList = new ArrayList<String>();	//定义一个数组用来存放java对象
		try {
			fTPUtils.CreateDirecroty(newRemote);
			//ftpClient.changeToParentDirectory();
			//System.err.println("ftpBoolen=="+ftpBoolen);
			FTPFile[] ftpFileArr = ftpClient.listFiles(sourcePath);	//获取父级文件夹类的文件
			for (FTPFile ftpFile : ftpFileArr) {
				String name = ftpFile.getName();
				String tmpJson = "";
				if (!ftpFile.isDirectory()) {
					String oldSourcePath = sourcePath + "/" + name;
					String newFilePath = newRemote + "/" + name;
					//fTPUtils.initFtpClient();
					tmpJson = fTPUtils.readFtpFile(oldSourcePath);
					jsonList.add(tmpJson);
					ftpClient.completePendingCommand();
					ftpClient.deleteFile(newFilePath);	//删除目标位置的文件，防止吧文件移动过去因为已存在无法移动
					ftpClient.rename(oldSourcePath, newFilePath);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonList;
	}
	
	/**
	 * 获取下载的到本地的 .json文件 内容，并转成List<String> 字符串 
	 */
    public String getJsonStrByFile(String localFilePath) {
    	String temp = "";
    	File localFile = new File(localFilePath);
    	StringBuffer strbuftmp = new StringBuffer("");
        FileInputStream fileInputStreamin=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        try {
        	fileInputStreamin = new FileInputStream(localFile);
            inputStreamReader = new InputStreamReader(fileInputStreamin);
            bufferedReader = new BufferedReader(inputStreamReader);
            String tmp = "";
            while ((tmp = bufferedReader.readLine()) != null) {
            	strbuftmp.append(tmp + "\n");
            }
            /*if (bufferedReader.read() != -1) {
            	strbuftmp.append(bufferedReader.readLine());
			}*/
            temp = strbuftmp.toString();
            System.err.println("read file success!");
        } catch (IOException e) {
            System.err.println("read file failed");
            e.printStackTrace();
        } catch (Exception e) {
        	System.err.println("read file failed");
            e.printStackTrace();
		}finally {
			try {
				bufferedReader.close();
				inputStreamReader.close();
				fileInputStreamin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return temp;
	}
    
	/**
	 * 从本地文件读取 数据，并转换成 java 对象
	 * @param <T>
	 */
	public <T> List<T> getJsonObject(String localFilePath, Class<T> clazz) {
		//把文件读取成string数据,是list<object> 类型的
		String jsonStr = getJsonStrByFile(localFilePath);
		System.err.println(jsonStr);
		List<T> jsonList = JSONArray.parseArray(jsonStr, clazz);
		return jsonList;
	}
	
	/**
	 * 把string 数据换成java对象
	 * @param <T>
	 */
	public <T> T jsonStrToBean(String jsonStr, Class<T> clazz) {
		T javaBean = JSONObject.parseObject(jsonStr, clazz);
		//T javaBean = JSONUtil.toBean(jsonStr, clazz);
		return javaBean;
	}
	
	public String getFtpFileByName(String fileName) {
		String sourcePath = publicFolder;	//比如/public/jsh.json
		fTPUtils.initFtpClient();
		FTPClient ftpClient = fTPUtils.getFtp();
		FTPFile[] ftpFileArr;
		String tmpJson = "";
		try {
			ftpFileArr = ftpClient.listFiles(sourcePath);
			for (FTPFile ftpFile : ftpFileArr) {
				String name = ftpFile.getName();
				
				if (!ftpFile.isDirectory() && name.equals(fileName)) {
					String oldSourcePath = sourcePath + "/" + name;
					//fTPUtils.initFtpClient();
					tmpJson = fTPUtils.readFtpFile(oldSourcePath).toString();
					//ftpClient.changeWorkingDirectory(newFilePath);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return tmpJson;
	}
	
	
    public String uploadPulicFileToFtp(String fileName, String fileContent) {
    	
    	String targetPath = publicFolder;
    	String filePath = "";
    	String ftpRemoteParh = "";
        try {
            filePath = targetPath + "/" + fileName;
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            String strContent = fileContent;
            myFile.println(strContent);
            myFile.close();
            resultFile.close();
            fTPUtils.initFtpClient();
            FTPClient ftpClient = fTPUtils.getFtp();
            ftpClient.deleteFile(filePath);	//删除目标位置的文件，防止吧文件移动过去因为已存在无法移动
            boolean flag = fTPUtils.uploadFile(targetPath, fileName, filePath);
            if (flag) {
            	ftpRemoteParh = "ftp://" + fTPUtils.getHostip() + filePath;
			};
        } catch (Exception e) {
        	System.err.println(e);
        	ftpRemoteParh = "upload file: "+ fileName +" with error !";
		}
        return ftpRemoteParh;
    }
	
}
