package com.jhontruse.wsrcitamedica.service;

import org.springframework.security.core.userdetails.UserDetails;

// JWT - AUTH
public interface IAuthService {

  public String login(String username, String password, UserDetails userDetails);

}
