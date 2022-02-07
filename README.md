# Challenge: Api Rest Login
 
 Api Rest modulo login 
 
 Link de collection Postman (local): https://www.getpostman.com/collections/95a53b62bfb5ba127259
  
 Opciones para usar la api:
 
 ### DockerHub Publico
 
 Con el comando: docker pull jorgerolon1998/springboot-postgresql
 
 ### Uso local sin docker
 
 Requerimientos:
 
 - jdk 11
 - graddle 7.1
 - git
 
 (Debe configurar el datasorce de postgre que quiere usar)
 
 Una vez descargado el repositorio:

 - gradle build
 - gradle bootRun 

(puerto por defecto 8080, recuerde configurar datasource)
  
  
 
 ### EC2 de AWS
 
 Con la collection (EC2 AWS): https://www.getpostman.com/collections/34cccc49e24259e819df

_________________________________________________

 ## Uso de API

 Herramientas utilizadas:
 
 - jdk 11
 - graddle
 - gson
 - lombok
 - spring boot
 - jwt
 - postgresql

### Servicio para crear un usuario: 

Crea un usuario asignandole roles (los roles posibles son user y admin)
Este recurso es publico

- **URL: URL_BASE/api/auth/signup**
- **Method:** POST
- **Body:** JSON :
```javascript
{
  "username": "jorge",
  "email": "jorge@gmail.com",
  "password":"123456789",
  "role": ["user", "admin"]          
}
```
 - **Respuesta Ejemplo:** 
  
```javascript
{
  "message": "User registered successfully!"
}
```
### Servicio para logear al usuario: 

Logea un usuario por username y password
Este recurso es publico

- **URL: URL_BASE/api/auth/signin**
- **Method:** POST
- **Body:** JSON :
```javascript
{
  "username":"jorge",
  "password":"123456789"
}
```
 - **Respuesta Ejemplo:** 
  
```javascript
{
    "id": 1,
    "username": "jorge",
    "email": "jorge@gmail.com",
    "roles": [
        "ROLE_ADMIN",
        "ROLE_USER"
    ]
}
```
El usuario va a quedar logeado en la cookies, revise poder visualizarla

![image](https://user-images.githubusercontent.com/50910693/152861960-b46faa24-abb3-4342-afd8-4c2a43257ec0.png)

### Servicio para finalizar sesion del usuario: 

Logea un usuario por username y password
Este recurso es publico

- **URL: URL_BASE/api/auth/signout**
- **Method:** POST

 - **Respuesta Ejemplo:** 
  
```javascript
{
  "message": "You've been signed out!"
}
```
Va a poder visualizar que el token de esta sesion ya no se encuentra

![image](https://user-images.githubusercontent.com/50910693/152862574-c6a2ab4a-2442-4298-aa5d-2d8ced4a9cad.png)

### Servicio sumar dos numeros enteros: 

Servicio que suma dos valores enteros
#Para acceder a este recurso es necesario por lo menos tener el rol de user

- **URL: URL_BASE/api/calculator/sum/{valor1}/{valor2}**
- **Method:** GET

 - **Respuesta Ejemplo:** 
  
```javascript
{
  "message": "The result of the operation is: 12."
}
```
### Servicio para ver el historico de solicitudes: 

Servicio que devuelve el historico de solicitudes paginado almacenado en la base de datos
Para acceder a este recurso se requiere el rol admin

- **URL: URL_BASE/api/history/request?page={page}&size={size} **
- **Method:** GET

 - **Respuesta Ejemplo:** 
  
```javascript
{
  "paging": {
      "total": 5,
      "size": 1,
      "page": 0
  },
  "items": [
      {
          "id": 1,
          "endpoint": "/api/auth/signup",
          "method": "POST",
          "body": "{\"username\":\"jorge\",\"email\":\"jorge@gmail.com\",\"role\":[\"admin\",\"user\"],\"password\":\"123456789\"}",
          "date": "2022-02-07T19:55:27.903+00:00"
      },
      {
          "id": 2,
          "endpoint": "/api/auth/signin",
          "method": "POST",
          "body": "{\"username\":\"jorge\",\"password\":\"123456789\"}",
          "date": "2022-02-07T19:55:33.972+00:00"
      },
      {
          "id": 3,
          "endpoint": "/api/auth/signout",
          "method": "POST",
          "body": null,
          "date": "2022-02-07T19:59:30.145+00:00"
      },
      {
          "id": 4,
          "endpoint": "/api/auth/signin",
          "method": "POST",
          "body": "{\"username\":\"jorge\",\"password\":\"123456789\"}",
          "date": "2022-02-07T20:03:32.827+00:00"
      },
      {
          "id": 5,
          "endpoint": "/api/calculator/sum/10/2",
          "method": "GET",
          "body": null,
          "date": "2022-02-07T20:03:35.771+00:00"
      }
  ]
}
```

