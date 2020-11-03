package br.com.efo.dbc.analisedados.repository;

import org.assertj.core.api.Assertions;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
public class RepositoryTest {

//    @Autowired
//    private GenericRepository clientRepository;

//    @Autowired
    private SalesItemRepository salesItemRepository;

//    @Test
    void injectedComponentsAreNotNull() {
//        Assertions.assertThat(clientRepository).isNotNull();
        Assertions.assertThat(salesItemRepository).isNotNull();
    }

}
