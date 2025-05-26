#!/bin/sh

echo "Desplegando Proyecto"

mvn clean package -DskipTests

docker-compose up --build -d

docker ps

echo "EndPoint de registro de Imágenes: http://localhost:2100/api/catImages/registerImage"
echo "EndPoint de conteo de Imágenes: http://localhost:2100/api/catImages/countImages"

read -p "Presiona ENTER para cerrar este terminal..."