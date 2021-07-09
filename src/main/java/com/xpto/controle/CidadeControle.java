package com.xpto.controle;

import com.xpto.dominio.Cidade;
import javax.ejb.Local;

import java.util.List;
import java.util.Map;

@Local
public interface CidadeControle {

    List<String> cidadesCapitais(List<Cidade> cidades);

    Map<String, Integer> estadoMaiorEMenor(List<Cidade> cidades);

    Map<String, Integer> cidadesPorEstado(List<Cidade> cidades);

    Cidade dadosCidadeByIdIBGE(Long id_ibge);
}
