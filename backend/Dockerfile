FROM maven:3.9.6-eclipse-temurin-21-alpine
RUN apk add --no-cache curl   # <-- adicione esta linha
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
# COPY src ./src
EXPOSE 8080
CMD ["mvn", "spring-boot:run"]