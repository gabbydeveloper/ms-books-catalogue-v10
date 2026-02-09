//DECLARACIÓN DE PAQUETES:
package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Categoria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 28/01/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    //MÉTODO PARA BUSCAR CATEGORIA POR NOMBRE:
    Categoria findByNombreCategoria(String nombreCategoria);

    //LISTAR TODAS LAS CATEGORIAS CON ORDENAMIENTO DINÁMICO:
    @Query(value = "SELECT * FROM tbl_categorias ORDER BY " +
          "CASE WHEN :orderBy = 'nombre' THEN tbl_categorias.nombre_categoria END ASC, " +
          "CASE WHEN :orderBy = 'nombre' AND :orderMode = 'desc' THEN tbl_categorias.nombre_categoria END DESC, " +
          "CASE WHEN :orderBy IS NULL OR :orderBy = 'id' THEN tbl_categorias.id_categoria END ASC, " +
          "CASE WHEN :orderBy = 'id' AND :orderMode = 'desc' THEN tbl_categorias.id_categoria END DESC",
          nativeQuery = true)
    Slice<Categoria> findAllCategoriasWithOrder(@Param("orderBy") String orderBy, @Param("orderMode") String orderMode, Pageable pageable);

    //BUSCAR CATEGORIAS POR KEYWORD CON ORDENAMIENTO DINÁMICO:
    @Query(value = "SELECT * FROM tbl_categorias WHERE " +
          "(tbl_categorias.nombre_categoria LIKE CONCAT('%', :keyword, '%')) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'nombre' THEN tbl_categorias.nombre_categoria END ASC, " +
          "CASE WHEN :orderBy = 'nombre' AND :orderMode = 'desc' THEN tbl_categorias.nombre_categoria END DESC, " +
          "CASE WHEN :orderBy IS NULL OR :orderBy = 'id' THEN tbl_categorias.id_categoria END ASC, " +
          "CASE WHEN :orderBy = 'id' AND :orderMode = 'desc' THEN tbl_categorias.id_categoria END DESC",
          nativeQuery = true)
    Slice<Categoria> findCategoriasByKeywordWithOrder(@Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode, Pageable pageable);

    //========================================================================================
    //MÉTODOS SIN PAGINACIÓN PARA SELECTS DEL FRONTEND:
    //========================================================================================

    //LISTAR TODAS LAS CATEGORIAS CON ORDENAMIENTO DINÁMICO (SIN PAGINACIÓN):
    @Query(value = "SELECT * FROM tbl_categorias ORDER BY " +
          "CASE WHEN :orderBy = 'nombre' THEN tbl_categorias.nombre_categoria END ASC, " +
          "CASE WHEN :orderBy = 'nombre' AND :orderMode = 'desc' THEN tbl_categorias.nombre_categoria END DESC, " +
          "CASE WHEN :orderBy IS NULL OR :orderBy = 'id' THEN tbl_categorias.id_categoria END ASC, " +
          "CASE WHEN :orderBy = 'id' AND :orderMode = 'desc' THEN tbl_categorias.id_categoria END DESC",
          nativeQuery = true)
    List<Categoria> findAllCategoriasWithOrderNoPagination(@Param("orderBy") String orderBy, @Param("orderMode") String orderMode);

    //BUSCAR CATEGORIAS POR KEYWORD CON ORDENAMIENTO DINÁMICO (SIN PAGINACIÓN):
    @Query(value = "SELECT * FROM tbl_categorias WHERE " +
          "(tbl_categorias.nombre_categoria LIKE CONCAT('%', :keyword, '%')) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'nombre' THEN tbl_categorias.nombre_categoria END ASC, " +
          "CASE WHEN :orderBy = 'nombre' AND :orderMode = 'desc' THEN tbl_categorias.nombre_categoria END DESC, " +
          "CASE WHEN :orderBy IS NULL OR :orderBy = 'id' THEN tbl_categorias.id_categoria END ASC, " +
          "CASE WHEN :orderBy = 'id' AND :orderMode = 'desc' THEN tbl_categorias.id_categoria END DESC",
          nativeQuery = true)
    List<Categoria> findCategoriasByKeywordWithOrderNoPagination(@Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);

    //MÉTODO PARA BUSCAR CATEGORIA POR ID:
    Optional<Categoria> findByIdCategoria(Long idCategoria);
}
