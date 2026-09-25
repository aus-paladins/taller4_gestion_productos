package aus.t4.paladins.gestorback.repository;

import aus.t4.paladins.gestorback.model.VarianteProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VarianteProductoRepository
    extends JpaRepository<VarianteProducto, Long> {

  /*
   * Todos los filtros son opcionales: el patrón "(:param IS NULL OR ...)"
   * hace que, si no se manda ese parámetro (llega null), la condición
   * completa se evalúe como verdadera y no filtre nada por ese criterio.
   * Trae producto y valoresAtributo (+ su atributo) ya resueltos,
   * Si se manda, se aplica la comparación real. Así una sola query fija
   * sirve tanto para "traer todo" como para cualquier combinación de
   * filtros, sin tener que armar la query a mano en runtime.
   * A diferencia de los demás filtros, mostrarInactivos arranca YA restringiendo
   * El ORDER BY se resuelve en el service, después de traer los resultados ya
   * filtrados.
   */
  @Query("""
      SELECT DISTINCT v FROM VarianteProducto v
      JOIN FETCH v.producto p
      JOIN FETCH p.categoria c
      JOIN FETCH c.departamento d
      LEFT JOIN FETCH v.valoresAtributo va
        LEFT JOIN FETCH va.atributo
        WHERE (:busqueda IS NULL
            OR LOWER(v.sku) LIKE LOWER(CONCAT('%', :busqueda, '%'))
            OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')))
          AND (:departamentoId IS NULL OR d.id = :departamentoId)
          AND (:precioMin IS NULL OR (p.precioBase + COALESCE(v.precioExtra, 0)) >= :precioMin)
          AND (:precioMax IS NULL OR (p.precioBase + COALESCE(v.precioExtra, 0)) <= :precioMax)
          AND (:soloConStock IS NULL OR :soloConStock = FALSE OR v.stock > 0)
          AND (:soloSinStock IS NULL OR :soloSinStock = FALSE OR v.stock <= 0)
          AND (COALESCE(:mostrarInactivos, FALSE) = TRUE OR p.activo = TRUE)
        """)
  List<VarianteProducto> buscar(
      @Param("busqueda") String busqueda,
      @Param("departamentoId") Long departamentoId,
      @Param("precioMin") BigDecimal precioMin,
      @Param("precioMax") BigDecimal precioMax,
      @Param("soloConStock") Boolean soloConStock,
      @Param("soloSinStock") Boolean soloSinStock,
      @Param("mostrarInactivos") Boolean mostrarInactivos);
}
