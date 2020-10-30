package br.com.efo.dbc.analisedados.report;

import java.io.IOException;

public interface IReportGenerator {

    void execute(final String fileName) throws IOException;

}
