package com.xpto.controle;

import com.xpto.dominio.Cidade;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Stateless
public class CidadeControleBean implements CidadeControle {

    @Override
    public List<String> cidadesCapitais(List<Cidade> cidades) {

        List<String> nomeCidades = new ArrayList<>();
        String nomeCidade = "";

        for (Cidade cidade : cidades) {
            if (cidade.isCapital()){
                nomeCidade = cidade.getNome();
                nomeCidades.add(nomeCidade);
            }
        }

        nomeCidades.sort(String::compareTo);

        return nomeCidades;
    }

    @Override
    public Map<String, Integer> estadoMaiorEMenor(List<Cidade> cidades) {

        Map<String, Integer> estados = estadosEOcorrencia(cidades);
        final Map<String, Integer> estadoMaiorEMenor = new HashMap<>();

        String uf;
        Integer n;

        // maior
        uf = estados.entrySet().iterator().next().getKey();
        n = estados.entrySet().iterator().next().getValue();
        estadoMaiorEMenor.put("Estado Maior: "+uf, n);
        // menor
        uf = estados.entrySet().stream().skip(estados.size() - 1).findFirst().get().getKey();
        n = estados.entrySet().stream().skip(estados.size() - 1).findFirst().get().getValue();
        estadoMaiorEMenor.put("Estado Menor: "+uf, n);

        return estadoMaiorEMenor;
    }

    @Override
    public Map<String, Integer> cidadesPorEstado(List<Cidade> cidades) {
        return estadosEOcorrencia(cidades);
    }

    @Override
    public Cidade dadosCidadeByIdIBGE(Long id_ibge) {

        return null;
    }

    private Map<String, Integer> estadosEOcorrencia(List<Cidade> cidades) {

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
