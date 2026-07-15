#Caso Bookpoint Chile - Arquitectura de Microservicios

Sistema distribuido para la gestión de catálogo de libros e inventarios en sucursales, diseñado bajo una arquitectura limpia de microservicios autónomos y desacoplados utilizando Java 25 y **Spring Boot.

---

#Integrantes
* **Cristóbal Millar** - Desarrollo Backend & Arquitectura de Catálogo e Inventario

---

#Tecnologías Utilizadas
* **Lenguaje:** Java 25
* **Framework:** Spring Boot (Spring Web, Spring Data JPA)
* **Persistencia:** Hibernate / MySQL (Bases de datos independientes por servicio)
* **Documentación:** Springdoc OpenAPI / Swagger UI
* **Comunicación Inter-servicios:** HTTP Síncrono mediante `RestTemplate`

---

#Arquitectura y Microservicios

El ecosistema está compuesto por dos microservicios principales que funcionan de manera autónoma, respetando el desacoplamiento de datos y poseyendo cada uno su propia persistencia física:

### 1. Catalog Service (`catalog`) ➔ Puerto `8083`
* **Base de Datos:** `db_catalog` (MySQL)
* **Responsabilidad:** Gestionar las entidades de `Producto` (Libros) y `Categoria`.
* **Endpoints Clave:**
  * `GET /api/productos` - Listar todos los libros.
  * `GET /api/productos/{id}` - Obtener el detalle de un libro específico (utilizado para validación remota).
  * `POST /api/productos` - Crear un nuevo libro.

# 2. Inventory Service (`inventory`) ➔ Puerto `8084`
* **Base de Datos:** `db_inventory` (MySQL)
* **Responsabilidad:** Controlar el stock de libros asociados a diferentes sucursales (`Sucursal` e `Inventario`).
* **Endpoints Clave:**
  * `GET /api/inventarios` - Listar el inventario completo.
  * `POST /api/inventarios` - Registrar stock para un producto.

---

#Comunicación Inter-Servicios

Para garantizar la integridad referencial lógica entre los servicios autónomos, se ha implementado un flujo de comunicación síncrona:

1. Al intentar guardar un registro de stock a través de `POST /api/inventarios` en el puerto `8084`, el servicio de Inventory realiza una llamada remota `GET` vía `RestTemplate` al puerto `8083` de Catalog (`http://localhost:8083/api/productos/{id}`).
2. Si el producto existe, **Catalog** responde con un DTO (`ProductoDTO`) y la transacción se procesa con éxito.
3. Si el producto no existe (o el servicio está caído), **Inventory** bloquea la operación y responde de forma segura con un código de error de negocio personalizado, impidiendo datos huérfanos en la persistencia local.

#Ejemplo de Respuesta de Error (Global Exception Handler)
Cuando se intenta ingresar un `idProducto` inexistente en el catálogo, el sistema intercepta la excepción remota y responde con un formato estructurado y un código de estado adecuado:

```json
{
  "timestamp": "2026-07-15T04:30:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "Error de validación remota: El producto con ID 9999 no existe en el catálogo o el servicio no está disponible.",
  "path": "/api/inventarios"
}

#Requisitos Previos
"Java JDK 25 instalado".

"Maven instalado".

"Motor MySQL corriendo localmente con las bases de datos creadas" (db_catalog y db_inventory).

#Pasos para levantar el ecosistema:
1. Clonar el repositorio: git clone [https://github.com/CristobalMillar/Bookpoint.git](https://github.com/CristobalMillar/Bookpoint.git)
cd Bookpoint

2. Levantar el Servicio de Catálogo:
Abrir una terminal en la ruta /catalog y ejecutar: mvn clean compile spring-boot:run -DskipTests

3. Levantar el Servicio de Inventario:
Abrir otra terminal en la ruta /inventory y ejecutar: mvn clean compile spring-boot:run -DskipTests

#Una vez que ambos servicios se encuentren levantados, la documentación interactiva OpenAPI estará disponible en las siguientes direcciones:

Swagger Catalog: http://localhost:8083/swagger-ui/index.html

Swagger Inventory: http://localhost:8084/swagger-ui/index.html
