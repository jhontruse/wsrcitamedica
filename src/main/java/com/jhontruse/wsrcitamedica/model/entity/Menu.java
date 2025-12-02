package com.jhontruse.wsrcitamedica.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un menú en el sistema")
@Table("MENU")
public class Menu {

  @Schema(description = "ID único de menu", example = "A87S6DA8D76AS8D68DA23")
  @Id
  @Column("ID_MENU")
  private String idMenu;

  @Schema(description = "Nombre del menú", example = "")
  @Column("NOMBRE_MENU")
  private String nombreMenu;

  @Schema(description = "Ruta del menú", example = "")
  @Column("RUTA_MENU")
  private String rutaMenu;

  @Schema(description = "Icono del menú", example = "")
  @Column("ICONO_MENU")
  private String iconoMenu;

  @Schema(description = "Orden del menú", example = "")
  @Column("ORDEN_MENU")
  private Integer ordenMenu;

  @Schema(description = "Padre del menú", example = "")
  @Column("PADRE_ID_MENU")
  private String padreIdMenu;

  @Schema(description = "Visible del menú", example = "")
  @Column("VISIBLE_MENU")
  private Boolean visibleMenu;

  @Schema(description = "Activo del menú", example = "")
  @Column("ACTIVO_MENU")
  private Boolean activoMenu;

  @Schema(description = "Fecha de creación del menú", example = "")
  @Column("FEC_CREACION_MENU")
  private LocalDateTime fecCreacionMenu;

  @Schema(description = "Fecha de actualizacion del menú", example = "")
  @Column("FEC_ACTUALIZA_MENU")
  private LocalDateTime fecActualizaMenu;

}
