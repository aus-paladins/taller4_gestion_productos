package aus.t4.paladins.gestorback.dto;

import java.math.BigDecimal;

/**
 * Criterios de filtro para GET /api/variantes/listado.
 * Campos opcionales (null = "no filtrar por esto")
 * mostrarInactivos es la excepción.
 */
public record VarianteFiltroDTO(
    String busqueda, // sku o nombre del producto
    Long departamentoId,
    BigDecimal precioMin,
    BigDecimal precioMax,
    Boolean soloConStock,
    Boolean soloSinStock,
    Boolean mostrarInactivos,
    String ordenarPor // "alfabetico" | "precio_asc" | "precio_desc"
) {
  public static VarianteFiltroDTO vacio() {
    return new VarianteFiltroDTO(null, null, null, null, null, null, null, null);
  }
}
