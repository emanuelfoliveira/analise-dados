package br.com.efo.dbc.analisedados.report.impl;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.outputPath;

import br.com.efo.dbc.analisedados.model.Client;
import br.com.efo.dbc.analisedados.model.Vendor;
import br.com.efo.dbc.analisedados.report.IReportGenerator;
import br.com.efo.dbc.analisedados.service.IGenericService;
import br.com.efo.dbc.analisedados.service.ISalesItemService;
import com.google.common.io.Files;
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
    private final static String MESSAGE_FORMAT_COUNT_WORST_VENDOR = "Pior Vendedor:%s";
    private final static String DATE_PATTERN = "ddMMyyyyHHmmss";
    private final static String FILE_NAME_FORMAT = "/%s_%s.done.dat";

    @Autowired
    private IGenericService<Vendor> vendorGenericService;

    @Autowired
    private IGenericService<Client> clientGenericService;

    @Autowired
    private ISalesItemService salesItemService;

    @Override
    public void execute(final File fileName) throws IOException {
        val flatFileName = getFlatFilename(fileName.getName());
        writeOutputFile(flatFileName);
    }

    private void writeOutputFile(final String flatFileName) throws IOException {
        val outputFile = new FileWriter(new File(outputPath().toString().concat(flatFileName)));
        outputFile.write(buildReportContent());
        outputFile.close();
    }

    private String buildReportContent() {
        val content = new StringBuilder();
        content.append(String.format(MESSAGE_FORMAT_COUNT_SALE_ID, clientGenericService.count(Client.class)));
        content.append(String.format(MESSAGE_FORMAT_COUNT_VENDOR, vendorGenericService.count(Vendor.class)));
        content.append(String.format(MESSAGE_FORMAT_COUNT_EXPENSIVE_SALE, salesItemService.findExpensiveSale()));
        content.append(String.format(MESSAGE_FORMAT_COUNT_WORST_VENDOR, salesItemService.findWorstVendorName()));

        return content.toString();
    }

    private String getFlatFilename(final String fileName) {
        val nameWithoutExtension = Files.getNameWithoutExtension(fileName);
        val actualDate = new SimpleDateFormat(DATE_PATTERN).format(new Date());
        return String.format(FILE_NAME_FORMAT, nameWithoutExtension, actualDate);
    }
}
