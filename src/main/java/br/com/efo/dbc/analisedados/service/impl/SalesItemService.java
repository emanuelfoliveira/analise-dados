package br.com.efo.dbc.analisedados.service.impl;

import br.com.efo.dbc.analisedados.model.SalesItem;
import br.com.efo.dbc.analisedados.repository.ISalesItemRepository;
import br.com.efo.dbc.analisedados.service.ISalesItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class SalesItemService extends GenericService<SalesItem> implements ISalesItemService {

    @Autowired
    private ISalesItemRepository repository;

    @Override
    public Integer findExpensiveSale() {
        return repository.findExpensiveSaleId();
    }

    @Override
    public String findWorstVendorName() {
        return repository.findWorstVendorName();
    }
}
