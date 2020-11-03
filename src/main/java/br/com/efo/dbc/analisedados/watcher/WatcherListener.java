package br.com.efo.dbc.analisedados.watcher;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.inputPath;

import br.com.efo.dbc.analisedados.controller.IController;
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
public class WatcherListener {

    private final static String FILE_EXTENSION = "dat";

    private final WatchService watchService;

    @Autowired
    private IController iController;


    @Async
    @PostConstruct
    public void launchMonitoring() {
        try {
            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (val event : key.pollEvents()) {
                    val stringPath = inputPath().toString();
                    val filename = event.context().toString();
                    log.info("THE FILE {} WAS CREATED ON PATH: {}", filename, stringPath);
                    if (isFileExtensionDat(filename)) {
                        continue;
                    }

                    iController.execute(new File(String.format("%s/%s", stringPath, filename)));
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

    private boolean isFileExtensionDat(final String filename) {
        if (FILE_EXTENSION.equals(Files.getFileExtension(filename))) {
            return Boolean.FALSE;
        }

        log.info("The new file it's not a .dat. No report generated.");
        return Boolean.TRUE;
    }

}
