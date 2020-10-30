package br.com.efo.dbc.analisedados.handler;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.getFieldByPosition;

import br.com.efo.dbc.analisedados.model.ClientEntity;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class ClientHandler {

    public ClientEntity process(final String[] line) {
        val cnpj = getFieldByPosition(line, 1);
        val name = getFieldByPosition(line, 2);
        val businessArea = getFieldByPosition(line, 3);

        return ClientEntity
            .builder()
            .cnpj(cnpj)
            .name(name)
            .businessArea(businessArea)
            .build();
    }
}
