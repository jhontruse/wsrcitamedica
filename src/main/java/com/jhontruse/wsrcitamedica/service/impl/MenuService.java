package com.jhontruse.wsrcitamedica.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhontruse.wsrcitamedica.model.entity.Menu;
import com.jhontruse.wsrcitamedica.repository.IMenuRepository;
import com.jhontruse.wsrcitamedica.service.IMenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService implements IMenuService {

  @Autowired
  private IMenuRepository iMenuRepository;

  @Override
  public List<Menu> findAll() {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuService - findAll");
    log.info("********************************");
    log.info("********************************");
    List<Menu> menus = iMenuRepository.findAll().stream().toList();
    log.info("menus {}", menus);
    return menus;
  }

  @Override
  public Optional<Menu> findById(String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuService - findById");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    Optional<Menu> menuOpt = iMenuRepository.findById(id).stream().findFirst();
    log.info("menuOpt {}", menuOpt);
    return menuOpt;
  }

  @Override
  public Menu saveMenu(Menu menu) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuService - saveMenu");
    log.info("********************************");
    log.info("********************************");
    menu.setIdMenu(UUID.randomUUID().toString());
    menu.setFecCreacionMenu(LocalDateTime.now());
    menu.setPadreIdMenu(
        menu.getPadreIdMenu() != null ? menu.getPadreIdMenu() : menu.getIdMenu().toString());
    log.info("menu {}", menu);
    Integer responseRepository = iMenuRepository.saveMenu(
        menu.getIdMenu().toString(),
        menu.getNombreMenu(),
        menu.getRutaMenu(),
        menu.getIconoMenu(),
        menu.getOrdenMenu(),
        menu.getPadreIdMenu(),
        menu.getVisibleMenu(),
        menu.getActivoMenu(),
        menu.getFecCreacionMenu());
    log.info("responseRepository {}", responseRepository);
    return responseRepository == 1 ? menu : null;
  }

  @Override
  public Optional<Menu> findByNombreMenu(String nombreMenu) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuService - findByNombreMenu");
    log.info("********************************");
    log.info("********************************");
    log.info("nombreMenu {}", nombreMenu);
    Optional<Menu> menuOpt = iMenuRepository.findByNombreMenu(nombreMenu.toString()).stream().findFirst();
    log.info("menuOpt {}", menuOpt);
    return menuOpt;
  }

  @Override
  public List<Menu> findByActivoMenu(Boolean activoMenu) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuService - findByActivoMenu");
    log.info("********************************");
    log.info("********************************");
    log.info("activoMenu {}", activoMenu);
    List<Menu> menuOpt = iMenuRepository.findByActivoMenu(activoMenu).stream().toList();
    log.info("menuOpt {}", menuOpt);
    return menuOpt;
  }

  @Override
  public List<Menu> findByVisibleMenu(Boolean visibleMenu) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuService - findByVisibleMenu");
    log.info("********************************");
    log.info("********************************");
    log.info("visibleMenu {}", visibleMenu);
    List<Menu> menuOpt = iMenuRepository.findByVisibleMenu(visibleMenu).stream().toList();
    log.info("menuOpt {}", menuOpt);
    return menuOpt;
  }

  @Override
  public Optional<Menu> updateMenu(Menu menu) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuService - updateMenu");
    log.info("********************************");
    log.info("********************************");
    log.info("menu {}", menu);
    int ResponseRepository = iMenuRepository.updateMenu(
        menu.getRutaMenu(),
        menu.getIconoMenu(),
        menu.getOrdenMenu(),
        menu.getPadreIdMenu(),
        menu.getVisibleMenu(),
        menu.getActivoMenu(),
        LocalDateTime.now(),
        menu.getIdMenu());
    log.info("ResponseRepository {}", ResponseRepository);

    return ResponseRepository == 1 ? Optional.ofNullable(menu) : null;
  }

  @Override
  public Optional<Menu> deleteById(String id) {
    log.info("********************************");
    log.info("********************************");
    log.info("MenuService - deleteById");
    log.info("********************************");
    log.info("********************************");
    log.info("id {}", id);
    iMenuRepository.deleteById(id);
    Optional<Menu> menu = iMenuRepository.findById(id).stream().findFirst();
    return menu;
  }

}
