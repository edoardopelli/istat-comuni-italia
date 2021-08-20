FROM edoardopelli/openjdk

ENV LOG_LEVEL=ERROR

ARG version

COPY target/istat-comuni-api-$version.jar /comuni-italiani.jar

CMD ["java","-Xms256m", "-Xmx2048m", "-XX:MaxMetaspaceSize=256M","-XX:MetaspaceSize=32M","-Duser.timezone=UTC","-jar","/comuni-italiani.jar"]