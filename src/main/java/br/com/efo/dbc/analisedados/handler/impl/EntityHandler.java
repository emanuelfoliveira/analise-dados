package br.com.efo.dbc.analisedados.handler.impl;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.getFieldByPosition;

import br.com.efo.dbc.analisedados.handler.IEntityHandler;
import br.com.efo.dbc.analisedados.model.ClientEntity;
import br.com.efo.dbc.analisedados.model.SalesEntity;
import br.com.efo.dbc.analisedados.model.SalesItemEntity;
import br.com.efo.dbc.analisedados.model.VendorEntity;
import java.util.ArrayList;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class EntityHandler implements IEntityHandler {

    private final static String DETAILS_DELIMITER = ",";
    private final static String ITEM_DETAILS_DELIMITER = "-";
    private final static String SQUARE_BRACKETS_REGEX = "[\\[\\]]";
    private final static Integer VECTOR_POSITION_ZERO = 0;
    private final static Integer VECTOR_POSITION_ONE = 1;
    private final static Integer VECTOR_POSITION_TWO = 2;
    private final static Integer VECTOR_POSITION_THREE = 3;

    @Override
    public ClientEntity buildClient(final String[] line) {
        val cnpj = getFieldByPosition(line, VECTOR_POSITION_ONE);
        val name = getFieldByPosition(line, VECTOR_POSITION_TWO);
        val businessArea = getFieldByPosition(line, VECTOR_POSITION_THREE);

        return ClientEntity
            .builder()
            .cnpj(cnpj)
            .name(name)
            .businessArea(businessArea)
            .build();
    }

    @Override
    public SalesEntity buildSales(final String[] line) {
        val saleId = getFieldByPosition(line, VECTOR_POSITION_ONE);
        val items = line[VECTOR_POSITION_TWO].replaceAll(SQUARE_BRACKETS_REGEX, "");
        val salesmanName = getFieldByPosition(line, VECTOR_POSITION_THREE);

        val spplitedItems = items.split(DETAILS_DELIMITER);

        val listItems = new ArrayList<SalesItemEntity>();
        for (int i = 0; i < spplitedItems.length; i++) {
            val itemsLine = spplitedItems[i].split(ITEM_DETAILS_DELIMITER);

            val entity = SalesItemEntity.builder()
                .itemId(getFieldByPosition(itemsLine, VECTOR_POSITION_ZERO))
                .itemQuantity(getFieldByPosition(itemsLine, VECTOR_POSITION_ONE))
                .itemPrice(Double.parseDouble(getFieldByPosition(itemsLine, VECTOR_POSITION_TWO)))
                .build();

            listItems.add(entity);
        }

        return SalesEntity
            .builder()
            .saleId(Integer.parseInt(saleId))
            .salesItemEntity(listItems)
            .salesmanName(salesmanName)
            .build();
    }

    @Override
    public VendorEntity buildVendor(final String[] line) {
        val cpf = getFieldByPosition(line, VECTOR_POSITION_ONE);
        val name = getFieldByPosition(line, VECTOR_POSITION_TWO);
        val salary = getFieldByPosition(line, VECTOR_POSITION_THREE);

        return VendorEntity
            .builder()
            .cpf(cpf)
            .name(name)
            .salary(salary)
            .build();
    }
}
