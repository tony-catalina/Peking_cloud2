FROM java:8u72-jre

MAINTAINER njawd sgh <306165699@qq.com>

#ENV JAVA_OPTS  -Xms200m
#ENV JAVA_OPTS  -Xmx600m
#ENV JAVA_OPTS  -XX:MinHeapFreeRatio=10
#ENV JAVA_OPTS  -XX:MaxHeapFreeRatio=80
#ENV JAVA_OPTS -server -Xms200m -Xmx600m -XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=80

ENV LANG zh_CN.UTF-8
ENV LC_ALL zh_CN.UTF-8

ADD ./target/awd.cloud.platform.webs.platform-1.0-SNAPSHOT.jar  /app/

CMD ["java","-Xms300m","-Xmx550m",\
	"-XX:CompressedClassSpaceSize=128m",\
	"-XX:MetaspaceSize=500m",\
	"-XX:MaxMetaspaceSize=500m",\
	"-XX:MinHeapFreeRatio=10",\
	"-XX:MaxHeapFreeRatio=80",\
	"-Duser.timezone=GMT+8", \
	"-Dfile.encoding=UTF-8",\
	"-jar", "/app/awd.cloud.platform.webs.platform-1.0-SNAPSHOT.jar",\
	"--eureka.serviceUrl=http://192.168.4.30:3001/eureka/,http://192.168.4.30:3002/eureka/,http://192.168.4.30:3003/eureka/", \
	"--hostname=awd-mis-platform-web",\
	"--hostip=192.168.4.30",\
	"--config.lable=awd-mis-servers-manager",\
	"--config.serviceUrl=http://192.168.4.30:3000",\
	"--config.profile=test",\
	"--cas.host=http://192.168.4.30:8899/cas",\
	#"--app.host=http://192.168.4.198:9000",\
	"--redisurl=192.168.4.30",\
	"--redisport=6379",\
	"--rabbitmqurl=192.168.4.30",\
	"--rabbitmqport=5672",\
	"--rabbitmqname=awd",\
	"--rabbitmqpass=awd",\
	"--port=12103"]

#ENV LANG zh_CN.UTF-8
#ENV LC_ALL zh_CN.UTF-8
#ENV JAVA_TOOL_OPTIONS -Dfile.encoding=utf-8


#闂傚倸鍊搁崐椋庣矆娓氾拷楠炴牠顢曢敃锟介悿顕�鏌涢幇顓犮偞闁哄鐗楃换娑㈠箣閻愮數鍙濋梺缁樺笒閿曘儵濡甸崟顖氬唨妞ゆ劦婢�缁爼鏌ｉ悢鍝ユ嚂缂佺姵鎹囬獮鍐潨閿熶粙骞冨▎鎰闁告劏鏅╅敓鑺ヮ殜濮婃椽宕ㄦ繝鍐炬闂佺懓鍤栭幏锟�
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  && echo 'Asia/Shanghai' >/etc/timezone 

#HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8080/health || exit 1

EXPOSE 12103