package br.com.efo.dbc.analisedados.utils;

import br.com.efo.dbc.analisedados.model.Client;
import br.com.efo.dbc.analisedados.model.Sales;
import br.com.efo.dbc.analisedados.model.SalesItem;
import br.com.efo.dbc.analisedados.model.Vendor;
import br.com.efo.dbc.analisedados.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleaner {

    @Autowired
    private IGenericService<Vendor> vendorGenericService;

    @Autowired
    private IGenericService<Client> clientGenericService;

    @Autowired
    private IGenericService<Sales> salesGenericService;

    @Autowired
    private IGenericService<SalesItem> salesItemGenericService;

    public void clean() throws Exception {
        try {
            vendorGenericService.deleteAll(Vendor.class);
            clientGenericService.deleteAll(Client.class);
            salesGenericService.deleteAll(Sales.class);
            salesItemGenericService.deleteAll(SalesItem.class);
        } catch (Exception ex) {
            throw new Exception("Problem to clean database.");
        }
    }

}
