//DECLARACIÓN DE PAQUETES:
package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.Constantes.MensajesConstantes;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.RespuestaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.CategoriaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service.CategoriaService;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.dao.CategoriaDAO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Categoria;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository.CategoriaRepository;
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
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired//INYECTAMOS EL DAO.
    private CategoriaDAO categoriaDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private CategoriaRepository categoriaRepository;
    
    //MÉTODO ÚNICO PARA LISTAR/FILTRAR/ORDENAR/PAGINAR CATEGORIAS:
    @Override
    public Slice<CategoriaDTO> listarCategorias(String keyword, String orderBy, String orderMode, Pageable pageable) {
        Slice<Categoria> categorias;
        
        //Si hay keyword, buscar con filtro; si no, listar todos.
        if (keyword != null && !keyword.trim().isEmpty()) {
           categorias = categoriaRepository.findCategoriasByKeywordWithOrder(keyword, orderBy, orderMode, pageable);
        } else {
            categorias = categoriaRepository.findAllCategoriasWithOrder(orderBy, orderMode, pageable);
        }
        
        return categorias.map(categoria -> categoriaDAO.categoriaDTO(categoria));
    }
    
    //MÉTODO PARA LISTAR/FILTRAR/ORDENAR CATEGORIAS SIN PAGINACIÓN (PARA SELECTS):
    @Override
    public List<CategoriaDTO> listarCategoriasNoPaginacion(String keyword, String orderBy, String orderMode) {
        List<Categoria> categorias;
        
        //Si hay keyword, buscar con filtro; si no, listar todos.
        if (keyword != null && !keyword.trim().isEmpty()) {
           categorias = categoriaRepository.findCategoriasByKeywordWithOrderNoPagination(keyword, orderBy, orderMode);
        } else {
            categorias = categoriaRepository.findAllCategoriasWithOrderNoPagination(orderBy, orderMode);
        }
        
        //Convertir entidades a DTOs.
        List<CategoriaDTO> categoriaDTOS = new ArrayList<>();
        for (Categoria categoria : categorias) {
            categoriaDTOS.add(categoriaDAO.categoriaDTO(categoria));
        }
        
        return categoriaDTOS;
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoriaNombre = categoriaRepository.findByNombreCategoria(categoriaDTO.getNombreCategoria().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        if (!(categoriaNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           banderaNombreRegistroEncontrado=1;
        }
        
        //System.out.println("INGRESA METODO CREAR.");
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setCategoriaDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           categoriaDTO.setIdCategoria(null);//SE IGNORA EL ID ENVIADO PARA QUE LA BASE DE DATOS ASIGNE UNO NUEVO AUTOINCREMENTAL.
           categoriaRepository.save(categoriaDAO.categoria(categoriaDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarCategoriaporId(Long idCategoria) {
        Optional<Categoria> categoriaId = categoriaRepository.findByIdCategoria(Long.valueOf(idCategoria));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        //CategoriaDTO categoriaDTO = null;
        if (categoriaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setCategoriaDTO(categoriaDAO.categoriaDTO(categoriaId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (categoriaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setCategoriaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarCategoriaporNombre(String nombreCategoria) {
        Optional<Categoria> categoriaNombre = Optional.ofNullable(categoriaRepository.findByNombreCategoria(nombreCategoria));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (categoriaNombre.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setCategoriaDTO(categoriaDAO.categoriaDTO(categoriaNombre.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (categoriaNombre.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setCategoriaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarCategoria(CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoriaId = categoriaRepository.findByIdCategoria(categoriaDTO.getIdCategoria());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (categoriaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL ID DEL REGISTRO CON EL ID PROPORCIONADO.
           Categoria categoria = categoriaDAO.categoria(categoriaDTO);
           categoriaRepository.save(categoria);
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
        }
        if (categoriaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setCategoriaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarCategoria(Long idCategoria) {
        Optional<Categoria> categoriaId = categoriaRepository.findById(idCategoria);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (categoriaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setCategoriaDTO(categoriaDAO.categoriaDTO(categoriaId.get()));
            categoriaRepository.delete(categoriaId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (categoriaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setCategoriaDTO(null);
        }
        
        return respuestaDTO;
    }
}
