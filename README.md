¡Bienvenido al proyecto **Foro Hub Challenge Alura**! Este proyecto es parte del desafío propuesto por Alura Latam para desarrollar una API RESTful que gestione un foro en línea, permitiendo operaciones CRUD sobre tópicos.

## Descripción

El **Foro Hub** es una plataforma donde los estudiantes de Alura pueden publicar sus preguntas relacionadas con diversos cursos. Esta API permite:

- Autenticarse para obtener un token de acceso.
- Crear un nuevo tópico.
- Mostrar un tópico específico.
- Mostrar todos los tópicos.
- Actualizar un tópico existente.
- Eliminar un tópico.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **Spring Boot**: Framework para crear la API REST.
- **MySQL**: Base de datos para almacenar la información.
- **Spring Security**: Para la gestión de seguridad y autenticación.
- **JWT (JSON Web Tokens)**: Para la autenticación basada en tokens.

## Requisitos Previos

- Java 11 o superior instalado.
- IDE (IntelliJ IDEA, Eclipse, etc.).
- MySQL instalado y configurado.

## Configuración Inicial

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/ECoraline/Foro-Hub-Challenge-Alura.git
   ```

2. **Configurar la base de datos**:

    - Crear una base de datos en MySQL llamada `foro_hub`.
    - Configurar las credenciales de acceso en el archivo `application.properties` ubicado en `src/main/resources/`:

      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
      spring.datasource.username=tu_usuario
      spring.datasource.password=tu_contraseña
      ```
    - Agrega un usuario en la base de datos con la clave en formato BCrypt.

## Documentación de la API

Una vez que la aplicación esté en funcionamiento, puedes acceder a la documentación interactiva de la API en `http://localhost:8080/swagger-ui.html`.

Además, la API ofrece un endpoint abierto que proporciona toda la documentación en formato JSON:
```
GET /v3/api-docs
```
## Endpoints Principales

- `POST login`: Iniciar sesión con un usuario.
- `POST /topicos`: Crear un nuevo tópico.
- `GET /topicos`: Obtener todos los tópicos con paginación.
- `GET /topicos/{id}`: Obtener un tópico por su ID.

- `PUT /topicos/{id}`: Actualizar un tópico existente.
- `DELETE /topicos/{id}`: Eliminar un tópico por su ID.

## Autenticación

La API utiliza JWT para autenticar a los usuarios. Para acceder a los endpoints protegidos, es necesario incluir el token en el encabezado de la solicitud.

## Migraciones de Base de Datos

La estructura de la base de datos se gestiona con Flyway. Los archivos de migración se encuentran en el directorio `src/main/resources/db/migration`.

## Autor

- **ECoraline**: [GitHub](https://github.com/ECoraline)