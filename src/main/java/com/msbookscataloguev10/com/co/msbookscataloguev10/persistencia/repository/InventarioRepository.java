package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository;

import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

  //Kardex de un libro
  List<Inventario> findByLibroIdLibroOrderByFechaInventarioDesc(Long idLibro);

  // Stock actual calculado
  @Query("""
        SELECT COALESCE(SUM(
            CASE 
                WHEN i.tipoMovimiento = 'ENTRADA' THEN i.cantidadInventario
                ELSE -i.cantidadInventario
            END
        ), 0)
        FROM Inventario i
        WHERE i.libro.idLibro = :idLibro
          AND i.libro.estadoLibro = 'VISIBLE'
    """)
  Integer calcularStockActual(@Param("idLibro") Long idLibro);

}
