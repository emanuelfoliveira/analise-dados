package br.com.efo.dbc.analisedados.handler;

import java.util.List;

public interface IFileHandler {

    void persistByEntityType(final List<String> lines) throws Exception;

}
