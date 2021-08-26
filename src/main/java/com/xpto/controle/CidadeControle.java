package com.xpto.controle;

import com.xpto.dominio.Cidade;
import com.xpto.dominio.CidadeDistancia;
import com.xpto.excecao.CidadeExcecao;

import javax.ejb.Local;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Local
public interface CidadeControle {

    List<String> cidadesQueSaoCapitais() throws CidadeExcecao;

    List<Object> estadoMaiorEMenor() throws CidadeExcecao;

    List<Object> cidadesPorEstado() throws CidadeExcecao;

    Optional<Cidade> dadosCidadeByIdIBGE(int id_ibge) throws CidadeExcecao;

    List<String> cidadesPorEstado(String uf);

    Cidade adicionarNovaCidade(Cidade cidade) throws SQLException;

    void deletarCidade(int id_ibge);

    Long quantidadeDeRegistro();

    void salvarCidadesCsv() throws IOException, SQLException;

    List<Cidade> lerArquivoCSV();

    List<String> stringPorColuna(String colunaQueEuQuero, String palavraQueProcuro);

    int registroPorColuna(String colunaQueEuQuero);

    CidadeDistancia distanciaEntreCidade() throws IOException;
}
