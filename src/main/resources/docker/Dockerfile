FROM java:8
VOLUME /tmp
ADD res-cms.jar /opt/border.jar
RUN sh -c 'touch /opt/border.jar'
COPY lib /opt/lib
ENV JAVA_OPTS="-server -Dwebapp.name=sitework-web -XX:+UseParallelGC -XX:+AggressiveOpts -XX:NewRatio=5 -Xms128m -Xmx512m -Dloader.path=/opt/lib/"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /opt/border.jar" ]