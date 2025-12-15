package com.jhontruse.wsrcitamedica.model.dto;

import com.jhontruse.wsrcitamedica.model.entity.Doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

  private Doctor doctor;

  private UsuarioDTO usuarioDTO;

}
