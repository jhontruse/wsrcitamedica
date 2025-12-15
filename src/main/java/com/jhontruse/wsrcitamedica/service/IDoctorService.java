package com.jhontruse.wsrcitamedica.service;

import java.util.List;
import java.util.Optional;

import com.jhontruse.wsrcitamedica.model.dto.DoctorDTO;
import com.jhontruse.wsrcitamedica.model.entity.Doctor;

public interface IDoctorService {

  List<Doctor> findAll();

  Optional<Doctor> findById(String id);

  List<Doctor> findByNombreDoctor(String nombreDoctor);

  List<Doctor> findByEspecialidadDoctor(String especialidadDoctor);

  List<Doctor> findByActivoDoctor(Boolean activoDoctor);

  List<Doctor> findByDuracionCitaDoctor(Integer duracionCitaDoctor);

  Optional<Doctor> findByIdUsuario(String idUsuario);

  Optional<Doctor> executeSaveDoctor(Doctor doctor);

  Optional<Doctor> executeUpdateDoctor(Doctor doctor);

  Optional<Doctor> deleteById(String id);

  List<DoctorDTO> findAllDoctorDTO();

}
