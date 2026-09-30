package aus.t4.paladins.gestorback.controller;

import aus.t4.paladins.gestorback.dto.ProductoAltaCompletaRequestDTO;
import aus.t4.paladins.gestorback.dto.VarianteFiltroDTO;
import aus.t4.paladins.gestorback.dto.VarianteListadoDTO;
import aus.t4.paladins.gestorback.dto.VarianteProductoRequestDTO;
import aus.t4.paladins.gestorback.dto.VarianteProductoResponseDTO;
import aus.t4.paladins.gestorback.service.IVarianteProductoService;
import aus.t4.paladins.gestorback.service.ProductoAltaCompletaService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/variantes")
public class VarianteProductoController {

  private final IVarianteProductoService service;
  private final ProductoAltaCompletaService altaCompletaService;

  public VarianteProductoController(
      IVarianteProductoService service, ProductoAltaCompletaService altaCompletaService) {

    this.service = service;
    this.altaCompletaService = altaCompletaService;
  }

  @GetMapping
  public List<VarianteProductoResponseDTO> findAll() {
    return service.findAll();
  }

  // Endpoint "de lectura directa" para lista-productos en Angular
  @GetMapping("/listado")
  public List<VarianteListadoDTO> buscar(
      @RequestParam(required = false) String busqueda,
      @RequestParam(required = false) Long departamentoId,
      @RequestParam(required = false) BigDecimal precioMin,
      @RequestParam(required = false) BigDecimal precioMax,
      @RequestParam(required = false) Boolean soloConStock,
      @RequestParam(required = false) Boolean soloSinStock,
      @RequestParam(required = false) Boolean mostrarInactivos,
      @RequestParam(required = false) String ordenarPor) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    boolean esAdmin = authentication != null && authentication.getAuthorities().stream()
        .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    VarianteFiltroDTO filtro = new VarianteFiltroDTO(
        busqueda, departamentoId, precioMin, precioMax,
        soloConStock, soloSinStock, esAdmin && Boolean.TRUE.equals(mostrarInactivos), ordenarPor);
    return service.buscar(filtro);
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

  @PostMapping("/alta-completa")
  public ResponseEntity<VarianteProductoResponseDTO> altaCompleta(
      @RequestBody ProductoAltaCompletaRequestDTO request) {
    return ResponseEntity.ok(altaCompletaService.altaCompleta(request));
  }
}
