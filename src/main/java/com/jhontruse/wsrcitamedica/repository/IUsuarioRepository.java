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

import com.jhontruse.wsrcitamedica.model.entity.Usuario;

@Repository
public interface IUsuarioRepository extends ListCrudRepository<Usuario, String>,
    PagingAndSortingRepository<Usuario, String> {

  List<Usuario> findAll();

  Optional<Usuario> findById(String id);

  Optional<Usuario> findByUsuario(String usuario);

  List<Usuario> findByActivoUsuario(Boolean activoUsuario);

  List<Usuario> findByLockedUsuario(Boolean lockedUsuario);

  List<Usuario> findByIntentoFallidoLoginUsuario(Integer intentoFallidoLoginUsuario);

  @Modifying
  @Query(value = "INSERT INTO DB_CITA_MEDICA.USUARIO (ID_USUARIO, USUARIO, PASSWORD, ACTIVO_USUARIO, FEC_CREACION_USUARIO, LOCKED_USUARIO, FEC_CUENTA_EXPIRADA_USUARIO, FEC_PASSWORD_EXPIRADA_USUARIO, INTENTO_FALLIDO_LOGIN_USUARIO) "
      + " VALUES (:idUsuario, :usuario, :password, :activoUsuario, :fecCreacionUsuario, :lockedUsuario, :fecCuentaExpiradaUsuario, :fecPasswordExpiradaUsuario, :intentoFallidoLoginUsuario) ", name = "saveUsuario")
  int saveUsuario(
      @Param("idUsuario") String idUsuario,
      @Param("usuario") String usuario,
      @Param("password") String password,
      @Param("activoUsuario") Boolean activoUsuario,
      @Param("fecCreacionUsuario") LocalDateTime fecCreacionUsuario,
      @Param("lockedUsuario") Boolean lockedUsuario,
      @Param("fecCuentaExpiradaUsuario") LocalDateTime fecCuentaExpiradaUsuario,
      @Param("fecPasswordExpiradaUsuario") LocalDateTime fecPasswordExpiradaUsuario,
      @Param("intentoFallidoLoginUsuario") Integer intentoFallidoLoginUsuario);

  @Modifying
  @Query(value = "UPDATE USUARIO "
      + " SET USUARIO= :usuario, ACTIVO_USUARIO=:activoUsuario, LOCKED_USUARIO=:lockedUsuario, FEC_ACTUALIZA_USUARIO= :fecActualizaUsuario  "
      + " WHERE ID_USUARIO = :idUsuario ", name = "updateUsuario")
  int updateUsuario(
      @Param("usuario") String usuario,
      @Param("activoUsuario") Boolean activoUsuario,
      @Param("lockedUsuario") Boolean lockedUsuario,
      @Param("fecActualizaUsuario") LocalDateTime fecActualizaUsuario,
      @Param("idUsuario") String idUsuario);

  void deleteById(String id);

}
