package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository;

import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Long> {

    // Listar reseñas por libro
    List<Resena> findByLibroIdLibro(Long idLibro);

    // Calcular promedio de calificación de un libro
    @Query("SELECT AVG(r.calificacionLibro) FROM Resena r WHERE r.libro.idLibro = :idLibro")
    Double obtenerPromedioCalificacion(@Param("idLibro") Long idLibro);


}
