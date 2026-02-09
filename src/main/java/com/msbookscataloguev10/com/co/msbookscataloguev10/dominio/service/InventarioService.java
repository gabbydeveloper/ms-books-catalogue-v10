package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.InventarioDTO;
import java.util.List;

public interface InventarioService {

  List<InventarioDTO> listarKardex(Long idLibro);

  void registrarEntrada(InventarioDTO inventarioDTO);

  void registrarSalida(InventarioDTO inventarioDTO);

  Integer consultarStock(Long idLibro);
}


