package aus.t4.paladins.gestorback.service;

import aus.t4.paladins.gestorback.dto.VarianteListadoDTO;
import aus.t4.paladins.gestorback.dto.VarianteProductoRequestDTO;
import aus.t4.paladins.gestorback.dto.VarianteProductoResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IVarianteProductoService {
  Optional<VarianteProductoResponseDTO> save(VarianteProductoRequestDTO request);

  Optional<VarianteProductoResponseDTO> findById(Long id);

  List<VarianteProductoResponseDTO> findAll();

  List<VarianteListadoDTO> findAllParaListado();

  // VarianteProductoResponseDTO findBySku(String sku);
  // List<VarianteProductoResponseDTO> findByProducto(Long productoId);

  Optional<VarianteProductoResponseDTO> update(Long id, VarianteProductoRequestDTO request);

  boolean deleteById(Long id);
}
