package com.xpto.controle;

import com.xpto.dominio.Cidade;
import com.xpto.excecao.CidadeExcecao;

import javax.ejb.Local;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Local
public interface CidadeControle {

    List<String> cidadesQueSaoCapitais() throws CidadeExcecao;

    List<Object> estadoMaiorEMenor() throws CidadeExcecao;

    List<Object> cidadesPorEstado() throws CidadeExcecao;

    Cidade dadosCidadeByIdIBGE(int id_ibge) throws CidadeExcecao;

    List<String> cidadesPorEstado(String uf);

    void adicionarNovaCidade(Cidade cidade) throws SQLException;

    boolean deletarCidade(int id_ibge) throws SQLException;

    Long quantidadeDeRegistro();

    void salvarCidadesCsv() throws IOException, SQLException;
}
