package aus.t4.paladins.gestorback.controller;

import aus.t4.paladins.gestorback.dto.DepartamentoRequestDTO;
import aus.t4.paladins.gestorback.dto.DepartamentoResponseDTO;
import aus.t4.paladins.gestorback.service.IDepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

  private final IDepartamentoService service;

  public DepartamentoController(IDepartamentoService service) {
    this.service = service;
  }

  @GetMapping
  public List<DepartamentoResponseDTO> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<DepartamentoResponseDTO> findById(
      @PathVariable Long id) {

    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<DepartamentoResponseDTO> create(
      @RequestBody DepartamentoRequestDTO request) {

    return ResponseEntity.ok(service.save(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<DepartamentoResponseDTO> update(
      @PathVariable Long id,
      @RequestBody DepartamentoRequestDTO request) {

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
