package br.com.efo.dbc.analisedados.builder;

import static br.com.efo.dbc.analisedados.builder.VendorBuilder.buildVendor;

import br.com.efo.dbc.analisedados.model.Vendor;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class VendorBuilderTest {

    private final static String DELIMITER = "ç";

    @Test
    public void vendor_build_sucess() {
        val line = "001ç1234567891234çPedroç50000".split(DELIMITER);
        val entity = buildVendor(line);

        Assertions.assertEquals(entity, buildExample());
    }

    @Test
    public void vendor_build_error() {
        val line = "001|1234567891234|Pedro|50000".split(DELIMITER);
        val entity = buildVendor(line);

        Assertions.assertNotEquals(entity, buildExample());
        Assertions.assertEquals("", entity.getName());
    }

    private Vendor buildExample() {
        return Vendor
            .builder()
            .cpf("1234567891234")
            .name("Pedro")
            .salary("50000")
            .build();
    }


}
