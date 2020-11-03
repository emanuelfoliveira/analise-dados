package br.com.efo.dbc.analisedados.factory;

import br.com.efo.dbc.analisedados.model.BaseEntity;

public interface IEntityFactory<E> {

    BaseEntity create(final String[] line);
}
