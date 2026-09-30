package aus.t4.paladins.gestorback.service;

import aus.t4.paladins.gestorback.model.Categoria;
import aus.t4.paladins.gestorback.repository.CategoriaRepository;
import aus.t4.paladins.gestorback.repository.DepartamentoRepository;
import aus.t4.paladins.gestorback.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Borra hacia arriba (Categoria -> Departamento) cuando quedan vacías.
// La usan:
// - VarianteProductoService (al borrar la última variante de un producto)
// - ProductoService (al reasignar la categoría de un producto existente, 
//   que puede dejar la categoría vieja sin productos).
@Service
public class LimpiezaCatalogoService {

  private final ProductoRepository productoRepository;
  private final CategoriaRepository categoriaRepository;
  private final DepartamentoRepository departamentoRepository;

  public LimpiezaCatalogoService(
      ProductoRepository productoRepository,
      CategoriaRepository categoriaRepository,
      DepartamentoRepository departamentoRepository) {
    this.productoRepository = productoRepository;
    this.categoriaRepository = categoriaRepository;
    this.departamentoRepository = departamentoRepository;
  }

  @Transactional
  public void eliminarCategoriaSiQuedoVacia(Long categoriaId) {
    if (productoRepository.countByCategoriaId(categoriaId) > 0) {
      return;
    }

    Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow();
    Long departamentoId = categoria.getDepartamento().getId();

    categoriaRepository.deleteById(categoriaId);

    if (categoriaRepository.countByDepartamentoId(departamentoId) == 0) {
      departamentoRepository.deleteById(departamentoId);
    }
  }
}
