package aus.t4.paladins.gestorback.service;

import java.util.List;
import java.util.Optional;

import aus.t4.paladins.gestorback.dto.AtributoRequestDTO;
import aus.t4.paladins.gestorback.dto.AtributoResponseDTO;

public interface IAtributoService {
  List<AtributoResponseDTO> findAll();

  Optional<AtributoResponseDTO> findById(Long id);

  AtributoResponseDTO save(AtributoRequestDTO request);

  Optional<AtributoResponseDTO> update(Long id, AtributoRequestDTO request);

  boolean deleteById(Long id);
}
