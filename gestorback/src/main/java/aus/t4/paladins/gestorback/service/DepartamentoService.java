package aus.t4.paladins.gestorback.service;

import aus.t4.paladins.gestorback.dto.DepartamentoRequestDTO;
import aus.t4.paladins.gestorback.dto.DepartamentoResponseDTO;
import aus.t4.paladins.gestorback.mapper.DepartamentoMapper;
import aus.t4.paladins.gestorback.model.Departamento;
import aus.t4.paladins.gestorback.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService implements IDepartamentoService {

  private final DepartamentoRepository repository;

  public DepartamentoService(DepartamentoRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<DepartamentoResponseDTO> findAll() {
    return repository.findAll()
        .stream()
        .map(DepartamentoMapper::toDTO)
        .toList();
  }

  @Override
  public Optional<DepartamentoResponseDTO> findById(Long id) {
    return repository.findById(id)
        .map(DepartamentoMapper::toDTO);
  }

  public DepartamentoResponseDTO save(DepartamentoRequestDTO request) {

    Departamento departamento = new Departamento();
    departamento.setNombre(request.getNombre());

    Departamento saved = repository.save(departamento);

    return DepartamentoMapper.toDTO(saved);
  }

  @Override
  public Optional<DepartamentoResponseDTO> update(
      Long id,
      DepartamentoRequestDTO request) {

    return repository.findById(id)
        .map(departamento -> {

          departamento.setNombre(request.getNombre());

          Departamento updated = repository.save(departamento);

          return DepartamentoMapper.toDTO(updated);
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
