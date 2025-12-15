package com.jhontruse.wsrcitamedica.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhontruse.wsrcitamedica.model.dto.DoctorDTO;
import com.jhontruse.wsrcitamedica.model.dto.UsuarioDTO;
import com.jhontruse.wsrcitamedica.model.entity.Doctor;
import com.jhontruse.wsrcitamedica.repository.IDoctorRepository;
import com.jhontruse.wsrcitamedica.service.IDoctorService;
import com.jhontruse.wsrcitamedica.service.IUsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorService implements IDoctorService {

  @Autowired
  private IDoctorRepository iDoctorRepository;

  @Autowired
  private IUsuarioService iUsuarioService;

  @Override
  public List<Doctor> findAll() {
    log.info("********************************");
    log.info("********************************");
    log.info("IDoctorRepository - findAll");
    log.info("********************************");
    log.info("********************************");
    List<Doctor> doctores = iDoctorRepository.findAll().stream().sorted().collect(Collectors.toList());
    log.info("doctores {}", doctores);
    return doctores;
  }

  @Override
  public Optional<Doctor> findById(String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("IDoctorRepository - findById");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    Optional<Doctor> doctorOpt = iDoctorRepository.findById(id).stream().findFirst();
    log.info("doctorOpt {}", doctorOpt);
    return doctorOpt;
  }

  @Override
  public List<Doctor> findByNombreDoctor(String nombreDoctor) {
    log.info("********************************");
    log.info("********************************");
    log.info("IDoctorRepository - findByNombreDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("nombreDoctor {}", nombreDoctor);
    List<Doctor> doctores = iDoctorRepository.findByNombreDoctor(
        nombreDoctor).stream().sorted().collect(Collectors.toList());
    log.info("doctores {}", doctores);
    return doctores;
  }

  @Override
  public List<Doctor> findByEspecialidadDoctor(String especialidadDoctor) {
    log.info("********************************");
    log.info("********************************");
    log.info("IDoctorRepository - findByEspecialidadDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("especialidadDoctor {}", especialidadDoctor);
    List<Doctor> doctores = iDoctorRepository.findByEspecialidadDoctor(
        especialidadDoctor).stream().sorted().collect(Collectors.toList());
    log.info("doctores {}", doctores);
    return doctores;
  }

  @Override
  public List<Doctor> findByActivoDoctor(Boolean activoDoctor) {
    log.info("********************************");
    log.info("********************************");
    log.info("IDoctorRepository - findByActivoDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("activoDoctor {}", activoDoctor);
    List<Doctor> doctores = iDoctorRepository.findByActivoDoctor(
        activoDoctor).stream().sorted().collect(Collectors.toList());
    log.info("doctores {}", doctores);
    return doctores;
  }

  @Override
  public List<Doctor> findByDuracionCitaDoctor(Integer duracionCitaDoctor) {
    log.info("********************************");
    log.info("********************************");
    log.info("IDoctorRepository - findByDuracionCitaDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("duracionCitaDoctor {}", duracionCitaDoctor);
    List<Doctor> doctores = iDoctorRepository.findByDuracionCitaDoctor(
        duracionCitaDoctor).stream().sorted().collect(Collectors.toList());
    log.info("doctores {}", doctores);
    return doctores;
  }

  @Override
  public Optional<Doctor> findByIdUsuario(String idUsuario) {
    log.info("********************************");
    log.info("********************************");
    log.info("IDoctorRepository - findByIdUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("idUsuario {}", idUsuario);
    Optional<Doctor> doctorOpt = iDoctorRepository.findByIdUsuario(idUsuario).stream().findFirst();
    log.info("doctorOpt {}", doctorOpt);
    return doctorOpt;
  }

  @Override
  public Optional<Doctor> executeSaveDoctor(Doctor doctor) {
    log.info("********************************");
    log.info("********************************");
    log.info("IDoctorRepository - executeSaveDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("doctor {}", doctor);
    doctor.setIdDoctor(UUID.randomUUID().toString());
    doctor.setFecCreacionDoctor(LocalDateTime.now());
    doctor.setActivoDoctor(true);
    log.info("doctor {}", doctor);

    Integer responseEntity = iDoctorRepository.executeSaveDoctor(
        doctor.getIdDoctor(),
        doctor.getIdUsuario(),
        doctor.getTipDocumDoctor(),
        doctor.getCodDocumDoctor(),
        doctor.getNombreDoctor(),
        doctor.getApePaternoDoctor(),
        doctor.getApeMaternoDoctor(),
        doctor.getSexoDoctor(),
        doctor.getEmailDoctor(),
        doctor.getTelefonoDoctor(),
        doctor.getEspecialidadDoctor(),
        doctor.getHoraInicioDoctor(),
        doctor.getHoraFinDoctor(),
        doctor.getDuracionCitaDoctor(),
        doctor.getActivoDoctor(),
        doctor.getFecCreacionDoctor());
    log.info("responseEntity {}", responseEntity);
    return responseEntity == 1 ? Optional.ofNullable(doctor) : null;
  }

  @Override
  public Optional<Doctor> executeUpdateDoctor(Doctor doctor) {
    log.info("********************************");
    log.info("********************************");
    log.info("IDoctorRepository - executeUpdateDoctor");
    log.info("********************************");
    log.info("********************************");
    log.info("doctor {}", doctor);
    doctor.setFecActualizaDoctor(LocalDateTime.now());
    log.info("doctor {}", doctor);

    Integer responseEntity = iDoctorRepository.executeUpdateDoctor(
        doctor.getCodDocumDoctor(),
        doctor.getNombreDoctor(),
        doctor.getApePaternoDoctor(),
        doctor.getApeMaternoDoctor(),
        doctor.getSexoDoctor(),
        doctor.getEmailDoctor(),
        doctor.getTelefonoDoctor(),
        doctor.getEspecialidadDoctor(),
        doctor.getHoraInicioDoctor(),
        doctor.getHoraFinDoctor(),
        doctor.getDuracionCitaDoctor(),
        doctor.getActivoDoctor(),
        doctor.getFecActualizaDoctor(),
        doctor.getIdDoctor());
    log.info("responseEntity {}", responseEntity);
    return responseEntity == 1 ? Optional.ofNullable(doctor) : null;
  }

  @Override
  public Optional<Doctor> deleteById(String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("IDoctorRepository - deleteById");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    iDoctorRepository.deleteById(id);
    Optional<Doctor> doctorOpt = iDoctorRepository.findById(id).stream().findFirst();
    log.info("doctorOpt {}", doctorOpt);
    return doctorOpt;
  }

  @Override
  @Transactional(readOnly = true)
  public List<DoctorDTO> findAllDoctorDTO() {
    log.info("********************************");
    log.info("********************************");
    log.info("IDoctorRepository - findAllDoctorDTO");
    log.info("********************************");
    log.info("********************************");

    List<Doctor> doctores = iDoctorRepository.findAll();

    log.info("doctores {}", doctores);

    List<DoctorDTO> doctoresDTO = doctores.stream()
        .map(doc -> {
          Optional<UsuarioDTO> usuDTO = iUsuarioService.findAllUsuarioDTO(doc.getIdUsuario()).stream().findFirst();
          log.info("usuDTO {}", usuDTO);
          return DoctorDTO.builder()
              .doctor(doc)
              .usuarioDTO(usuDTO.orElse(null))
              .build();
        })
        .toList();

    log.info("doctoresDTO {}", doctoresDTO);

    return doctoresDTO;
  }

}
