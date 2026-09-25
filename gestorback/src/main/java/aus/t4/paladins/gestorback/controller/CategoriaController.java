package aus.t4.paladins.gestorback.controller;

import aus.t4.paladins.gestorback.dto.CategoriaRequestDTO;
import aus.t4.paladins.gestorback.dto.CategoriaResponseDTO;
import aus.t4.paladins.gestorback.service.ICategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

  private final ICategoriaService service;

  public CategoriaController(ICategoriaService service) {
    this.service = service;
  }

  @GetMapping
  public List<CategoriaResponseDTO> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoriaResponseDTO> findById(
      @PathVariable Long id) {

    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<CategoriaResponseDTO> create(
      @RequestBody CategoriaRequestDTO request) {

    return service.save(request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoriaResponseDTO> update(
      @PathVariable Long id,
      @RequestBody CategoriaRequestDTO request) {

    return service.update(id, request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {

    if (!service.deleteById(id)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
  }
}
