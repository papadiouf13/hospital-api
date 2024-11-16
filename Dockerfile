# Utilisez une image de base OpenJDK
FROM openjdk:17-jdk-slim

# Créez un répertoire de travail
WORKDIR /app

# Copiez le fichier pom.xml et téléchargez les dépendances
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copiez le code source et construisez l'application
COPY src ./src
RUN mvn package

# Spécifiez le port sur lequel l'application écoute
EXPOSE 8080

# Démarrez l'application
CMD ["java", "-jar", "target/hospital-api.jar"]
