package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.ParametroDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

public interface ParametroService {
    RespuestaDTO consultarParametroporId(Long idParametro);
    RespuestaDTO actualizarParametro(ParametroDTO ParametroDTO);
}
