# Start with a base image containing Java runtime
FROM registry.cn-beijing.aliyuncs.com/bmwpoc/jdk8

# Add Maintainer Info
MAINTAINER Bing <yuanbing1113@gmail.com>

# Set Env
ENV TZ Asia/Shanghai

# The application's jar file
ARG JAR_FILE=target/sm-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]                           