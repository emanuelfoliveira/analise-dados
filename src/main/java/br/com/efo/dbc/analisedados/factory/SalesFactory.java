package br.com.efo.dbc.analisedados.factory;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.getFieldByPosition;

import br.com.efo.dbc.analisedados.model.SalesEntity;
import br.com.efo.dbc.analisedados.model.SalesItemEntity;
import java.util.ArrayList;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class SalesFactory {

    private final static String DETAILS_DELIMITER = ",";
    private final static String ITEM_DETAILS_DELIMITER = "-";

    public SalesEntity process(final String[] line) {
        val saleId = getFieldByPosition(line, 1);
        val items = line[2].replaceAll("[\\[\\]]", "");
        val salesmanName = getFieldByPosition(line, 3);

        val spplitedItems = items.split(DETAILS_DELIMITER);

        val listItems = new ArrayList<SalesItemEntity>();
        for (int i = 0; i < spplitedItems.length; i++) {
            val itemsLine = spplitedItems[i].split(ITEM_DETAILS_DELIMITER);

            val entity = SalesItemEntity.builder()
                .itemId(getFieldByPosition(itemsLine, 0))
                .itemQuantity(getFieldByPosition(itemsLine, 1))
                .itemPrice(Double.parseDouble(getFieldByPosition(itemsLine, 2)))
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


}
