package org.olivianeacsu;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;


public class BookService {
    @Inject
    EntityManager em;

    @Transactional(Transactional.TxType.SUPPORTS)
    // SUPPORTS allows this to inherit transactional type from the caller of this method
    public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Book> findAll() {
        TypedQuery<Book> query =
                em.createQuery("SELECT b FROM Book b ORDER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Book create(Book book) {
        em.persist(book);
        return book;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id) {
        em.remove(em.getReference(Book.class, id));
    }
}
