package br.com.efo.dbc.analisedados.handler;

import br.com.efo.dbc.analisedados.model.SalesEntity;
import br.com.efo.dbc.analisedados.model.SalesItemEntity;
import br.com.efo.dbc.analisedados.model.VendorEntity;
import java.util.Arrays;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SalesHandlerTests {

    private final static String DELIMITER = "ç";

    @Test
    public void test_handler_sucess() {
        val line = "003ç10ç[1-10-100]çPedro".split(DELIMITER);
        val handler = new SalesHandler();

        val entity = handler.process(line);

        Assertions.assertEquals(entity, buildEntity());
    }

    private SalesEntity buildEntity() {
        val item = SalesItemEntity.builder()
            .itemId("1")
            .itemQuantity("10")
            .itemPrice(100.0)
            .build();

        return SalesEntity
            .builder()
            .saleId(10)
            .salesItemEntity(Arrays.asList(item))
            .salesmanName("Pedro")
            .build();
    }
}
