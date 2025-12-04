package com.jhontruse.wsrcitamedica.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jhontruse.wsrcitamedica.model.entity.Rol;
import com.jhontruse.wsrcitamedica.model.entity.Usuario;
import com.jhontruse.wsrcitamedica.repository.IRolRepository;
import com.jhontruse.wsrcitamedica.repository.IUsuarioRepository;
import com.jhontruse.wsrcitamedica.security.JwtUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// JWT - AUTH
public class JwtUserDetailsService implements UserDetailsService {

  private static final long TEMP_LOCK_MINUTES = 30; // si usas bloqueo temporal

  @Autowired
  private final IUsuarioRepository iUsuarioRepository;

  @Autowired
  private final IRolRepository iRolRepository;

  private static final Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    log.info("********************************");
    log.info("********************************");
    log.info("JwtUserDetailsService - loadUserByUsername");
    log.info("********************************");
    log.info("********************************");
    log.info("username: {}", username);

    Usuario usuario = iUsuarioRepository.findByUsuario(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

    log.info("usuario: {}", usuario);

    if (usuario.getLockedUsuario() != null && usuario.getLockedUsuario() && usuario.getFecBloqueoUsuario() != null) {
      log.info("username: {}", username);
      log.info("TEMP_LOCK_MINUTES: {}", TEMP_LOCK_MINUTES);
      log.info("LocalDateTime.now(ZoneId.of(\"America/Lima\")): {}", LocalDateTime.now(ZoneId.of("America/Lima")));
      int desbloqueo = iUsuarioRepository.executeUnloackUSuarioExpirate(username, TEMP_LOCK_MINUTES,
          LocalDateTime.now(ZoneId.of("America/Lima")));
      log.info("desbloqueo: {}", desbloqueo);
    }

    List<Rol> roles = iRolRepository.executeUsuarioRolSearch(username);

    log.info("roles: {}", roles);

    return new JwtUserDetails(usuario, roles);

  }

}
