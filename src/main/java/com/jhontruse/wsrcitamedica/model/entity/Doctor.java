package com.jhontruse.wsrcitamedica.model.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un Doctor en el sistema")
@Table("DOCTOR")
public class Doctor {

  @Schema(description = "ID único de doctor", example = "")
  @Id
  @Column("ID_DOCTOR")
  private String idDoctor;

  @Schema(description = "ID único de usuario", example = "")
  @Column("ID_USUARIO")
  private String idUsuario;

  @Schema(description = "Tipo de documento del doctor", example = "")
  @Column("TIP_DOCUM_DOCTOR")
  @Size(max = 3, min = 3)
  @NotNull(message = "El campo es obligatorio.")
  private String tipDocumDoctor;

  @Schema(description = "Codigo de documento del doctor", example = "")
  @Column("COD_DOCUM_DOCTOR")
  @Size(max = 8, min = 8)
  @NotNull(message = "El campo es obligatorio.")
  private String codDocumDoctor;

  @Schema(description = "Nombre del doctor", example = "")
  @Column("NOMBRE_DOCTOR")
  @Size(max = 100, min = 3)
  @NotNull(message = "El campo es obligatorio.")
  private String nombreDoctor;

  @Schema(description = "Apellido paterno del doctor", example = "")
  @Column("APE_PATERNO_DOCTOR")
  @Size(max = 100, min = 3)
  @NotNull(message = "El campo es obligatorio.")
  private String apePaternoDoctor;

  @Schema(description = "Apellido materno del doctor", example = "")
  @Column("APE_MATERNO_DOCTOR")
  @Size(max = 100, min = 3)
  @NotNull(message = "El campo es obligatorio.")
  private String apeMaternoDoctor;

  @Schema(description = "Sexo del doctor", example = "")
  @Column("SEXO_DOCTOR")
  @Size(max = 1, min = 1)
  @NotNull(message = "El campo es obligatorio.")
  private String sexoDoctor;

  @Schema(description = "Email del doctor", example = "")
  @Column("EMAIL_DOCTOR")
  @Size(max = 100, min = 3)
  @NotNull(message = "El campo es obligatorio.")
  @Email
  private String emailDoctor;

  @Schema(description = "Telefono del doctor", example = "")
  @Column("TELEFONO_DOCTOR")
  @Size(max = 9, min = 9)
  @NotNull(message = "El campo es obligatorio.")
  private String telefonoDoctor;

  @Schema(description = "Especialidad del doctor", example = "")
  @Column("ESPECIALIDAD_DOCTOR")
  @Size(max = 100, min = 9)
  @NotNull(message = "El campo es obligatorio.")
  private String especialidadDoctor;

  @Schema(description = "Hora de inicio del doctor", example = "")
  @Column("HORA_INICIO_DOCTOR")
  @NotNull(message = "El campo es obligatorio.")
  private LocalTime horaInicioDoctor;

  @Schema(description = "Hora de fin del doctor", example = "")
  @Column("HORA_FIN_DOCTOR")
  @NotNull(message = "El campo es obligatorio.")
  private LocalTime horaFinDoctor;

  @Schema(description = "Duracion del doctor", example = "")
  @Column("DURACION_CITA_DOCTOR")
  @NotNull(message = "El campo es obligatorio.")
  private Integer duracionCitaDoctor;

  @Schema(description = "Activo del doctor", example = "")
  @Column("ACTIVO_DOCTOR")
  @NotNull(message = "El campo es obligatorio.")
  private Boolean activoDoctor;

  @Schema(description = "Fecha de creación del doctor", example = "")
  @Column("FEC_CREACION_DOCTOR")
  private LocalDateTime fecCreacionDoctor;

  @Schema(description = "Fecha de actualizacion del doctor", example = "")
  @Column("FEC_ACTUALIZA_DOCTOR")
  private LocalDateTime fecActualizaDoctor;

}
