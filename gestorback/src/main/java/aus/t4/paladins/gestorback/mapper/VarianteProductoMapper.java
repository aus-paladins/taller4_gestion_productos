package aus.t4.paladins.gestorback.mapper;

import aus.t4.paladins.gestorback.dto.VarianteListadoDTO;
import aus.t4.paladins.gestorback.dto.VarianteProductoResponseDTO;
import aus.t4.paladins.gestorback.model.VarianteProducto;

import java.math.BigDecimal;
import java.util.Collections;

public class VarianteProductoMapper {

  private VarianteProductoMapper() {
  }

  public static VarianteProductoResponseDTO toDTO(
      VarianteProducto entity) {

    VarianteProductoResponseDTO dto = new VarianteProductoResponseDTO();

    dto.setId(entity.getId());
    dto.setSku(entity.getSku());
    dto.setPrecioExtra(entity.getPrecioExtra());
    dto.setStock(entity.getStock());

    dto.setProductoId(entity.getProducto().getId());
    dto.setProductoNombre(entity.getProducto().getNombre());

    if (entity.getValoresAtributo() != null) {
      dto.setValoresAtributoIds(
          entity.getValoresAtributo()
              .stream()
              .map(valor -> valor.getId())
              .toList());
    } else {
      dto.setValoresAtributoIds(
          Collections.emptyList());
    }

    return dto;
  }

  // Versión "plana" para el listado del front: combina producto y variante
  public static VarianteListadoDTO toListadoDTO(
      VarianteProducto entity) {

    VarianteListadoDTO dto = new VarianteListadoDTO();

    dto.setId(entity.getId());
    dto.setSku(entity.getSku());
    dto.setNombre(entity.getProducto().getNombre());
    dto.setStock(entity.getStock());

    BigDecimal precioExtra = entity.getPrecioExtra() != null
        ? entity.getPrecioExtra()
        : BigDecimal.ZERO;

    dto.setPrecio(
        entity.getProducto().getPrecioBase().add(precioExtra));

    if (entity.getValoresAtributo() != null) {
      dto.setAtributos(
          entity.getValoresAtributo()
              .stream()
              .map(valor -> valor.getAtributo().getNombre()
                  + ": " + valor.getValor())
              .toList());
    } else {
      dto.setAtributos(Collections.emptyList());
    }

    dto.setProductoId(entity.getProducto().getId());

    return dto;
  }
}
