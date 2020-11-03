package br.com.efo.dbc.analisedados.filereader.impl;

import br.com.efo.dbc.analisedados.filereader.IFileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class FileReader implements IFileReader {

    @Override
    public List<String> read(final File file) throws Exception {
        try {
            val scanner = new Scanner(file);
            val listLines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                val optLine = Optional.ofNullable(scanner.nextLine());
                if (optLine.isPresent()) {
                    listLines.add(optLine.get());
                }
            }

            return listLines;
        } catch (Exception ex) {
            throw new Exception("Problem during the reading of Files");
        }
    }
}
