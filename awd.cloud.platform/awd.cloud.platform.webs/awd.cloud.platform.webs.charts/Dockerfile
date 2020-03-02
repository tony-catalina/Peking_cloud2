FROM java:8u72-jre

MAINTAINER njawd sgh <306165699@qq.com>

ADD ./target/awd.cloud.platform.webs.charts-1.0-SNAPSHOT.jar /app/

#ENV JAVA_OPTS  -Xms200m
#ENV JAVA_OPTS  -Xmx1024m
#ENV JAVA_OPTS  -XX:MinHeapFreeRatio=10
#ENV JAVA_OPTS  -XX:MaxHeapFreeRatio=80
#ENV JAVA_OPTS  -Duser.timezone=GMT+8


CMD ["java","-Xms400m","-Xmx800m",\
	"-XX:CompressedClassSpaceSize=128m",\
	"-XX:MetaspaceSize=800m",\
	"-XX:MaxMetaspaceSize=800m",\
	"-XX:MinHeapFreeRatio=10",\
	"-XX:MaxHeapFreeRatio=80",\
	"-Duser.timezone=GMT+8", \
	"-jar", "/app/awd.cloud.platform.webs.charts-1.0-SNAPSHOT.jar", \
	"--eureka.serviceUrl=http://192.168.4.30:3001/eureka/,http://192.168.4.30:3002/eureka/,http://192.168.4.30:3003/eureka/", \
	"--hostname=awd-mis-charts-web", \
	"--hostip=192.168.4.30", \
	"--cas.host=http://192.168.4.30:8899/cas", \
	"--config.serviceUrl=http://192.168.4.30:3000", \
	"--config.lable=awd-mis-servers-manager", \
	"--config.profile=dev", \
	"--redisurl=192.168.4.30",\
	"--redisport=6379",\
	"--rabbitmqurl=192.168.4.30",\
	"--rabbitmqport=5672",\
	"--rabbitmqname=awd",\
	"--rabbitmqpass=awd",\
	"--port=12103"]

#HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8080/health || exit 1

EXPOSE 12103