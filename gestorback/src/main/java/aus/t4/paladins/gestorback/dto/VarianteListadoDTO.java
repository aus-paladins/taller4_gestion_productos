package aus.t4.paladins.gestorback.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

// DTO "plano" pensado para la pantalla de listado del front (lista-productos).
@Data
public class VarianteListadoDTO {

  private Long id;
  private String sku;
  private String nombre; // nombre del producto padre
  private Long departamentoId;
  private String departamentoNombre;
  private Long categoriaId;
  private String categoriaNombre;
  private List<String> atributos; // ej: ["Talle: L", "Color: Negro"]
  private Integer stock;
  private BigDecimal precio; // precioBase del producto + precioExtra de la variante
  private Long productoId; // Agregado para que al updatear una variante, se pueda saber a que producto pertenece
}
