package br.com.efo.dbc.analisedados.controller.impl;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.outputPath;

import br.com.efo.dbc.analisedados.cleaner.IDatabaseCleaner;
import br.com.efo.dbc.analisedados.controller.IController;
import br.com.efo.dbc.analisedados.handler.IFileHandler;
import br.com.efo.dbc.analisedados.reader.IFileReader;
import br.com.efo.dbc.analisedados.report.IReportGenerator;
import java.io.File;
import java.io.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Controller implements IController {

    @Autowired
    private IFileReader fileReader;

    @Autowired
    private IFileHandler fileHandler;

    @Autowired
    private IReportGenerator reportGenerator;

    @Autowired
    private IDatabaseCleaner databaseCleaner;

    @Override
    public void execute(final File file) throws FileNotFoundException {
        try {
            log.info("Data Reader Started");
            val lines = fileReader.read(file);
            log.info("Data Reader Finished");

            log.info("Persist Data Started");
            fileHandler.persist(lines);
            log.info("Data Persisted");

            log.info("Report Generator Started");
            reportGenerator.execute(file);
            log.info("File was successfuly generated on Path {}", outputPath().toString());

            log.info("Cleaning Database Started");
            databaseCleaner.clean();
            log.info("Database Cleaned");
        } catch (Exception ex) {
            log.error("Problem generating the report. Eror in {}", ex.getMessage());
        }
    }

}
