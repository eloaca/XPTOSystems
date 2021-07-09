package com.xpto.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.xpto.dominio.Cidade;
import org.apache.tomcat.util.http.fileupload.FileItem;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVUtil {

    private List<Cidade> lerEExtrairCSV(FileItem csv) throws IOException, CsvException {
        Reader reader = Files.newBufferedReader((Path) csv);
        CsvToBean<Cidade> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Cidade.class)
                .build();
        return csvToBean.parse();
    }

}
