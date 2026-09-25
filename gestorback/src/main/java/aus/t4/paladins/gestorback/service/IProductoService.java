package aus.t4.paladins.gestorback.service;

import java.util.List;
import java.util.Optional;

import aus.t4.paladins.gestorback.dto.ProductoRequestDTO;
import aus.t4.paladins.gestorback.dto.ProductoResponseDTO;

public interface IProductoService {

  List<ProductoResponseDTO> findAll();

  Optional<ProductoResponseDTO> findById(Long id);

  Optional<ProductoResponseDTO> save(ProductoRequestDTO request);

  Optional<ProductoResponseDTO> update(Long id, ProductoRequestDTO request);

  boolean deleteById(Long id);
}
