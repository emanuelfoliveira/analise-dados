package br.com.efo.dbc.analisedados.builder;

import static br.com.efo.dbc.analisedados.builder.ClientBuilder.buildClient;

import br.com.efo.dbc.analisedados.model.Client;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ClientBuilderTest {

    private final static String DELIMITER = "ç";

    @Test
    public void client_build_sucess() {
        val line = "002ç2345675434544345çJose da SilvaçRural".split(DELIMITER);
        val entity = buildClient(line);

        Assertions.assertEquals(entity, buildExample());
    }

    @Test
    public void client_build_error() {
        val line = "002|2345675434544345|Jose da Silva|Rural".split(DELIMITER);
        val entity = buildClient(line);

        Assertions.assertNotEquals(entity, buildExample());
        Assertions.assertEquals("", entity.getBusinessArea());
    }

    private Client buildExample() {
        return Client
            .builder()
            .cnpj("2345675434544345")
            .name("Jose da Silva")
            .businessArea("Rural")
            .build();
    }

}
