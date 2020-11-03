package br.com.efo.dbc.analisedados.service;

import br.com.efo.dbc.analisedados.model.GenericEntity;
import java.util.List;

public interface IGenericService<T extends GenericEntity> {

    List<T> findAll(final Class<T> clazz);

    T save(final T entity);

    void deleteAll(final Class<T> clazz);

    int count(final Class<T> clazz);

}
