FROM tomcat:8.5.9-jre8
ADD  ./target/awd.cloud.suppers.report-1.0-SNAPSHOT.war webapps/report.war
ADD  ./server.xml conf/server.xml
ADD  ./tomcat-users.xml conf/tomcat-users.xml
#COPY  ./catalina.sh bin/catalina.sh
#RUN  chmod +x bin/catalina.sh
#ENV JAVA_OPTS = -Xms200m -Xmx200m -XX:CompressedClassSpaceSize=128m -XX:MetaspaceSize=200m -XX:MaxMetaspaceSize=200m

ENV JAVA_OPTS  -Xms200m
ENV JAVA_OPTS  -Xmx800m
ENV JAVA_OPTS  -XX:MinHeapFreeRatio=10
ENV JAVA_OPTS  -XX:MaxHeapFreeRatio=80

CMD ["catalina.sh", "run", "-Duser.timezone=GMT+8"]
EXPOSE 8086