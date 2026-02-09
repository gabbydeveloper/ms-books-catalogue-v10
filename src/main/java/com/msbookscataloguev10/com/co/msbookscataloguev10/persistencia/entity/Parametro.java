package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name="TBL_PARAMETROS")
public class Parametro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID_PARAMETRO", columnDefinition ="BIGINT(20) NOT NULL")
    private Long idParametro;
    @Column (name = "URL_BASE_LIBRO", columnDefinition = "TEXT NOT NULL")
    private String urlBaseLibro;
}
