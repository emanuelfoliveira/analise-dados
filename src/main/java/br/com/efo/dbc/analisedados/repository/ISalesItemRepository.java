package br.com.efo.dbc.analisedados.repository;

import br.com.efo.dbc.analisedados.model.SalesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalesItemRepository extends JpaRepository<SalesItem, Long> {

    @Query("SELECT DISTINCT sales.saleId FROM SalesItem WHERE itemPrice = (SELECT MAX(itemPrice) FROM SalesItem) ")
    Integer findExpensiveSaleId();

    @Query("SELECT DISTINCT sales.salesmanName FROM SalesItem WHERE itemPrice = (SELECT MIN(itemPrice) FROM SalesItem) ")
    String findWorstVendorName();

}
