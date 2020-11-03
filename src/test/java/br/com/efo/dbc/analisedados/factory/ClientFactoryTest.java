package br.com.efo.dbc.analisedados.factory;

import br.com.efo.dbc.analisedados.factory.impl.ClientFactory;
import br.com.efo.dbc.analisedados.model.Client;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ClientFactoryTest {

    private final static String DELIMITER = "ç";

    @Test
    public void client_build_sucess() {
        val line = "002ç2345675434544345çJose da SilvaçRural".split(DELIMITER);
        val factory = new ClientFactory();

        val entity = factory.create(line);

        Assertions.assertEquals(entity, buildClient());
    }

    @Test
    public void client_build_error() {
        val line = "002|2345675434544345|Jose da Silva|Rural".split(DELIMITER);
        val factory = new ClientFactory();

        val entity = factory.create(line);

        Assertions.assertNotEquals(entity, buildClient());
        Assertions.assertEquals("", entity.getBusinessArea());
    }

    private Client buildClient() {
        return Client
            .builder()
            .cnpj("2345675434544345")
            .name("Jose da Silva")
            .businessArea("Rural")
            .build();
    }

}
