package br.com.efo.dbc.analisedados.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnaliseDadosUtils {

    private final static String PATH = "user.home";
    private final static String PATH_DATA = "data";
    private final static String PATH_DATA_IN = "in";
    private final static String PATH_DATA_OUT = "out";

    public static String getFieldByPosition(final String[] value, final int position) {
        try {
            return Optional.ofNullable(value[position]).orElse("");
        } catch (ArrayIndexOutOfBoundsException ex) {
            log.error("Delimiter of the line is wrong. Line {}", value);
            return "";
        }
    }

    public static Path inputPath() {
        return Paths.get(
            System.getProperty(PATH)
                .concat(File.separator)
                .concat(PATH_DATA)
                .concat(File.separator)
                .concat(PATH_DATA_IN));
    }

    public static Path outputPath() {
        return Paths.get(
            System.getProperty(PATH)
                .concat(File.separator)
                .concat(PATH_DATA)
                .concat(File.separator)
                .concat(PATH_DATA_OUT));
    }

}
