package br.com.efo.dbc.analisedados.reader;

import static br.com.efo.dbc.analisedados.utils.AnaliseDadosUtils.outputPath;

import br.com.efo.dbc.analisedados.reader.impl.FileReader;
import java.io.File;
import java.io.FileWriter;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class FileReaderTest {

    private static final String FILE_NAME = "/teste.dat";

    @BeforeAll
    @SneakyThrows
    private static void before() {
        val outputFile = new FileWriter(new File(outputPath().toString().concat(FILE_NAME)));
        outputFile.write(content());
        outputFile.close();
    }

    @AfterAll
    @SneakyThrows
    private static void after() {
        val file = new File(outputPath().toString().concat(FILE_NAME));
        file.delete();
    }

    @Test
    @SneakyThrows
    public void success() {
        val fileReader = new FileReader();

        val list = fileReader.read(new File(outputPath().toString().concat(FILE_NAME)));

        Assertions.assertEquals("001ç1234567891234çPedroç50000", list.get(0));
        Assertions.assertEquals(2, list.size());
    }


    private static String content() {
        val sb = new StringBuilder();
        sb.append("001ç1234567891234çPedroç50000\n");
        sb.append("002ç2345675434544345çJose da SilvaçRural");
        return sb.toString();
    }

}
