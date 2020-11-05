package br.com.efo.dbc.analisedados.builder;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.getFieldByPosition;

import br.com.efo.dbc.analisedados.model.Client;

public class ClientBuilder {

    private final static Integer VECTOR_POSITION_ONE = 1;
    private final static Integer VECTOR_POSITION_TWO = 2;
    private final static Integer VECTOR_POSITION_THREE = 3;

    public static Client buildClient(final String[] line) {
        return Client
            .builder()
            .cnpj(getFieldByPosition(line, VECTOR_POSITION_ONE))
            .name(getFieldByPosition(line, VECTOR_POSITION_TWO))
            .businessArea(getFieldByPosition(line, VECTOR_POSITION_THREE))
            .build();
    }
}
