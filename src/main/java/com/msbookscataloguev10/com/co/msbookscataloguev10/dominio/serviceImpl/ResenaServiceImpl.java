package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.serviceImpl;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.ResenaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service.ResenaService;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.dao.ResenaDAO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Resena;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResenaServiceImpl implements ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private ResenaDAO resenaDAO;

    @Override
    public List<ResenaDTO> listarResenas(Long idLibro) {
        List<Resena> resenas = resenaRepository.findByLibroIdLibro(idLibro);
        return resenas.stream().map(resenaDAO::resenaDTO).toList();
    }

    @Override
    public void crearResena(ResenaDTO resenaDTO) {
        if (resenaDTO.getCalificacionLibro() < 1 || resenaDTO.getCalificacionLibro() > 5) {
            throw new IllegalStateException("La calificación debe estar entre 1 y 5");
        }
        resenaRepository.save(resenaDAO.resena(resenaDTO));
    }

    @Override
    public ResenaDTO consultarResenaporId(Long idResena) {
        Optional<Resena> resenaId = resenaRepository.findById(idResena);
        if (resenaId.isEmpty()) {
            throw new IllegalStateException("La reseña no existe");
        }
        return resenaDAO.resenaDTO(resenaId.get());
    }

    @Override
    public void actualizarResena(Long idResena,ResenaDTO resenaDTO) {
        Optional<Resena> resenaOptional =
                resenaRepository.findById(idResena);
        if (resenaOptional.isEmpty()) {
            throw new IllegalStateException("La reseña no existe");
        }
        Resena resena = resenaOptional.get();

        // Actualizar campos
        resena.setCalificacionLibro(resenaDTO.getCalificacionLibro());
        resena.setTextoResena(resenaDTO.getTextoResena());

        // Guardar (JPA hace UPDATE)
        resenaRepository.save(resena);


    }

    @Override
    public void eliminarResena(Long idResena) {
        Optional<Resena> resenaId = resenaRepository.findById(idResena);
        if (resenaId.isPresent()) {
            resenaRepository.delete(resenaId.get());
        } else {
            throw new IllegalStateException("Esta reseña no existe");
        }
    }

    @Override
    public Double consultarCalificacion(Long idLibro) {
        return resenaRepository.obtenerPromedioCalificacion(idLibro);
    }
}
