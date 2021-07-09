package com.xpto.controle;

import com.xpto.dominio.Cidade;
import org.easymock.EasyMock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class CidadeControleBeanTest {

    CidadeControleBean cidadeControleBean;

    List<Cidade> cidades = new ArrayList<>();

    @BeforeClass
    public void init(){
        cidadeControleBean = new CidadeControleBean();
        getCidades();
    }

    @Test
    public void testCidadesCapitais() {
    }

    @Test
    public void testEstadoMaiorEMenor() {
    }

    private void getCidades() {
        Cidade cidade = EasyMock.createMock(Cidade.class);
        cidades.add(cidade);
    }
}