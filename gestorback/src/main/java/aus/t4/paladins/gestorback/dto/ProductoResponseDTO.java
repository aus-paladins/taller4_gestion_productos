package aus.t4.paladins.gestorback.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private String description;
    private BigDecimal precioBase;
    private Boolean activo;
    private Long categoriaId;
    private String categoriaNombre;
    private List<Long> atributoIds;
}