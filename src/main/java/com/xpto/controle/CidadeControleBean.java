package com.xpto.controle;

import com.xpto.dominio.Cidade;
import com.xpto.excecao.CidadeExcecao;
import com.xpto.repositorio.CidadeRepositorio;
import com.xpto.util.CSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CidadeControleBean implements CidadeControle {

    @Autowired
    private CidadeRepositorio cidadeRepositorio;

    private CSVUtil util = new CSVUtil();

    @Override
    public List<String> cidadesQueSaoCapitais() throws CidadeExcecao {
        List<Cidade> cidades = cidadeRepositorio.buscarCidadesCapitais();
        if (cidades.isEmpty()) {
            throw new CidadeExcecao("Nao foi encontrado nenhum resultado para sua busca");
        }

        List<String> nomeCidades = new ArrayList<>();

        for (Cidade cidade : cidades) {
            nomeCidades.add(cidade.getNo_accents());
        }
        nomeCidades.sort(String::compareTo);
        return nomeCidades;
    }

    @Override
    public List<Object> estadoMaiorEMenor() throws CidadeExcecao {
        List<Object> cidades = cidadesPorEstado();
        if (cidades.isEmpty()) {
            throw new CidadeExcecao("Nao foi encontrado nenhum resultado para sua busca");
        }
        List<Object> cidadeMaiorEMenor = new ArrayList<>();
        cidadeMaiorEMenor.add(cidades.get(0));
        cidadeMaiorEMenor.add(cidades.get(cidades.size()-2)); // ignorando DF

        return cidadeMaiorEMenor;
    }

    @Override
    public List<Object> cidadesPorEstado() throws CidadeExcecao {
        return cidadeRepositorio.buscarQtoCidadesPorEstado();
    }

    @Override
    public Cidade dadosCidadeByIdIBGE(int id_ibge) throws CidadeExcecao {
        Cidade c = cidadeRepositorio.buscarCidadePeloIBGEId(id_ibge);
        if (c == null) {
            throw new CidadeExcecao("Nao foi encontrado nenhum resultado para sua busca");
        } else
            return c;
    }

    @Override
    public List<String> cidadesPorEstado(String uf) {
        List<Cidade> cidades = cidadeRepositorio.buscarCidadesPorParametro(uf);
        if (cidades.isEmpty()) {
            throw new CidadeExcecao("Nao foi encontrado nenhum resultado para sua busca");
        }

        List<String> nomeDasCidades = new ArrayList<>();
        for (Cidade c : cidades) {
            nomeDasCidades.add(c.getNo_accents());
        }
        return nomeDasCidades;
    }

    @Override
    public void adicionarNovaCidade(Cidade cidade) throws SQLException {
        cidadeRepositorio.adicionarCidade(cidade);
    }

    @Override
    public boolean deletarCidade(int id_ibge) {
        if (cidadeRepositorio.deletarCidade(id_ibge) == 1)
            return true;
        return false;
    }

    @Override
    public Long quantidadeDeRegistro() {
        try {
            return cidadeRepositorio.qtoDeRegistros();
        } catch (CidadeExcecao e){
            throw new CidadeExcecao("Excecao lancada" +e.getCause());
        }
    }

    @Override
    public void salvarCidadesCsv() throws IOException, SQLException {
        String file = "src/main/resources/Cidades.csv";
        List<Cidade> cidades = util.lerEExtrairCSV(file);
        for (Cidade c : cidades) {
            adicionarNovaCidade(c);
        }

    }

    @Override
    public List<Cidade> lerArquivoCSV(String csv) {
        try {
            return util.lerEExtrairCSV(csv);
        } catch (IOException e) {
            throw new CidadeExcecao("Não foi possivel concluir esta acao: "+e.getMessage());
        }
    }

    private List<Cidade> getCidades() {
        return cidadeRepositorio.buscarTodasAsCidades();
    }
}
