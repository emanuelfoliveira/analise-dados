package br.com.efo.dbc.analisedados.parser;

import static br.com.efo.dbc.analisedados.parser.ClientParser.parseClient;

import br.com.efo.dbc.analisedados.model.Client;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ClientParserTest {

    private final static String DELIMITER = "ç";

    @Test
    public void client_sucess() {
        val line = "002ç2345675434544345çJose da SilvaçRural".split(DELIMITER);
        val entity = parseClient(line);

        Assertions.assertEquals(entity, build());
    }

    @Test
    public void client_error() {
        val line = "002|2345675434544345|Jose da Silva|Rural".split(DELIMITER);
        val entity = parseClient(line);

        Assertions.assertNotEquals(entity, build());
        Assertions.assertEquals("", entity.getBusinessArea());
    }

    private Client build() {
        return Client
            .builder()
            .cnpj("2345675434544345")
            .name("Jose da Silva")
            .businessArea("Rural")
            .build();
    }

}
