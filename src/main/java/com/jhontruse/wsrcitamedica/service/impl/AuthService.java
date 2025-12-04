package com.jhontruse.wsrcitamedica.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jhontruse.wsrcitamedica.model.entity.Menu;
import com.jhontruse.wsrcitamedica.repository.IMenuRepository;
import com.jhontruse.wsrcitamedica.security.JwtTokenUtil;
import com.jhontruse.wsrcitamedica.service.IAuthService;

import lombok.RequiredArgsConstructor;

// JWT - AUTH
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

  @Autowired
  private final IMenuRepository iMenuRepository;

  @Autowired
  private final JwtTokenUtil jwtTokenUtil;

  private static final Logger log = LoggerFactory.getLogger(AuthService.class);

  @Override
  public String login(String username, String password, UserDetails userDetails) {
    log.info("********************************");
    log.info("********************************");
    log.info("AuthService - login");
    log.info("********************************");
    log.info("********************************");
    log.info("username: {}", username);
    log.info("password: {}", password);
    log.info("userDetails: {}", userDetails);
    List<String> menus = iMenuRepository.executeUsuarioMenuSearch(username).stream()
        .map(Menu::getNombreMenu)
        .toList();
    log.info("menus: {}", menus);

    return jwtTokenUtil.generateToken(userDetails, menus);

  }

}
