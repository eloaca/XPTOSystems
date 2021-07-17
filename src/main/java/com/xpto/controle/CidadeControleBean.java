package com.xpto.controle;

import com.opencsv.exceptions.CsvValidationException;
import com.xpto.dominio.Cidade;
import com.xpto.dominio.CidadeDistancia;
import com.xpto.excecao.CidadeExcecao;
import com.xpto.repositorio.CidadeRepositorio;
import com.xpto.util.CSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.xpto.dominio.Cidade.getCidade;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;


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
    public Cidade adicionarNovaCidade(Cidade cidade) throws SQLException {
        return cidadeRepositorio.adicionarCidade(cidade);
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
        List<Cidade> cidades = util.lerEExtrairCSV();
        for (Cidade c : cidades) {
            adicionarNovaCidade(c);
        }

    }

    @Override
    public List<Cidade> lerArquivoCSV() {
        try {
            return util.lerEExtrairCSV();
        } catch (IOException e) {
            throw new CidadeExcecao("Não foi possivel concluir esta acao: "+e.getMessage());
        }
    }

    @Override
    public List<String> stringPorColuna(String colunaQueEuQuero, String palavraQueProcuro) {
        try {
            return util.lerColunaEFiltrarStringCSV(colunaQueEuQuero.toLowerCase(), palavraQueProcuro.toLowerCase());
        } catch (IOException e) {
            throw new CidadeExcecao("Não foi possivel concluir esta acao: "+e.getMessage());
        } catch (CsvValidationException e) {
            throw new CidadeExcecao("Não foi possivel concluir esta acao: "+e.getMessage());
        }
    }

    @Override
    public int registroPorColuna(String colunaQueEuQuero) {
        try {
            return util.registroPorColunaCSV(colunaQueEuQuero);
        } catch (IOException e) {
            throw new CidadeExcecao("Não foi possivel concluir esta acao: "+e.getMessage());
        } catch (CsvValidationException e) {
            throw new CidadeExcecao("Não foi possivel concluir esta acao: "+e.getMessage());
        }
    }

    public CidadeDistancia distanciaEntreCidade() throws IOException {
        List<Cidade> cidades = util.lerEExtrairCSV();

        if (cidades.size() < 3){
            switch (cidades.size()){
                case 1:
                    return new CidadeDistancia(cidades.get(0), null, BigDecimal.ZERO);
                case 2:
                    return new CidadeDistancia(cidades.get(0), cidades.get(1), this.calculoDistanciaEntreCidades(cidades.get(0), cidades.get(1)));
            }
        }

        Cidade a = null, b = null;
        BigDecimal distancia = BigDecimal.ZERO, d;

        for (int y = 0; y < cidades.size() - 1; y++){
            for (int z = y; z < cidades.size(); z++){
                d = calculoDistanciaEntreCidades(cidades.get(z), cidades.get(y));
                if (d.compareTo(distancia) > 0){
                    distancia = d;
                    a = cidades.get(y);
                    b = cidades.get(z);
                }
            }
        }

        final Cidade c1 = a == null ? null : getCidade(a);
        final Cidade c2 = b == null ? null : getCidade(b);

        return new CidadeDistancia(c1, c2, distancia);
    }

    private BigDecimal calculoDistanciaEntreCidades (Cidade a, Cidade b) {
        double lat1 = toRadians(a.getLat().doubleValue());
        double lat2 = toRadians(b.getLat().doubleValue());
        double delta= toRadians(b.getLon().subtract(a.getLon()).doubleValue());
        double cosS = sin(lat2) * sin(lat1) + cos(lat2) * cos(lat1) * cos(delta);
        double s    = acos(cosS);

        try {
            return BigDecimal.valueOf(s).multiply(new BigDecimal("6371"));
        } catch (NumberFormatException e){
            return BigDecimal.ZERO;
        }
    }

    private List<Cidade> getCidades() {
        return cidadeRepositorio.buscarTodasAsCidades();
    }
}
