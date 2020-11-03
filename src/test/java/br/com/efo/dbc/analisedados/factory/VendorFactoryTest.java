package br.com.efo.dbc.analisedados.factory;

import br.com.efo.dbc.analisedados.factory.impl.VendorFactory;
import br.com.efo.dbc.analisedados.model.Vendor;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class VendorFactoryTest {

    private final static String DELIMITER = "ç";

    @Test
    public void vendor_build_sucess() {
        val line = "001ç1234567891234çPedroç50000".split(DELIMITER);
        val factory = new VendorFactory();

        val entity = factory.create(line);

        Assertions.assertEquals(entity, buildVendor());
    }

    @Test
    public void vendor_build_error() {
        val line = "001|1234567891234|Pedro|50000".split(DELIMITER);
        val factory = new VendorFactory();

        val entity = factory.create(line);

        Assertions.assertNotEquals(entity, buildVendor());
        Assertions.assertEquals("", entity.getName());
    }

    private Vendor buildVendor() {
        return Vendor
            .builder()
            .cpf("1234567891234")
            .name("Pedro")
            .salary("50000")
            .build();
    }


}
