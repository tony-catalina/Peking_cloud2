FROM java:8u72-jre

MAINTAINER njawd sgh <306165699@qq.com>

ENV LANG zh_CN.UTF-8
ENV LC_ALL zh_CN.UTF-8
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=utf-8

ADD ./target/awd.cloud.platform.webs.appstore-1.0-SNAPSHOT.jar /app/

CMD ["java","-Xms300m","-Xmx550m",\
	"-XX:CompressedClassSpaceSize=128m",\
	"-XX:MetaspaceSize=500m",\
	"-XX:MaxMetaspaceSize=500m",\
	"-XX:MinHeapFreeRatio=10",\
	"-XX:MaxHeapFreeRatio=80",\
	"-Duser.timezone=GMT+8", \
	"-jar","/app/awd.cloud.platform.webs.appstore-1.0-SNAPSHOT.jar",\
	"--eureka.serviceUrl=http://192.168.4.50:3001/eureka/,http://192.168.4.50:3002/eureka/,http://192.168.4.50:3003/eureka/", \
	"--hostname=awd-mis-appstore-web",\
	"--hostip=192.168.4.50",\
	"--config.lable=awd-mis-servers-manager",\
	"--config.serviceUrl=http://192.168.4.50:3000",\
	"--config.profile=test",\
	"--redisurl=192.168.4.50",\
	"--redisport=6379",\
	"--rabbitmqurl=192.168.4.50",\
	"--rabbitmqport=5672",\
	"--rabbitmqname=awd",\
	"--rabbitmqpass=awd",\
	"--port=9000"]

#HEALTHCHECK --interval=10s --timeout=3s --retries=24 CMD curl -f http://localhost:3000/health || exit 1

EXPOSE 9000