FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/*.jar /app/livraria-api.jar

EXPOSE 8083

CMD java -XX:+UseContainerSupport -Xmx300m -Xms300m -Dserver.port=${PORT} -jar livraria-api.jar