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
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mejor que .AUTO
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "precio_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioBase;

    @Column(nullable = false)
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    // Tabla intermedia: producto_atributo
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "producto_atributo",
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "atributo_id")
    ) // podría obviarse, union explicita por las id.
    @Builder.Default // con el `new` evitamos NullPointerException
    private List<Atributo> atributos = new ArrayList<>();

    @OneToMany(mappedBy = "producto", 
        cascade = CascadeType.ALL, // actuar s/padre entero repica en el hijo
        orphanRemoval = true,       // al desconectar un hijo no queda huérfano
        fetch = FetchType.LAZY)
    @Builder.Default
    private List<VarianteProducto> variantes = new ArrayList<>();

    /*
    // Constructor vacío requerido por JPA
    public Producto() {
        this.atributos = new ArrayList<>();
        this.variantes = new ArrayList<>(); // se inicisliza para que no sea null
    }
    */ // Lo reempazamos por @Builder.Default y el new in-line
}
