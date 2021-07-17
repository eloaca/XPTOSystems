package com.xpto.controle;

import com.xpto.dominio.Cidade;
import com.xpto.dominio.CidadeDistancia;
import org.easymock.EasyMock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class CidadeControleBeanTest {

    private CidadeControleBean bean;

    @BeforeMethod
    private void init(){
        bean = EasyMock.createNiceMock(CidadeControleBean.class);
    }

    @Test
    public void testCidadesQueSaoCapitais() {
        List<String> cidadesMock = new ArrayList<>();
        cidadesMock.add("SP");
        cidadesMock.add("RJ");
        expect(bean.cidadesQueSaoCapitais()).andReturn(cidadesMock);
        replay(bean);
        List<String> cidades = bean.cidadesQueSaoCapitais();
        verify(bean);
        Assert.assertEquals(cidades, cidadesMock);
    }

    @Test
    public void testEstadoMaiorEMenor() {
        List<Object> objetosMock = new ArrayList<>();
        objetosMock.add("Estado Maior - MG");
        objetosMock.add("Estado Menor - AP");
        expect(bean.estadoMaiorEMenor()).andReturn(objetosMock);
        replay(bean);
        List<Object> objetos = bean.estadoMaiorEMenor();
        verify(bean);
        Assert.assertEquals(objetos, objetosMock);
    }

    @Test
    public void testCidadesPorEstado() {
        List<Object> objetosMock = new ArrayList<>();
        objetosMock.add("RJ - 10");
        objetosMock.add("ES - 3");
        expect(bean.cidadesPorEstado()).andReturn(objetosMock);
        replay(bean);
        List<Object> objetos = bean.cidadesPorEstado();
        verify(bean);
        Assert.assertEquals(objetos, objetosMock);
    }

    @Test
    public void testDadosCidadeByIdIBGE() {
        Cidade cidadeMock = getCidadeMock();
        expect(bean.dadosCidadeByIdIBGE(EasyMock.anyInt())).andReturn(cidadeMock);
        replay(bean);
        Cidade cidade = bean.dadosCidadeByIdIBGE(EasyMock.anyInt());
        verify(bean);
        Assert.assertEquals(cidade, cidadeMock);
    }

    @Test
    public void testAdicionarNovaCidade() throws SQLException {
        Cidade cidadeMock = getCidadeMock();
        expect(bean.adicionarNovaCidade(cidadeMock)).andReturn(cidadeMock);
        replay(bean);
        Cidade cidade = bean.adicionarNovaCidade(cidadeMock);
        verify(bean);
        Assert.assertEquals(cidade, cidadeMock);
    }

    @Test
    public void testDeletarCidade() {
        int id = 1;
        expect(bean.deletarCidade(id)).andReturn(true);
        replay(bean);
        boolean retorno = bean.deletarCidade(id);
        verify(bean);
        Assert.assertTrue(retorno);
    }

    @Test
    public void testQuantidadeDeRegistro() {
        Long registroMock = 1L;
        expect(bean.quantidadeDeRegistro()).andReturn(registroMock);
        replay(bean);
        Long registro = bean.quantidadeDeRegistro();
        verify(bean);
        Assert.assertEquals(registro, registroMock);
    }

    @Test
    public void testLerArquivoCSV() {
        List<Cidade> cidadesMock = new ArrayList<>();
        cidadesMock.add(getCidadeMock());
        expect(bean.lerArquivoCSV()).andReturn(cidadesMock);
        replay(bean);
        List<Cidade> cidades = bean.lerArquivoCSV();
        Assert.assertEquals(cidades, cidadesMock);
    }

    @Test
    public void testRegistroPorColuna() {
        int registroMock = 10;
        expect(bean.registroPorColuna(anyString())).andReturn(registroMock);
        replay(bean);
        int registro = bean.registroPorColuna(anyString());
        verify(bean);
        Assert.assertEquals(registro, registroMock);
    }

    @Test
    public void testDistanciaEntreCidade() throws IOException {
        CidadeDistancia cidadeDistanciaMock = EasyMock.createNiceMock(CidadeDistancia.class);
        expect(bean.distanciaEntreCidade()).andReturn(cidadeDistanciaMock);
        replay(bean);
        CidadeDistancia cidadeDistancia = bean.distanciaEntreCidade();
        verify(bean);
        Assert.assertEquals(cidadeDistancia, cidadeDistanciaMock);
    }

    private Cidade getCidadeMock() {
        return new Cidade(
                1, "SP", "Maldivas", false, BigDecimal.valueOf(1L), BigDecimal.valueOf(-1L),
                "Maldivas", "", "Araras", "Franca");
    }
}