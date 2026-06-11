package aus.t4.paladins.gestorback.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor // requerido por JPA y Builder
@Builder
@Table(name = "valor_atributo")
public class ValorAtributo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", length = 50)
    private String valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atributo_id", nullable = false)
    private Atributo atributo;

    @OneToMany(mappedBy = "valorAtributo", 
        cascade = CascadeType.ALL, // actuar s/padre entero repica en el hijo
        orphanRemoval = true,       // al desconectar un hijo no queda huérfano
        fetch = FetchType.LAZY)
    @Builder.Default
    private List<VarianteValor> varianteValores = new ArrayList<>();

}
