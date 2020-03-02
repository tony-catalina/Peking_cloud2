FROM java:8u72-jdk

MAINTAINER njawd sgh <306165699@qq.com>

ADD ./target/awd.cloud.suppers.ftpfileforOracle-1.0-SNAPSHOT.jar /app/

CMD ["java","-Xms300m","-Xmx500m",\
	"-XX:CompressedClassSpaceSize=128m",\
	"-XX:MetaspaceSize=500m",\
	"-XX:MaxMetaspaceSize=500m",\
	"-XX:MinHeapFreeRatio=10",\
	"-XX:MaxHeapFreeRatio=80",\
	"-Duser.timezone=GMT+8", \
	"-jar","/app/awd.cloud.suppers.ftpfileforOracle-1.0-SNAPSHOT.jar",\
	"--hostname=awd-cloud-ftp-server-oracle",\
	"--hostip=192.168.4.50",\
	"--ftpHostip=192.168.4.103",\
	"--ftpPort=21",\
	"--ftpUsername=awd",\
	"--ftpPassword=awd",\
	"--mysqlFolder=/smsy/mysql_ftpfolder",\
	"--oracleFolder=/smsy/oracle_ftpfolder",\
	"--publicFolder=/smsy/public_ftpfolder",\
	"--oracleurl=192.168.4.103",\
	"--oraclesid=awddb",\
	"--oraclename=d_sh_kss",\
	"--oraclepass=dddd",\
	"--port=9991"]

ENV LANG zh_CN.UTF-8
ENV LC_ALL zh_CN.UTF-8
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=utf-8	 

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  && echo 'Asia/Shanghai' >/etc/timezone 

#HEALTHCHECK --interval=10s --timeout=3s --retries=24 CMD curl -f http://localhost:3000/health || exit 1

EXPOSE 9991