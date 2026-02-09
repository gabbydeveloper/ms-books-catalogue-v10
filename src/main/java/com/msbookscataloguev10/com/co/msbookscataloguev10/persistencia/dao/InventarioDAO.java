package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.dao;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.InventarioDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Inventario;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Libro;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository.LibroRepository;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.utils.TipoMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InventarioDAO {

  @Autowired
  private LibroRepository libroRepository;

  //Este método es para guardar los datos. Se hace la conversión DTO → ENTITY
  public Inventario inventario(InventarioDTO dto, TipoMovimiento tipoMovimiento) {

    Inventario inventario = new Inventario();

    inventario.setIdInventario(dto.getIdInventario());

    Libro libro = libroRepository.findById(dto.getIdLibro())
        .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

    inventario.setLibro(libro);
    inventario.setTipoMovimiento(tipoMovimiento);
    inventario.setCantidadInventario(dto.getCantidadInventario());
    inventario.setFechaInventario(LocalDateTime.now());

    return inventario;
  }

  //Este método es para presentar los datos. Se hace la conversión ENTITY → DTO
  public InventarioDTO inventarioDTO(Inventario inventario) {

    InventarioDTO dto = new InventarioDTO();

    dto.setIdInventario(inventario.getIdInventario());
    dto.setIdLibro(inventario.getLibro().getIdLibro());
    dto.setTipoMovimiento(inventario.getTipoMovimiento().name());
    dto.setCantidadInventario(inventario.getCantidadInventario());
    dto.setFechaInventario(inventario.getFechaInventario().toString());

    return dto;
  }
}
