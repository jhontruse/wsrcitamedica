package com.jhontruse.wsrcitamedica.service;

import java.util.List;
import java.util.Optional;

import com.jhontruse.wsrcitamedica.model.entity.Rol;

public interface IRolService {

  List<Rol> findAll();

  Optional<Rol> findById(String id);

  Optional<Rol> findByNombreRol(String nombreRol);

  List<Rol> findByActivoRol(Boolean activoRol);

  Rol saveRol(Rol rol);

  Optional<Rol> updateRol(Rol rol);

  Optional<Rol> deleteById(String id);

}
