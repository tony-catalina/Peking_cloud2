FROM java:8u72-jre

MAINTAINER njawd sgh <306165699@qq.com>

ENV LANG zh_CN.UTF-8
ENV LC_ALL zh_CN.UTF-8
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=utf-8

ADD ./target/awd.cloud.platform.servers.manager-1.0-SNAPSHOT.jar /app/

CMD ["java","-Xms500m","-Xmx1600m",\
	"-XX:CompressedClassSpaceSize=128m",\
	"-XX:MetaspaceSize=1600m",\
	"-XX:MaxMetaspaceSize=1600m",\
	"-XX:MinHeapFreeRatio=10",\
	"-XX:MaxHeapFreeRatio=80",\
	"-Duser.timezone=GMT+8", \
	"-jar","/app/awd.cloud.platform.servers.manager-1.0-SNAPSHOT.jar",\
	"--eureka.serviceUrl=http://192.168.4.50:3001/eureka/,http://192.168.4.50:3002/eureka/,http://192.168.4.50:3003/eureka/", \
	"--hostname=awd-mis-manager-server",\
	"--hostip=192.168.4.50",\
	"--config.serviceUrl=http://192.168.4.50:3000",\
	"--config.lable=awd-mis-servers-manager", \
	"--config.profile=dev", \
	"--redisurl=192.168.4.50",\
	"--redisport=6379",\
	"--rabbitmqurl=192.168.4.50",\
	"--rabbitmqport=5672",\
	"--rabbitmqname=awd",\
	"--rabbitmqpass=awd",\
	"--urlW=jdbc:mysql://192.168.4.103:3307/soa_manager?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",\
	"--myUserW=root",\
	"--myPassW=root",\
	"--urlR=jdbc:mysql://192.168.4.103:3307/soa_manager?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",\
	"--myUserR=root",\
	"--myPassR=root",\
	"--urlF=jdbc:mysql://192.168.4.103:3307/soa_finger?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",\
    "--myUserF=root",\
    "--myPassF=root",\
	"--txurl=http://192.168.4.103:8761",\
	"--txdriver=com.mysql.jdbc.Driver",\
	"--txdb=jdbc:mysql://192.168.4.50:3307/soa_logs?useUnicode=true&amp;characterEncoding=utf8",\
	"--txuser=root",\
	"--txpass=root",\
	"--port=12102"]

#时区设置
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  && echo 'Asia/Shanghai' >/etc/timezone

#HEALTHCHECK --interval=10s --timeout=3s --retries=24 CMD curl -f http://localhost:3000/health || exit 1

EXPOSE 12102