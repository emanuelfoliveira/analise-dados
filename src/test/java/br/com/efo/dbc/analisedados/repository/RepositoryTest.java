package br.com.efo.dbc.analisedados.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private SalesItemRepository salesItemRepository;

    @Test
    void injectedComponentsAreNotNull() {
        Assertions.assertThat(clientRepository).isNotNull();
        Assertions.assertThat(salesRepository).isNotNull();
        Assertions.assertThat(vendorRepository).isNotNull();
        Assertions.assertThat(salesItemRepository).isNotNull();
    }

}
