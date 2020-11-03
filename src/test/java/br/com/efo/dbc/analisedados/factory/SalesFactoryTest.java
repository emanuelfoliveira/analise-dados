package br.com.efo.dbc.analisedados.factory;

import br.com.efo.dbc.analisedados.factory.impl.SalesFactory;
import br.com.efo.dbc.analisedados.model.Sales;
import br.com.efo.dbc.analisedados.model.SalesItem;
import java.util.Arrays;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SalesFactoryTest {

    private final static String DELIMITER = "ç";

    @Test
    public void sales_build_sucess() {
        val line = "003ç10ç[1-10-100]çPedro".split(DELIMITER);
        val factory = new SalesFactory();

        val entity = factory.create(line);

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
            .salesItemEntity(Arrays.asList(item))
            .salesmanName("Pedro")
            .build();
    }

}
