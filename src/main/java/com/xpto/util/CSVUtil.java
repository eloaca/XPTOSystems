package com.xpto.util;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.xpto.dominio.Cidade;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVUtil {

    public List<Cidade> lerEExtrairCSV() throws IOException {

        String file = "src/main/resources/Cidades.csv";
        Reader reader = Files.newBufferedReader(Paths.get(file));
        CsvToBean<Cidade> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Cidade.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
        return csvToBean.parse();
    }
}
