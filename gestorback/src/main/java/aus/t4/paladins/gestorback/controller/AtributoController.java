package aus.t4.paladins.gestorback.controller;

import aus.t4.paladins.gestorback.dto.AtributoRequestDTO;
import aus.t4.paladins.gestorback.dto.AtributoResponseDTO;
import aus.t4.paladins.gestorback.service.IAtributoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atributos")
public class AtributoController {

  private final IAtributoService service;

  public AtributoController(IAtributoService service) {
    this.service = service;
  }

  @GetMapping
  public List<AtributoResponseDTO> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<AtributoResponseDTO> findById(
      @PathVariable Long id) {

    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<AtributoResponseDTO> create(
      @RequestBody AtributoRequestDTO request) {

    return ResponseEntity.ok(service.save(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AtributoResponseDTO> update(
      @PathVariable Long id,
      @RequestBody AtributoRequestDTO request) {

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
