//DECLARACIÓN DE PAQUETES:
package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.Constantes.MensajesConstantes;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.RespuestaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.AutorDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service.AutorService;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.dao.AutorDAO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Autor;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 28/01/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class AutorServiceImpl implements AutorService {
    @Autowired//INYECTAMOS EL DAO.
    private AutorDAO autorDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private AutorRepository autorRepository;
    
    //MÉTODO ÚNICO PARA LISTAR/FILTRAR/ORDENAR/PAGINAR AUTORES:
    @Override
    public Slice<AutorDTO> listarAutores(String keyword, String orderBy, String orderMode, Pageable pageable) {
        Slice<Autor> autores;
        
        //Si hay keyword, buscar con filtro; si no, listar todos.
        if (keyword != null && !keyword.trim().isEmpty()) {
            autores = autorRepository.findAutoresByKeywordWithOrder(keyword, orderBy, orderMode, pageable);
        } else {
            autores = autorRepository.findAllAutoresWithOrder(orderBy, orderMode, pageable);
        }
        
        return autores.map(autor -> autorDAO.autorDTO(autor));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearAutor(AutorDTO autorDTO) {
        Long maxIdAutor = null;
        //Optional<Autor> autorId = autorRepository.findByIdAutor(autorDTO.getIdAutor());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        autorDTO.setIdAutor(null);//SE IGNORA EL ID ENVIADO PARA QUE LA BASE DE DATOS ASIGNE UNO NUEVO AUTOINCREMENTAL.
        autorRepository.save(autorDAO.autor(autorDTO));
        respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarAutorporId(Long idAutor) {
        Optional<Autor> autorId = autorRepository.findByIdAutor(Long.valueOf(idAutor));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        //AutorDTO autorDTO = null;
        if (autorId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           //autorDTO.setNombreTipoDocumentoIdentificacion("CEDULA");
           respuestaDTO.setAutorDTO(autorDAO.autorDTO(autorId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (autorId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setAutorDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarAutor(AutorDTO autorDTO) {
        Optional<Autor> autorId = autorRepository.findByIdAutor(autorDTO.getIdAutor());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (autorId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL ID DEL REGISTRO CON EL ID PROPORCIONADO.
           Autor autor = autorDAO.autor(autorDTO);
           autorRepository.save(autor);
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
        }
        if (autorId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setAutorDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarAutor(Long idAutor) {
        Optional<Autor> autorId = autorRepository.findById(idAutor);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (autorId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setAutorDTO(autorDAO.autorDTO(autorId.get()));
            autorRepository.delete(autorId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (autorId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setAutorDTO(null);
        }
        
        return respuestaDTO;
    }
}
