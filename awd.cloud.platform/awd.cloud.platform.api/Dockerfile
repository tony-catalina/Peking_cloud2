FROM java:8u72-jre

MAINTAINER njawd sgh <306165699@qq.com>

ADD ./target/awd.cloud.platform.api-1.0-SNAPSHOT.jar /app/

#ENV JAVA_OPTS  -Xms200m
#ENV JAVA_OPTS  -Xmx600m
#ENV JAVA_OPTS  -XX:MinHeapFreeRatio=10
#ENV JAVA_OPTS  -XX:MaxHeapFreeRatio=80

CMD ["java","-Xms300m","-Xmx600m",\
	"-XX:CompressedClassSpaceSize=128m",\
	"-XX:MetaspaceSize=500m",\
	"-XX:MaxMetaspaceSize=500m",\
	"-XX:MinHeapFreeRatio=10",\
	"-XX:MaxHeapFreeRatio=80",\
	"-Duser.timezone=GMT+8", \
	"-jar", "/app/awd.cloud.platform.api-1.0-SNAPSHOT.jar",\
	"--eureka.serviceUrl=http://192.168.4.50:3001/eureka/,http://192.168.4.50:3002/eureka/,http://192.168.4.50:3003/eureka/", \
	"--hostname=awd-mis-platform-api", \
	"--hostip=192.168.4.50", \
	"--config.serviceUrl=http://192.168.4.50:3000", \
	"--config.lable=awd-mis-servers-manager", \
	"--config.profile=dev", \	
	"--port=9009"]
#HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8080/health || exit 1

EXPOSE 9009
