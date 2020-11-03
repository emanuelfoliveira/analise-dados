package br.com.efo.dbc.analisedados.factory;

import br.com.efo.dbc.analisedados.model.GenericEntity;

public interface IEntityFactory<E> {

    GenericEntity create(final String[] line);
}
