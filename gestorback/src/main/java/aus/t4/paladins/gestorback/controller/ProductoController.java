package aus.t4.paladins.gestorback.controller;

import aus.t4.paladins.gestorback.dto.ProductoRequestDTO;
import aus.t4.paladins.gestorback.dto.ProductoResponseDTO;
import aus.t4.paladins.gestorback.service.IProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

  private final IProductoService service;

  public ProductoController(IProductoService service) {
    this.service = service;
  }

  @GetMapping
  public List<ProductoResponseDTO> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductoResponseDTO> findById(
      @PathVariable Long id) {

    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<ProductoResponseDTO> create(
      @RequestBody ProductoRequestDTO request) {

    return service.save(request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductoResponseDTO> update(
      @PathVariable Long id,
      @RequestBody ProductoRequestDTO request) {

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
