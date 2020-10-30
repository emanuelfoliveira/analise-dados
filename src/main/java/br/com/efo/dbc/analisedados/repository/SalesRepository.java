package br.com.efo.dbc.analisedados.repository;

import br.com.efo.dbc.analisedados.model.SalesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends CrudRepository<SalesEntity, Long> {

}
