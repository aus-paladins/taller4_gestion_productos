package aus.t4.paladins.gestorback.service;

import aus.t4.paladins.gestorback.dto.CategoriaRequestDTO;
import aus.t4.paladins.gestorback.dto.CategoriaResponseDTO;
import aus.t4.paladins.gestorback.mapper.CategoriaMapper;
import aus.t4.paladins.gestorback.model.Categoria;
import aus.t4.paladins.gestorback.model.Departamento;
import aus.t4.paladins.gestorback.repository.CategoriaRepository;
import aus.t4.paladins.gestorback.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {

  private final CategoriaRepository categoriaRepository;
  private final DepartamentoRepository departamentoRepository;

  public CategoriaService(
      CategoriaRepository categoriaRepository,
      DepartamentoRepository departamentoRepository) {

    this.categoriaRepository = categoriaRepository;
    this.departamentoRepository = departamentoRepository;
  }

  @Override
  public List<CategoriaResponseDTO> findAll() {
    return categoriaRepository.findAll()
        .stream()
        .map(CategoriaMapper::toDTO)
        .toList();
  }

  @Override
  public Optional<CategoriaResponseDTO> findById(Long id) {
    return categoriaRepository.findById(id)
        .map(CategoriaMapper::toDTO);
  }

  @Override
  public Optional<CategoriaResponseDTO> save(CategoriaRequestDTO request) {

    Optional<Departamento> departamento = departamentoRepository.findById(request.getDepartamentoId());

    if (departamento.isEmpty()) {
      return Optional.empty();
    }

    Categoria categoria = new Categoria();
    categoria.setNombre(request.getNombre());
    categoria.setDepartamento(departamento.get());

    Categoria saved = categoriaRepository.save(categoria);

    return Optional.of(CategoriaMapper.toDTO(saved));
  }

  @Override
  public Optional<CategoriaResponseDTO> update(
      Long id,
      CategoriaRequestDTO request) {

    Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

    Optional<Departamento> departamento = departamentoRepository.findById(request.getDepartamentoId());

    if (categoriaOptional.isEmpty() || departamento.isEmpty()) {
      return Optional.empty();
    }

    Categoria categoria = categoriaOptional.get();

    categoria.setNombre(request.getNombre());
    categoria.setDepartamento(departamento.get());

    Categoria updated = categoriaRepository.save(categoria);

    return Optional.of(CategoriaMapper.toDTO(updated));
  }

  @Override
  public boolean deleteById(Long id) {

    if (!categoriaRepository.existsById(id)) {
      return false;
    }

    categoriaRepository.deleteById(id);
    return true;
  }
}
