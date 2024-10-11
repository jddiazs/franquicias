# API- Franquicias!

API para la gestión de franquicias, sucursales  y productos.


# Despliegue



## Docker
###  Requisitos previos
- Instalación de [docker compose](https://docs.docker.com/compose/install/).
### Paso a Paso
- Compilar el proyecto con el siguiente comando `sudo ./gradlew bootJar`
- Construir e inicializar dockers con el siguiente comando  `sudo docker compose up`
- (usar postman)[postman/README.md]

## Terraform
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
```
