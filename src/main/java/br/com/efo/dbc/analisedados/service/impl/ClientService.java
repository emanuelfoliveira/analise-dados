package br.com.efo.dbc.analisedados.service.impl;

import br.com.efo.dbc.analisedados.factory.ClientFactory;
import br.com.efo.dbc.analisedados.repository.ClientRepository;
import br.com.efo.dbc.analisedados.service.IClientService;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientFactory factory;

    @Autowired
    private ClientRepository repository;

    @Override
    @Transactional
    public void execute(final String[] line) {
        val entity = factory.process(line);
        repository.save(entity);
    }

    @Override
    public Long count() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).count();
    }
}
