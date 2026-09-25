package aus.t4.paladins.gestorback.service;

import aus.t4.paladins.gestorback.dto.AtributoRequestDTO;
import aus.t4.paladins.gestorback.dto.AtributoResponseDTO;
import aus.t4.paladins.gestorback.mapper.AtributoMapper;
import aus.t4.paladins.gestorback.model.Atributo;
import aus.t4.paladins.gestorback.repository.AtributoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtributoService implements IAtributoService {

  private final AtributoRepository repository;

  public AtributoService(AtributoRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<AtributoResponseDTO> findAll() {
    return repository.findAll()
        .stream()
        .map(AtributoMapper::toDTO)
        .toList();
  }

  @Override
  public Optional<AtributoResponseDTO> findById(Long id) {
    return repository.findById(id)
        .map(AtributoMapper::toDTO);
  }

  @Override
  public AtributoResponseDTO save(AtributoRequestDTO request) {
    Atributo atributo = new Atributo();
    atributo.setNombre(request.getNombre());

    Atributo saved = repository.save(atributo);

    return AtributoMapper.toDTO(saved);
  }

  @Override
  public Optional<AtributoResponseDTO> update(
      Long id,
      AtributoRequestDTO request) {

    return repository.findById(id)
        .map(atributo -> {
          atributo.setNombre(request.getNombre());

          Atributo updated = repository.save(atributo);

          return AtributoMapper.toDTO(updated);
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
