package com.jhontruse.wsrcitamedica.exception;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jhontruse.wsrcitamedica.model.dto.ApiError;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

//PASO 11 -JWT
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalAuthExceptionHandler {

  private final HttpServletRequest request;

  private static final Logger log = LoggerFactory.getLogger(GlobalAuthExceptionHandler.class);

  private String cid() {
    return request.getHeader("X-Correlation-Id");
  }

  // ==== Autenticación (login) ====

  // ==== Autorización (también puede saltar aquí si ocurre dentro del controller)

  // ==== JWT (filtros/servicios que validan token) ====

  // ==== Validación de request ====

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError validation(MethodArgumentNotValidException ex) {
    log.error("********************************");
    log.error("********************************");
    log.error("GlobalAuthExceptionHandler - validation");
    log.error("********************************");
    log.error("********************************");
    log.error("ex {}", ex);
    var errors = ex.getBindingResult().getFieldErrors().stream()
        .map(f -> f.getField() + ": " + f.getDefaultMessage())
        .collect(Collectors.toList());
    log.error("errors {}", errors);
    return ApiErrorFactory.of(400, "BAD_REQUEST",
        "Solicitud inválida. Revisa los campos.",
        ex.getClass().getSimpleName(), request, cid(), errors);
  }

  // ==== Fallback auth ====

}
