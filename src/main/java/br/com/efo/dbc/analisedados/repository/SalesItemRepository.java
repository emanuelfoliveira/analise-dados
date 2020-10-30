package br.com.efo.dbc.analisedados.repository;

import br.com.efo.dbc.analisedados.model.SalesItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesItemRepository extends CrudRepository<SalesItemEntity, Long> {

    @Query("SELECT salesEntity.saleId from SalesItemEntity where itemPrice = (SELECT MAX(itemPrice) from SalesItemEntity) ")
    Integer findExpensiveSaleId();

    @Query("SELECT salesEntity.salesmanName from SalesItemEntity where itemPrice = (SELECT MIN(itemPrice) from SalesItemEntity) ")
    String findCheapestVendorName();

}
