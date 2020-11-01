package br.com.efo.dbc.analisedados.datareader.impl;

import static br.com.efo.dbc.analisedados.model.EntityCodeEnum.getEntityByCode;

import br.com.efo.dbc.analisedados.datareader.IDataReader;
import br.com.efo.dbc.analisedados.service.IClientService;
import br.com.efo.dbc.analisedados.service.ISalesService;
import br.com.efo.dbc.analisedados.service.IVendorService;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class DataReader implements IDataReader {

    private final static String DELIMITER = "ç";
    private final static Integer VECTOR_POSITION_ZERO = 0;

    @Autowired
    private IVendorService vendorService;

    @Autowired
    private IClientService clientService;

    @Autowired
    private ISalesService salesService;

    @Override
    public void execute(final File file) throws FileNotFoundException {
        log.info("Data Reader Started");
        val scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            val optLine = Optional.ofNullable(scanner.nextLine());
            if (optLine.isPresent()) {
                handler(optLine.get());
            }
        }
        log.info("Data Reader Finished");
    }

    private void handler(final String line) {
        val splittedLine = line.split(DELIMITER);
        val entityCode = getEntityByCode(splittedLine[VECTOR_POSITION_ZERO]);

        switch (entityCode) {
            case VENDOR:
                vendorService.execute(splittedLine);
                break;
            case CLIENT:
                clientService.execute(splittedLine);
                break;
            case SALES:
                salesService.execute(splittedLine);
                break;
            default:
                break;
        }
    }

}
