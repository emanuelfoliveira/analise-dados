package br.com.efo.dbc.analisedados.report.impl;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.outputPath;

import br.com.efo.dbc.analisedados.report.IReportGenerator;
import br.com.efo.dbc.analisedados.service.IClientService;
import br.com.efo.dbc.analisedados.service.ISalesItemService;
import br.com.efo.dbc.analisedados.service.IVendorService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private final static String MESSAGE_FORMAT_COUNT_CHEAPEST_VENDOR = "Pior Vendedor:%s";
    private final static String DATE_PATTERN = "ddMMyyyyHHmmss";
    private final static String FILE_NAME_FORMAT = "/%s_%s.done.dat";

    @Autowired
    private IClientService clientService;

    @Autowired
    private IVendorService vendorService;

    @Autowired
    private ISalesItemService salesItemService;

    @Override
    public void execute(final String fileName) throws IOException {
        log.info("Report Generator Started");

        val countClient = clientService.count();
        val countVendor = vendorService.count();
        val expensiveSaleId = salesItemService.findExpensiveSale();
        val cheapestVendorName = salesItemService.findCheapestVendorName();
        val flatFileName = String.format(FILE_NAME_FORMAT, fileName,new SimpleDateFormat(DATE_PATTERN).format(new Date()));

        val outputFile = new FileWriter(new File(outputPath().toString().concat(flatFileName)));
        outputFile.write(buildReportContent(countClient, countVendor, expensiveSaleId, cheapestVendorName));
        outputFile.close();

        log.info("Report Generator Finished. File {} was generated on Path {}", flatFileName, outputPath().toString());
    }

    private String buildReportContent(final Long countClient, final Long countVendor,
        final Integer expensiveSaleId,
        final String cheapestVendorName) {

        val contentStringBuilder = new StringBuilder();
        contentStringBuilder.append(String.format(MESSAGE_FORMAT_COUNT_SALE_ID, countClient));
        contentStringBuilder.append(String.format(MESSAGE_FORMAT_COUNT_VENDOR, countVendor));
        contentStringBuilder.append(String.format(MESSAGE_FORMAT_COUNT_EXPENSIVE_SALE, expensiveSaleId));
        contentStringBuilder.append(String.format(MESSAGE_FORMAT_COUNT_CHEAPEST_VENDOR, cheapestVendorName));

        return contentStringBuilder.toString();
    }
}
