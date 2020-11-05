package br.com.efo.dbc.analisedados.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import lombok.val;

public class FileWriterUtils {

    public static void writeOutputFile(final File file, final String content) throws IOException {
        val outputFile = new FileWriter(file);
        outputFile.write(content);
        outputFile.close();
    }

}
