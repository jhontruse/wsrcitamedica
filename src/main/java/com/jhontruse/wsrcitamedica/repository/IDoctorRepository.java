package com.jhontruse.wsrcitamedica.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jhontruse.wsrcitamedica.model.entity.Doctor;

@Repository
public interface IDoctorRepository extends ListCrudRepository<Doctor, String>,
    PagingAndSortingRepository<Doctor, String> {

  List<Doctor> findAll();

  Optional<Doctor> findById(String id);

  List<Doctor> findByNombreDoctor(String nombreDoctor);

  List<Doctor> findByEspecialidadDoctor(String especialidadDoctor);

  List<Doctor> findByActivoDoctor(Boolean activoDoctor);

  List<Doctor> findByDuracionCitaDoctor(Integer duracionCitaDoctor);

  Optional<Doctor> findByIdUsuario(String idUsuario);

  @Modifying
  @Query(value = """
      INSERT INTO DB_CITA_MEDICA.DOCTOR (
        ID_DOCTOR,
        ID_USUARIO,
        TIP_DOCUM_DOCTOR,
        COD_DOCUM_DOCTOR,
        NOMBRE_DOCTOR,
        APE_PATERNO_DOCTOR,
        APE_MATERNO_DOCTOR,
        SEXO_DOCTOR,
        EMAIL_DOCTOR,
        TELEFONO_DOCTOR,
        ESPECIALIDAD_DOCTOR,
        HORA_INICIO_DOCTOR,
        HORA_FIN_DOCTOR,
        DURACION_CITA_DOCTOR,
        ACTIVO_DOCTOR,
        FEC_CREACION_DOCTOR)
      VALUES (
        :idDoctor,
        :idUsuario,
        :tipDocumDoctor,
        :codDocumDoctor,
        :nombreDoctor,
        :apePaternoDoctor,
        :apeMaternoDoctor,
        :sexoDoctor,
        :emailDoctor,
        :telefonoDoctor,
        :especialidadDoctor,
        :horaInicioDoctor,
        :horaFinDoctor,
        :duracionCitaDoctor,
        :activoDoctor,
        :fecCreacionDoctor)
            """, name = "executeSaveDoctor")
  int executeSaveDoctor(
      @Param("idDoctor") String idDoctor,
      @Param("idUsuario") String idUsuario,
      @Param("tipDocumDoctor") String tipDocumDoctor,
      @Param("codDocumDoctor") String codDocumDoctor,
      @Param("nombreDoctor") String nombreDoctor,
      @Param("apePaternoDoctor") String apePaternoDoctor,
      @Param("apeMaternoDoctor") String apeMaternoDoctor,
      @Param("sexoDoctor") String sexoDoctor,
      @Param("emailDoctor") String emailDoctor,
      @Param("telefonoDoctor") String telefonoDoctor,
      @Param("especialidadDoctor") String especialidadDoctor,
      @Param("horaInicioDoctor") LocalTime horaInicioDoctor,
      @Param("horaFinDoctor") LocalTime horaFinDoctor,
      @Param("duracionCitaDoctor") Integer duracionCitaDoctor,
      @Param("activoDoctor") Boolean activoDoctor,
      @Param("fecCreacionDoctor") LocalDateTime fecCreacionDoctor);

  @Modifying
  @Query(value = """
      UPDATE DOCTOR SET
        COD_DOCUM_DOCTOR= :codDocumDoctor,
        NOMBRE_DOCTOR = :nombreDoctor,
        APE_PATERNO_DOCTOR = :apePaternoDoctor,
        APE_MATERNO_DOCTOR = :apeMaternoDoctor,
        SEXO_DOCTOR = :sexoDoctor,
        EMAIL_DOCTOR = :emailDoctor,
        TELEFONO_DOCTOR = :telefonoDoctor,
        ESPECIALIDAD_DOCTOR = :especialidadDoctor,
        HORA_INICIO_DOCTOR = :horaInicioDoctor,
        HORA_FIN_DOCTOR = :horaFinDoctor,
        DURACION_CITA_DOCTOR = :duracionCitaDoctor,
        ACTIVO_DOCTOR = :activoDoctor,
        FEC_ACTUALIZA_DOCTOR = :fecActualizaDoctor
        WHERE ID_DOCTOR = :idDoctor
      """, name = "executeUpdateDoctor")
  int executeUpdateDoctor(
      @Param("codDocumDoctor") String codDocumDoctor,
      @Param("nombreDoctor") String nombreDoctor,
      @Param("apePaternoDoctor") String apePaternoDoctor,
      @Param("apeMaternoDoctor") String apeMaternoDoctor,
      @Param("sexoDoctor") String sexoDoctor,
      @Param("emailDoctor") String emailDoctor,
      @Param("telefonoDoctor") String telefonoDoctor,
      @Param("especialidadDoctor") String especialidadDoctor,
      @Param("horaInicioDoctor") LocalTime horaInicioDoctor,
      @Param("horaFinDoctor") LocalTime horaFinDoctor,
      @Param("duracionCitaDoctor") Integer duracionCitaDoctor,
      @Param("activoDoctor") Boolean activoDoctor,
      @Param("fecActualizaDoctor") LocalDateTime fecActualizaDoctor,
      @Param("idDoctor") String idDoctor);

  void deleteById(String id);

}
