package aus.t4.paladins.gestorback.repository;

import aus.t4.paladins.gestorback.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("""
        SELECT DISTINCT p
        FROM Producto p
        LEFT JOIN FETCH p.atributos
        WHERE p.id = :id
        """)
    Optional<Producto> findByIdWithAtributos(Long id);

}