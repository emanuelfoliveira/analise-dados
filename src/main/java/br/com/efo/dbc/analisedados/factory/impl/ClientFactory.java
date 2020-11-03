package br.com.efo.dbc.analisedados.factory.impl;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.getFieldByPosition;

import br.com.efo.dbc.analisedados.factory.IEntityFactory;
import br.com.efo.dbc.analisedados.model.Client;
import lombok.val;

public class ClientFactory implements IEntityFactory {

    private final static Integer VECTOR_POSITION_ONE = 1;
    private final static Integer VECTOR_POSITION_TWO = 2;
    private final static Integer VECTOR_POSITION_THREE = 3;

    @Override
    public Client create(final String[] line) {
        val cnpj = getFieldByPosition(line, VECTOR_POSITION_ONE);
        val name = getFieldByPosition(line, VECTOR_POSITION_TWO);
        val businessArea = getFieldByPosition(line, VECTOR_POSITION_THREE);

        return Client
            .builder()
            .cnpj(cnpj)
            .name(name)
            .businessArea(businessArea)
            .build();
    }
}
