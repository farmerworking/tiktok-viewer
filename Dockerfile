FROM maven:3.8.3-openjdk-17

COPY . .
RUN mvn clean package

#  docker run -d -p 8080:8080 tiktok-viewer java -jar /target/tiktok-viewer-1.0-SNAPSHOT.jar