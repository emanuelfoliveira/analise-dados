package br.com.efo.dbc.analisedados.parser;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.getFieldByPosition;

import br.com.efo.dbc.analisedados.model.Sales;
import br.com.efo.dbc.analisedados.model.SalesItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.val;

public class SalesParser {

    private final static String DETAILS_DELIMITER = ",";
    private final static String ITEM_DETAILS_DELIMITER = "-";
    private final static String SQUARE_BRACKETS_REGEX = "[\\[\\]]";
    private final static Integer VECTOR_POSITION_ZERO = 0;
    private final static Integer VECTOR_POSITION_ONE = 1;
    private final static Integer VECTOR_POSITION_TWO = 2;
    private final static Integer VECTOR_POSITION_THREE = 3;

    public static Sales parseSales(final String[] line) {
        return Sales
            .builder()
            .saleId(Integer.parseInt(getFieldByPosition(line, VECTOR_POSITION_ONE)))
            .salesItem(getSalesItems(line))
            .salesmanName(getFieldByPosition(line, VECTOR_POSITION_THREE))
            .build();
    }

    private static List<SalesItem> getSalesItems(final String[] line) {
        val items = line[VECTOR_POSITION_TWO].replaceAll(SQUARE_BRACKETS_REGEX, "");
        val spplitedItems = items.split(DETAILS_DELIMITER);

        val listItems = new ArrayList<SalesItem>();
        Arrays.stream(spplitedItems).forEach(item -> {
            val itemsLine = item.split(ITEM_DETAILS_DELIMITER);
            val entity = SalesItem
                .builder()
                .itemId(getFieldByPosition(itemsLine, VECTOR_POSITION_ZERO))
                .itemQuantity(getFieldByPosition(itemsLine, VECTOR_POSITION_ONE))
                .itemPrice(Double.parseDouble(getFieldByPosition(itemsLine, VECTOR_POSITION_TWO)))
                .build();

            listItems.add(entity);
        });

        return listItems;
    }
}
