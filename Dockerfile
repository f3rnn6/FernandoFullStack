# Multi-stage build para optimizar tamaño
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

# Directorio de trabajo
WORKDIR /app

# Copiar archivos necesarios
COPY pom.xml .
COPY src ./src

# Compilar aplicación (con verificación)
RUN mvn clean package -DskipTests && \
    ls -la /app/target/ # Verificación de archivos generados

# Imagen final más liviana
FROM eclipse-temurin:17-jre-alpine

# Crear usuario y grupo no-root
RUN addgroup -S appuser && adduser -S appuser -G appuser

# Directorio de trabajo
WORKDIR /app

# Copiar JAR con nombre explícito y permisos
COPY --from=build --chown=appuser:appuser /app/target/serv_usuario-*.jar ./app.jar

# Verificar que el JAR existe y tiene permisos correctos
RUN ls -la /app && \
    echo "JAR size: $(du -h app.jar)" && \
    java -version

# Variables de entorno para JVM
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Puerto que expone la aplicación
EXPOSE 8080

# Usuario no-root
USER appuser

# Comando para ejecutar la aplicación (con ruta absoluta)
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar"]