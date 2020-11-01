package br.com.efo.dbc.analisedados.repository;

import br.com.efo.dbc.analisedados.model.SalesItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesItemRepository extends CrudRepository<SalesItemEntity, Long> {

    @Query("SELECT DISTINCT salesEntity.saleId FROM SalesItemEntity WHERE itemPrice = (SELECT MAX(itemPrice) FROM SalesItemEntity) ")
    Integer findExpensiveSaleId();

    @Query("SELECT DISTINCT salesEntity.salesmanName FROM SalesItemEntity WHERE itemPrice = (SELECT MIN(itemPrice) FROM SalesItemEntity) ")
    String findCheapestVendorName();

}
