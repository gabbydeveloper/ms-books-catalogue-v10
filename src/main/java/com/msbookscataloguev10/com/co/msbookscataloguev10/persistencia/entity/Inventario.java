package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity;

import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.utils.TipoMovimiento;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="TBL_INVENTARIOS")
public class Inventario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_INVENTARIO")
  private Long IdInventario;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_LIBRO", nullable = false)
  private Libro libro;

  @Enumerated(EnumType.STRING)
  @Column(name = "TIPO_MOVIMIENTO", nullable = false)
  private TipoMovimiento tipoMovimiento;

  @Column(name = "FECHA_INVENTARIO", nullable = false)
  private LocalDateTime fechaInventario;

  @Column(name = "CANTIDAD_INVENTARIO", nullable = false)
  private Integer cantidadInventario;
}
