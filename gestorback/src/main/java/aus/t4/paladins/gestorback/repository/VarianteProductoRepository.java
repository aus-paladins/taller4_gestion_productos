package aus.t4.paladins.gestorback.repository;

import aus.t4.paladins.gestorback.model.VarianteProducto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VarianteProductoRepository
    extends JpaRepository<VarianteProducto, Long> {

  // Trae producto y valoresAtributo (+ su atributo) ya resueltos,
  @Query("""
      SELECT DISTINCT v FROM VarianteProducto v
      JOIN FETCH v.producto
      LEFT JOIN FETCH v.valoresAtributo va
      LEFT JOIN FETCH va.atributo
      """)
  List<VarianteProducto> findAllForListado();
}
