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

import com.jhontruse.wsrcitamedica.model.entity.Rol;
import com.jhontruse.wsrcitamedica.service.IRolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/rol")
@Tag(name = "Rol", description = "Gestión de rol del sistema")
@SecurityRequirement(name = "bearerAuth")
public class RolController {

  @Autowired
  private final IRolService iRolService;

  private static final Logger log = LoggerFactory.getLogger(RolController.class);

  @Operation(summary = "Buscar rol", description = "")
  @GetMapping("find/all")
  public ResponseEntity<List<Rol>> findAllRol() {
    log.info("********************************");
    log.info("********************************");
    log.info("RolController - findAllRol");
    log.info("********************************");
    log.info("********************************");
    List<Rol> roles = iRolService.findAll();
    return ResponseEntity.ok(roles);
  }

  @Operation(summary = "Buscar rol", description = "")
  @GetMapping("find/id/{id}")
  public ResponseEntity<Optional<Rol>> findByIdRol(
      @Parameter(description = "UUID a buscar", example = "", required = true) @PathVariable("id") String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolController - findByIdRol");
    log.info("********************************");
    log.info("********************************");
    log.info("id: {}", id);
    Optional<Rol> rol = iRolService.findById(id);
    return ResponseEntity.ok(rol);
  }

  @Operation(summary = "Buscar rol", description = "")
  @GetMapping("find/nombre_rol/{nombre_rol}")
  public ResponseEntity<Optional<Rol>> findByNombreRolRol(
      @Parameter(description = "Nombre rol a buscar", example = "", required = true) @PathVariable("nombre_rol") String nombre_rol) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolController - findByNombreRolRol");
    log.info("********************************");
    log.info("********************************");
    log.info("nombre_rol: {}", nombre_rol);
    Optional<Rol> rol = iRolService.findByNombreRol(nombre_rol);
    return ResponseEntity.ok(rol);
  }

  @Operation(summary = "Buscar rol", description = "")
  @GetMapping("find/activo/{activo}")
  public ResponseEntity<List<Rol>> findByActivoRolRol(
      @Parameter(description = "Activo rol a buscar", example = "", required = true) @PathVariable("activo") Boolean activo) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolController - findByActivoRolRol");
    log.info("********************************");
    log.info("********************************");
    log.info("activo: {}", activo);
    List<Rol> roles = iRolService.findByActivoRol(activo);
    return ResponseEntity.ok(roles);
  }

  @Operation(summary = "Registrar rol", description = "")
  @PostMapping("save")
  public ResponseEntity<Rol> saveRol(
      @Parameter(description = "Entity Rol", example = "", required = true) @Valid @RequestBody Rol rolRequest) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolController - saveRol");
    log.info("********************************");
    log.info("********************************");
    log.info("rolRequest {}", rolRequest);
    Rol rolSave = iRolService.saveRol(rolRequest);
    log.info("rolSave {}", rolSave);
    return ResponseEntity.ok(rolSave);
  }

  @Operation(summary = "Actualizar rol", description = "")
  @PutMapping("update")
  public ResponseEntity<Optional<Rol>> updateRol(
      @Parameter(description = "Entity Rol", example = "", required = true) @Valid @RequestBody Rol rolRequest) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolController - updateRol");
    log.info("********************************");
    log.info("********************************");
    log.info("rolRequest {}", rolRequest);
    Optional<Rol> rolSave = iRolService.updateRol(rolRequest);
    log.info("rolSave {}", rolSave);
    return ResponseEntity.ok(rolSave);
  }

  @Operation(summary = "Eliminar rol", description = "")
  @DeleteMapping("delete/{id}")
  public ResponseEntity<Optional<Rol>> deleteRol(
      @Parameter(description = "UUID a buscar", example = "", required = true) @PathVariable("id") String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolController - deleteRol");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    Optional<Rol> rol = iRolService.deleteById(id);
    log.info("rol {}", rol);
    return ResponseEntity.ok(rol);
  }

}
