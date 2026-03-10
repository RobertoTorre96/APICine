Aquí tienes el **README final actualizado para tu portfolio**, con:

* Swagger
* GlobalExceptionHandler
* arquitectura por feature
* tu **email y GitHub**
* escrito de forma profesional para recruiters

Un README bien estructurado ayuda a que alguien entienda tu proyecto rápidamente y mejora la primera impresión del repositorio, algo clave cuando se usa GitHub como portfolio. ([Readmecodegen][1])

Puedes copiarlo directamente a `README.md`.

---

# 🎬 Cinema Reservation API

API REST desarrollada con **Spring Boot** que implementa un sistema completo de **reservas de asientos para un cine**.

El sistema permite administrar:

* usuarios
* películas
* géneros
* salas
* funciones
* reservas de asientos

Además implementa **control de concurrencia** para evitar que dos usuarios reserven el mismo asiento para la misma función.

Este proyecto fue desarrollado como práctica de **backend con Spring Boot, JPA y buenas prácticas de arquitectura**.

---

# 📌 Tabla de Contenidos

* Descripción
* Arquitectura
* Organización del proyecto
* Tecnologías
* Modelo del dominio
* Flujo del sistema
* Control de concurrencia
* Documentación de API
* Manejo global de errores
* Instalación
* Endpoints principales
* Mejoras futuras
* Autor / Contacto

---

# 📖 Descripción

Este proyecto simula el backend de un sistema de reservas de cine.

Permite:

* registrar usuarios
* administrar películas y géneros
* crear salas con generación automática de asientos
* programar funciones
* reservar asientos para una función

El sistema está diseñado para evitar **problemas de concurrencia en reservas**, uno de los problemas más comunes en sistemas de tickets.

---

# 🏗 Arquitectura

La aplicación sigue una arquitectura en capas:

```
Controller
↓
Service
↓
Repository
↓
Entity
↓
Database
```

Cada capa tiene responsabilidades claras.

| Capa       | Responsabilidad        |
| ---------- | ---------------------- |
| Controller | Exponer endpoints REST |
| Service    | Lógica de negocio      |
| Repository | Acceso a base de datos |
| Entity     | Modelo de dominio      |

---

# 📦 Organización del Proyecto

El proyecto está organizado **por feature (feature-based packaging)**.

En lugar de tener paquetes globales como:

```
service
repository
dto
```

cada entidad del dominio tiene su propio paquete.

Esto facilita:

* encontrar rápidamente la lógica relacionada
* mantener el proyecto escalable
* evitar paquetes demasiado grandes
* mejorar la mantenibilidad

Estructura simplificada:

```
cine
│
├── usuario
│   ├── dto
│   ├── repository
│   ├── service
│   └── UsuarioEntity
│
├── pelicula
│   ├── dto
│   ├── repository
│   ├── service
│   └── PeliculaEntity
│
├── reserva
│   ├── dto
│   ├── repository
│   ├── service
│   └── ReservaEntity
│
├── sala
│   ├── dto
│   ├── repository
│   ├── service
│   └── SalaEntity
│
├── funcion
│   ├── dto
│   ├── repository
│   ├── service
│   └── FuncionEntity
│
├── genero
│   ├── dto
│   ├── repository
│   ├── service
│   └── GeneroEntity
│
├── mapper
└── exception
```

---

# ⚙️ Tecnologías

Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* Spring Security
* Lombok

Documentación

* Swagger / OpenAPI

Base de datos

* MySQL / PostgreSQL

Herramientas

* Maven
* Postman

---

# 🧩 Modelo del Dominio

Entidades principales:

```
Usuario
Pelicula
Genero
PeliculaGenero
Sala
Asiento
Funcion
Reserva
ReservaAsiento
```

Relación simplificada:

```
Usuario
   │
   └── Reserva
          │
          └── ReservaAsiento
                 │
                 └── Asiento
                        │
                        └── Sala
                               │
                               └── Funcion
                                      │
                                      └── Pelicula
```

---

# 💺 Generación automática de asientos

Cuando se crea una sala, el sistema genera automáticamente los asientos según filas y columnas.

Ejemplo:

```
A1 A2 A3 A4
B1 B2 B3 B4
C1 C2 C3 C4
```

Esto permite crear salas de diferentes tamaños.

---

# 🎟 Flujo del Sistema

1️⃣ Se crea una **película**

2️⃣ Se crea una **sala**
(se generan automáticamente los asientos)

3️⃣ Se crea una **función**
(película + sala + horario)

4️⃣ Los usuarios realizan **reservas de asientos**

---

# 🔒 Control de Concurrencia

Para evitar reservas duplicadas se utiliza una restricción única en la base de datos:

```
unique(funcion_id, asiento_id)
```

Esto garantiza que:

```
funcion + asiento = único
```

Si dos usuarios intentan reservar el mismo asiento al mismo tiempo:

1. el primero se guarda correctamente
2. el segundo produce una excepción
3. el sistema informa que el asiento ya está reservado

---

# 📚 Documentación de API

La API está documentada usando **Swagger / OpenAPI**.

Permite:

* visualizar todos los endpoints
* probar requests desde el navegador
* ver modelos de request y response

Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

---

# ⚠ Manejo Global de Errores

El proyecto implementa un **Global Exception Handler** usando:

```
@ControllerAdvice
```

Esto permite:

* centralizar el manejo de errores
* devolver respuestas consistentes
* evitar duplicación de lógica en los controladores

---

# ⚙️ Instalación

### 1 Clonar repositorio

```
git clone https://github.com/RobertoTorre96/cinema-api.git
```

---

### 2 Configurar base de datos

Editar:

```
src/main/resources/application.properties
```

Ejemplo:

```
spring.datasource.url=jdbc:mysql://localhost:3306/cinema
spring.datasource.username=root
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3 Ejecutar el proyecto

Con Maven:

```
mvn spring-boot:run
```

O ejecutando:

```
CinemaApplication.java
```

---

# 📡 Endpoints principales

Usuarios

```
POST /usuarios
GET /usuarios
```

Películas

```
POST /peliculas
GET /peliculas
GET /peliculas/{id}
```

Salas

```
POST /salas
GET /salas
```

Funciones

```
POST /funciones
GET /funciones
```

Reservas

```
POST /reservas
```

Ejemplo request:

```json
{
  "usuarioId": 1,
  "funcionId": 3,
  "asientosIds": [5,6]
}
```

---

# 🚀 Mejoras Futuras

* Tests unitarios con JUnit
* Endpoint para consultar disponibilidad de asientos
* Seguridad JWT
* Cache para disponibilidad de asientos
* Rate limiting para reservas

---

# 👨‍💻 Autor

**Roberto Torre**

📧 Email
[torreroberto1996@gmail.com](mailto:torreroberto1996@gmail.com)

💻 GitHub
[https://github.com/RobertoTorre96](https://github.com/RobertoTorre96)

