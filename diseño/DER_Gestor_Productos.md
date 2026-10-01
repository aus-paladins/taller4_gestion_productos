# Diagrama de Entidad-Relación
## Gestor de Productos con Variantes y Atributos
*Basado en el modelo de Odoo (simplificado)*

---

## Descripción del modelo

El modelo permite gestionar productos con características comunes y generar variantes automáticamente a partir de la combinación de atributos.

La lógica central es: un **Producto** define qué **Atributos** aplica (por ejemplo Talle y Color), y una **Variante de Producto** representa una combinación concreta de esos valores (por ejemplo Talle M + Color Rojo).

---

## Entidades

### DEPARTAMENTO
Nivel superior de la jerarquía del catálogo. Agrupa categorías afines (ej: Indumentaria, Hogar, Tecnología).

| Campo  | Tipo          | Descripción                                      |
|--------|---------------|--------------------------------------------------|
| id     | INT (PK)      | Identificador único del departamento             |
| nombre | VARCHAR(100)  | Nombre del departamento (ej: Indumentaria)       |

---

### CATEGORIA
Agrupa los productos en categorías temáticas o comerciales.

| Campo           | Tipo          | Descripción                                    |
|-----------------|---------------|------------------------------------------------|
| id              | INT (PK)      | Identificador único de la categoría            |
| nombre          | VARCHAR(100)  | Nombre de la categoría (ej: Ropa, Calzado)     |
| departamento_id | BIGINT (FK)   | Referencia a DEPARTAMENTO                      |

---

### PRODUCTO
Representa el producto base con sus características comunes. Un producto puede tener múltiples variantes.

| Campo         | Tipo            | Descripción                                          |
|---------------|-----------------|------------------------------------------------------|
| id            | INT (PK)        | Identificador único del producto                     |
| nombre        | VARCHAR(200)    | Nombre del producto                                  |
| descripcion   | TEXT            | Descripción detallada del producto                   |
| precio_base   | DECIMAL(10,2)   | Precio base antes de aplicar extras de variante      |
| categoria_id  | INT (FK)        | Referencia a CATEGORIA                               |
| activo        | BOOLEAN         | Indica si el producto está disponible                |

---

### ATRIBUTO
Define un tipo de atributo que puede aplicarse a los productos (por ejemplo: Talle, Color, Material).

| Campo  | Tipo          | Descripción                                         |
|--------|---------------|-----------------------------------------------------|
| id     | INT (PK)      | Identificador único del atributo                    |
| nombre | VARCHAR(100)  | Nombre del atributo (ej: Talle, Color)              |

---

### VALOR_ATRIBUTO
Representa los valores posibles de un atributo (ej: S, M, L para Talle; Rojo, Azul para Color).

| Campo        | Tipo          | Descripción                                         |
|--------------|---------------|-----------------------------------------------------|
| id           | INT (PK)      | Identificador único del valor                       |
| atributo_id  | INT (FK)      | Referencia al ATRIBUTO al que pertenece             |
| valor        | VARCHAR(100)  | Valor del atributo (ej: S, M, L, Rojo, Azul)       |

---

### PRODUCTO_ATRIBUTO *(tabla intermedia)*
Define qué atributos son aplicables a cada producto. Por ejemplo, la "Remera" puede usar Talle y Color.

| Campo        | Tipo     | Descripción                                       |
|--------------|----------|---------------------------------------------------|
| producto_id  | INT (FK) | Referencia al PRODUCTO                            |
| atributo_id  | INT (FK) | Referencia al ATRIBUTO que aplica al producto     |

---

### VARIANTE_PRODUCTO
Representa una combinación concreta de un producto con una selección de valores de atributos. Tiene su propio SKU, precio adicional y stock.

| Campo        | Tipo            | Descripción                                            |
|--------------|-----------------|--------------------------------------------------------|
| id           | INT (PK)        | Identificador único de la variante                     |
| producto_id  | INT (FK)        | Referencia al PRODUCTO base                            |
| sku          | VARCHAR(100)    | Código de producto único de la variante                |
| precio_extra | DECIMAL(10,2)   | Diferencia de precio respecto al precio base           |
| stock        | INT             | Cantidad disponible en inventario                      |

---

