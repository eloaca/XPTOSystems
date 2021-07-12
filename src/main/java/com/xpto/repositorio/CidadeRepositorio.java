package com.xpto.repositorio;

import com.xpto.dominio.Cidade;

import java.util.List;

public interface CidadeRepositorio {

    Cidade buscarCidadePeloIBGEId(int idIBGE);

    List<Cidade> buscarTodasAsCidades();

    void salvarTodasCidades(List<Cidade> cidades);

    List<Cidade> buscarCidadesPorParametro(String uf);

    void adicionarCidade(Cidade cidade);

    int deletarCidade(Long idIBGE);
}
