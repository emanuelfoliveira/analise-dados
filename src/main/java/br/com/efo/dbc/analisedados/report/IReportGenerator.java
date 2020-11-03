package br.com.efo.dbc.analisedados.report;

import java.io.File;
import java.io.IOException;

public interface IReportGenerator {

    void execute(final File file) throws IOException;

}
