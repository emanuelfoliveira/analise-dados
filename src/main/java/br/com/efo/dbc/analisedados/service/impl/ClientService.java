package br.com.efo.dbc.analisedados.service.impl;

import br.com.efo.dbc.analisedados.handler.IEntityHandler;
import br.com.efo.dbc.analisedados.repository.ClientRepository;
import br.com.efo.dbc.analisedados.service.IClientService;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ClientService implements IClientService {

    @Autowired
    private IEntityHandler handler;

    @Autowired
    private ClientRepository repository;

    @Override
    @Transactional
    public void execute(final String[] line) {
        repository.save(handler.buildClient(line));
    }

    @Override
    public Long count() {
        return StreamSupport.stream(repository.findAll().spliterator(), Boolean.FALSE).count();
    }
}
