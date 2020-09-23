FROM openjdk

WORKDIR /app

COPY target/sistema-importacao-arquivo-0.0.1-SNAPSHOT.jar /app/sia.jar

ENTRYPOINT ["java", "-jar", "sia.jar"]