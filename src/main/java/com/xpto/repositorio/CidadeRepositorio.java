package com.xpto.repositorio;

import com.xpto.dominio.Cidade;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CidadeRepositorio {

    Cidade buscarCidadePeloIBGEId(Long idIBGE);

    List<Cidade> buscarTodasAsCidades();

    void salvarTodasCidades(List<Cidade> cidades);

    List<Cidade> buscarCidadesPorParametro(String ... strings);

    void adicionarCidade(Cidade cidade);

    void deletarCidade(Cidade cidade);
}
