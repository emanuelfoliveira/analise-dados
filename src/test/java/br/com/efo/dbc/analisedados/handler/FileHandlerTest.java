package br.com.efo.dbc.analisedados.handler;

import br.com.efo.dbc.analisedados.handler.impl.FileHandler;
import br.com.efo.dbc.analisedados.model.Client;
import br.com.efo.dbc.analisedados.model.Sales;
import br.com.efo.dbc.analisedados.model.SalesItem;
import br.com.efo.dbc.analisedados.model.Vendor;
import br.com.efo.dbc.analisedados.service.impl.GenericService;
import java.util.Arrays;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FileHandlerTest {

    @InjectMocks
    private FileHandler fileHandler;

    @Mock
    private GenericService<Client> clientGenericService;

    @Mock
    private GenericService<Vendor> vendorGenericService;

    @Mock
    private GenericService<Sales> salesGenericService;


    @Test
    @SneakyThrows
    public void success_client() {
        val list = Arrays.asList("002ç2345675434544345çJose da SilvaçRural");
        val object = buildClient();

        Mockito.doReturn(object).when(clientGenericService).save(object);

        fileHandler.persistByEntityType(list);
        Mockito.verify(clientGenericService, Mockito.times(1)).save(object);
    }

    @Test
    @SneakyThrows
    public void success_sales() {
        val list = Arrays.asList("003ç10ç[1-10-100]çPedro");
        val object = buildSales();

        Mockito.doReturn(object).when(salesGenericService).save(object);

        fileHandler.persistByEntityType(list);
        Mockito.verify(salesGenericService, Mockito.times(1)).save(object);
    }


    @Test
    @SneakyThrows
    public void success_vendor() {
        val list = Arrays.asList("001ç1234567891234çPedroç50000");
        val object = buildVendor();

        Mockito.doReturn(object).when(vendorGenericService).save(object);

        fileHandler.persistByEntityType(list);
        Mockito.verify(vendorGenericService, Mockito.times(1)).save(object);
    }

    private Vendor buildVendor() {
        return Vendor.builder()
            .cpf("1234567891234")
            .name("Pedro")
            .salary("50000")
            .build();
    }

    private Client buildClient() {
        return Client.builder()
            .cnpj("2345675434544345")
            .name("Jose da Silva")
            .businessArea("Rural")
            .build();
    }

    private Sales buildSales() {
        val item = SalesItem.builder()
            .itemId("1")
            .itemQuantity("10")
            .itemPrice(100.0)
            .build();

        return Sales
            .builder()
            .saleId(10)
            .salesItemEntity(Arrays.asList(item))
            .salesmanName("Pedro")
            .build();
    }
}
