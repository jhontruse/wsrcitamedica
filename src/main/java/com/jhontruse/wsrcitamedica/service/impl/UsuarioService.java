package com.jhontruse.wsrcitamedica.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhontruse.wsrcitamedica.model.entity.Usuario;
import com.jhontruse.wsrcitamedica.repository.IUsuarioRepository;
import com.jhontruse.wsrcitamedica.service.IUsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

  @Autowired
  private IUsuarioRepository iUsuarioRepository;

  @Override
  public List<Usuario> findAll() {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioService - findAll");
    log.info("********************************");
    log.info("********************************");
    List<Usuario> usuarios = iUsuarioRepository.findAll().stream().toList();
    log.info("usuarios {}", usuarios);
    return usuarios;
  }

  @Override
  public Optional<Usuario> findById(String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioService - findById");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    Optional<Usuario> usuarioOpt = iUsuarioRepository.findById(id).stream().findFirst();
    log.info("usuarioOpt {}", usuarioOpt);
    return usuarioOpt;
  }

  @Override
  public Optional<Usuario> findByUsuario(String usuario) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioService - findByUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("usuario {}", usuario);
    Optional<Usuario> usuarioOpt = iUsuarioRepository.findByUsuario(usuario).stream().findFirst();
    log.info("usuarioOpt {}", usuarioOpt);
    return usuarioOpt;
  }

  @Override
  public List<Usuario> findByActivoUsuario(Boolean activoUsuario) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioService - findByActivoUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("activoUsuario {}", activoUsuario);
    List<Usuario> usuarioOpt = iUsuarioRepository.findByActivoUsuario(activoUsuario).stream().toList();
    log.info("usuarioOpt {}", usuarioOpt);
    return usuarioOpt;
  }

  @Override
  public List<Usuario> findByLockedUsuario(Boolean lockedUsuario) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioService - findByLockedUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("lockedUsuario {}", lockedUsuario);
    List<Usuario> usuarioOpt = iUsuarioRepository.findByLockedUsuario(lockedUsuario).stream().toList();
    log.info("usuarioOpt {}", usuarioOpt);
    return usuarioOpt;
  }

  @Override
  public List<Usuario> findByIntentoFallidoLoginUsuario(Integer intentoFallidoLoginUsuario) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioService - findByIntentoFallidoLoginUsuario");
    log.info("********************************");
    log.info("********************************");
    log.info("intentoFallidoLoginUsuario {}", intentoFallidoLoginUsuario);
    List<Usuario> usuarioOpt = iUsuarioRepository.findByIntentoFallidoLoginUsuario(intentoFallidoLoginUsuario).stream()
        .toList();
    log.info("usuarioOpt {}", usuarioOpt);
    return usuarioOpt;
  }

  @Override
  public Usuario saveUsuario(Usuario usuario) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioService - saveUsuario");
    log.info("********************************");
    log.info("********************************");
    usuario.setIdUsuario(UUID.randomUUID().toString());
    usuario.setFecCreacionUsuario(LocalDateTime.now());
    usuario.setLockedUsuario(false);
    usuario.setFecCuentaExpiradaUsuario(LocalDateTime.now().plusYears(1));
    usuario.setFecPasswordExpiradaUsuario(LocalDateTime.now().plusYears(1));
    usuario.setIntentoFallidoLoginUsuario(0);
    log.info("usuario {}", usuario);

    Integer responseRepository = iUsuarioRepository.saveUsuario(
        usuario.getIdUsuario().toString(),
        usuario.getUsuario(),
        usuario.getPassword(),
        usuario.getActivoUsuario(),
        usuario.getFecCreacionUsuario(),
        usuario.getLockedUsuario(),
        usuario.getFecCuentaExpiradaUsuario(),
        usuario.getFecPasswordExpiradaUsuario(),
        usuario.getIntentoFallidoLoginUsuario());
    log.info("responseRepository {}", responseRepository);
    return responseRepository == 1 ? usuario : null;
  }

  @Override
  public Optional<Usuario> updateUsuario(Usuario usuario) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioService - updateUsuario");
    log.info("********************************");
    log.info("********************************");
    usuario.setFecActualizaUsuario(LocalDateTime.now());
    log.info("usuario {}", usuario);
    int ResponseRepository = iUsuarioRepository.updateUsuario(
        usuario.getUsuario(),
        usuario.getActivoUsuario(),
        usuario.getLockedUsuario(),
        usuario.getFecActualizaUsuario(),
        usuario.getIdUsuario());
    log.info("ResponseRepository {}", ResponseRepository);
    return ResponseRepository == 1 ? Optional.ofNullable(usuario) : null;
  }

  @Override
  public Optional<Usuario> deleteById(String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioService - deleteById");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    iUsuarioRepository.deleteById(id);
    Optional<Usuario> usuario = iUsuarioRepository.findById(id).stream().findFirst();
    return usuario;
  }

}
