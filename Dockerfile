# Usiamo un'immagine base ufficiale che contiene Java 17
FROM openjdk:17-jdk-slim

# Impostiamo una cartella di lavoro all'interno del container
WORKDIR /app

# Copiamo i file del nostro progetto necessari per la build
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Scarichiamo le dipendenze
RUN ./mvnw dependency:go-offline

# Copiamo il resto del codice sorgente
COPY src ./src

# Compiliamo l'applicazione
RUN ./mvnw package -DskipTests

# Esponiamo la porta 8080
EXPOSE 8080

# Comando per avviare l'applicazione
ENTRYPOINT ["java", "-jar", "target/gym-planner-0.0.1-SNAPSHOT.jar"]