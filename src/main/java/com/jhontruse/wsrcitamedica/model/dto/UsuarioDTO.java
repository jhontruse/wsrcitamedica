package com.jhontruse.wsrcitamedica.model.dto;

import java.util.List;

import com.jhontruse.wsrcitamedica.model.entity.Menu;
import com.jhontruse.wsrcitamedica.model.entity.Rol;
import com.jhontruse.wsrcitamedica.model.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

  private Usuario usuario;

  private List<Rol> rol;

  private List<Menu> menu;

}
