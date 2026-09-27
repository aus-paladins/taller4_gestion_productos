package aus.t4.paladins.gestorback.controller;

import aus.t4.paladins.gestorback.security.JwtProvider;
import aus.t4.paladins.gestorback.model.RolUsuario;
import aus.t4.paladins.gestorback.model.Usuario;
import aus.t4.paladins.gestorback.repository.UsuarioRepository;
import aus.t4.paladins.gestorback.web.dto.AuthResponseDTO;
import aus.t4.paladins.gestorback.web.dto.LoginRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtProvider,
            UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String token = jwtProvider.generateToken(authentication);
        String role = authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        return ResponseEntity.ok(new AuthResponseDTO(token, authentication.getName(), role));
        }

        @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody LoginRequestDTO request) {
        String username = request.getUsername() == null ? "" : request.getUsername().trim();
        String password = request.getPassword();
        if (username.length() < 3 || username.length() > 80 || password == null || password.length() < 8) {
            return ResponseEntity.badRequest().body("El usuario debe tener entre 3 y 80 caracteres y la contraseña al menos 8.");
        }
        if (usuarioRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ese nombre de usuario ya está registrado.");
        }

        Usuario usuario = Usuario.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .rol(RolUsuario.INVITADO)
            .build();
        usuarioRepository.save(usuario);

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new AuthResponseDTO(token, username, RolUsuario.INVITADO.name()));
    }

    @GetMapping("/me")
    public ResponseEntity<String> me(Authentication authentication) {
        if (authentication == null)
            return ResponseEntity.ok("anonymous");
        return ResponseEntity.ok(authentication.getName());
    }
}
