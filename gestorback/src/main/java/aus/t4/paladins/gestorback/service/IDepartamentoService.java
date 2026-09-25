package aus.t4.paladins.gestorback.service;

import java.util.List;
import java.util.Optional;

import aus.t4.paladins.gestorback.dto.DepartamentoRequestDTO;
import aus.t4.paladins.gestorback.dto.DepartamentoResponseDTO;

public interface IDepartamentoService {

  List<DepartamentoResponseDTO> findAll();

  Optional<DepartamentoResponseDTO> findById(Long id);

  DepartamentoResponseDTO save(DepartamentoRequestDTO request);

  Optional<DepartamentoResponseDTO> update(Long id, DepartamentoRequestDTO request);

  boolean deleteById(Long id);
}
