//DECLARACIÓN DE PAQUETES:
package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Autor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 28/01/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface AutorRepository extends JpaRepository<Autor,Long> {

    //MÉTODO PARA BUSCAR AUTOR POR ID:
    Optional<Autor> findByIdAutor(Long idAutor);

    //LISTAR TODOS LOS AUTORES CON ORDENAMIENTO DINÁMICO:
    @Query(value = "SELECT * FROM tbl_autores ORDER BY " +
          "CASE WHEN :orderBy = 'nombres' THEN tbl_autores.nombres_autor END ASC, " +
          "CASE WHEN :orderBy = 'nombres' AND :orderMode = 'desc' THEN tbl_autores.nombres_autor END DESC, " +
          "CASE WHEN :orderBy = 'primerapellido' THEN tbl_autores.primer_apellido_autor END ASC, " +
          "CASE WHEN :orderBy = 'primerapellido' AND :orderMode = 'desc' THEN tbl_autores.primer_apellido_autor END DESC, " +
          "CASE WHEN :orderBy = 'segundoapellido' THEN tbl_autores.segundo_apellido_autor END ASC, " +
          "CASE WHEN :orderBy = 'segundoapellido' AND :orderMode = 'desc' THEN tbl_autores.segundo_apellido_autor END DESC, " +
          "CASE WHEN :orderBy IS NULL OR :orderBy = 'id' THEN tbl_autores.id_autor END ASC, " +
          "CASE WHEN :orderBy = 'id' AND :orderMode = 'desc' THEN tbl_autores.id_autor END DESC",
          nativeQuery = true)
    Slice<Autor> findAllAutoresWithOrder(@Param("orderBy") String orderBy, @Param("orderMode") String orderMode, Pageable pageable);

    //BUSCAR AUTORES POR KEYWORD CON ORDENAMIENTO DINÁMICO:
    @Query(value = "SELECT * FROM tbl_autores WHERE " +
          "(tbl_autores.nombres_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_autores.primer_apellido_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_autores.segundo_apellido_autor LIKE CONCAT('%', :keyword, '%')) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'nombres' THEN tbl_autores.nombres_autor END ASC, " +
          "CASE WHEN :orderBy = 'nombres' AND :orderMode = 'desc' THEN tbl_autores.nombres_autor END DESC, " +
          "CASE WHEN :orderBy = 'primerapellido' THEN tbl_autores.primer_apellido_autor END ASC, " +
          "CASE WHEN :orderBy = 'primerapellido' AND :orderMode = 'desc' THEN tbl_autores.primer_apellido_autor END DESC, " +
          "CASE WHEN :orderBy = 'segundoapellido' THEN tbl_autores.segundo_apellido_autor END ASC, " +
          "CASE WHEN :orderBy = 'segundoapellido' AND :orderMode = 'desc' THEN tbl_autores.segundo_apellido_autor END DESC, " +
          "CASE WHEN :orderBy IS NULL OR :orderBy = 'id' THEN tbl_autores.id_autor END ASC, " +
          "CASE WHEN :orderBy = 'id' AND :orderMode = 'desc' THEN tbl_autores.id_autor END DESC",
          nativeQuery = true)
    Slice<Autor> findAutoresByKeywordWithOrder(@Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode, Pageable pageable);
}
