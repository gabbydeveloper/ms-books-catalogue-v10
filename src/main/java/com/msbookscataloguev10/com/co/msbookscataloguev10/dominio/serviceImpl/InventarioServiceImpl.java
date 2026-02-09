package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.serviceImpl;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.InventarioDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service.InventarioService;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.dao.InventarioDAO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Inventario;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository.InventarioRepository;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.utils.TipoMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class InventarioServiceImpl implements InventarioService {

  @Autowired
  private InventarioRepository inventarioRepository;

  @Autowired
  private InventarioDAO inventarioDAO;

  // =====================
  // ENTRADA
  // =====================
  @Override
  public void registrarEntrada(InventarioDTO inventarioDTO) {

    Inventario inventario =
        inventarioDAO.inventario(inventarioDTO, TipoMovimiento.ENTRADA);

    inventarioRepository.save(inventario);
  }

  // =====================
  // SALIDA
  // =====================
  @Override
  public void registrarSalida(InventarioDTO inventarioDTO) {

    Integer stockActual =
        inventarioRepository.calcularStockActual(inventarioDTO.getIdLibro());

    if (stockActual < inventarioDTO.getCantidadInventario()) {
      throw new IllegalStateException("Stock insuficiente");
    }

    Inventario inventario =
        inventarioDAO.inventario(inventarioDTO, TipoMovimiento.SALIDA);

    inventarioRepository.save(inventario);
  }

  // =====================
  // STOCK
  // =====================
  @Override
  public Integer consultarStock(Long idLibro) {
    return inventarioRepository.calcularStockActual(idLibro);
  }

  @Override
  public List<InventarioDTO> listarKardex(Long idLibro) {

    List<Inventario> inventarios = inventarioRepository.findByLibroIdLibroOrderByFechaInventarioDesc(idLibro);

    return inventarios.stream().map(inventarioDAO::inventarioDTO).toList();
  }
}
