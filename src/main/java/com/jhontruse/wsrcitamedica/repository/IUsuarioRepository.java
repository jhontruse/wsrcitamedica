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
import org.springframework.transaction.annotation.Transactional;

import com.jhontruse.wsrcitamedica.model.entity.Usuario;

@Repository
// JWT - AUTH
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
                        + " VALUES (:idUsuario, :usuario, :password, :activoUsuario, :fecCreacionUsuario, :lockedUsuario, :fecCuentaExpiradaUsuario, :fecPasswordExpiradaUsuario, :intentoFallidoLoginUsuario) ", name = "executeSaveUsuario")
        int executeSaveUsuario(
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
                        + " WHERE ID_USUARIO = :idUsuario ", name = "executeUpdateUsuario")
        int executeUpdateUsuario(
                        @Param("usuario") String usuario,
                        @Param("activoUsuario") Boolean activoUsuario,
                        @Param("lockedUsuario") Boolean lockedUsuario,
                        @Param("fecActualizaUsuario") LocalDateTime fecActualizaUsuario,
                        @Param("idUsuario") String idUsuario);

        void deleteById(String id);

        /**
         * (Opcional) Desbloqueo por expiración del bloqueo temporal.
         * Si el bloqueo ya excedió el umbral, desbloquea y limpia intentos.
         */
        @Modifying
        @Query(value = "UPDATE USUARIO SET LOCKED_USUARIO = 0, FEC_BLOQUEO_USUARIO = NULL, INTENTO_FALLIDO_LOGIN_USUARIO = 0, FEC_ACTUALIZA_USUARIO = CURRENT_TIMESTAMP WHERE USUARIO = :usuario AND LOCKED_USUARIO = 1 AND FEC_BLOQUEO_USUARIO IS NOT NULL AND TIMESTAMPADD (MINUTE, :minutesThreshold, FEC_BLOQUEO_USUARIO) <= :now", name = "executeUnloackUSuarioExpirate")
        int executeUnloackUSuarioExpirate(
                        @Param("usuario") String usuario,
                        @Param("minutesThreshold") long minutesThreshold,
                        @Param("now") LocalDateTime now);

        /**
         * Incrementa los intentos fallidos.
         * Úsalo en AuthenticationSuccessEvent.
         */
        @Transactional
        @Modifying
        @Query(value = "UPDATE USUARIO SET INTENTO_FALLIDO_LOGIN_USUARIO = INTENTO_FALLIDO_LOGIN_USUARIO + 1, FEC_ACTUALIZA_USUARIO = CURRENT_TIMESTAMP WHERE USUARIO = :usuario", name = "executeFailedAttempts")
        int executeFailedAttempts(@Param("usuario") String usuario);

        /**
         * Bloqueo de usuario por intentos fallidos.
         * Úsalo en AuthenticationSuccessEvent.
         */
        @Transactional
        @Modifying
        @Query(value = "UPDATE USUARIO SET LOCKED_USUARIO = 1, FEC_BLOQUEO_USUARIO = :lockedAt, FEC_ACTUALIZA_USUARIO = CURRENT_TIMESTAMP WHERE USUARIO = :usuario", name = "executeBlockUsuario")
        int executeBlockUsuario(
                        @Param("lockedAt") LocalDateTime lockedAt,
                        @Param("usuario") String usuario);

        /**
         * Resetea intentos fallidos y desbloquea si estaba bloqueado.
         * Úsalo en AuthenticationSuccessEvent.
         */
        @Transactional
        @Modifying
        @Query(value = "UPDATE USUARIO SET INTENTO_FALLIDO_LOGIN_USUARIO = 0, LOCKED_USUARIO = 0, FEC_BLOQUEO_USUARIO = NULL, FEC_ACTUALIZA_USUARIO = CURRENT_TIMESTAMP WHERE  USUARIO = :usuario", name = "executeResetAttemptAndUnlocked")
        int executeResetAttemptAndUnlocked(String usuario);

}
