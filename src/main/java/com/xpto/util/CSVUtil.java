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
import java.util.List;

public class CSVUtil {

    public List<Cidade> lerEExtrairCSV(String file) throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get(file));
        CsvToBean<Cidade> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Cidade.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
        return csvToBean.parse();
    }

    public List<String> lerColunaCSV(String colunaQueEuQuero, String palavraQueProcuro) throws IOException, CsvValidationException, CidadeExcecao {

        String file = "src/main/resources/Cidades.csv";
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
                    else {
                        throw new CidadeExcecao("Coluna nao encontrada");
                    }
                }
            }
            contador++;
            String palavraDaColuna = linha[coluna];
            if (palavraDaColuna.contains(palavraQueProcuro)){
                stringsDaquelaColuna.add(palavraDaColuna);
            }
        }
        return stringsDaquelaColuna;
    }

    public static void main (String[] args) throws IOException, CsvValidationException {
        CSVUtil util = new CSVUtil();



        List<String> palavrasDaquelaColuna = util.lerColunaCSV
                ("no_accents", "Sao");

        for (String s : palavrasDaquelaColuna) {
            System.out.println(s);
        }
    }
}
