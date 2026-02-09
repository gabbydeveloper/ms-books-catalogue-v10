//DECLARACIÓN DE PAQUETES:
package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import java.io.Serializable;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 28/01/2026.
* Declaración de la entidad.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TBL_AUTORES")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class Autor {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    @GeneratedValue(strategy = GenerationType.IDENTITY)//DECLARACIÓN DE LA GENERACIÓN AUTOMÁTICA DEL ID AUTOINCREMENTAL.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_AUTOR", columnDefinition="BIGINT(20) NOT NULL")
    private Long idAutor;
    
    @Column(name = "NOMBRES_AUTOR", columnDefinition="VARCHAR2(150) NOT NULL")
    private String nombresAutor;
    
    @Column(name = "PRIMER_APELLIDO_AUTOR", columnDefinition="VARCHAR2(150) NOT NULL")
    private String primerApellidoAutor;
    
    @Column(name = "SEGUNDO_APELLIDO_AUTOR", columnDefinition="VARCHAR2(150) NOT NULL")
    private String segundoApellidoAutor;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Long getIdAutor() {
        return idAutor;
    }
    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }
    public String getNombresAutor() {
        return nombresAutor;
    }
    public void setNombresAutor(String nombresAutor) {
        this.nombresAutor = nombresAutor;
    }
    public String getPrimerApellidoAutor() {
        return primerApellidoAutor;
    }
    public void setPrimerApellidoAutor(String primerApellidoAutor) {
        this.primerApellidoAutor = primerApellidoAutor;
    }
    public String getSegundoApellidoAutor() {
        return segundoApellidoAutor;
    }
    public void setSegundoApellidoAutor(String segundoApellidoAutor) {
        this.segundoApellidoAutor = segundoApellidoAutor;
    }*/
}
