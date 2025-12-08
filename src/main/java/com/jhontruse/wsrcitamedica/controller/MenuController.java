package com.jhontruse.wsrcitamedica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhontruse.wsrcitamedica.model.entity.Menu;
import com.jhontruse.wsrcitamedica.service.IMenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Tag(name = "Menu", description = "Gestión de menu del sistema")
@SecurityRequirement(name = "bearerAuth")
public class MenuController {

  @Autowired
  private final IMenuService iMenuService;

  private static final Logger log = LoggerFactory.getLogger(MenuController.class);

  @Operation(summary = "Buscar menu", description = "")
  @GetMapping("find/all")
  public ResponseEntity<List<Menu>> findAllMenu() {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuController - findAllMenu");
    log.info("********************************");
    log.info("********************************");
    List<Menu> menus = iMenuService.findAll();
    return ResponseEntity.ok(menus);
  }

  @Operation(summary = "Buscar menu", description = "")
  @GetMapping("find/id/{id}")
  public ResponseEntity<Optional<Menu>> findByIdMenu(
      @Parameter(description = "UUID a buscar", example = "", required = true) @PathVariable("id") String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuController - findByIdMenu");
    log.info("********************************");
    log.info("********************************");
    log.info("id: {}", id);
    Optional<Menu> menus = iMenuService.findById(id);
    return ResponseEntity.ok(menus);
  }

  @Operation(summary = "Buscar menu", description = "")
  @PreAuthorize("@authorizeLogic.hasAccess('findByNombreMenuMenu')")
  @GetMapping("find/nombre_menu/{nombre_menu}")
  public ResponseEntity<Optional<Menu>> findByNombreMenuMenu(
      @Parameter(description = "Nombre menu a buscar", example = "", required = true) @PathVariable("nombre_menu") String nombre_menu) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuController - findByNombreMenuMenu");
    log.info("********************************");
    log.info("********************************");
    log.info("nombre_menu: {}", nombre_menu);
    Optional<Menu> menus = iMenuService.findByNombreMenu(nombre_menu);
    return ResponseEntity.ok(menus);
  }

  @Operation(summary = "Buscar menu", description = "")
  @GetMapping("find/activo/{activo}")
  public ResponseEntity<List<Menu>> findByActivoMenuMenu(
      @Parameter(description = "Activo menu a buscar", example = "", required = true) @PathVariable("activo") Boolean activo) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuController - findByActivoMenuMenu");
    log.info("********************************");
    log.info("********************************");
    log.info("activo: {}", activo);
    List<Menu> menus = iMenuService.findByActivoMenu(activo);
    return ResponseEntity.ok(menus);
  }

  @Operation(summary = "Buscar menu", description = "")
  @GetMapping("find/visible/{visible}")
  public ResponseEntity<List<Menu>> findByVisibleMenuMenu(
      @Parameter(description = "Activo menu a buscar", example = "", required = true) @PathVariable("visible") Boolean visible) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuController - findByVisibleMenuMenu");
    log.info("********************************");
    log.info("********************************");
    log.info("visible: {}", visible);
    List<Menu> menus = iMenuService.findByVisibleMenu(visible);
    return ResponseEntity.ok(menus);
  }

  @Operation(summary = "Registrar menu", description = "")
  @PostMapping("save")
  public ResponseEntity<Menu> saveMenu(
      @Parameter(description = "Entity Menu", example = "", required = true) @Valid @RequestBody Menu menuRequest) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuController - saveMenu");
    log.info("********************************");
    log.info("********************************");
    log.info("menuRequest {}", menuRequest);
    Menu menuSave = iMenuService.saveMenu(menuRequest);
    log.info("menuSave {}", menuSave);
    return ResponseEntity.ok(menuSave);
  }

  @Operation(summary = "Actualizar menu", description = "")
  @PutMapping("update")
  public ResponseEntity<Optional<Menu>> updateMenu(
      @Parameter(description = "Entity Menu", example = "", required = true) @Valid @RequestBody Menu menuRequest) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuController - updateMenu");
    log.info("********************************");
    log.info("********************************");
    log.info("menuRequest {}", menuRequest);
    Optional<Menu> menuSave = iMenuService.updateMenu(menuRequest);
    log.info("menuSave {}", menuSave);
    return ResponseEntity.ok(menuSave);
  }

  @Operation(summary = "Eliminar menu", description = "")
  @DeleteMapping("delete/{id}")
  public ResponseEntity<Optional<Menu>> deleteMenu(
      @Parameter(description = "UUID a buscar", example = "", required = true) @PathVariable("id") String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuController - deleteMenu");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    Optional<Menu> menu = iMenuService.deleteById(id);
    log.info("menu {}", menu);
    return ResponseEntity.ok(menu);
  }

}
