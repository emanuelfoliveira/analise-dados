package br.com.efo.dbc.analisedados.handler.impl;

import static br.com.efo.dbc.analisedados.model.EntityCodeEnum.getEntityByCode;

import br.com.efo.dbc.analisedados.factory.impl.ClientFactory;
import br.com.efo.dbc.analisedados.factory.impl.SalesFactory;
import br.com.efo.dbc.analisedados.factory.impl.VendorFactory;
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
                        vendorGenericService.save(new VendorFactory().create(splittedLine));
                        break;
                    case CLIENT:
                        clientGenericService.save(new ClientFactory().create(splittedLine));
                        break;
                    case SALES:
                        salesGenericService.save(new SalesFactory().create(splittedLine));
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
