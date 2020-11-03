package br.com.efo.dbc.analisedados.factory.impl;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.getFieldByPosition;

import br.com.efo.dbc.analisedados.factory.IEntityFactory;
import br.com.efo.dbc.analisedados.model.Vendor;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class VendorFactory implements IEntityFactory {

    private final static Integer VECTOR_POSITION_ONE = 1;
    private final static Integer VECTOR_POSITION_TWO = 2;
    private final static Integer VECTOR_POSITION_THREE = 3;

    @Override
    public Vendor create(final String[] line) {
        val cpf = getFieldByPosition(line, VECTOR_POSITION_ONE);
        val name = getFieldByPosition(line, VECTOR_POSITION_TWO);
        val salary = getFieldByPosition(line, VECTOR_POSITION_THREE);

        return Vendor
            .builder()
            .cpf(cpf)
            .name(name)
            .salary(salary)
            .build();
    }
}
