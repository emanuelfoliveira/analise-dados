package br.com.efo.dbc.analisedados.handler;

import br.com.efo.dbc.analisedados.model.ClientEntity;
import br.com.efo.dbc.analisedados.model.SalesEntity;
import br.com.efo.dbc.analisedados.model.VendorEntity;

public interface IEntityHandler {

    ClientEntity buildClient(final String[] line);

    VendorEntity buildVendor(final String[] line);

    SalesEntity buildSales(final String[] line);
}
