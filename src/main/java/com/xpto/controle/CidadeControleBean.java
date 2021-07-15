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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class CidadeControleBean implements CidadeControle {

    @Autowired
    private CidadeRepositorio cidadeRepositorio;

    @Override
    public List<String> cidadesQueSaoCapitais() throws CidadeExcecao {
        List<Cidade> cidades = cidadeRepositorio.buscarCidadesCapitais();
        if (cidades.isEmpty() || cidades == null)
            throw new CidadeExcecao("Nao foi encontrado nenhum resultado para sua busca");

        List<String> nomeCidades = new ArrayList<>();
        String nomeCidade;

        for (Cidade cidade : cidades) {
            nomeCidade = cidade.getName();
            nomeCidades.add(nomeCidade);
        }
        nomeCidades.sort(String::compareTo);
        return nomeCidades;
    }

    @Override
    public Map<String, Integer> estadoMaiorEMenor() throws CidadeExcecao {
        Map<String, Integer> estados = estadosEOcorrencia();
        if (estados.isEmpty() || estados == null)
            throw new CidadeExcecao("Nao foi encontrado nenhum resultado para sua busca");

        final Map<String, Integer> estadoMaiorEMenor = new HashMap<>();

        // maior
        String uf = estados.entrySet().iterator().next().getKey();
        Integer n = estados.entrySet().iterator().next().getValue();
        estadoMaiorEMenor.put("Estado Maior: "+uf, n);
        // menor
        uf = estados.entrySet().stream().skip(estados.size() - 1).findFirst().get().getKey();
        n = estados.entrySet().stream().skip(estados.size() - 1).findFirst().get().getValue();
        estadoMaiorEMenor.put("Estado Menor: "+uf, n);

        return estadoMaiorEMenor;
    }

    @Override
    public Map<String, Integer> cidadesPorEstado() throws CidadeExcecao {
        return estadosEOcorrencia();
    }

    @Override
    public Cidade dadosCidadeByIdIBGE(int id_ibge) throws CidadeExcecao {
        try {
            return cidadeRepositorio.buscarCidadePeloIBGEId(id_ibge);
        } catch (CidadeExcecao e){
            throw new CidadeExcecao("Excecao lancada: "+e.getCause());
        }
    }

    @Override
    public List<String> cidadesPorEstado(String uf) {
        List<Cidade> cidades = cidadeRepositorio.buscarCidadesPorParametro(uf);
        if (cidades.isEmpty() || cidades == null)
            throw new CidadeExcecao("Nao foi encontrado nenhum resultado para sua busca");

        List<String> nomeDasCidades = new ArrayList<>();
        for (Cidade c : cidades) {
            nomeDasCidades.add(c.getName());
        }
        return nomeDasCidades;
    }

    @Override
    public void adicionarNovaCidade(Cidade cidade) throws SQLException {
        cidadeRepositorio.adicionarCidade(cidade);
    }

    @Override
    public boolean deletarCidade(int id_ibge) {
        int i = cidadeRepositorio.deletarCidade(id_ibge);
        return Boolean.FALSE;
    }

    @Override
    public Long quantidadeDeRegistro() {
        try {
            return Long.valueOf(getCidades().size());
        } catch (CidadeExcecao e){
            throw new CidadeExcecao("Excecao lancada" +e.getCause());
        }
    }

    @Override
    public void salvarCidadesCsv() throws IOException, SQLException {
        CSVUtil util = new CSVUtil();
        List<Cidade> cidades = util.lerEExtrairCSV();
        for (Cidade c : cidades) {
            adicionarNovaCidade(c);
        }

    }

    private List<Cidade> getCidades() {
        return cidadeRepositorio.buscarTodasAsCidades();
    }

    private Map<String, Integer> estadosEOcorrencia() throws CidadeExcecao {
        List<Cidade> cidades = getCidades();
        if (cidades.isEmpty() || cidades == null) {
            throw new CidadeExcecao("Nenhum valor foi encontrado");
        }
        int a, b = 1;
        Map<String, Integer> estados = new TreeMap<String, Integer> (Collections.reverseOrder());
        for (Cidade cidade : cidades) {
            if (estados.isEmpty()){
                estados.put(cidade.getUf() + " -> ", new Integer(b));
            } else {
                for (Map.Entry<String, Integer> estado : estados.entrySet()) {
                    if (estado.getKey().contains(cidade.getUf())) {
                        a = estado.getValue() + b;
                        estados.put(cidade.getUf() + " -> ", new Integer(a));
                    } else {
                        estados.put(cidade.getUf() + " -> ", new Integer(b));
                    }
                }
            }
        }

        estados.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        return estados;
    }

}
