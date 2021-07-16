package com.xpto.repositorio;

import com.xpto.dominio.Cidade;

import java.sql.SQLException;
import java.util.List;

public interface CidadeRepositorio {

    Cidade buscarCidadePeloIBGEId(int idIBGE);

    List<Cidade> buscarTodasAsCidades();

    List<Cidade> buscarCidadesPorParametro(String uf);

    void adicionarCidade(Cidade cidade) throws SQLException;

    int deletarCidade(int idIBGE);

    List<Cidade> buscarCidadesCapitais();

    List<Object> buscarQtoCidadesPorEstado();

    Long qtoDeRegistros();
}
