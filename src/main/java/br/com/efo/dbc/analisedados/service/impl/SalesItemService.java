package br.com.efo.dbc.analisedados.service.impl;

import br.com.efo.dbc.analisedados.repository.SalesItemRepository;
import br.com.efo.dbc.analisedados.service.ISalesItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesItemService implements ISalesItemService {

    @Autowired
    private SalesItemRepository repository;

    @Override
    public Integer findExpensiveSale() {
        return repository.findExpensiveSaleId();
    }

    @Override
    public String findWorstVendorName() {
        return repository.findWorstVendorName();
    }
}
