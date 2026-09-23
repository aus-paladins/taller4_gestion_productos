package aus.t4.paladins.gestorback.service;

import aus.t4.paladins.gestorback.dto.VarianteListadoDTO;
import aus.t4.paladins.gestorback.dto.VarianteProductoRequestDTO;
import aus.t4.paladins.gestorback.dto.VarianteProductoResponseDTO;
import aus.t4.paladins.gestorback.mapper.VarianteProductoMapper;
import aus.t4.paladins.gestorback.model.Producto;
import aus.t4.paladins.gestorback.model.ValorAtributo;
import aus.t4.paladins.gestorback.model.VarianteProducto;
import aus.t4.paladins.gestorback.repository.ProductoRepository;
import aus.t4.paladins.gestorback.repository.ValorAtributoRepository;
import aus.t4.paladins.gestorback.repository.VarianteProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VarianteProductoService implements IVarianteProductoService {

  private final VarianteProductoRepository repository;
  private final ProductoRepository productoRepository;
  private final ValorAtributoRepository valorAtributoRepository;

  public VarianteProductoService(
      VarianteProductoRepository repository,
      ProductoRepository productoRepository,
      ValorAtributoRepository valorAtributoRepository) {

    this.repository = repository;
    this.productoRepository = productoRepository;
    this.valorAtributoRepository = valorAtributoRepository;
  }

  @Override
  public List<VarianteProductoResponseDTO> findAll() {
    return repository.findAll()
        .stream()
        .map(VarianteProductoMapper::toDTO)
        .toList();
  }

  @Override
  public List<VarianteListadoDTO> findAllParaListado() {
    return repository.findAllForListado()
        .stream()
        .map(VarianteProductoMapper::toListadoDTO)
        .toList();
  }

  @Override
  public Optional<VarianteProductoResponseDTO> findById(
      Long id) {

    return repository.findById(id)
        .map(VarianteProductoMapper::toDTO);
  }

  @Override
  public Optional<VarianteProductoResponseDTO> save(
      VarianteProductoRequestDTO request) {

    Optional<Producto> producto = productoRepository.findById(request.getProductoId());

    if (producto.isEmpty()) {
      return Optional.empty();
    }

    List<ValorAtributo> valores = valorAtributoRepository.findAllById(
        request.getValoresAtributoIds());

    if (valores.size() != request.getValoresAtributoIds().size()) {
      return Optional.empty();
    }

    VarianteProducto variante = new VarianteProducto();

    variante.setSku(request.getSku());
    variante.setPrecioExtra(request.getPrecioExtra());
    variante.setStock(request.getStock());
    variante.setProducto(producto.get());
    variante.setValoresAtributo(valores);

    VarianteProducto saved = repository.save(variante);

    return Optional.of(
        VarianteProductoMapper.toDTO(saved));
  }

  @Override
  public Optional<VarianteProductoResponseDTO> update(
      Long id,
      VarianteProductoRequestDTO request) {

    Optional<Producto> producto = productoRepository.findById(request.getProductoId());

    if (producto.isEmpty()) {
      return Optional.empty();
    }

    List<ValorAtributo> valores = valorAtributoRepository.findAllById(
        request.getValoresAtributoIds());

    if (valores.size() != request.getValoresAtributoIds().size()) {
      return Optional.empty();
    }

    return repository.findById(id)
        .map(variante -> {

          variante.setSku(request.getSku());
          variante.setPrecioExtra(
              request.getPrecioExtra());
          variante.setStock(request.getStock());
          variante.setProducto(producto.get());
          variante.setValoresAtributo(valores);

          VarianteProducto updated = repository.save(variante);

          return VarianteProductoMapper
              .toDTO(updated);
        });
  }

  @Override
  public boolean deleteById(Long id) {

    if (!repository.existsById(id)) {
      return false;
    }

    repository.deleteById(id);
    return true;
  }
}
