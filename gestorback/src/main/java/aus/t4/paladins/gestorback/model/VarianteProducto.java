package aus.t4.paladins.gestorback.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor // requerido por JPA y Builder
@Builder
@Table(name = "variante_producto")
public class VarianteProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_ref", nullable = false, unique = true, length = 50)
    private String sku;

    @Column(name = "precio_extra", precision = 10, scale = 2)
    private BigDecimal precioExtra;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @OneToMany(mappedBy = "variante", 
        cascade = CascadeType.ALL, // actuar s/padre entero repica en el hijo
        orphanRemoval = true,       // al desconectar un hijo no queda huérfano
        fetch = FetchType.LAZY)
    @Builder.Default
    private List<VarianteValor> varianteValores = new ArrayList<>();

}
