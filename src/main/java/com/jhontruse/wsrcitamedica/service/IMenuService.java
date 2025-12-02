package com.jhontruse.wsrcitamedica.service;

import java.util.List;
import java.util.Optional;

import com.jhontruse.wsrcitamedica.model.entity.Menu;

public interface IMenuService {

  List<Menu> findAll();

  Optional<Menu> findById(String id);

  Menu saveMenu(Menu menu);

  Optional<Menu> findByNombreMenu(String nombreMenu);

  List<Menu> findByActivoMenu(Boolean activoMenu);

  List<Menu> findByVisibleMenu(Boolean visibleMenu);

  Optional<Menu> updateMenu(Menu menu);

  Optional<Menu> deleteById(String id);

}
