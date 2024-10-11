# 🚀 Api franquicias

plantilla para consumo del api de franquicias, sucursales y productos.
**Nota**: los métodos delete aplican eliminación en cascada

#### **1: Definir variables**

establecer el valor para la variable `base_url` valor actual localhost

#### 2: Gestión franquicias

**Métodos HTTP**: POST, GET, PUT, y DELETE.  
**uri:** {{base_url}}/api/v1/franquicia

#### 2.1 Crear:

**método**: POST  
**payload-body**: JSON

| **Nombre de campo** | **Tipo** | **Obligatorio** |
| --- | --- | --- |
| name | string | Si |

**Respuesta:**_JSON  
_

```
{
    "id": 1,
    "name": "Franquisia B"
}

 ```

#### 2.2 Listar:

##### **método**: GET

**Respuesta:**_JSON

```
[
    {
        "id": 1,
        "name": "Franquisia A"
    },
    {
        "id": 2,
        "name": "Franquisia B"
    }
]

 ```

#### 2.3 Editar:

**uri:** {{base_url}}/api/v1/franquicia/1  
**nota**: cambiar el valor del id

##### **método**: Put

**payload-body**: JSON

| **Nombre de campo** | **Tipo** | **Obligatorio** |
| --- | --- | --- |
| name | string | Si |

**Respuesta:**_JSON

```
{
    "id": 1,
    "name": "Franquicia A2"
}

 ```

#### 2.4 Eliminar:

**uri:** {{base_url}}/api/v1/franquicia/1  
**nota**: cambiar el valor del id

##### **método**: DELETE

**Respuesta: EMPTY**

#### 3: Gestión sucursales

**Metodos HTTP**: POST, GET, PUT, y DELETE.  
**uri:** {{base_url}}/api/v1/sucursal

#### 3.1 Crear:

**método**: POST  
**payload-body**: JSON

| **Nombre de campo** | **Tipo** | **Obligatorio** |
| --- | --- | --- |
| franchiseId | number | Si |
| name | string | Si |

**Respuesta:**_JSON  
_

```
{
    "id": 1,
    "franchiseId": 1,
    "name": "Sucursal B"
}

 ```

#### 3.2 Listar:

##### **método**: GET

**Respuesta:**_JSON

```
[
    {
        "id": 1,
        "franchiseId": 1,
        "name": "Sucursal B"
    },
    {
        "id": 2,
        "franchiseId": 1,
        "name": "Sucursal A"
    }
]

 ```

#### 3.3 Editar:

**uri:** {{base_url}}/api/v1/sucursal/1  
**nota**: cambiar el valor del id

##### **método**: Put

**payload-body**: JSON

| **Nombre de campo** | **Tipo** | **Obligatorio** |
| --- | --- | --- |
| franchiseId | number | No |
| name | string | No |

**Respuesta:**_JSON

```
{
    "id": 1,
    "franchiseId": 2,
    "name": "Sucursal A2"
}

 ```

#### 3.4 Eliminar:

**uri:** {{base_url}}/api/v1/sucursal/1  
**nota**: cambiar el valor del id

##### **método**: DELETE

**Respuesta: EMPTY**

#### 4: Gestión Porductos

**Metodos HTTP**: POST, GET, PUT, y DELETE.  
**uri:** {{base_url}}/api/v1/producto

#### 4.1 Crear:

**método**: POST  
**payload-body**: JSON

| **Nombre de campo** | **Tipo** | **Obligatorio** |
| --- | --- | --- |
| subsidiaryId | number | Si |
| name | string | Si |
| stock | number | Si |

**Respuesta:**_JSON  
_

```
{
    "id": 1,
    "subsidiaryId": 1,
    "name": "Producto E",
    "stock": 20
}

 ```

#### 4.2 Listar:

##### **método**: GET

**Respuesta:**_JSON

```
[
    {
        "id": 1,
        "subsidiaryId": 1,
        "name": "Producto E",
        "stock": 20
    },
    {
        "id": 2,
        "subsidiaryId": 2,
        "name": "Producto E",
        "stock": 5
    },
    {
        "id": 3,
        "subsidiaryId": 3,
        "name": "Producto F",
        "stock": 15
    },
    {
        "id": 4,
        "subsidiaryId": 3,
        "name": "Producto G",
        "stock": 1
    }
]

 ```

#### 4.3 Editar:

**uri:** {{base_url}}/api/v1/producto/1  
**nota**: cambiar el valor del id

##### **método**: Put

**payload-body**: JSON

| **Nombre de campo** | **Tipo** | **Obligatorio** |
| --- | --- | --- |
| subsidiaryId | number | No |
| name | string | No |
| stock | number | No |

**Respuesta:**_JSON

```
{
    "id": 1,
    "subsidiaryId": 1,
    "name": "Prueba",
    "stock": 12
}

 ```

#### 4.4 Eliminar:

**uri:** {{base_url}}/api/v1/producto/1  
**nota**: cambiar el valor del id

##### **método**: DELETE

#### **Respuesta: EMPTY

**4.5 Producto con mas stock por franquicia:

**uri:** {{base_url}}/api/v1/producto/franquicia/1  
**nota**: cambiar el valor del id

##### **método**: GET

**Respuesta:**_JSON

```
[
    [
        "Producto E",
        "Sucursal A",
        5
    ],
    [
        "Producto F",
        "Sucursal C",
        15
    ]
]

 ```