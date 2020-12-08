FROM maven:3.6.3-jdk-11 AS builder

COPY ./src/ /root/src
COPY ./pom.xml /root/
COPY ./checkstyle.xml /root/
WORKDIR /root
RUN mvn package
RUN java -Djarmode=layertools -jar /root/target/demo-0.0.1-SNAPSHOT.jar list
RUN java -Djarmode=layertools -jar /root/target/demo-0.0.1-SNAPSHOT.jar extract
RUN ls -l /root

FROM openjdk:11.0.6-jre

ENV TZ=UTC
ENV DB_IP=ec2-54-246-67-245.eu-west-1.compute.amazonaws.com
ENV DB_PORT=5432
ENV DB_USER=mavymjmkzqqrrz
ENV DB_PASSWORD=a64f0670d373fa7b1a4dd90a0a5fbc51ee84acfadedebd45effcbb264edde13b
ENV DB_DBNAME=dcurvudgns8pm3


COPY --from=builder /root/dependencies/ ./
COPY --from=builder /root/snapshot-dependencies/ ./

RUN sleep 10
COPY --from=builder /root/spring-boot-loader/ ./
COPY --from=builder /root/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher","-XX:+UseContainerSupport -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 -Xms512m -Xmx512m -XX:+UseG1GC -XX:+UseSerialGC -Xss512k -XX:MaxRAM=72m"]

EXPOSE 1089
