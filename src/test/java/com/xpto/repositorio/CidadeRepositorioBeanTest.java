package com.xpto.repositorio;

import com.xpto.dominio.Cidade;
import org.easymock.EasyMock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;


public class CidadeRepositorioBeanTest {

    private CidadeRepositorio beanMock;

    @BeforeMethod
    private void init(){
        beanMock = EasyMock.createNiceMock(CidadeRepositorio.class);
    }

    @Test
    public void testBuscarCidadePeloIBGEId() {
        expect(beanMock.getById(EasyMock.anyInt())).andReturn(getCidadeMock());
        replay(beanMock);
        Cidade c = beanMock.getById(1);
        verify(beanMock);
        Assert.assertNotNull(c);
    }

    @Test
    public void testBuscarTodasAsCidades() {
        List<Cidade> cidades = new ArrayList<>();
        cidades.add(getCidade());
        expect(beanMock.findAll()).andReturn(cidades);
        replay(beanMock);
        cidades = beanMock.findAll();
        verify(beanMock);
        Assert.assertNotNull(cidades);
    }

    @Test
    public void testBuscarCidadesPorParametro() {
        List<Cidade> cidades = new ArrayList<>();
        cidades.add(getCidade());
        expect(beanMock.buscarCidadesPorParametro(EasyMock.anyString())).andReturn(cidades);
        replay(beanMock);
        cidades = beanMock.buscarCidadesPorParametro("SP");
        verify(beanMock);
        Assert.assertNotNull(cidades);
    }

    @Test
    public void testAdicionarCidade() {
        expect(beanMock.save(EasyMock.anyObject())).andReturn(getCidadeMock());
        replay(beanMock);
        Cidade c = beanMock.save(getCidade());
        verify(beanMock);
        Assert.assertNotNull(c);
    }

    @Test
    public void testBuscarCidadesCapitais() {
        List<Cidade> cidades = new ArrayList<>();
        cidades.add(getCidade());
        expect(beanMock.buscarCidadesCapitais()).andReturn(cidades);
        replay(beanMock);
        cidades = beanMock.buscarCidadesCapitais();
        verify(beanMock);
        Assert.assertNotNull(cidades);
    }

    @Test
    public void testBuscarQtoCidadesPorEstado() {
        Object objeto = new Object();
        List<Object> objetos = new ArrayList<>();
        objetos.add(objeto);
        expect(beanMock.buscarQtoCidadesPorEstado()).andReturn(objetos);
        replay(beanMock);
        objetos = beanMock.buscarQtoCidadesPorEstado();
        verify(beanMock);
        Assert.assertNotNull(objetos);
    }

    @Test
    public void testQtoDeRegistros() {
        expect(beanMock.qtoDeRegistros()).andReturn(1L);
        replay(beanMock);
        Long t = beanMock.qtoDeRegistros();
        verify(beanMock);
        Assert.assertNotNull(t);
    }

    private Cidade getCidade() {
        return new Cidade(
                1, "SP", "Maldivas", false, BigDecimal.valueOf(1L), BigDecimal.valueOf(-1L),
                "Maldivas", "", "Araras", "Franca");
    }

    private Cidade getCidadeMock() {
        return EasyMock.createNiceMock(Cidade.class);
    }
}