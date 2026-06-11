package aus.t4.paladins.gestorback.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// Opción de generar una entidad para la relación (ver si justifica)
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor // requerido por JPA y Builder
@Builder
@Table(name = "variante_valor")
public class VarianteValor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variante_id", nullable = false)
    private VarianteProducto variante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "valor_atributo_id", nullable = false)
    private ValorAtributo valorAtributo;

    // Campo extra para ordenar
    // @Column(name = "orden_visualización")
    // private Integer orden;

    // Auditoria interna
    @Column(name = "fecha_asociación", nullable = false)
    private LocalDateTime fechaAsociacion;
}
