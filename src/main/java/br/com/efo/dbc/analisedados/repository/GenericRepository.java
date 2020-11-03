package br.com.efo.dbc.analisedados.repository;

import br.com.efo.dbc.analisedados.model.GenericEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.val;
import org.springframework.stereotype.Repository;

@Repository
public class GenericRepository<T extends GenericEntity> {

    private final static String STR_FORMAT_FIND_ALL_QUERY = "select o from %s o";

    @PersistenceContext
    EntityManager em;

    public T save(final T obj) {
        return em.merge(obj);
    }

    public List<T> findAll(final Class<T> clazz) {
        val query = em.createQuery(String.format(STR_FORMAT_FIND_ALL_QUERY, clazz.getSimpleName()));
        return query.getResultList();
    }

    public void deleteAll(final Class<T> clazz) {
        val all = findAll(clazz);
        all.forEach(item -> {
            em.remove(item);
        });
    }

}
