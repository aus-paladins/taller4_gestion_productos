package aus.t4.paladins.gestorback.service;

import aus.t4.paladins.gestorback.dto.ProductoRequestDTO;
import aus.t4.paladins.gestorback.dto.ProductoResponseDTO;
import aus.t4.paladins.gestorback.mapper.ProductoMapper;
import aus.t4.paladins.gestorback.model.Atributo;
import aus.t4.paladins.gestorback.model.Categoria;
import aus.t4.paladins.gestorback.model.Producto;
import aus.t4.paladins.gestorback.repository.AtributoRepository;
import aus.t4.paladins.gestorback.repository.CategoriaRepository;
import aus.t4.paladins.gestorback.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

  private final ProductoRepository productoRepository;
  private final CategoriaRepository categoriaRepository;
  private final AtributoRepository atributoRepository;

  public ProductoService(
      ProductoRepository productoRepository,
      CategoriaRepository categoriaRepository,
      AtributoRepository atributoRepository) {

    this.productoRepository = productoRepository;
    this.categoriaRepository = categoriaRepository;
    this.atributoRepository = atributoRepository;
  }

  @Override
  public List<ProductoResponseDTO> findAll() {
    return productoRepository.findAll()
        .stream()
        .map(ProductoMapper::toDTO)
        .toList();
  }

  @Override
  public Optional<ProductoResponseDTO> findById(Long id) {
    return productoRepository.findByIdWithAtributos(id)
        .map(ProductoMapper::toDTO);
  }

  @Override
  public Optional<ProductoResponseDTO> save(ProductoRequestDTO request) {

    Optional<Categoria> categoria =
        categoriaRepository.findById(request.getCategoriaId());

    if (categoria.isEmpty()) {
      return Optional.empty();
    }

    Producto producto = new Producto();

    producto.setNombre(request.getNombre());
    producto.setDescription(request.getDescription());
    producto.setPrecioBase(request.getPrecioBase());
    producto.setActivo(request.getActivo());
    producto.setCategoria(categoria.get());

    if (request.getAtributoIds() != null) {
      List<Atributo> atributos =
          atributoRepository.findAllById(request.getAtributoIds());

      producto.setAtributos(atributos);
    }

    Producto saved = productoRepository.save(producto);

    return Optional.of(ProductoMapper.toDTO(saved));
  }

  @Override
  public Optional<ProductoResponseDTO> update(
      Long id,
      ProductoRequestDTO request) {

    Optional<Producto> productoOptional =
        productoRepository.findById(id);

    Optional<Categoria> categoria =
        categoriaRepository.findById(request.getCategoriaId());

    if (productoOptional.isEmpty() || categoria.isEmpty()) {
      return Optional.empty();
    }

    Producto producto = productoOptional.get();

    producto.setNombre(request.getNombre());
    producto.setDescription(request.getDescription());
    producto.setPrecioBase(request.getPrecioBase());
    producto.setActivo(request.getActivo());
    producto.setCategoria(categoria.get());

    if (request.getAtributoIds() != null) {
      List<Atributo> atributos =
          atributoRepository.findAllById(request.getAtributoIds());

      producto.setAtributos(atributos);
    }

    Producto updated = productoRepository.save(producto);

    return Optional.of(ProductoMapper.toDTO(updated));
  }

  @Override
  public boolean deleteById(Long id) {

    if (!productoRepository.existsById(id)) {
      return false;
    }

    productoRepository.deleteById(id);
    return true;
  }
}