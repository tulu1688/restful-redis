FROM tulu1688/alpine-jdk:base
MAINTAINER  Kai.Tran<ngockhai.tulu@gmail.com>

RUN mkdir -p /opt/
COPY target/*.jar /opt/service.jar

ENTRYPOINT ["/bin/sh", "-c", "java -Dredis.cluster=$REDIS_CLUSTER -Dserver.port=$SERVER_PORT -jar /opt/service.jar"]
