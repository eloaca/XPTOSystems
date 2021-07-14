package com.xpto.controle;

import com.xpto.dominio.Cidade;
import com.xpto.excecao.CidadeExcecao;
import com.xpto.repositorio.CidadeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            nomeCidade = cidade.getNome();
            nomeCidades.add(nomeCidade);
        }
        nomeCidades.sort(String::compareTo);
        return nomeCidades;
    }

    @Override
    public Map<String, Integer> estadoMaiorEMenor() throws CidadeExcecao {
        Map<String, Integer> estados = estadosEOcorrencia();
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
    public Cidade dadosCidadeByIdIBGE(int id_ibge) {
        return cidadeRepositorio.buscarCidadePeloIBGEId(id_ibge);
    }

    @Override
    public List<String> cidadesPorEstado(String uf) {
        List<Cidade> cidades = cidadeRepositorio.buscarCidadesPorParametro(uf);
        List<String> nomeDasCidades = new ArrayList<>();
        for (Cidade c : cidades) {
            nomeDasCidades.add(c.getNome());
        }
        return nomeDasCidades;
    }

    @Override
    public void adicionarNovaCidade(Cidade cidade) {
        cidadeRepositorio.adicionarCidade(cidade);
    }

    @Override
    public boolean deletarCidade(Long id_ibge) {
        int i = cidadeRepositorio.deletarCidade(id_ibge);
        return Boolean.FALSE;
    }

    @Override
    public Long quantidadeDeRegistro() {
        return Long.valueOf(getCidades().size());
    }

    private List<Cidade> getCidades() {
        return cidadeRepositorio.buscarTodasAsCidades();
    }

    private Map<String, Integer> estadosEOcorrencia() throws CidadeExcecao {
        List<Cidade> cidades = getCidades();
        if (cidades.isEmpty() || cidades == null) {
            throw new CidadeExcecao("Valor vazio");
        }
        int a, b = 1;
        Map<String, Integer> estados = new TreeMap<String, Integer> (Collections.reverseOrder());
        for (Cidade cidade : cidades) {
            if (estados.containsKey(cidade.getUf())) {
                a = estados.get(cidade.getUf()) + b;
                estados.put(cidade.getUf() + " -> ", new Integer(a));
            } else {
                estados.put(cidade.getUf() + " -> ", new Integer(b));
            }
        }

        estados.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        return estados;
    }

}
