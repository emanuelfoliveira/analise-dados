package br.com.efo.dbc.analisedados.report.impl;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.outputPath;
import static br.com.efo.dbc.analisedados.utils.FileWriterUtils.write;
import static br.com.efo.dbc.analisedados.utils.FileWriterUtils.buildFlatFilename;

import br.com.efo.dbc.analisedados.model.Client;
import br.com.efo.dbc.analisedados.model.Vendor;
import br.com.efo.dbc.analisedados.report.IReportGenerator;
import br.com.efo.dbc.analisedados.service.IGenericService;
import br.com.efo.dbc.analisedados.service.ISalesItemService;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReportGenerator implements IReportGenerator {

    private final static String MESSAGE_FORMAT_COUNT_SALE_ID = "Quantidade de clientes no arquivo de entrada:%s \n";
    private final static String MESSAGE_FORMAT_COUNT_VENDOR = "Quantidade de vendedor no arquivo de entrada:%s \n";
    private final static String MESSAGE_FORMAT_COUNT_EXPENSIVE_SALE = "ID da venda mais cara:%s \n";
    private final static String MESSAGE_FORMAT_COUNT_WORST_VENDOR = "Pior Vendedor:%s";
    private final static String FULL_PATH_FORMAT = "%s%s";

    @Autowired
    private IGenericService<Vendor> vendorGenericService;

    @Autowired
    private IGenericService<Client> clientGenericService;

    @Autowired
    private ISalesItemService salesItemService;

    @Override
    public void execute(final File filename) throws IOException {
        val filePath = String.format(FULL_PATH_FORMAT, outputPath().toString(), buildFlatFilename(filename.getName()));

        write(new File(filePath), buildReportContent());
    }

    private String buildReportContent() {
        return new StringBuilder()
            .append(String.format(MESSAGE_FORMAT_COUNT_SALE_ID, clientGenericService.count(Client.class)))
            .append(String.format(MESSAGE_FORMAT_COUNT_VENDOR, vendorGenericService.count(Vendor.class)))
            .append(String.format(MESSAGE_FORMAT_COUNT_EXPENSIVE_SALE, salesItemService.findExpensiveSale()))
            .append(String.format(MESSAGE_FORMAT_COUNT_WORST_VENDOR, salesItemService.findWorstVendorName()))
            .toString();
    }


}
