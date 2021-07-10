package com.xpto.controle;

import com.xpto.dominio.Cidade;
import com.xpto.excecao.CidadeExcecao;

import javax.ejb.Local;

import java.util.List;
import java.util.Map;

@Local
public interface CidadeControle {

    List<String> cidadesCapitais() throws CidadeExcecao;

    Map<String, Integer> estadoMaiorEMenor() throws CidadeExcecao;

    Map<String, Integer> cidadesPorEstado() throws CidadeExcecao;

    Cidade dadosCidadeByIdIBGE(Long id_ibge);

    List<String> cidadesPorEstado(String uf);

    void adicionarNovaCidade(Cidade cidade);

    void deletarCidade(Cidade cidade);
}
