package br.com.efo.dbc.analisedados.handler.impl;

import static br.com.efo.dbc.analisedados.model.EntityCodeEnum.getEntityByCode;

import static br.com.efo.dbc.analisedados.builder.ClientBuilder.buildClient;
import static br.com.efo.dbc.analisedados.builder.SalesBuilder.buildSales;
import static br.com.efo.dbc.analisedados.builder.VendorBuilder.buildVendor;
import br.com.efo.dbc.analisedados.handler.IFileHandler;
import br.com.efo.dbc.analisedados.model.Client;
import br.com.efo.dbc.analisedados.model.Sales;
import br.com.efo.dbc.analisedados.model.Vendor;
import br.com.efo.dbc.analisedados.service.IGenericService;
import java.security.InvalidParameterException;
import java.util.List;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileHandler implements IFileHandler {

    private final static String DELIMITER = "ç";
    private final static Integer VECTOR_POSITION_ZERO = 0;

    @Autowired
    private IGenericService<Vendor> vendorGenericService;

    @Autowired
    private IGenericService<Client> clientGenericService;

    @Autowired
    private IGenericService<Sales> salesGenericService;

    @Override
    public void persist(final List<String> lines) throws Exception {
        try {
            lines.forEach(line -> {
                val splittedLine = line.split(DELIMITER);
                val entityCode = getEntityByCode(splittedLine[VECTOR_POSITION_ZERO]);

                switch (entityCode) {
                    case VENDOR:
                        vendorGenericService.save(buildVendor(splittedLine));
                        break;
                    case CLIENT:
                        clientGenericService.save(buildClient(splittedLine));
                        break;
                    case SALES:
                        salesGenericService.save(buildSales(splittedLine));
                        break;
                    default:
                        throw new InvalidParameterException("Invalid Parameter");
                }
            });
        } catch (Exception ex) {
            throw new Exception("Problem during data persist");
        }
    }


}
