package br.com.efo.dbc.analisedados.service.impl;

import br.com.efo.dbc.analisedados.handler.VendorHandler;
import br.com.efo.dbc.analisedados.repository.VendorRepository;
import br.com.efo.dbc.analisedados.service.IVendorService;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class VendorService implements IVendorService {

    @Autowired
    private VendorHandler handler;

    @Autowired
    private VendorRepository repository;

    @Override
    @Transactional
    public void execute(final String[] line) {
        repository.save(handler.process(line));
    }

    @Override
    public Long count() {
        return StreamSupport.stream(repository.findAll().spliterator(), Boolean.FALSE).count();
    }
}
