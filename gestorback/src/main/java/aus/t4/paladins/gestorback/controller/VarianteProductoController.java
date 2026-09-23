package aus.t4.paladins.gestorback.controller;

import aus.t4.paladins.gestorback.dto.VarianteListadoDTO;
import aus.t4.paladins.gestorback.dto.VarianteProductoRequestDTO;
import aus.t4.paladins.gestorback.dto.VarianteProductoResponseDTO;
import aus.t4.paladins.gestorback.service.IVarianteProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variantes")
public class VarianteProductoController {

  private final IVarianteProductoService service;

  public VarianteProductoController(
      IVarianteProductoService service) {

    this.service = service;
  }

  @GetMapping
  public List<VarianteProductoResponseDTO> findAll() {
    return service.findAll();
  }

  // Endpoint "de lectura directa" para lista-productos en Angular
  @GetMapping("/listado")
  public List<VarianteListadoDTO> findAllParaListado() {
    return service.findAllParaListado();
  }

  @GetMapping("/{id}")
  public ResponseEntity<VarianteProductoResponseDTO> findById(
      @PathVariable Long id) {

    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<VarianteProductoResponseDTO> create(
      @RequestBody VarianteProductoRequestDTO request) {

    return service.save(request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<VarianteProductoResponseDTO> update(
      @PathVariable Long id,
      @RequestBody VarianteProductoRequestDTO request) {

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
