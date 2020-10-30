package br.com.efo.dbc.analisedados.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class AnaliseDadosUtils {

    public static String getFieldByPosition(final String[] value, final int position) {
        return Optional.ofNullable(value[position]).orElse("");
    }

    public static Path inputPath() {
        return Paths.get(
            System.getProperty("user.home")
                .concat(File.separator)
                .concat("data")
                .concat(File.separator)
                .concat("in"));
    }

    public static Path outputPath() {
        return Paths.get(
            System.getProperty("user.home")
                .concat(File.separator)
                .concat("data")
                .concat(File.separator)
                .concat("out"));
    }

}
