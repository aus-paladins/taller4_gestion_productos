package aus.t4.paladins.gestorback.service;

import java.util.List;
import java.util.Optional;

import aus.t4.paladins.gestorback.dto.CategoriaRequestDTO;
import aus.t4.paladins.gestorback.dto.CategoriaResponseDTO;

public interface ICategoriaService {

  List<CategoriaResponseDTO> findAll();

  Optional<CategoriaResponseDTO> findById(Long id);

  Optional<CategoriaResponseDTO> save(CategoriaRequestDTO request);

  Optional<CategoriaResponseDTO> update(Long id, CategoriaRequestDTO request);

  boolean deleteById(Long id);
}
