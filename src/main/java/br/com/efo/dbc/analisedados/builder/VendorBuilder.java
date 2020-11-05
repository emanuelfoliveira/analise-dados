package br.com.efo.dbc.analisedados.builder;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.getFieldByPosition;

import br.com.efo.dbc.analisedados.model.Vendor;

public class VendorBuilder {

    private final static Integer VECTOR_POSITION_ONE = 1;
    private final static Integer VECTOR_POSITION_TWO = 2;
    private final static Integer VECTOR_POSITION_THREE = 3;

    public static Vendor buildVendor(final String[] line) {
        return Vendor
            .builder()
            .cpf(getFieldByPosition(line, VECTOR_POSITION_ONE))
            .name(getFieldByPosition(line, VECTOR_POSITION_TWO))
            .salary(getFieldByPosition(line, VECTOR_POSITION_THREE))
            .build();
    }
}
