# Utilizando uma imagem base do JDK
FROM openjdk:17-jdk-slim

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o arquivo JAR para o container
COPY target/estoque-0.0.1-SNAPSHOT.jar app.jar

# Expondo a porta 8080
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
