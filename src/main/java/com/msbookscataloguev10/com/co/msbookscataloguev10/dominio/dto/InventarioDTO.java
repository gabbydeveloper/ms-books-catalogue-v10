package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class InventarioDTO {
  @Schema(description = "ID del inventario, no es necesario colocar.", nullable = true)
  private Long idInventario;
  @Schema(description = "ID del libro.", example = "1")
  private Long idLibro;
  @Schema(description = "Nombre del tipo de movimiento.", example = "ENTRADA/SALIDA")
  private String tipoMovimiento;
  @Schema(description = "Fecha del inventario.", example = "2026-02-05")
  private String fechaInventario;
  @Schema(description = "Cantidad del movimiento.", example = "10")
  private Integer cantidadInventario;
}
