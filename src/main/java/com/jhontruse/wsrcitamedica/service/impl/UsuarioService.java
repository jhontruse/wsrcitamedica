package com.jhontruse.wsrcitamedica.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhontruse.wsrcitamedica.model.dto.UsuarioDTO;
import com.jhontruse.wsrcitamedica.model.entity.Menu;
import com.jhontruse.wsrcitamedica.model.entity.Rol;
import com.jhontruse.wsrcitamedica.model.entity.Usuario;
import com.jhontruse.wsrcitamedica.repository.IMenuRepository;
import com.jhontruse.wsrcitamedica.repository.IRolRepository;
import com.jhontruse.wsrcitamedica.repository.IUsuarioRepository;
import com.jhontruse.wsrcitamedica.service.IUsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
// JWT - AUTH
public class UsuarioService implements IUsuarioService {

  @Autowired
  private IUsuarioRepository iUsuarioRepository;

  @Autowired
  private IMenuRepository iMenuRepository;

  @Autowired
  private IRolRepository iRolRepository;

  @Autowired
  private final PasswordEncoder bcrypt;

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
    usuario.setPassword(bcrypt.encode(usuario.getPassword()));
    log.info("usuario {}", usuario);

    Integer responseRepository = iUsuarioRepository.executeSaveUsuario(
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
    int ResponseRepository = iUsuarioRepository.executeUpdateUsuario(
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
    log.info("usuario {}", usuario);
    return usuario;
  }

  @Override
  @Transactional(readOnly = true)
  public List<UsuarioDTO> findAllUsuarioDTO() {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioService - findAllUsuarioDTO");
    log.info("********************************");
    log.info("********************************");

    List<Usuario> usuarios = iUsuarioRepository.findAll();

    log.info("usuarios {}", usuarios);

    return usuarios.stream()
        .map(usuario -> {
          usuario.setPassword(null);
          log.info("usuario {}", usuario);
          List<Rol> rol = iRolRepository.executeUsuarioRolSearch(usuario.getUsuario());
          log.info("rol {}", rol);
          List<Menu> menus = iMenuRepository.executeUsuarioMenuSearch(usuario.getUsuario());
          log.info("menus {}", menus);
          // Construir DTO
          return UsuarioDTO.builder()
              .usuario(usuario)
              .rol(rol)
              .menu(menus)
              .build();
        })
        .sorted(Comparator.comparing(dto -> dto.getUsuario().getUsuario()))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<UsuarioDTO> findAllUsuarioDTO(String idUsuario) {
    log.info("********************************");
    log.info("********************************");
    log.info("UsuarioService - findAllUsuarioDTO");
    log.info("********************************");
    log.info("********************************");
    log.info("idUsuario {}", idUsuario);

    Optional<Usuario> usuarios = iUsuarioRepository.findById(idUsuario);

    log.info("usuarios {}", usuarios);

    return usuarios
        .map(usuario -> {
          usuario.setPassword(null);
          log.info("usuario {}", usuario);
          List<Rol> rol = iRolRepository.executeUsuarioRolSearch(usuario.getUsuario());
          log.info("rol {}", rol);
          List<Menu> menus = iMenuRepository.executeUsuarioMenuSearch(usuario.getUsuario());
          log.info("menus {}", menus);
          // Construir DTO
          return UsuarioDTO.builder()
              .usuario(usuario)
              .rol(rol)
              .menu(menus)
              .build();
        });
  }

}
