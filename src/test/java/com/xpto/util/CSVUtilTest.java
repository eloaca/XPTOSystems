package com.xpto.util;

import com.opencsv.exceptions.CsvValidationException;
import com.xpto.dominio.Cidade;
import org.easymock.EasyMock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class CSVUtilTest {

    CSVUtil util;

    @BeforeMethod
    private void init(){
        util = new CSVUtil();
    }

    @Test
    public void testRegistroPorColunaCSV() throws CsvValidationException, IOException {
        int registro = util.registroPorColunaCSV("uf");
        Assert.assertNotNull(registro);
        Assert.assertEquals(registro, 27);
    }

    @Test
    public void testLerColunaEFiltrarStringCSV() throws CsvValidationException, IOException {
        List<String> strings = util.lerColunaEFiltrarStringCSV("uf", "SP");
        Assert.assertNotNull(strings);
    }
}