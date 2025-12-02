package com.jhontruse.wsrcitamedica.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jhontruse.wsrcitamedica.model.entity.Menu;

@Repository
public interface IMenuRepository extends ListCrudRepository<Menu, String>,
    PagingAndSortingRepository<Menu, String> {

  List<Menu> findAll();

  Optional<Menu> findById(String id);

  Optional<Menu> findByNombreMenu(String nombreMenu);

  List<Menu> findByActivoMenu(Boolean activoMenu);

  List<Menu> findByVisibleMenu(Boolean visibleMenu);

  @Modifying
  @Query(value = "INSERT INTO DB_CITA_MEDICA.MENU (ID_MENU, NOMBRE_MENU, RUTA_MENU, ICONO_MENU, ORDEN_MENU, PADRE_ID_MENU, VISIBLE_MENU, ACTIVO_MENU, FEC_CREACION_MENU) VALUES (:idMenu, :nombreMenu, :rutaMenu, :iconoMenu, :ordenMenu, :padreIdMenu, :visibleMenu, :activoMenu, :fecCreacionMenu)", name = "saveMenu")
  int saveMenu(
      @Param("idMenu") String idMenu,
      @Param("nombreMenu") String nombreMenu,
      @Param("rutaMenu") String rutaMenu,
      @Param("iconoMenu") String iconoMenu,
      @Param("ordenMenu") Integer ordenMenu,
      @Param("padreIdMenu") String padreIdMenu,
      @Param("visibleMenu") Boolean visibleMenu,
      @Param("activoMenu") Boolean activoMenu,
      @Param("fecCreacionMenu") LocalDateTime fecCreacionMenu);

  @Modifying
  @Query(value = "UPDATE MENU SET RUTA_MENU = :rutaMenu, ICONO_MENU = :iconoMenu, ORDEN_MENU = :ordenMenu, PADRE_ID_MENU = :padreIdMenu, VISIBLE_MENU = :visibleMenu, ACTIVO_MENU = :activoMenu, FEC_ACTUALIZA_MENU = :fecActualizaMenu WHERE ID_MENU = :idMenu", name = "updateMenu")
  int updateMenu(
      @Param("rutaMenu") String rutaMenu,
      @Param("iconoMenu") String iconoMenu,
      @Param("ordenMenu") Integer ordenMenu,
      @Param("padreIdMenu") String padreIdMenu,
      @Param("visibleMenu") Boolean visibleMenu,
      @Param("activoMenu") Boolean activoMenu,
      @Param("fecActualizaMenu") LocalDateTime fecActualizaMenu,
      @Param("idMenu") String idMenu);

  void deleteById(String id);

}
