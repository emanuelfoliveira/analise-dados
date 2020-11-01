package br.com.efo.dbc.analisedados.handler;

import br.com.efo.dbc.analisedados.handler.impl.EntityHandler;
import br.com.efo.dbc.analisedados.model.ClientEntity;
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
public class EntityHandlerTests {

    private final static String DELIMITER = "ç";

    @Test
    public void test_sales_handler_sucess() {
        val line = "003ç10ç[1-10-100]çPedro".split(DELIMITER);
        val handler = new EntityHandler();

        val entity = handler.buildSales(line);

        val item = SalesItemEntity.builder()
            .itemId("1")
            .itemQuantity("10")
            .itemPrice(100.0)
            .build();

        val sales = SalesEntity
            .builder()
            .saleId(10)
            .salesItemEntity(Arrays.asList(item))
            .salesmanName("Pedro")
            .build();

        Assertions.assertEquals(entity, sales);
    }


    @Test
    public void test_client_handler_sucess() {
        val line = "002ç2345675434544345çJose da SilvaçRural".split(DELIMITER);
        val handler = new EntityHandler();

        val entity = handler.buildClient(line);

        val client = ClientEntity
            .builder()
            .cnpj("2345675434544345")
            .name("Jose da Silva")
            .businessArea("Rural")
            .build();

        Assertions.assertEquals(entity, client);
    }

    @Test
    public void test_vendor_handler_sucess() {
        val line = "001ç1234567891234çPedroç50000".split(DELIMITER);
        val handler = new EntityHandler();

        val entity = handler.buildVendor(line);

        val vendor = VendorEntity
            .builder()
            .cpf("1234567891234")
            .name("Pedro")
            .salary("50000")
            .build();

        Assertions.assertEquals(entity, vendor);
    }

}
