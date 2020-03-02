FROM java:8u72-jre

MAINTAINER njawd sgh <306165699@qq.com>

#ENV JAVA_OPTS  -Xms200m
#ENV JAVA_OPTS  -Xmx600m
#ENV JAVA_OPTS  -XX:MinHeapFreeRatio=10
#ENV JAVA_OPTS  -XX:MaxHeapFreeRatio=80
#ENV JAVA_OPTS -server -Xms200m -Xmx600m -XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=80

ENV LANG zh_CN.UTF-8
ENV LC_ALL zh_CN.UTF-8

ADD ./target/awd.cloud.suppers.message.manager-1.0-SNAPSHOT.jar  /app/

CMD ["java","-Xms300m","-Xmx550m",\
	"-XX:CompressedClassSpaceSize=128m",\
	"-XX:MetaspaceSize=500m",\
	"-XX:MaxMetaspaceSize=500m",\
	"-XX:MinHeapFreeRatio=10",\
	"-XX:MaxHeapFreeRatio=80",\
	"-Duser.timezone=GMT+8", \
	"-Dfile.encoding=UTF-8",\
	"-jar", "/app/awd.cloud.suppers.message.manager-1.0-SNAPSHOT.jar",\
	"--eureka.serviceUrl=http://192.168.4.50:3001/eureka/,http://192.168.4.50:3002/eureka/,http://192.168.4.50:3003/eureka/", \
	"--hostname=awd-mis-message-server",\
	"--hostip=192.168.4.50",\
	#"--config.lable=awd-mis-servers-manager",\
	#"--config.serviceUrl=http://192.168.4.50:3000",\
	#"--config.profile=dev",\
	"--cas.host=http://192.168.4.50:8899/cas",\
	"--port=14117"]

#ENV LANG zh_CN.UTF-8
#ENV LC_ALL zh_CN.UTF-8
#ENV JAVA_TOOL_OPTIONS -Dfile.encoding=utf-8

#时区同步
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  && echo 'Asia/Shanghai' >/etc/timezone

#HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8080/health || exit 1

EXPOSE 14117