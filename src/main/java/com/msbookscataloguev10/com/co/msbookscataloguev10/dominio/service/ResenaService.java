package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.ResenaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResenaService {

    List<ResenaDTO> listarResenas(Long idLibro);
    void crearResena(ResenaDTO resenaDTO);
    ResenaDTO consultarResenaporId(Long idResena);
    void actualizarResena(Long idResena,ResenaDTO resenaDTO);
    void eliminarResena(Long idResena);
    Double consultarCalificacion(Long idLibro);
}

