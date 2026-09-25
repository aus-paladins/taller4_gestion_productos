package aus.t4.paladins.gestorback.service;

import java.util.List;
import java.util.Optional;

import aus.t4.paladins.gestorback.dto.ValorAtributoRequestDTO;
import aus.t4.paladins.gestorback.dto.ValorAtributoResponseDTO;

public interface IValorAtributoService {

  List<ValorAtributoResponseDTO> findAll();

  Optional<ValorAtributoResponseDTO> findById(Long id);

  Optional<ValorAtributoResponseDTO> save(ValorAtributoRequestDTO request);

  Optional<ValorAtributoResponseDTO> update(Long id, ValorAtributoRequestDTO request);

  boolean deleteById(Long id);
}
