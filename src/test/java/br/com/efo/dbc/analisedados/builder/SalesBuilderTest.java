package br.com.efo.dbc.analisedados.builder;

import static br.com.efo.dbc.analisedados.builder.SalesBuilder.buildSales;

import br.com.efo.dbc.analisedados.model.Sales;
import br.com.efo.dbc.analisedados.model.SalesItem;
import java.util.Arrays;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SalesBuilderTest {

    private final static String DELIMITER = "ç";

    @Test
    public void sales_build_sucess() {
        val line = "003ç10ç[1-10-100]çPedro".split(DELIMITER);
        val entity = buildSales(line);

        Assertions.assertEquals(entity, build());
    }

    private Sales build() {
        val item = SalesItem.builder()
            .itemId("1")
            .itemQuantity("10")
            .itemPrice(100.0)
            .build();

        return Sales
            .builder()
            .saleId(10)
            .salesItem(Arrays.asList(item))
            .salesmanName("Pedro")
            .build();
    }

}
