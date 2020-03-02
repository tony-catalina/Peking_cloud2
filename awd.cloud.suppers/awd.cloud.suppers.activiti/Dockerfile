FROM java:8u72-jre

MAINTAINER njawd sgh <306165699@qq.com>

ADD ./target/awd.cloud.suppers.activiti-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/awd.cloud.suppers.activiti-1.0-SNAPSHOT.jar",\
	"--eureka.serviceUrl=http://192.168.4.50:3001/eureka/,http://192.168.4.50:3002/eureka/,http://192.168.4.50:3003/eureka/",\
	"--hostname=awd-mis-activiti-server",\
	"--redisUrl=192.168.4.50",\
	"--redisPort=6379",\
	"--dataSourceUrl=jdbc:mysql://192.168.4.103:3307/soa_manager?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false",\
	"--dataSourceUserName=root",\
	"--dataSourceUserPassword=root",\
	"--txurl=http://192.168.4.50:8761",\
	"--txdriver=com.mysql.jdbc.Driver",\
	"--txdb=jdbc:mysql://192.168.4.50:3307/soa_logs?useUnicode=true&amp;characterEncoding=utf8&useSSL=false",\
	"--txuser=root",\
	"--txpass=root",\
	"--port=4002"]



#HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8080/health || exit 1

EXPOSE 4002