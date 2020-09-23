FROM openjdk

WORKDIR /app

COPY build/libs/sistemaimportacaoarquivo-0.0.1-SNAPSHOT.jar /app/sia.jar

ENTRYPOINT ["java", "-jar", "sia.jar"]