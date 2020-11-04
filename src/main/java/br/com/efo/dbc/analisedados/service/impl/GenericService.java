package br.com.efo.dbc.analisedados.service.impl;

import br.com.efo.dbc.analisedados.model.GenericEntity;
import br.com.efo.dbc.analisedados.repository.IGenericRepository;
import br.com.efo.dbc.analisedados.repository.impl.GenericRepository;
import br.com.efo.dbc.analisedados.service.IGenericService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenericService<T extends GenericEntity> implements IGenericService<T> {

    @Autowired
    private IGenericRepository<T> repository;

    @Override
    public List<T> findAll(final Class<T> clazz) {
        return repository.findAll(clazz);
    }

    @Override
    @Transactional
    public T save(final T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteAll(final Class<T> clazz) {
        repository.deleteAll(clazz);
    }

    @Override
    public int count(final Class<T> clazz) {
        return findAll(clazz).size();
    }


}
