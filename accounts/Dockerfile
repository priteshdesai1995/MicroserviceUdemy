#Start the java from the DockerHub
FROM openjdk:17-slim

# add the author Details
MAINTAINER brainvire.com

#VOLUME /tmp
#EXPOSE 8080
#ARG JAR_FILE=target/accounts-0.0.1-SNAPSHOT.jar

#copy the account jar to the Docker
#ADD ${JAR_FILE} accounts-0.0.1-SNAPSHOT.jar
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# run the jar file with the below command
ENTRYPOINT ["java","-jar","/accounts-0.0.1-SNAPSHOT.jar"]