package br.com.efo.dbc.analisedados.filereader;

import java.io.File;
import java.util.List;

public interface IFileReader {

    List<String> read(final File file) throws Exception;

}
