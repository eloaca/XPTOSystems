package com.xpto.util;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.xpto.dominio.Cidade;
import com.xpto.excecao.CidadeExcecao;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CSVUtil {

    private String file = "src/main/resources/Cidades.csv";

    public List<Cidade> lerEExtrairCSV() throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get(file));
        CsvToBean<Cidade> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Cidade.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
        return csvToBean.parse();
    }

    public int registroPorColunaCSV(String colunaQueEuQuero) throws IOException, CsvValidationException {

        CSVReader c = new CSVReader(new FileReader(file));
        String[] linha;
        int coluna = 0, contador = 0;
        Set<String> stringsDaquelaColuna = new HashSet<>();

        for (int y = 0; y < 1; y++){
            linha = c.readNext();
            for (int i = 0; i < linha.length; i++) {
                if (linha[i].equals(colunaQueEuQuero)) {
                    coluna = i;
                    break;
                }
            }
        }

        while ((linha = c.readNext()) != null) {
            String palavraDaColuna = linha[coluna];
            if (contador == 0) {
                stringsDaquelaColuna.add(palavraDaColuna);
            }
            contador++;
            stringsDaquelaColuna.add(palavraDaColuna);
        }
        return stringsDaquelaColuna.size();
    }

    public List<String> lerColunaEFiltrarStringCSV(String colunaQueEuQuero, String palavraQueProcuro) throws IOException, CsvValidationException {

        CSVReader c = new CSVReader(new FileReader(file));
        String[] linha;
        int contador = 0, coluna = 0;
        List<String> stringsDaquelaColuna = new ArrayList<>();

        while ((linha = c.readNext()) != null) {
            if (contador == 0) {
                for (int i = 0; i < linha.length; i++) {
                    if (linha[i].equals(colunaQueEuQuero)) {
                        coluna = i;
                        break;
                    }
                }
            }
            contador++;
            String palavraDaColuna = linha[coluna].toLowerCase();
            if (palavraDaColuna.contains(palavraQueProcuro)){
                stringsDaquelaColuna.add(palavraDaColuna);
            }
        }
        return stringsDaquelaColuna;
    }

}
