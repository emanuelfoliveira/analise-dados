package br.com.efo.dbc.analisedados.datareader;

import java.io.File;
import java.io.FileNotFoundException;

public interface IDataReader {

    void execute(final File file) throws FileNotFoundException;

}
