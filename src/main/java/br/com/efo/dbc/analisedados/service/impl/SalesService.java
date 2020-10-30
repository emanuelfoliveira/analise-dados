package br.com.efo.dbc.analisedados.service.impl;

import br.com.efo.dbc.analisedados.handler.SalesHandler;
import br.com.efo.dbc.analisedados.repository.SalesRepository;
import br.com.efo.dbc.analisedados.service.ISalesService;
import javax.transaction.Transactional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesService implements ISalesService {

    @Autowired
    private SalesHandler handler;

    @Autowired
    private SalesRepository repository;

    @Override
    @Transactional
    public void execute(final String[] line) {
        val entity = handler.process(line);
        repository.save(entity);
    }
}
