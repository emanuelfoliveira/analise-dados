package br.com.efo.dbc.analisedados.repository;

import br.com.efo.dbc.analisedados.model.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

}
