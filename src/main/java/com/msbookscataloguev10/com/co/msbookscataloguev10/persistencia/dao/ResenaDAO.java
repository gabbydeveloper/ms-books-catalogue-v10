package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.dao;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.ResenaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Libro;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Resena;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResenaDAO {

    @Autowired
    private LibroRepository libroRepository;

    //Este metodo es para guardar los datos
    public Resena resena(ResenaDTO resenaDTO) {
        Resena resena = new Resena();
        resena.setIdResena(resenaDTO.getIdResena());
        resena.setTextoResena(resenaDTO.getTextoResena());
        resena.setEstadoResena("ACTIVO");
        resena.setCalificacionLibro(resenaDTO.getCalificacionLibro());
        resena.setIdUsuario(resenaDTO.getIdUsuario());
        Libro libro= libroRepository.findByIdLibro(resenaDTO.getIdLibro()).orElseThrow(()->new RuntimeException("Libro no encontrado"));
        resena.setLibro(libro);
        return resena;
    }

    //Este metodo es para presentar los datos
    public ResenaDTO resenaDTO(Resena resena){
        ResenaDTO resenaDTO=new ResenaDTO();
        resenaDTO.setIdResena(resena.getIdResena());
        resenaDTO.setIdLibro(resena.getLibro().getIdLibro());
        resenaDTO.setTextoResena(resena.getTextoResena());
        resenaDTO.setEstadoResena(resena.getEstadoResena());
        resenaDTO.setCalificacionLibro(resena.getCalificacionLibro());
        resenaDTO.setIdUsuario(resena.getIdUsuario());
        return resenaDTO;
    }
}