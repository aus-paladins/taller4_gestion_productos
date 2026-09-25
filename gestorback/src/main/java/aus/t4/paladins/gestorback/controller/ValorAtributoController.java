package aus.t4.paladins.gestorback.controller;

import aus.t4.paladins.gestorback.dto.ValorAtributoRequestDTO;
import aus.t4.paladins.gestorback.dto.ValorAtributoResponseDTO;
import aus.t4.paladins.gestorback.service.IValorAtributoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/valores-atributo")
public class ValorAtributoController {

  private final IValorAtributoService service;

  public ValorAtributoController(
      IValorAtributoService service) {

    this.service = service;
  }

  @GetMapping
  public List<ValorAtributoResponseDTO> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ValorAtributoResponseDTO> findById(
      @PathVariable Long id) {

    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<ValorAtributoResponseDTO> create(
      @RequestBody ValorAtributoRequestDTO request) {

    return service.save(request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ValorAtributoResponseDTO> update(
      @PathVariable Long id,
      @RequestBody ValorAtributoRequestDTO request) {

    return service.update(id, request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @PathVariable Long id) {

    if (!service.deleteById(id)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
  }
}
