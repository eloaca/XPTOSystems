package com.xpto.repositorio;

import com.xpto.dominio.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.SQLException;
import java.util.List;

public interface CidadeRepositorio extends JpaRepository<Cidade, Integer> {

    @Query("select c from Cidade c where upper(c.uf) = upper(?1)")
    List<Cidade> buscarCidadesPorParametro(String uf);

    @Query("select c from Cidade c where c.capital = true")
    List<Cidade> buscarCidadesCapitais();

    @Query("select c.uf, COUNT(c.uf) from Cidade c GROUP BY c.uf ORDER BY 2 DESC")
    List<Object> buscarQtoCidadesPorEstado();

    @Query("select count(c) from Cidade c")
    Long qtoDeRegistros();
}
