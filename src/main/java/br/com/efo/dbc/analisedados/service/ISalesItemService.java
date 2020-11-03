package br.com.efo.dbc.analisedados.service;

import br.com.efo.dbc.analisedados.model.SalesItem;

public interface ISalesItemService extends IGenericService<SalesItem> {

    Integer findExpensiveSale();

    String findWorstVendorName();

}
