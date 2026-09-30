package aus.t4.paladins.gestorback.repository;

import aus.t4.paladins.gestorback.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  long countByDepartamentoId(Long departamentoId);
}
