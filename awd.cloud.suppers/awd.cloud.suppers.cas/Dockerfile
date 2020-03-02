FROM tomcat:8.5.9-jre8

ADD  ./target/cas.war webapps/
ADD  ./server.xml conf/server.xml
ADD  ./tomcat-users.xml conf/tomcat-users.xml

COPY ./etc/cas    /etc/cas

ENV JAVA_OPTS  -Xms200m
ENV JAVA_OPTS  -Xmx1024m
ENV JAVA_OPTS  -XX:MinHeapFreeRatio=10
ENV JAVA_OPTS  -XX:MaxHeapFreeRatio=80

CMD ["catalina.sh", "run", "-Duser.timezone=GMT+8"]
EXPOSE 8898