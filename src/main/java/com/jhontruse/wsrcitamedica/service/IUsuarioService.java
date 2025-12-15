package com.jhontruse.wsrcitamedica.service;

import java.util.List;
import java.util.Optional;

import com.jhontruse.wsrcitamedica.model.dto.UsuarioDTO;
import com.jhontruse.wsrcitamedica.model.entity.Usuario;

public interface IUsuarioService {

  List<Usuario> findAll();

  Optional<Usuario> findById(String id);

  Optional<Usuario> findByUsuario(String usuario);

  List<Usuario> findByActivoUsuario(Boolean activoUsuario);

  List<Usuario> findByLockedUsuario(Boolean lockedUsuario);

  List<Usuario> findByIntentoFallidoLoginUsuario(Integer intentoFallidoLoginUsuario);

  Usuario saveUsuario(Usuario usuario);

  Optional<Usuario> updateUsuario(Usuario usuario);

  Optional<Usuario> deleteById(String id);

  List<UsuarioDTO> findAllUsuarioDTO();

  Optional<UsuarioDTO> findAllUsuarioDTO(String idUsuario);

}
