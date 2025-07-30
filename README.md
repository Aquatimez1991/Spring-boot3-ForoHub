# 🚀 ForoHub API

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.10-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Maven](https://img.shields.io/badge/Maven-3.8+-red)
![JWT](https://img.shields.io/badge/JWT-Auth-purple)

**API REST completa para sistema de foro desarrollada con Spring Boot 3**

[Características](#características) • [Instalación](#instalación) • [Uso](#uso) • [API Endpoints](#api-endpoints) • [Testing](#testing)

</div>

---

## 📋 Tabla de Contenidos

- [Descripción](#descripción)
- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Uso](#uso)
- [API Endpoints](#api-endpoints)
- [Base de Datos](#base-de-datos)
- [Testing con Insomnia](#testing-con-insomnia)
- [Casos de Uso](#casos-de-uso)
- [Contribución](#contribución)

---

## 📖 Descripción

**ForoHub** es una API REST moderna desarrollada con Spring Boot 3 que permite gestionar un sistema de foro completo. Los usuarios pueden crear tópicos, responder a ellos, y gestionar sus perfiles con un sistema de autenticación JWT robusto.

### ✨ Características

- 🔐 **Autenticación JWT** - Sistema de login seguro con tokens
- 👥 **Gestión completa de usuarios** - CRUD completo con soft delete
- 💬 **Sistema de tópicos** - Crear, editar, eliminar y listar tópicos
- 💭 **Sistema de respuestas** - Responder a tópicos con gestión completa
- 🛡️ **Seguridad robusta** - Spring Security con validaciones
- 📊 **Base de datos MySQL** - Con migraciones Flyway
- 📚 **Documentación OpenAPI** - Swagger UI integrado
- ✅ **Validaciones** - Bean Validation en todos los endpoints
- 🏗️ **Arquitectura limpia** - Separación por capas y dominios

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Java** | 17 | Lenguaje de programación |
| **Spring Boot** | 3.3.10 | Framework principal |
| **Spring Security** | 6.x | Seguridad y autenticación |
| **Spring Data JPA** | 3.x | Persistencia de datos |
| **MySQL** | 8.0+ | Base de datos |
| **Flyway** | 9.x | Migraciones de BD |
| **JWT** | 4.5.0 | Tokens de autenticación |
| **Lombok** | 1.18+ | Reducción de código boilerplate |
| **SpringDoc OpenAPI** | 2.2.0 | Documentación de API |
| **Maven** | 3.8+ | Gestión de dependencias |

---

## 🚀 Instalación

### Prerrequisitos

- ☕ Java 17 o superior
- 🗃️ MySQL 8.0 o superior
- 📦 Maven 3.8 o superior

### Pasos de instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/Aquatimez1991/Spring-boot3-ForoHub.git
cd Spring-boot3-ForoHub
```

2. **Configurar la base de datos**
```sql
CREATE DATABASE forohub;
CREATE USER 'forohub_user'@'localhost' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON forohub.* TO 'forohub_user'@'localhost';
FLUSH PRIVILEGES;
```

3. **Configurar variables de entorno** (crear archivo `.env`)
```properties
DB_HOST=localhost:3306
DB_NAME=forohub
DB_USERNAME=forohub_user
DB_PASSWORD=password123
JWT_SECRET=tu_clave_secreta_muy_segura_aqui
```

4. **Compilar y ejecutar**
```bash
# Usando Maven Wrapper (recomendado)
./mvnw clean install
./mvnw spring-boot:run

# O usando Maven instalado
mvn clean install
mvn spring-boot:run
```

5. **Verificar instalación**
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html

---

## ⚙️ Configuración

### application.properties

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

# Flyway
spring.flyway.locations=classpath:db/migration

# JWT
api.security.token.secret=${JWT_SECRET}

# OpenAPI
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

---

## 💡 Uso

### 1. Registro de Usuario
```bash
curl -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "login": "usuario@foro.hub",
    "contrasena": "123456"
  }'
```

### 2. Login
```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "login": "usuario@foro.hub",
    "contrasena": "123456"
  }'
```

### 3. Crear Tópico
```bash
curl -X POST http://localhost:8080/topico \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "titulo": "Mi primer tópico",
    "mensaje": "Este es el contenido del tópico",
    "nombreCurso": "Java"
  }'
```

---

## 🔌 API Endpoints

### 🔐 Autenticación
| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `POST` | `/login` | Iniciar sesión | ❌ |

### 👥 Usuarios
| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `POST` | `/usuarios` | Registrar usuario | ❌ |
| `GET` | `/usuarios` | Listar usuarios | ✅ |
| `GET` | `/usuarios/{id}` | Obtener usuario | ✅ |
| `PUT` | `/usuarios` | Actualizar usuario | ✅ |
| `DELETE` | `/usuarios/{id}` | Eliminar usuario | ✅ |

### 💬 Tópicos
| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `POST` | `/topico` | Crear tópico | ✅ |
| `GET` | `/topico` | Listar tópicos | ✅ |
| `GET` | `/topico/{id}` | Obtener tópico | ✅ |
| `PUT` | `/topico` | Actualizar tópico | ✅ |
| `DELETE` | `/topico/{id}` | Eliminar tópico | ✅ |

### 💭 Respuestas
| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `POST` | `/respuesta` | Crear respuesta | ✅ |
| `GET` | `/respuesta` | Listar respuestas | ✅ |
| `GET` | `/respuesta/{id}` | Obtener respuesta | ✅ |
| `PUT` | `/respuesta` | Actualizar respuesta | ✅ |
| `DELETE` | `/respuesta/{id}` | Eliminar respuesta | ✅ |

---

## 🗄️ Base de Datos

### Esquema de la BD

```sql
-- Tabla usuarios
CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    login VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(300) NOT NULL,
    activo TINYINT DEFAULT 1,
    PRIMARY KEY (id)
);

-- Tabla topicos
CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    nombre_curso VARCHAR(100) NOT NULL,
    usuario_id BIGINT NOT NULL,
    activo TINYINT DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE KEY UK_titulo_mensaje (titulo, mensaje),
    CONSTRAINT FK_topicos_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Tabla respuestas
CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    solucion TINYINT DEFAULT 0,
    topico_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_respuestas_topicos FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT FK_respuestas_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
```

### Migraciones Flyway

Las migraciones se encuentran en `src/main/resources/db/migration/`:

- `V1__create-table-usuarios.sql` - Creación tabla usuarios
- `V2__create-table-topicos.sql` - Creación tabla tópicos
- `V3__alter-table-topicos-add-column-activo.sql` - Soft delete para tópicos
- `V4__crear-tabla-respuestas.sql` - Creación tabla respuestas
- `V5__alter-table-topicos-titulo-mensaje-unique.sql` - Constraint único
- `V6__alter-table-usuarios-add-column-activo.sql` - Soft delete para usuarios

---

## 🧪 Testing con Insomnia

### Importar colección

1. Importa el archivo `Insomnia_2025-07-29.yaml` en Insomnia
2. La colección incluye todos los endpoints organizados por módulos

### Variables de entorno configuradas

```yaml
bearer: "tu_jwt_token_aqui"
tokenforo: "token_alternativo"
nuevousuario: "token_nuevo_usuario"
```

### Flujo de testing recomendado

1. **Registrar usuario** → `POST /usuarios`
2. **Hacer login** → `POST /login` (guarda el token)
3. **Crear tópico** → `POST /topico`
4. **Listar tópicos** → `GET /topico`
5. **Crear respuesta** → `POST /respuesta`
6. **Probar actualizaciones y eliminaciones**

---

## 📋 Casos de Uso

### 🎯 Caso de Uso 1: Usuario nuevo en el foro

**Objetivo**: Un usuario nuevo quiere participar en el foro

**Flujo**:
1. Usuario se registra con email y contraseña
2. Sistema valida datos y crea cuenta
3. Usuario inicia sesión y recibe JWT token
4. Usuario puede crear tópicos y respuestas

**Endpoints utilizados**:
- `POST /usuarios` - Registro
- `POST /login` - Autenticación

---

### 🎯 Caso de Uso 2: Crear discusión sobre Java

**Objetivo**: Usuario autenticado quiere iniciar una discusión

**Flujo**:
1. Usuario autenticado crea un nuevo tópico
2. Sistema valida que título y mensaje sean únicos
3. Tópico se crea con estado "NO_RESPONDIDO"
4. Otros usuarios pueden ver y responder

**Endpoints utilizados**:
- `POST /topico` - Crear tópico
- `GET /topico` - Listar tópicos

---

### 🎯 Caso de Uso 3: Responder a una consulta

**Objetivo**: Usuario experto responde a una consulta

**Flujo**:
1. Usuario ve lista de tópicos disponibles
2. Selecciona un tópico específico
3. Crea una respuesta detallada
4. Opcionalmente marca como solución

**Endpoints utilizados**:
- `GET /topico/{id}` - Ver tópico específico
- `POST /respuesta` - Crear respuesta
- `PUT /respuesta` - Marcar como solución

---

### 🎯 Caso de Uso 4: Gestión de contenido propio

**Objetivo**: Usuario gestiona sus propios tópicos y respuestas

**Flujo**:
1. Usuario ve sus tópicos y respuestas
2. Puede editar contenido propio
3. Puede eliminar (soft delete) su contenido
4. No puede modificar contenido de otros

**Endpoints utilizados**:
- `PUT /topico` - Actualizar tópico
- `PUT /respuesta` - Actualizar respuesta
- `DELETE /topico/{id}` - Eliminar tópico
- `DELETE /respuesta/{id}` - Eliminar respuesta

---

### 🎯 Caso de Uso 5: Moderación de usuarios

**Objetivo**: Administrador gestiona usuarios del sistema

**Flujo**:
1. Administrador lista todos los usuarios
2. Puede ver detalles de usuarios específicos
3. Puede desactivar usuarios problemáticos
4. Mantiene integridad del sistema

**Endpoints utilizados**:
- `GET /usuarios` - Listar usuarios
- `GET /usuarios/{id}` - Ver detalle usuario
- `DELETE /usuarios/{id}` - Desactivar usuario

---

## 🔒 Seguridad

### Autenticación JWT
- Tokens con expiración configurable
- Refresh automático no implementado (mejora futura)
- Headers de autorización requeridos

### Validaciones implementadas
- ✅ Email único para usuarios
- ✅ Título y mensaje únicos para tópicos
- ✅ Solo propietarios pueden modificar contenido
- ✅ Soft delete para mantener integridad
- ✅ Validación de campos obligatorios

### Manejo de errores
- Respuestas HTTP estándar
- Mensajes de error descriptivos
- Logging de errores de seguridad

---

## 🚀 Mejoras Futuras

- [ ] Refresh tokens automático
- [ ] Sistema de roles y permisos
- [ ] Notificaciones en tiempo real
- [ ] Búsqueda y filtros avanzados
- [ ] Sistema de votación para respuestas
- [ ] Archivos adjuntos en tópicos
- [ ] API versioning
- [ ] Rate limiting
- [ ] Cache con Redis
- [ ] Tests automatizados completos

---

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para detalles.

---

## 👨‍💻 Autor

**Tu Nombre**
- GitHub: [@Aquatimez1991](https://github.com/Aquatimez1991)
- LinkedIn: [Tu perfil]()

---

<div align="center">

### 🌟 ¡Dale una estrella si te gusta el proyecto! ⭐

</div>
