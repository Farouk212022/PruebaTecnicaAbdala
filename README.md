# Prueba Técnica Tyba - Credicorp

Carlos Farouk Abdalá Rincón

## Instrucciones de uso

Tras descargar el repositorio del proyecto, inicialmente es necesario verificar la instalación de Maven para poder ejecutar comandos de gestión del proyecto. Para verificar, ingrese en el terminal el siguiente comando:

```cpp
mvn -version
```
Debería ver el versionamiento de la siguiente manera

![Instalación Maven](images/mvn.png)

En caso de que no vea la versión por favor dirigirse a este link e instale Maven:
https://maven.apache.org/download.cgi

De igual manera verifique la instalación de Docker:
```cpp
docker -version
```
![Instalación Docker](images/docker.png)

En caso de no tenerlo instalado, puede seguir el siguiente link: https://docs.docker.com/desktop/

Recuerde verificar que el servicio de Docker Engine se esté ejcutando antes de seguir.

Para continuar, diríjase a la carpeta en al que descargo el repositorio. Una vez se encuentre en la carpeta, oprima click derecho sobre esta y busque la opción "Abrir en Terminal":

![Abrir Terminal](images/abrirEnTerminal.png)

una vez en el terminal, ejecute el siguiente comando:
```cpp
./run.sh
```
Es posible que se le solicite abrir alguna aplicación, en ese caso seleccione la que prefiera que tenga funciones para ejecución de comandos.
Debería ver lo siguiente o de manera muy similar:

![Comando de Inicio Terminal](images/composeTerminal.png)

Si se fija al final de los mensajes, ya puede acceder a los endpoints.

## Endpoints

El sistema back end desarrollado tiene dos endpoints que son los siguientes:

- http://localhost:2100/api/catImages/registerImage: Endpoint que recupera imágenes de los gatos, las almacena en formato binario en la base de datos e incluye fecha de último llamado a cada imagen registrada y de creación de cada registro.

![EndPoint de Registro](images/endPoint1.png)
- http://localhost:2100/api/catImages/countImages: En este endpoint se recuperarán la cantidad de registros que tiene la base de datos.

![CEndPoint de Conteo](images/endPoint2.png)
Es necesario aclarar que por facilidad de acceso, ambos endpoints están diseñados para recibir peticiones de tipo "GET" para poder ser accedidos desde el navegador web de preferencia sin necesidad de un desarrollo de front.

## Imagen de Docker Hub

Aunque se hizo la implementación pertinente de Continuous Deployement (CD) para el despliegue automático de la imagen cada vez que se haga un push a la rama main del repositorio,
en el siguiente link se puede encontrar la imagen "pruebatecnica" que corresponde al sistema desarrollado:
https://hub.docker.com/r/farouk21/pruebatecnica

## Recomendaciones Adicionales 

En caso de presentar algún problema para la ejecución del run.sh o del Docker Compose. Para poder hacer uso del servicio únicamente debe ejecutar una base de datos de MySQL con la variables de entorno que puede encontrar en el application.properties en el repositorio y con una red llamada "mynetwork" una vez montado el contenedor de base de datos,debería poder descargar desde DockerHub la imagen del servicio y ejecutar el contenedor de manera apropiada.
