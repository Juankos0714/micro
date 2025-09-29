# Reactive Commerce Platform

Plataforma de comercio electrónico basada en microservicios reactivos usando Spring WebFlux y R2DBC.

## Arquitectura

El sistema está compuesto por tres microservicios independientes:

### 1. **Clients Service** (Puerto 9001)
- Gestión de clientes y perfiles de usuario
- Endpoints: `/api/clients/**`
- Base de datos: `commerce_clients_db`

### 2. **Catalog Service** (Puerto 9002)
- Gestión del catálogo de productos/artículos
- Endpoints: `/api/catalog/items/**`
- Base de datos: `commerce_catalog_db`

### 3. **Sales Service** (Puerto 9003)
- Gestión de compras y transacciones
- Endpoints: `/api/sales/purchases/**`
- Base de datos: `commerce_sales_db`

## Tecnologías

- **Spring Boot 3.5.3**
- **Spring WebFlux** (Programación reactiva)
- **R2DBC** (Acceso reactivo a base de datos)
- **MySQL 8.0**
- **H2** (Para tests)
- **JUnit 5** y **Reactor Test**

## Instalación y Ejecución

### Prerrequisitos
- Java 17+
- Maven 3.6+
- MySQL 8.0

### Base de Datos
```bash
# Ejecutar el script de inicialización
mysql -u root -p < mysql-setup.sql
```

### Compilación
```bash
mvn clean install
```

### Ejecución de servicios
```bash
# Terminal 1 - Clients Service
mvn spring-boot:run -pl clients

# Terminal 2 - Catalog Service  
mvn spring-boot:run -pl catalog

# Terminal 3 - Sales Service
mvn spring-boot:run -pl sales

### Con Docker
```bash
docker-compose up -d
```

## API Endpoints

### Clients Service (http://localhost:9001)
- `GET /api/clients` - Listar todos los clientes
- `GET /api/clients/{id}` - Obtener cliente por ID
- `POST /api/clients` - Crear nuevo cliente
- `PUT /api/clients/{id}` - Actualizar cliente
- `DELETE /api/clients/{id}` - Eliminar cliente

### Catalog Service (http://localhost:9002)
- `GET /api/catalog/items` - Listar todos los artículos
- `GET /api/catalog/items/{id}` - Obtener artículo por ID
- `POST /api/catalog/items` - Crear nuevo artículo
- `PUT /api/catalog/items/{id}` - Actualizar artículo
- `DELETE /api/catalog/items/{id}` - Eliminar artículo

### Sales Service (http://localhost:9003)
- `GET /api/sales/purchases` - Listar todas las compras
- `GET /api/sales/purchases/{id}` - Obtener compra por ID
- `POST /api/sales/purchases` - Crear nueva compra
- `PUT /api/sales/purchases/{id}` - Actualizar compra
- `DELETE /api/sales/purchases/{id}` - Eliminar compra

## Tests
```bash
mvn test
```

## Monitoreo

Cada servicio expone endpoints de actuator en:
- `/actuator/health`
- `/actuator/info` 
- `/actuator/metrics`

## Estructura del Proyecto

```
reactive-commerce-platform/
├── clients/                 # Servicio de clientes
│   ├── src/main/java/io/biztech/clients/
│   ├── src/test/java/
│   └── pom.xml
├── catalog/                 # Servicio de catálogo
│   ├── src/main/java/io/biztech/catalog/
│   ├── src/test/java/
│   └── pom.xml
├── sales/                   # Servicio de ventas
│   ├── src/main/java/io/biztech/sales/
│   ├── src/test/java/
│   └── pom.xml
├── docker-compose.yml
├── mysql-setup.sql
└── pom.xml                 # POM padre
```
