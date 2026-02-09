package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="TBL_RESENAS")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResena;
    private String textoResena;
    private String estadoResena;
    private Integer calificacionLibro;
    private int idUsuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LIBRO",nullable = false)
    private Libro libro;



}
