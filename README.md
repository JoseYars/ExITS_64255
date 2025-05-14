passwordsql= Escolario123

http requets
###
POST http://localhost:8080/libros
Content-Type: application/json

{
  "titulo": "El hols",
  "autor": "Patrick ddss"
}

###
GET http://localhost:8080/libros

###
GET http://localhost:8080/libros/2

###

PUT http://localhost:8080/libros/9
Content-Type: application/json

{
  "titulo": "El nombre del viento",
  "autor": "Patrick Rothfuss"
}

<> 2025-05-14T163916.200.json

###
###
DELETE http://localhost:8080/libros/2

### //personas



###
GET http://localhost:8080/personas
###
GET http://localhost:8080/personas/3

###
POST http://localhost:8080/personas
Content-Type: application/json

{
  "nombre": "Juan",
  "apellido": "Pérez"
}

###
PUT http://localhost:8080/personas/3
Content-Type: application/json

{
  "id": 3,
  "nombre": "jose",
  "email": "jose@prueba",
  "prestamos": []
}

###
DELETE http://localhost:8080/personas/5

### /*prestamos*/
GET http://localhost:8080/personas/3/prestamos

### 

###
POST http://localhost:8080/prestamos
Content-Type: application/json

{
  "id": 0,
  "personaId": 3,
  "libroId": 2
}
