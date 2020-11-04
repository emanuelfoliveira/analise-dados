package br.com.efo.dbc.analisedados.repository;

import br.com.efo.dbc.analisedados.model.GenericEntity;
import java.util.List;

public interface IGenericRepository<T extends GenericEntity> {

    T save(final T obj);

    List<T> findAll(final Class<T> clazz);

    void deleteAll(final Class<T> clazz);

}
