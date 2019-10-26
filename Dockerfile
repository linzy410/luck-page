FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG jarFile
COPY ${jarFile} luck-1.0.jar
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Duser.timezone=GMT+08","-jar","/luck-1.0.jar"]