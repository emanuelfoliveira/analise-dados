package br.com.efo.dbc.analisedados.parser;

import static br.com.efo.dbc.analisedados.parser.VendorParser.parseVendor;

import br.com.efo.dbc.analisedados.model.Vendor;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class VendorParserTest {

    private final static String DELIMITER = "ç";

    @Test
    public void vendor_sucess() {
        val line = "001ç1234567891234çPedroç50000".split(DELIMITER);
        val entity = parseVendor(line);

        Assertions.assertEquals(entity, build());
    }

    @Test
    public void vendor_error() {
        val line = "001|1234567891234|Pedro|50000".split(DELIMITER);
        val entity = parseVendor(line);

        Assertions.assertNotEquals(entity, build());
        Assertions.assertEquals("", entity.getName());
    }

    private Vendor build() {
        return Vendor
            .builder()
            .cpf("1234567891234")
            .name("Pedro")
            .salary("50000")
            .build();
    }


}
