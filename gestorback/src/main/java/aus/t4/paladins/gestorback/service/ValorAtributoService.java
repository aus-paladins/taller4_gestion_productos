package aus.t4.paladins.gestorback.service;

import aus.t4.paladins.gestorback.dto.ValorAtributoRequestDTO;
import aus.t4.paladins.gestorback.dto.ValorAtributoResponseDTO;
import aus.t4.paladins.gestorback.mapper.ValorAtributoMapper;
import aus.t4.paladins.gestorback.model.Atributo;
import aus.t4.paladins.gestorback.model.ValorAtributo;
import aus.t4.paladins.gestorback.repository.AtributoRepository;
import aus.t4.paladins.gestorback.repository.ValorAtributoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValorAtributoService implements IValorAtributoService {

  private final ValorAtributoRepository repository;
  private final AtributoRepository atributoRepository;

  public ValorAtributoService(
      ValorAtributoRepository repository,
      AtributoRepository atributoRepository) {

    this.repository = repository;
    this.atributoRepository = atributoRepository;
  }

  @Override
  public List<ValorAtributoResponseDTO> findAll() {
    return repository.findAll()
        .stream()
        .map(ValorAtributoMapper::toDTO)
        .toList();
  }

  @Override
  public Optional<ValorAtributoResponseDTO> findById(Long id) {
    return repository.findById(id)
        .map(ValorAtributoMapper::toDTO);
  }

  @Override
  public Optional<ValorAtributoResponseDTO> save(
      ValorAtributoRequestDTO request) {

    Optional<Atributo> atributo = atributoRepository.findById(request.getAtributoId());

    if (atributo.isEmpty()) {
      return Optional.empty();
    }

    ValorAtributo valor = new ValorAtributo();

    valor.setValor(request.getValor());
    valor.setAtributo(atributo.get());

    ValorAtributo saved = repository.save(valor);

    return Optional.of(ValorAtributoMapper.toDTO(saved));
  }

  @Override
  public Optional<ValorAtributoResponseDTO> update(
      Long id,
      ValorAtributoRequestDTO request) {

    Optional<Atributo> atributo = atributoRepository.findById(request.getAtributoId());

    if (atributo.isEmpty()) {
      return Optional.empty();
    }

    return repository.findById(id)
        .map(valor -> {

          valor.setValor(request.getValor());
          valor.setAtributo(atributo.get());

          ValorAtributo updated = repository.save(valor);

          return ValorAtributoMapper.toDTO(updated);
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
