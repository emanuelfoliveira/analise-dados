package br.com.efo.dbc.analisedados.config;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.inputPath;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FileWatcherConfig {

    @Bean
    public WatchService watchService() {
        val path = inputPath();
        log.info("MONITORING_FOLDER: {}", path);
        WatchService watchService = null;
        try {
            watchService = FileSystems.getDefault().newWatchService();

            if (!Files.isDirectory(path)) {
                throw new RuntimeException("incorrect monitoring folder: " + path);
            }
            path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE
            );
        } catch (IOException e) {
            log.error("exception for watch service creation:", e);
        }

        return watchService;
    }
}
