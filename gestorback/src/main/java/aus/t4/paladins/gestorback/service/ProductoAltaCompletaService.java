package aus.t4.paladins.gestorback.service;

import aus.t4.paladins.gestorback.dto.ProductoAltaCompletaRequestDTO;
import aus.t4.paladins.gestorback.dto.ProductoAltaCompletaRequestDTO.AtributoSeleccionadoDTO;
import aus.t4.paladins.gestorback.dto.VarianteProductoResponseDTO;
import aus.t4.paladins.gestorback.mapper.VarianteProductoMapper;
import aus.t4.paladins.gestorback.model.*;
import aus.t4.paladins.gestorback.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoAltaCompletaService {

  private final DepartamentoRepository departamentoRepository;
  private final CategoriaRepository categoriaRepository;
  private final AtributoRepository atributoRepository;
  private final ValorAtributoRepository valorAtributoRepository;
  private final ProductoRepository productoRepository;
  private final VarianteProductoRepository varianteProductoRepository;

  public ProductoAltaCompletaService(
      DepartamentoRepository departamentoRepository,
      CategoriaRepository categoriaRepository,
      AtributoRepository atributoRepository,
      ValorAtributoRepository valorAtributoRepository,
      ProductoRepository productoRepository,
      VarianteProductoRepository varianteProductoRepository) {
    this.departamentoRepository = departamentoRepository;
    this.categoriaRepository = categoriaRepository;
    this.atributoRepository = atributoRepository;
    this.valorAtributoRepository = valorAtributoRepository;
    this.productoRepository = productoRepository;
    this.varianteProductoRepository = varianteProductoRepository;
  }

  @Transactional
  public VarianteProductoResponseDTO altaCompleta(ProductoAltaCompletaRequestDTO request) {

    Departamento departamento = resolverDepartamento(request);
    Categoria categoria = resolverCategoria(request, departamento);

    List<Atributo> atributosDelProducto = new ArrayList<>();
    List<ValorAtributo> valoresDeLaVariante = new ArrayList<>();

    for (AtributoSeleccionadoDTO sel : request.getAtributos()) {
      Atributo atributo = resolverAtributo(sel);
      ValorAtributo valor = resolverValorAtributo(sel, atributo);

      atributosDelProducto.add(atributo);
      valoresDeLaVariante.add(valor);
    }

    Producto producto = new Producto();
    producto.setNombre(request.getNombre());
    producto.setDescription(request.getDescription());
    producto.setPrecioBase(request.getPrecioBase());
    producto.setActivo(request.getActivo());
    producto.setCategoria(categoria);
    producto.setAtributos(atributosDelProducto);
    producto = productoRepository.save(producto);

    VarianteProducto variante = new VarianteProducto();
    variante.setSku(request.getSku());
    variante.setPrecioExtra(request.getPrecioExtra());
    variante.setStock(request.getStock());
    variante.setProducto(producto);
    variante.setValoresAtributo(valoresDeLaVariante);
    variante = varianteProductoRepository.save(variante);

    return VarianteProductoMapper.toDTO(variante);
  }

  private Departamento resolverDepartamento(ProductoAltaCompletaRequestDTO request) {
    if (request.getDepartamentoId() != null) {
      return departamentoRepository.findById(request.getDepartamentoId())
          .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));
    }
    Departamento nuevo = new Departamento();
    nuevo.setNombre(request.getNuevoDepartamento());
    return departamentoRepository.save(nuevo);
  }

  private Categoria resolverCategoria(ProductoAltaCompletaRequestDTO request, Departamento departamento) {
    if (request.getCategoriaId() != null) {
      return categoriaRepository.findById(request.getCategoriaId())
          .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
    }
    Categoria nueva = new Categoria();
    nueva.setNombre(request.getNuevaCategoria());
    nueva.setDepartamento(departamento); // el resuelto arriba, exista o se haya creado ahora
    return categoriaRepository.save(nueva);
  }

  private Atributo resolverAtributo(AtributoSeleccionadoDTO sel) {
    if (sel.getAtributoId() != null) {
      return atributoRepository.findById(sel.getAtributoId())
          .orElseThrow(() -> new IllegalArgumentException("Atributo no encontrado"));
    }
    Atributo nuevo = new Atributo();
    nuevo.setNombre(sel.getNuevoAtributo());
    return atributoRepository.save(nuevo);
  }

  private ValorAtributo resolverValorAtributo(AtributoSeleccionadoDTO sel, Atributo atributo) {
    if (sel.getValorAtributoId() != null) {
      return valorAtributoRepository.findById(sel.getValorAtributoId())
          .orElseThrow(() -> new IllegalArgumentException("Valor no encontrado"));
    }
    ValorAtributo nuevo = new ValorAtributo();
    nuevo.setValor(sel.getNuevoValor());
    nuevo.setAtributo(atributo);
    return valorAtributoRepository.save(nuevo);
  }
}
