package br.com.efo.dbc.analisedados.repository;

import br.com.efo.dbc.analisedados.model.VendorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends CrudRepository<VendorEntity, Long> {

}
