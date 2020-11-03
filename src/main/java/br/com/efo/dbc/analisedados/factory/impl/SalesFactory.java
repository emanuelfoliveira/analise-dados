package br.com.efo.dbc.analisedados.factory.impl;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.getFieldByPosition;

import br.com.efo.dbc.analisedados.factory.IEntityFactory;
import br.com.efo.dbc.analisedados.model.Sales;
import br.com.efo.dbc.analisedados.model.SalesItem;
import java.util.ArrayList;
import java.util.List;
import lombok.val;

public class SalesFactory implements IEntityFactory {

    private final static String DETAILS_DELIMITER = ",";
    private final static String ITEM_DETAILS_DELIMITER = "-";
    private final static String SQUARE_BRACKETS_REGEX = "[\\[\\]]";
    private final static Integer VECTOR_POSITION_ZERO = 0;
    private final static Integer VECTOR_POSITION_ONE = 1;
    private final static Integer VECTOR_POSITION_TWO = 2;
    private final static Integer VECTOR_POSITION_THREE = 3;

    @Override
    public Sales create(final String[] line) {
        val saleId = getFieldByPosition(line, VECTOR_POSITION_ONE);
        val items = line[VECTOR_POSITION_TWO].replaceAll(SQUARE_BRACKETS_REGEX, "");
        val salesmanName = getFieldByPosition(line, VECTOR_POSITION_THREE);

        val spplitedItems = items.split(DETAILS_DELIMITER);

        return Sales
            .builder()
            .saleId(Integer.parseInt(saleId))
            .salesItemEntity(getSalesItems(spplitedItems))
            .salesmanName(salesmanName)
            .build();
    }

    private List<SalesItem> getSalesItems(final String[] spplitedItems) {
        val listItems = new ArrayList<SalesItem>();
        for (int i = 0; i < spplitedItems.length; i++) {
            val itemsLine = spplitedItems[i].split(ITEM_DETAILS_DELIMITER);

            val entity = SalesItem.builder()
                .itemId(getFieldByPosition(itemsLine, VECTOR_POSITION_ZERO))
                .itemQuantity(getFieldByPosition(itemsLine, VECTOR_POSITION_ONE))
                .itemPrice(Double.parseDouble(getFieldByPosition(itemsLine, VECTOR_POSITION_TWO)))
                .build();

            listItems.add(entity);
        }
        return listItems;
    }
}
