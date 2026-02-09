//DECLARACIÓN DE PAQUETES:
package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:

import lombok.Data;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

//import java.io.Serializable;


/**
 * @Autor PD04. HERNAN ADOLFO NUÑEZ GONZALEZ.
 * @Since 01/08/2023.
 * Declaración de la entidad.
 * @Actualizacion David Paez 04/02/2026.
 */

@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TBL_LIBROS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class Libro {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    @GeneratedValue(strategy = GenerationType.IDENTITY)//DECLARACIÓN DE LA GENERACIÓN AUTOMÁTICA DEL ID AUTOINCREMENTAL.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_LIBRO", columnDefinition="BIGINT(20) NOT NULL")
    private Long idLibro;
    
    @Column(name = "TITULO_LIBRO", columnDefinition="VARCHAR2(150) NOT NULL")
    private String tituloLibro;
    
    @ManyToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_AUTOR", columnDefinition = "BIGINT(20) NOT NULL")
    private Autor autor;
    
    @Column(name = "FECHA_PUBLICACION_LIBRO", columnDefinition="DATE NOT NULL")
    private String fechaPublicacionLibro;
    
    @Column(name = "SINOPSIS_LIBRO", columnDefinition="TEXT NOT NULL")
    private String sinopsisLibro;
    
    @Column(name = "CODIGO_ISBN_LIBRO", columnDefinition="VARCHAR2(50) NOT NULL")
    private String codigoIsbnLibro;
    
    @Column(name = "PRECIO_LIBRO", columnDefinition="DECIMAL(15,4) NOT NULL")
    private double precioLibro;
    
    @Column(name = "FORMATO_LIBRO", columnDefinition="VARCHAR2(10) NOT NULL")
    private String formatoLibro;
    
    @Column(name = "NOMBRE_ARCHIVO_IMAGEN_LIBRO", columnDefinition="VARCHAR2(256) NULL")
    private String nombreArchivoImagenLibro;
    
    @Column(name = "ESTADO_LIBRO", columnDefinition="VARCHAR2(10) NOT NULL")
    private String estadoLibro;

    //RELACIÓN MUCHOS A MUCHOS CON CATEGORIAS (TABLA PIVOTE: tbl_libros_x_categoria)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tbl_libros_x_categorias",
            joinColumns = @JoinColumn(name = "id_libro"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias = new HashSet<>();


    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Long getIdLibro() {
        return idLibro;
    }
    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }
    public String getTituloLibro() {
        return tituloLibro;
    }
    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public int getAnioPublicacionLibro() {
        return anioPublicacionLibro;
    }
    public void setAnioPublicacionLibro(int anioPublicacionLibro) {
        this.anioPublicacionLibro = anioPublicacionLibro;
    }
    public String getSinopsisLibro() {
        return sinopsisLibro;
    }
    public void setSinopsisLibro(String sinopsisLibro) {
        this.sinopsisLibro = sinopsisLibro;
    }
    public String getCodigoIsbnLibro() {
        return codigoIsbnLibro;
    }
    public void setCodigoIsbnLibro(String codigoIsbnLibro) {
        this.codigoIsbnLibro = codigoIsbnLibro;
    }
    public double getPrecioLibro() {
        return precioLibro;
    }
    public void setPrecioLibro(double precioLibro) {
        this.precioLibro = precioLibro;
    }
    public String getFormatoLibro() {
        return formatoLibro;
    }
    public void setFormatoLibro(String formatoLibro) {
        this.formatoLibro = formatoLibro;
    }
    public String getNombreArchivoImagenLibro() {
        return nombreArchivoImagenLibro;
    }
    public void setNombreArchivoImagenLibro(String nombreArchivoImagenLibro) {
        this.nombreArchivoImagenLibro = nombreArchivoImagenLibro;
    }
    public String getEstadoLibro() {
        return estadoLibro;
    }
    public void setEstadoLibro(String estadoLibro) {
        this.estadoLibro = estadoLibro;
    }*/
}
