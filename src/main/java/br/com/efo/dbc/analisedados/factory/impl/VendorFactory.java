package br.com.efo.dbc.analisedados.factory.impl;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.getFieldByPosition;

import br.com.efo.dbc.analisedados.factory.IEntityFactory;
import br.com.efo.dbc.analisedados.model.Vendor;
import org.springframework.stereotype.Component;

@Component
public class VendorFactory implements IEntityFactory {

    private final static Integer VECTOR_POSITION_ONE = 1;
    private final static Integer VECTOR_POSITION_TWO = 2;
    private final static Integer VECTOR_POSITION_THREE = 3;

    @Override
    public Vendor create(final String[] line) {
        return Vendor
            .builder()
            .cpf(getFieldByPosition(line, VECTOR_POSITION_ONE))
            .name(getFieldByPosition(line, VECTOR_POSITION_TWO))
            .salary(getFieldByPosition(line, VECTOR_POSITION_THREE))
            .build();
    }
}
