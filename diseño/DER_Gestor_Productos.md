# Diagrama de Entidad-Relación
## Gestor de Productos con Variantes y Atributos
*Basado en el modelo de Odoo (simplificado)*

---

## Descripción del modelo

El modelo permite gestionar productos con características comunes y generar variantes automáticamente a partir de la combinación de atributos.

La lógica central es: un **Producto** define qué **Atributos** aplica (por ejemplo Talle y Color), y una **Variante de Producto** representa una combinación concreta de esos valores (por ejemplo Talle M + Color Rojo).

---

## Entidades

### CATEGORIA
Agrupa los productos en categorías temáticas o comerciales.

| Campo  | Tipo          | Descripción                                    |
|--------|---------------|------------------------------------------------|
| id     | INT (PK)      | Identificador único de la categoría            |
| nombre | VARCHAR(100)  | Nombre de la categoría (ej: Ropa, Calzado)     |

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

---

## Relaciones

| Entidad origen      | Cardinalidad | Entidad destino     | Descripción                                          |
|---------------------|:------------:|---------------------|------------------------------------------------------|
| CATEGORIA           | 1 a N        | PRODUCTO            | Una categoría tiene muchos productos                 |
| PRODUCTO            | 1 a N        | PRODUCTO_ATRIBUTO   | Un producto define múltiples atributos aplicables    |
| ATRIBUTO            | 1 a N        | PRODUCTO_ATRIBUTO   | Un atributo puede aplicarse a múltiples productos    |
| ATRIBUTO            | 1 a N        | VALOR_ATRIBUTO      | Un atributo tiene múltiples valores posibles         |
| PRODUCTO            | 1 a N        | VARIANTE_PRODUCTO   | Un producto genera múltiples variantes               |
| VARIANTE_PRODUCTO   | 1 a N        | VARIANTE_VALOR      | Una variante se compone de múltiples valores         |
| VALOR_ATRIBUTO      | 1 a N        | VARIANTE_VALOR      | Un valor de atributo puede estar en muchas variantes |

---

## Ejemplo práctico

**Producto:** Remera Básica | Precio base: $5.000  
**Atributos aplicables:** Talle (S, M, L) y Color (Rojo, Azul)

| Variante              | Talle | Color | SKU          |
|-----------------------|-------|-------|--------------|
| Remera Básica S Rojo  | S     | Rojo  | REM-S-ROJO   |
| Remera Básica M Rojo  | M     | Rojo  | REM-M-ROJO   |
| Remera Básica M Azul  | M     | Azul  | REM-M-AZUL   |
| Remera Básica L Azul  | L     | Azul  | REM-L-AZUL   |
