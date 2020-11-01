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

    @Override
    public ClientEntity buildClient(final String[] line) {
        val cnpj = getFieldByPosition(line, 1);
        val name = getFieldByPosition(line, 2);
        val businessArea = getFieldByPosition(line, 3);

        return ClientEntity
            .builder()
            .cnpj(cnpj)
            .name(name)
            .businessArea(businessArea)
            .build();
    }

    @Override
    public SalesEntity buildSales(final String[] line) {
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

    @Override
    public VendorEntity buildVendor(final String[] line) {
        val cpf = getFieldByPosition(line, 1);
        val name = getFieldByPosition(line, 2);
        val salary = getFieldByPosition(line, 3);

        return VendorEntity
            .builder()
            .cpf(cpf)
            .name(name)
            .salary(salary)
            .build();
    }
}
