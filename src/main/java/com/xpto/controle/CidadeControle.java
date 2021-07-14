package com.xpto.controle;

import com.xpto.dominio.Cidade;
import com.xpto.excecao.CidadeExcecao;

import javax.ejb.Local;

import java.util.List;
import java.util.Map;

@Local
public interface CidadeControle {

    List<String> cidadesQueSaoCapitais() throws CidadeExcecao;

    Map<String, Integer> estadoMaiorEMenor() throws CidadeExcecao;

    Map<String, Integer> cidadesPorEstado() throws CidadeExcecao;

    Cidade dadosCidadeByIdIBGE(int id_ibge);

    List<String> cidadesPorEstado(String uf);

    void adicionarNovaCidade(Cidade cidade);

    boolean deletarCidade(Long id_ibge);

    Long quantidadeDeRegistro();
}
