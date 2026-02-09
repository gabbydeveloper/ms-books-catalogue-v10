package com.msbookscataloguev10.com.co.msbookscataloguev10.web.controller;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.ResenaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service.ResenaService;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Resena;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @Operation(
            summary = "Obtener las reseñas de un libro",
            description = "Devuelve todas las reseñas ingresadas por los usuarios en un libro"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reseñas obtenidas correctamente"),
            @ApiResponse(responseCode = "404", description = "Reseñas no encontradas")
    })
    @GetMapping("/libro/{idLibro}")
    public ResponseEntity<List<ResenaDTO>> listarResenas(@PathVariable Long idLibro) {
        List<ResenaDTO> resenas = resenaService.listarResenas(idLibro);
        if (resenas.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resenas, HttpStatus.OK);
    }

    @GetMapping("/calificacion/{idLibro}")
    public Double consultarCalificacion(@PathVariable Long idLibro) {

        return resenaService.consultarCalificacion(idLibro);
    }

    @Operation(
            summary = "Crea una reseña",
            description = "Crea la reseña con el cuerpo JSON enviado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reseña creada"),
            @ApiResponse(responseCode = "400", description = "Reseña con calificación errada, solo recibe del 1 al 5")
    })

    @PostMapping
    public ResponseEntity<ResenaDTO> crearResena(@RequestBody ResenaDTO resenaDTO) {
        try {
            resenaService.crearResena(resenaDTO);
            return ResponseEntity.ok(resenaDTO);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Consultar reseña por Id",
            description = "Consulta la reseña por el Id de la reseña"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reseña obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron reseñas con el id ingresado")
    })

    @GetMapping("/{idResena}")
    public ResponseEntity<ResenaDTO> consultarResenaporId(@PathVariable Long idResena) {
        try {
            ResenaDTO resenaDTO = resenaService.consultarResenaporId(idResena);
            return ResponseEntity.ok(resenaDTO);
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Actualizar reseña",
            description = "Actualizar reseña con Id y cuerpo de JSON enviado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reseña actualizada correctamente"),
    })
    @PutMapping("/{idResena}")
    public ResponseEntity<Void> actualizarResena(@PathVariable Long idResena, @RequestBody ResenaDTO resenaDTO) {
        try {
            resenaService.actualizarResena(idResena, resenaDTO);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Eliminar reseña",
            description = "Elimina la reseña por el Id de la reseña"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reseña eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "La reseña no existe")
    })
    @DeleteMapping("/{idResena}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Long idResena) {
        try {
            resenaService.eliminarResena(idResena);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
