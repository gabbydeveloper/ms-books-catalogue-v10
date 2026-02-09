package com.msbookscataloguev10.com.co.msbookscataloguev10.web.controller;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.ParametroDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.RespuestaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service.ParametroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParametroController {

    @Autowired
   private ParametroService parametroService;
    @GetMapping("/parametrosbyId/{idParametro}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public ResponseEntity<RespuestaDTO> consultarParametrobyId(@PathVariable Long idParametro){
        RespuestaDTO respuesta = parametroService.consultarParametroporId(idParametro);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(respuesta, httpStatus);
    }


    //MODIFICAR REGISTRO:
    @PutMapping("/parametros/{idParametro}")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public ResponseEntity<RespuestaDTO> actualizarParametro(@PathVariable Long idParametro, @RequestBody ParametroDTO parametroDTO){
        parametroDTO.setIdParametro(idParametro);
        RespuestaDTO respuesta = parametroService.actualizarParametro(parametroDTO);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(respuesta, httpStatus);
    }

}
