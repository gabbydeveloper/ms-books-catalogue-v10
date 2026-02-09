package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class ParametroDTO {
    @Schema(description = "ID del parametro.", nullable = true)
    private Long idParametro;
    @Schema(description = "Url base del libro.", example = "EJEMPLO: /archivos-relatos-papel/fotos-libros")
    private String urlBaseLibro;
}
