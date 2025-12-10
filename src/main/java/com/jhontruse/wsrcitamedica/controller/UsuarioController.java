package com.jhontruse.wsrcitamedica.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhontruse.wsrcitamedica.model.dto.UsuarioDTO;
import com.jhontruse.wsrcitamedica.model.entity.Usuario;
import com.jhontruse.wsrcitamedica.service.IUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Gestión de usuario del sistema")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

  @Autowired
  private final IUsuarioService iUsuarioService;

  private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

  @Operation(summary = "Buscar usuario", description = "")
  @GetMapping("find/all")
  public ResponseEntity<List<Usuario>> findAllUsuario() {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioController - findAllUsuario");
    log.info("********************************");
    log.info("********************************");
    List<Usuario> usuarios = iUsuarioService.findAll();
    return ResponseEntity.ok(usuarios);
  }

  @Operation(summary = "Buscar usuario", description = "")
  @GetMapping("find/all/dto")
  public ResponseEntity<List<UsuarioDTO>> findAllUsuarioDTO() {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioController - findAllUsuarioDTO");
    log.info("********************************");
    log.info("********************************");
    List<UsuarioDTO> usuariosDTO = iUsuarioService.findAllUsuarioDTO();
    return ResponseEntity.ok(usuariosDTO);
  }

  @Operation(summary = "Buscar id", description = "")
  @GetMapping("find/id/{id}")
  public ResponseEntity<Optional<Usuario>> findByIdUsuario(
      @Parameter(description = "UUID a buscar", example = "", required = true) @PathVariable("id") String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioController - findByIdUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("id: {}", id);
    Optional<Usuario> usuario = iUsuarioService.findById(id);
    return ResponseEntity.ok(usuario);
  }

  @Operation(summary = "Buscar usuario", description = "")
  @GetMapping("find/usuario/{usuario}")
  public ResponseEntity<Optional<Usuario>> findByUsuarioUsuario(
      @Parameter(description = "Usuario a buscar", example = "", required = true) @PathVariable("usuario") String usuario) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioController - findByUsuarioUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("usuario: {}", usuario);
    Optional<Usuario> usuarioOpt = iUsuarioService.findByUsuario(usuario);
    return ResponseEntity.ok(usuarioOpt);
  }

  @Operation(summary = "Buscar usuario", description = "")
  @GetMapping("find/activo/{activo}")
  public ResponseEntity<List<Usuario>> findByActivoUsuario(
      @Parameter(description = "Activo usuario a buscar", example = "", required = true) @PathVariable("activo") Boolean activo) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioController - findByActivoUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("activo: {}", activo);
    List<Usuario> usuarios = iUsuarioService.findByActivoUsuario(activo);
    return ResponseEntity.ok(usuarios);
  }

  @Operation(summary = "Buscar usuario", description = "")
  @GetMapping("find/locked/{locked}")
  public ResponseEntity<List<Usuario>> findByLockedUsuario(
      @Parameter(description = "Bloqueo usuario a buscar", example = "", required = true) @PathVariable("locked") Boolean locked) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioController - findByLockedUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("locked: {}", locked);
    List<Usuario> usuarios = iUsuarioService.findByLockedUsuario(locked);
    return ResponseEntity.ok(usuarios);
  }

  @Operation(summary = "Buscar usuario", description = "")
  @GetMapping("find/intentos/{intentos}")
  public ResponseEntity<List<Usuario>> findByIntentoFallidoLoginUsuario(
      @Parameter(description = "Intentos usuario a buscar", example = "", required = true) @PathVariable("intentos") Integer intentos) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioController - findByIntentoFallidoLoginUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("intentos: {}", intentos);
    List<Usuario> usuarios = iUsuarioService.findByIntentoFallidoLoginUsuario(intentos);
    return ResponseEntity.ok(usuarios);
  }

  @Operation(summary = "Registrar usuario", description = "")
  @PostMapping("save")
  public ResponseEntity<Usuario> saveUsuario(
      @Parameter(description = "Entity Usuario", example = "", required = true) @Valid @RequestBody Usuario usuarioRequest) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioController - saveUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("usuarioRequest {}", usuarioRequest);
    Usuario usuarioSave = iUsuarioService.saveUsuario(usuarioRequest);
    log.info("usuarioSave {}", usuarioSave);
    return ResponseEntity.ok(usuarioSave);
  }

  @Operation(summary = "Actualizar usuario", description = "")
  @PutMapping("update")
  public ResponseEntity<Optional<Usuario>> updateUsuario(
      @Parameter(description = "Entity Usuario", example = "", required = true) @RequestBody Usuario usuarioRequest) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioController - updateUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("usuarioRequest {}", usuarioRequest);
    Optional<Usuario> usuarioSave = iUsuarioService.updateUsuario(usuarioRequest);
    log.info("usuarioSave {}", usuarioSave);
    return ResponseEntity.ok(usuarioSave);
  }

  @Operation(summary = "Eliminar usuario", description = "")
  @DeleteMapping("delete/{id}")
  public ResponseEntity<Optional<Usuario>> deleteUsuario(
      @Parameter(description = "UUID a buscar", example = "", required = true) @PathVariable("id") String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioController - deleteUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    Optional<Usuario> usuario = iUsuarioService.deleteById(id);
    log.info("usuario {}", usuario);
    return ResponseEntity.ok(usuario);
  }

}
