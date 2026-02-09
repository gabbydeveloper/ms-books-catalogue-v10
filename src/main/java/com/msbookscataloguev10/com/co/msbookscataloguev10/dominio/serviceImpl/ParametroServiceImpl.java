package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.serviceImpl;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.Constantes.MensajesConstantes;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.CategoriaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.RespuestaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.ParametroDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service.ParametroService;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.dao.ParametroDAO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Categoria;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Parametro;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service

public class ParametroServiceImpl implements ParametroService {

    @Autowired//INYECTAMOS EL DAO.
    private ParametroDAO parametroDAO;

    @Autowired//INYECTAMOS EL REPOSITORIO.
    private ParametroRepository parametroRepository;

    @Override
    public RespuestaDTO consultarParametroporId(Long idParametro) {
        Optional<Parametro> parametroId = parametroRepository.findByIdParametro(Long.valueOf(idParametro));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        if (parametroId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
            respuestaDTO.setParametroDTO(parametroDAO.parametroDTO(parametroId.get()));
            respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
            respuestaDTO.setBanderaexito(true);
        }
        if (parametroId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
            respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
            respuestaDTO.setParametroDTO(null);
        }
        return respuestaDTO;
    }

    @Override
    //SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarParametro(ParametroDTO parametroDTO) {
        Optional<Parametro> parametroId = parametroRepository.findByIdParametro(parametroDTO.getIdParametro());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);

        if (parametroId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL ID DEL REGISTRO CON EL ID PROPORCIONADO.
            Parametro parametro = parametroDAO.parametro(parametroDTO);
            parametroRepository.save(parametro);
            respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
        }
        if (parametroId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
            respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
            respuestaDTO.setParametroDTO(null);
        }

        return respuestaDTO;
    }
}
