package br.com.efo.dbc.analisedados.service.impl;

import br.com.efo.dbc.analisedados.model.BaseEntity;
import br.com.efo.dbc.analisedados.repository.CommonRepository;
import br.com.efo.dbc.analisedados.service.IGenericService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenericService<T extends BaseEntity> implements IGenericService<T> {

    @Autowired
    private CommonRepository<T> repository;

    @Override
    public List<T> findAll(final Class<T> clazz) {
        return repository.findAll(clazz);
    }

    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteAll(final Class<T> clazz) {
        repository.deleteAll(clazz);
    }

    @Override
    public int count(Class<T> clazz) {
        return findAll(clazz).size();
    }


}
