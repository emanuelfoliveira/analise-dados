package br.com.efo.dbc.analisedados.parser;

import static br.com.efo.dbc.analisedados.parser.SalesParser.parseSales;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.efo.dbc.analisedados.model.Sales;
import br.com.efo.dbc.analisedados.model.SalesItem;
import java.util.Arrays;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SalesParserTest {

    private final static String DELIMITER = "ç";

    @Test
    public void sales_sucess() {
        val line = "003ç10ç[1-10-100]çPedro".split(DELIMITER);
        val entity = parseSales(line);

        assertEquals(entity, build());
    }

    private Sales build() {
        val item = SalesItem
            .builder()
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
