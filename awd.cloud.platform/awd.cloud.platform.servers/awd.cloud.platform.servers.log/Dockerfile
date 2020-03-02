FROM java:8u72-jdk

MAINTAINER njawd sgh <306165699@qq.com>

ADD ./target/awd.cloud.platform.servers.log-1.0-SNAPSHOT.jar /app/

ADD ./src/main/resources/fdfs_client.conf /app/

CMD ["java","-Xms500m","-Xmx1600m",\
	"-XX:CompressedClassSpaceSize=128m",\
	"-XX:MetaspaceSize=1600m",\
	"-XX:MaxMetaspaceSize=1600m",\
	"-XX:MinHeapFreeRatio=10",\
	"-XX:MaxHeapFreeRatio=80",\
	"-Duser.timezone=GMT+8", \
	"-jar","/app/awd.cloud.platform.servers.log-1.0-SNAPSHOT.jar",\
	"--eureka.serviceUrl=http://10.9.15.62:3001/eureka/,http://10.9.15.62:3002/eureka/,http://10.9.15.62:3003/eureka/", \
	"--hostname=awd-mis-logs-server",\
	"--hostip=10.9.15.62",\
	"--tracker_server_url=10.9.15.62:22122", \
	"--config.serviceUrl=http://10.9.15.62:3000", \
	"--config.lable=awd-mis-servers-manager", \
	"--config.profile=dev", \
	"--redisurl=10.9.15.62",\
	"--redisport=6379",\
	"--rabbitmqurl=10.9.15.62",\
	"--rabbitmqport=5672",\
	"--rabbitmqname=awd",\
	"--rabbitmqpass=awd",\
	"--urlW=jdbc:mysql://10.9.15.62:3307/soa_logs?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",\
	"--myUserW=root",\
	"--myPassW=root",\
	"--urlR=jdbc:mysql://10.9.15.62:3307/soa_logs?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",\
	"--myUserR=root",\
	"--myPassR=root",\
	"--port=12101"]

ENV JAVA_OPTS  -Xms500m
ENV JAVA_OPTS  -Xmx1600m
ENV JAVA_OPTS  -XX:MinHeapFreeRatio=10
ENV JAVA_OPTS  -XX:MaxHeapFreeRatio=80
	 
ENV LANG zh_CN.UTF-8
ENV LC_ALL zh_CN.UTF-8
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=utf-8	 

#闂傚倸鍊搁崐宄懊归崶褏鏆﹂柛顭戝亝閸欏繒锟借娲栧ú銊╂儗閸℃褰掓晲閸偅缍堝┑鐐叉噽婵挳婀侀梺缁樏Ο濠囧磿閹扮増鐓曢悗锝呭悁闁垶鏌＄仦鍓ф创闁糕晛瀚板畷姗�顢旀担璇℃綌缂傚倸鍊风欢锟犲磻閸曨厾鐭撶憸鐗堝笒缁犳牜鎲搁悧鍫濈瑨濞磋偐濞�閺屾盯寮撮妸銉ヮ潽闂佺瀛╅幑鍥ь潖婵犳艾纾兼慨姗嗗幖閻濇棃姊洪悷鏉挎闁瑰嚖鎷�
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  && echo 'Asia/Shanghai' >/etc/timezone 

#HEALTHCHECK --interval=10s --timeout=3s --retries=24 CMD curl -f http://localhost:3000/health || exit 1

EXPOSE 12101