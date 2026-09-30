package aus.t4.paladins.gestorback.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductoAltaCompletaRequestDTO {

  // Departamento: o departamentoId (ya existe) o nuevoDepartamento (nombre a
  // crear)
  private Long departamentoId;
  private String nuevoDepartamento;

  // Categoría: mismo criterio. Si es nueva, cuelga del departamento de arriba
  // (exista o se acabe de crear en esta misma request).
  private Long categoriaId;
  private String nuevaCategoria;

  // Un elemento por cada atributo que participa en la variante
  // (ej: uno para Talle, otro para Color).
  private List<AtributoSeleccionadoDTO> atributos;

  // Producto
  private String nombre;
  private String description;
  private BigDecimal precioBase;
  private Boolean activo;

  // Variante
  private String sku;
  private BigDecimal precioExtra;
  private Integer stock;

  @Data
  public static class AtributoSeleccionadoDTO {
    private Long atributoId; // si el atributo ya existe
    private String nuevoAtributo; // si hay que crearlo

    private Long valorAtributoId; // si el valor ya existe
    private String nuevoValor; // si hay que crearlo (bajo el atributo de arriba)
  }
}
