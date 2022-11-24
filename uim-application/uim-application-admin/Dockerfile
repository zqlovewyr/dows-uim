FROM openjdk:11

ENV LANG=en_US.UTF-8
ENV LC_ALL=en_US.UTF-8
ENV TZ=Asia/Shanghai

ENV JAVA_OPTIONS=""
ENV PARAMS=""

#多阶段构建方式：ADD maven/ /opt/
COPY target/app.jar /opt/app.jar

# 记录一下最后保存进来的时间
RUN touch /dows/app.jar

# docker run -e JAVA_OPTIONS="-Xmx -Xms..." imageName
ENTRYPOINT [
"java",
'$JAVA_OPTIONS',
"-Dfile.encoding=UTF-8",
"-Duser.timezone=GMT+08",
"-Djava.security.egd=file:/dev/./urandom",
"-jar","/dows/app.jar",
'$PARAMS'
]

EXPOSE 8080