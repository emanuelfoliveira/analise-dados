package br.com.efo.dbc.analisedados.watcher;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.inputPath;

import br.com.efo.dbc.analisedados.datareader.IDataReader;
import br.com.efo.dbc.analisedados.report.IReportGenerator;
import br.com.efo.dbc.analisedados.utils.DatabaseCleaner;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
class WatcherListener {

    private final static String FILE_EXTENSION = "dat";

    private final WatchService watchService;

    @Autowired
    private IDataReader dataReader;

    @Autowired
    private IReportGenerator reportGenerator;

    @Autowired
    private DatabaseCleaner databaseCleaner;


    @Async
    @PostConstruct
    public void launchMonitoring() {
        try {
            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (val event : key.pollEvents()) {
                    val stringPath = inputPath().toString();
                    log.info("NEW FILE WAS CREATED ON PATH: {}", stringPath);
                    val filename = event.context().toString();
                    if (isFileExtensionDat(filename)) {
                        continue;
                    }
                    
                    process(stringPath, filename);
                }
                key.reset();
            }
        } catch (InterruptedException | IOException e) {
            log.warn("interrupted exception for monitoring service");
        }
    }

    @PreDestroy
    public void stopMonitoring() {
        log.info("STOP_MONITORING");

        if (Objects.nonNull(watchService)) {
            try {
                watchService.close();
            } catch (IOException e) {
                log.error("exception while closing the monitoring service");
            }
        }
    }

    private void process(final String path, final String filename) throws IOException {
        dataReader.execute(new File(String.format("%s/%s", path, filename)));
        reportGenerator.execute(filename);
        databaseCleaner.clean();
    }

    private boolean isFileExtensionDat(final String filename) {
        if (FILE_EXTENSION.equals(Files.getFileExtension(filename))) {
            return Boolean.FALSE;
        }

        log.info("The new file it's not a .dat. No report generated.");
        return Boolean.TRUE;
    }

}
