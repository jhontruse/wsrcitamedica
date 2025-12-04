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

import com.jhontruse.wsrcitamedica.model.entity.Rol;

@Repository
// JWT - AUTH
public interface IRolRepository extends ListCrudRepository<Rol, String>,
                PagingAndSortingRepository<Rol, String> {

        List<Rol> findAll();

        Optional<Rol> findById(String id);

        Optional<Rol> findByNombreRol(String nombreRol);

        List<Rol> findByActivoRol(Boolean activoRol);

        @Modifying
        @Query(value = "INSERT INTO DB_CITA_MEDICA.ROL (ID_ROL, NOMBRE_ROL, DESCRIPCION_ROL, ACTIVO_ROL, FEC_CREACION_ROL) VALUES (:idRol, :nombreRol, :descripcionRol, :activoRol, :fecCreacionRol)", name = "executeSaveRol")
        int executeSaveRol(
                        @Param("idRol") String idRol,
                        @Param("nombreRol") String nombreRol,
                        @Param("descripcionRol") String descripcionRol,
                        @Param("activoRol") Boolean activoRol,
                        @Param("fecCreacionRol") LocalDateTime fecCreacionRol);

        @Modifying
        @Query(value = "UPDATE ROL SET NOMBRE_ROL=:nombreRol , DESCRIPCION_ROL=:descripcionRol, ACTIVO_ROL=:activoRol , FEC_ACTUALIZA_ROL = :fecActualizaRol WHERE ID_ROL= :idRol", name = "executeUpdateRol")
        int executeUpdateRol(
                        @Param("nombreRol") String nombreRol,
                        @Param("descripcionRol") String descripcionRol,
                        @Param("activoRol") Boolean activoRol,
                        @Param("fecActualizaRol") LocalDateTime fecActualizaRol,
                        @Param("idRol") String idRol);

        void deleteById(String id);

        @Query("SELECT R.* FROM ROL R INNER JOIN USUARIO_ROL UR ON UR.ID_ROL = R.ID_ROL INNER JOIN USUARIO U ON U.ID_USUARIO = UR.ID_USUARIO WHERE U.USUARIO = :usuario ")
        List<Rol> executeUsuarioRolSearch(
                        @Param("usuario") String usuario);

}
