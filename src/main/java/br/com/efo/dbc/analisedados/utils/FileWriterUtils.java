package br.com.efo.dbc.analisedados.utils;

import com.google.common.io.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.val;

public class FileWriterUtils {

    private final static String DATE_PATTERN = "ddMMyyyyHHmmss";
    private final static String FILE_NAME_FORMAT = "/%s_%s.done.dat";

    public static void writeOutputFile(final File file, final String content) throws IOException {
        val outputFile = new FileWriter(file);
        outputFile.write(content);
        outputFile.close();
    }

    public static String buildFlatFilename(final String fileName) {
        val nameWithoutExtension = Files.getNameWithoutExtension(fileName);
        val date = new SimpleDateFormat(DATE_PATTERN).format(new Date());
        return String.format(FILE_NAME_FORMAT, nameWithoutExtension, date);
    }


}
