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

import com.jhontruse.wsrcitamedica.model.entity.Doctor;
import com.jhontruse.wsrcitamedica.model.entity.Usuario;
import com.jhontruse.wsrcitamedica.service.IDoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/doctor")
@Tag(name = "Doctor", description = "Gestión de doctor del sistema")
@SecurityRequirement(name = "bearerAuth")
public class DoctorController {

  @Autowired
  private final IDoctorService iDoctorService;

  private static final Logger log = LoggerFactory.getLogger(DoctorController.class);

  @Operation(summary = "Buscar doctor", description = "")
  @GetMapping("find/all")
  public ResponseEntity<List<Doctor>> findAllDoctor() {
    log.info("********************************");
    log.info("********************************");
    log.info("DoctorController - findAllDoctor");
    log.info("********************************");
    log.info("********************************");
    List<Doctor> doctores = iDoctorService.findAll();
    log.info("doctores: {}", doctores);
    return ResponseEntity.ok(doctores);
  }

  @Operation(summary = "Buscar doctor", description = "")
  @GetMapping("find/id/{id}")
  public ResponseEntity<Optional<Doctor>> findByIdDoctor(
      @Parameter(description = "UUID a buscar", example = "", required = true) @PathVariable("id") String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("DoctorController - findByIdDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("id: {}", id);
    Optional<Doctor> doctor = iDoctorService.findById(id);
    log.info("doctor: {}", doctor);
    return ResponseEntity.ok(doctor);
  }

  @Operation(summary = "Buscar doctor", description = "")
  @GetMapping("find/nombre/{nombreDoctor}")
  public ResponseEntity<List<Doctor>> findByNombreDoctor(
      @Parameter(description = "Nombre a buscar", example = "", required = true) @PathVariable("nombreDoctor") String nombreDoctor) {
    log.info("********************************");
    log.info("********************************");
    log.info("DoctorController - findByNombreDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("nombreDoctor: {}", nombreDoctor);
    List<Doctor> doctores = iDoctorService.findByNombreDoctor(nombreDoctor);
    log.info("doctores: {}", doctores);
    return ResponseEntity.ok(doctores);
  }

  @Operation(summary = "Buscar doctor", description = "")
  @GetMapping("find/especialidad/{especialidadDoctor}")
  public ResponseEntity<List<Doctor>> findByEspecialidadDoctor(
      @Parameter(description = "Especialidad a buscar", example = "", required = true) @PathVariable("especialidadDoctor") String especialidadDoctor) {
    log.info("********************************");
    log.info("********************************");
    log.info("DoctorController - findByEspecialidadDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("especialidadDoctor: {}", especialidadDoctor);
    List<Doctor> doctores = iDoctorService.findByEspecialidadDoctor(especialidadDoctor);
    log.info("doctores: {}", doctores);
    return ResponseEntity.ok(doctores);
  }

  @Operation(summary = "Buscar doctor", description = "")
  @GetMapping("find/activo/{activoDoctor}")
  public ResponseEntity<List<Doctor>> findByActivoDoctor(
      @Parameter(description = "Activo a buscar", example = "", required = true) @PathVariable("activoDoctor") Boolean activoDoctor) {
    log.info("********************************");
    log.info("********************************");
    log.info("DoctorController - findByActivoDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("activoDoctor: {}", activoDoctor);
    List<Doctor> doctores = iDoctorService.findByActivoDoctor(activoDoctor);
    log.info("doctores: {}", doctores);
    return ResponseEntity.ok(doctores);
  }

  @Operation(summary = "Buscar doctor", description = "")
  @GetMapping("find/duracion/{duracionCitaDoctor}")
  public ResponseEntity<List<Doctor>> findByDuracionCitaDoctor(
      @Parameter(description = "Duracion a buscar", example = "", required = true) @PathVariable("duracionCitaDoctor") Integer duracionCitaDoctor) {
    log.info("********************************");
    log.info("********************************");
    log.info("DoctorController - findByDuracionCitaDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("duracionCitaDoctor: {}", duracionCitaDoctor);
    List<Doctor> doctores = iDoctorService.findByDuracionCitaDoctor(duracionCitaDoctor);
    log.info("doctores: {}", doctores);
    return ResponseEntity.ok(doctores);
  }

  @Operation(summary = "Buscar doctor", description = "")
  @GetMapping("find/usuario/{idUsuario}")
  public ResponseEntity<Optional<Doctor>> findByIdUsuarioDoctor(
      @Parameter(description = "Usuario a buscar", example = "", required = true) @PathVariable("idUsuario") String idUsuario) {
    log.info("********************************");
    log.info("********************************");
    log.info("DoctorController - findByDuracionCitaDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("idUsuario: {}", idUsuario);
    Optional<Doctor> doctorOpt = iDoctorService.findByIdUsuario(idUsuario);
    log.info("doctorOpt: {}", doctorOpt);
    return ResponseEntity.ok(doctorOpt);
  }

  @Operation(summary = "Registrar doctor", description = "")
  @PostMapping("save")
  public ResponseEntity<Optional<Doctor>> saveDoctor(
      @Parameter(description = "Entity Doctor", example = "", required = true) @Valid @RequestBody Doctor doctorRequest) {
    log.info("********************************");
    log.info("********************************");
    log.info("DoctorController - saveDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("doctorRequest {}", doctorRequest);
    Optional<Doctor> doctorSave = iDoctorService.executeSaveDoctor(doctorRequest);
    log.info("doctorSave {}", doctorSave);
    return ResponseEntity.ok(doctorSave);
  }

  @Operation(summary = "Actualizar doctor", description = "")
  @PutMapping("update")
  public ResponseEntity<Optional<Doctor>> updateDoctor(
      @Parameter(description = "Entity Doctor", example = "", required = true) @RequestBody Doctor doctorRequest) {
    log.info("********************************");
    log.info("********************************");
    log.info("DoctorController - updateDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("doctorRequest {}", doctorRequest);
    Optional<Doctor> doctorOpt = iDoctorService.executeUpdateDoctor(doctorRequest);
    log.info("doctorOpt {}", doctorOpt);
    return ResponseEntity.ok(doctorOpt);
  }

  @Operation(summary = "Eliminar doctor", description = "")
  @DeleteMapping("delete/{id}")
  public ResponseEntity<Optional<Doctor>> deleteDoctor(
      @Parameter(description = "UUID a buscar", example = "", required = true) @PathVariable("id") String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("DoctorController - deleteDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    Optional<Doctor> doctorOpt = iDoctorService.deleteById(id);
    log.info("doctorOpt {}", doctorOpt);
    return ResponseEntity.ok(doctorOpt);
  }

}