### VARIANTE_VALOR *(tabla intermedia)*
Define qué valores de atributo componen cada variante. Una variante tiene exactamente un valor por cada atributo aplicable.

| Campo              | Tipo     | Descripción                                             |
|--------------------|----------|---------------------------------------------------------|
| variante_id        | INT (FK) | Referencia a VARIANTE_PRODUCTO                          |
| valor_atributo_id  | INT (FK) | Referencia al VALOR_ATRIBUTO que compone la variante    |

### USUARIO
Entidad de autenticación y autorización. Guarda las credenciales y el rol de quienes acceden al sistema. **No tiene relaciones** con las entidades del catálogo: el dominio de productos y la seguridad están separados a propósito.

| Campo    | Tipo          | Descripción                                                   |
|----------|---------------|---------------------------------------------------------------|
| id       | INT (PK)      | Identificador único del usuario                               |
| username | VARCHAR(80)   | Nombre de usuario (3 a 80 caracteres)                         |
| password | VARCHAR(255)  | Contraseña almacenada como hash BCrypt (nunca en texto plano) |
| rol      | VARCHAR(20)   | Rol del usuario: ADMIN o INVITADO (enum RolUsuario)           |

**Roles:**

| Rol      | Permisos                                                                    |
|----------|-----------------------------------------------------------------------------|
| INVITADO | Solo lectura. Es el rol asignado al registrarse (POST /api/auth/register).  |
| ADMIN    | Lectura y escritura (alta, edición y baja). Puede ver productos inactivos.  |

---

## Relaciones

| Entidad origen      | Cardinalidad | Entidad destino     | Descripción                                          |
|---------------------|:------------:|---------------------|------------------------------------------------------|
| DEPARTAMENTO        | 1 a N        | CATEGORIA           | Un departamento agrupa muchas categorías             |
| CATEGORIA           | 1 a N        | PRODUCTO            | Una categoría tiene muchos productos                 |
| PRODUCTO            | 1 a N        | PRODUCTO_ATRIBUTO   | Un producto define múltiples atributos aplicables    |
| ATRIBUTO            | 1 a N        | PRODUCTO_ATRIBUTO   | Un atributo puede aplicarse a múltiples productos    |
| ATRIBUTO            | 1 a N        | VALOR_ATRIBUTO      | Un atributo tiene múltiples valores posibles         |
| PRODUCTO            | 1 a N        | VARIANTE_PRODUCTO   | Un producto genera múltiples variantes               |
| VARIANTE_PRODUCTO   | 1 a N        | VARIANTE_VALOR      | Una variante se compone de múltiples valores         |
| VALOR_ATRIBUTO      | 1 a N        | VARIANTE_VALOR      | Un valor de atributo puede estar en muchas variantes |

Las relaciones `PRODUCTO` ↔ `ATRIBUTO` y `VARIANTE_PRODUCTO` ↔ `VALOR_ATRIBUTO` son **N a M**, resueltas mediante las tablas intermedias `PRODUCTO_ATRIBUTO` y `VARIANTE_VALOR`.

### Reglas de integridad en cascada

El borrado se propaga hacia abajo por `cascade = ALL` y `orphanRemoval = true` (Departamento → Categoría → Producto → Variante; Atributo → Valor de atributo). Además, la capa de servicio aplica una **limpieza hacia arriba**: al eliminar la última variante de un producto se elimina el producto, y si su categoría queda vacía se elimina la categoría y, si corresponde, el departamento (`LimpiezaCatalogoService`).

---

## Ejemplo práctico

**Jerarquía:** Indumentaria (departamento) → Remeras (categoría)
**Producto:** Remera Básica | Precio base: $5.000  
**Atributos aplicables:** Talle (S, M, L) y Color (Rojo, Azul)

| Variante              | Talle | Color | SKU          |
|-----------------------|-------|-------|--------------|
| Remera Básica S Rojo  | S     | Rojo  | REM-S-ROJO   |
| Remera Básica M Rojo  | M     | Rojo  | REM-M-ROJO   |
| Remera Básica M Azul  | M     | Azul  | REM-M-AZUL   |
| Remera Básica L Azul  | L     | Azul  | REM-L-AZUL   |

**Usuarios de ejemplo:**

| username | rol      |
|----------|----------|
| admin    | ADMIN    |
| maria    | INVITADO |

