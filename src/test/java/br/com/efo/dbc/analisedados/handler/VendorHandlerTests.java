package br.com.efo.dbc.analisedados.handler;

import br.com.efo.dbc.analisedados.model.ClientEntity;
import br.com.efo.dbc.analisedados.model.VendorEntity;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class VendorHandlerTests {

    private final static String DELIMITER = "ç";

    @Test
    public void test_handler_sucess() {
        val line = "001ç1234567891234çPedroç50000".split(DELIMITER);
        val handler = new VendorHandler();

        val entity = handler.process(line);

        Assertions.assertEquals(entity, buildEntity());
    }

    private VendorEntity buildEntity() {
        return VendorEntity
            .builder()
            .cpf("1234567891234")
            .name("Pedro")
            .salary("50000")
            .build();
    }
}
