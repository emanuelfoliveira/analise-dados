package br.com.efo.dbc.analisedados.handler;

import br.com.efo.dbc.analisedados.model.ClientEntity;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ClientHandlerTests {

    private final static String DELIMITER = "ç";

    @Test
    public void test_handler_sucess() {
        val line = "002ç2345675434544345çJose da SilvaçRural".split(DELIMITER);
        val handler = new ClientHandler();

        val entity = handler.process(line);

        Assertions.assertEquals(entity, buildEntity());
    }

    private ClientEntity buildEntity() {
        return ClientEntity
            .builder()
            .cnpj("2345675434544345")
            .name("Jose da Silva")
            .businessArea("Rural")
            .build();
    }
}
