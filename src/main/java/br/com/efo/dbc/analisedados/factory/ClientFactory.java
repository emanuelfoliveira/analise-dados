package br.com.efo.dbc.analisedados.factory;

import br.com.efo.dbc.analisedados.model.ClientEntity;
import br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory {

    public ClientEntity process(final String[] line) {
        val cnpj = AnaliseDadosUtils.getFieldByPosition(line, 1);
        val name = AnaliseDadosUtils.getFieldByPosition(line, 2);
        val businessArea = AnaliseDadosUtils.getFieldByPosition(line, 3);

        return ClientEntity
            .builder()
            .cnpj(cnpj)
            .name(name)
            .businessArea(businessArea)
            .build();
    }
}
