package br.com.efo.dbc.analisedados.utils;

import br.com.efo.dbc.analisedados.repository.ClientRepository;
import br.com.efo.dbc.analisedados.repository.SalesItemRepository;
import br.com.efo.dbc.analisedados.repository.SalesRepository;
import br.com.efo.dbc.analisedados.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleaner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SalesItemRepository salesItemRepository;

    public void clean() {
        clientRepository.deleteAll();
        vendorRepository.deleteAll();
        salesRepository.deleteAll();
        salesItemRepository.deleteAll();
    }

}
