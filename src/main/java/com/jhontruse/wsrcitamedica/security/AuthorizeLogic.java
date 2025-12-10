package com.jhontruse.wsrcitamedica.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

// JWT - AUTH
@Component
public class AuthorizeLogic {

        private static final Logger log = LoggerFactory.getLogger(AuthorizeLogic.class);

        // Reglas de autorización por “clave de método/acción”.
        // Usa nombres cortos y consistentes; evita hardcodear en switches.
        private static final Map<String, Set<String>> REQUIRED_ROLES = Map.ofEntries(
                        // Usuarios: administración completa
                        Map.entry("findByNombreMenuMenu",
                                        roles("ADMIN", "PROFESOR")),
                        Map.entry("findPersonaByUsername",
                                        roles("ADMIN",
                                                        "PROFESOR")),
                        Map.entry("findUsuarioByUsername",
                                        roles("ADMIN",
                                                        "PROFESOR"))

        // Si no se encuentra la clave, abajo hay un default (ROOT)
        );

        public boolean hasAccess(String key) {
                log.info("********************************");
                log.info("********************************");
                log.info("AuthorizeLogic - hasAccess");
                log.info("********************************");
                log.info("********************************");
                log.info("key: {}", key);
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                log.info("auth: {}", auth);
                return hasAccess(key, auth);
        }

        /**
         * Sobrecarga testeable y reutilizable.
         */
        public boolean hasAccess(String key, Authentication auth) {

                log.info("********************************");
                log.info("********************************");
                log.info("AuthorizeLogic - hasAccess");
                log.info("********************************");
                log.info("********************************");
                log.info("key: {}", key);
                log.info("auth: {}", auth);

                if (!isAuthenticated(auth))
                        return false;

                // Roles requeridos para la acción; default: ROOT
                Set<String> required = REQUIRED_ROLES.getOrDefault(key, roles("ROOT"));

                log.info("required: {}", required);

                // Normaliza y junta los roles del usuario
                Set<String> userRoles = auth.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .filter(Objects::nonNull)
                                .map(AuthorizeLogic::normalizeRole)
                                .collect(Collectors.toUnmodifiableSet());

                log.info("userRoles: {}", userRoles);

                // ¿Hay intersección entre los roles del usuario y los requeridos?
                return !Collections.disjoint(userRoles, required);
        }

        // ---------- Helpers ----------

        private static Set<String> roles(String... roles) {
                log.info("********************************");
                log.info("********************************");
                log.info("AuthorizeLogic - roles");
                log.info("********************************");
                log.info("********************************");
                return Arrays.stream(roles)
                                .map(AuthorizeLogic::normalizeRole)
                                .collect(Collectors.toUnmodifiableSet());
        }

        private static String normalizeRole(String role) {
                String r = role.trim().toUpperCase(Locale.ROOT);
                return r.startsWith("ROLE_") ? r : "ROLE_" + r;
        }

        private static boolean isAuthenticated(Authentication auth) {
                return auth != null
                                && auth.isAuthenticated()
                                && !(auth instanceof AnonymousAuthenticationToken);
        }

}
