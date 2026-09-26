package aus.t4.paladins.gestorback.mapper;

import aus.t4.paladins.gestorback.dto.ProductoResponseDTO;
import aus.t4.paladins.gestorback.model.Producto;

public class ProductoMapper {

    private ProductoMapper() {
    }

    public static ProductoResponseDTO toDTO(Producto entity) {

        ProductoResponseDTO dto = new ProductoResponseDTO();

        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescription(entity.getDescription());
        dto.setPrecioBase(entity.getPrecioBase());
        dto.setActivo(entity.getActivo());

        if (entity.getCategoria() != null) {
            dto.setCategoriaId(entity.getCategoria().getId());
            dto.setCategoriaNombre(entity.getCategoria().getNombre());
        }

        if (entity.getAtributos() != null) {
            dto.setAtributoIds(
            entity.getAtributos()
                .stream()
                .map(atributo -> atributo.getId())
                .toList()
            );
        }

        return dto;
    }
}