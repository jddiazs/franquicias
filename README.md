# API- Franquicias!

API para la gestión de franquicias, sucursales  y productos.


# Despliegue



## Docker
###  Requisitos previos
- Instalación de [docker compose](https://docs.docker.com/compose/install/).

### Paso a Paso
- Compilar el proyecto con el siguiente comando `sudo ./gradlew bootJar`
- Construir e inicializar dockers con el siguiente comando  `sudo docker compose up`
- [usar postman](postman/README.md)
### Desplegar con MYSQL
El proyecto por defecto esta configurado para desplegar y conectarse a una Base de datos Postgres si se desea desplegar con MySql se debe hacer los siguientes ajustes.

- Buscar el archivo `build.gradle` y eliminar la dependencia `runtimeOnly 'org.postgresql:postgresql'`  y habilitar la dependencia `implementation 'com.mysql:mysql-connector-j:9.0.0'`
- Bucar el archivo `docker-compose.yml` y remplazar las variables de entono de java_app por las siguientes

  ```
    environment:          
      - URL_DB=jdbc:mysql://java_db:3306/nequi  
      - USER_DB=nequi_u 
      - PASS_DB=root
- Sustituir el bloque de java_db por el siguiente
  ```
  java_db:
    container_name: java_db
    image: bitnami/mysql:5.7
    volumes:
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql
    ports:
      - 3306:3306
    environment:
      MYSQL_DATABASE: nequi
      MYSQL_USER: nequi_u
      MYSQL_PASSWORD: root
      MYSQL_ROOT_PASSWORD: root
      MYSQL_AUTHENTICATION_PLUGIN: mysql_native_password

- Ejecutar el Paso a Paso nuevamente

## [Terraform](terraform/README.md)
###  Requisitos previos
- Instalación de [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html).
- Instalación de  [Terraform](https://developer.hashicorp.com/terraform/install).

### Paso a Paso
- Ubicarse en el directorio  terraform `cd terraform`
- Añadir valores a las variables del archivo **secrets.tfvars**
- Generar archivo ssh para conexiones a las instancias `ssh-keygen -t rsa -b 4096 -m pem -f nequi_kp && openssl rsa -in nequi_kp -outform pem && chmod 400 nequi_kp.pem`

- Inicializar proyecto  de terraform y luego aplicar los cambios
  ```
      terraform init
      terraform apply -var-file="secrets.tfvars"

- Copiar url de conexión de base de datos y asígnalo a la variable de entorno del java_app para conectar a la base de datos
- volver a desplegar los cambios
