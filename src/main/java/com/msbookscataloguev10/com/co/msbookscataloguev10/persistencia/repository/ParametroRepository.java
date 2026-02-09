package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository;

import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ParametroRepository extends JpaRepository<Parametro, Long>{
    Optional <Parametro> findByIdParametro(Long idParametro);
}
