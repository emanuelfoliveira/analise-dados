package br.com.efo.dbc.analisedados.handler;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.getFieldByPosition;

import br.com.efo.dbc.analisedados.model.VendorEntity;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class VendorHandler {

    public VendorEntity process(final String[] line) {
        val cpf = getFieldByPosition(line, 1);
        val name = getFieldByPosition(line, 2);
        val salary = getFieldByPosition(line, 3);

        return VendorEntity
            .builder()
            .cpf(cpf)
            .name(name)
            .salary(salary)
            .build();
    }

}
