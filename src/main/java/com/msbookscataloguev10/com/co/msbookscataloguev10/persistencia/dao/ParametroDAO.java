package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.dao;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.ParametroDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Parametro;
import org.springframework.stereotype.Component;


@Component

public class ParametroDAO {


    public Parametro parametro(ParametroDTO parametroDTO) {
        Parametro parametro = new Parametro();
        parametro.setIdParametro(parametroDTO.getIdParametro());
        parametro.setUrlBaseLibro(parametroDTO.getUrlBaseLibro());

        return parametro;
    }

    public ParametroDTO parametroDTO(Parametro parametro){
        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.setIdParametro(parametro.getIdParametro());
        parametroDTO.setUrlBaseLibro(parametro.getUrlBaseLibro());

        return parametroDTO;
    }
}
