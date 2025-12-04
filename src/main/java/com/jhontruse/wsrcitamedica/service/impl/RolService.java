package com.jhontruse.wsrcitamedica.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhontruse.wsrcitamedica.model.entity.Rol;
import com.jhontruse.wsrcitamedica.repository.IRolRepository;
import com.jhontruse.wsrcitamedica.service.IRolService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
// JWT - AUTH
public class RolService implements IRolService {

  @Autowired
  private IRolRepository iRolRepository;

  @Override
  public List<Rol> findAll() {
    log.info("********************************");
    log.info("********************************");
    log.info("RolService - findAll");
    log.info("********************************");
    log.info("********************************");
    List<Rol> roles = iRolRepository.findAll().stream().toList();
    log.info("roles {}", roles);
    return roles;
  }

  @Override
  public Optional<Rol> findById(String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolService - findById");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    Optional<Rol> rolOpt = iRolRepository.findById(id).stream().findFirst();
    log.info("rolOpt {}", rolOpt);
    return rolOpt;
  }

  @Override
  public Optional<Rol> findByNombreRol(String nombreRol) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolService - findByNombreRol");
    log.info("********************************");
    log.info("********************************");
    log.info("nombreRol {}", nombreRol);
    Optional<Rol> rolOpt = iRolRepository.findByNombreRol(nombreRol.toString()).stream().findFirst();
    log.info("rolOpt {}", rolOpt);
    return rolOpt;
  }

  @Override
  public List<Rol> findByActivoRol(Boolean activoRol) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolService - findByActivoRol");
    log.info("********************************");
    log.info("********************************");
    log.info("activoRol {}", activoRol);
    List<Rol> rolOpt = iRolRepository.findByActivoRol(activoRol).stream().toList();
    log.info("rolOpt {}", rolOpt);
    return rolOpt;
  }

  @Override
  public Rol saveRol(Rol rol) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolService - saveRol");
    log.info("********************************");
    log.info("********************************");
    rol.setIdRol(UUID.randomUUID().toString());
    rol.setFecCreacionRol(LocalDateTime.now());
    log.info("rol {}", rol);
    Integer responseRepository = iRolRepository.executeSaveRol(
        rol.getIdRol().toString(),
        rol.getNombreRol(),
        rol.getDescripcionRol(),
        rol.getActivoRol(),
        rol.getFecCreacionRol());
    log.info("responseRepository {}", responseRepository);
    return responseRepository == 1 ? rol : rol;
  }

  @Override
  public Optional<Rol> updateRol(Rol rol) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolService - updateRol");
    log.info("********************************");
    log.info("********************************");
    log.info("rol {}", rol);
    int ResponseRepository = iRolRepository.executeUpdateRol(
        rol.getNombreRol(),
        rol.getDescripcionRol(),
        rol.getActivoRol(),
        LocalDateTime.now(),
        rol.getIdRol());
    log.info("ResponseRepository {}", ResponseRepository);

    return ResponseRepository == 1 ? Optional.ofNullable(rol) : null;
  }

  @Override
  public Optional<Rol> deleteById(String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("RolService - deleteById");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    iRolRepository.deleteById(id);
    Optional<Rol> rol = iRolRepository.findById(id).stream().findFirst();
    return rol;
  }

}
