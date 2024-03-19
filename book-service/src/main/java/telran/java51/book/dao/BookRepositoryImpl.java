package telran.java51.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import telran.java51.book.model.Author;
import telran.java51.book.model.Book;
import telran.java51.book.model.Publisher;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public Stream<Book> findBooksByAuthors(Author author) {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE :author MEMBER OF b.authors", Book.class);
		query.setParameter("author", author);
		return query.getResultStream();
	}

	@Override
	public Stream<Book> findBooksByPublisher(Publisher publisher) {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE :publisher = b.publisher", Book.class);
		query.setParameter("publisher", publisher);
		return query.getResultStream();
	}

	@Override
	public void deleteByAuthorsName(String name) {
		Author author = em.find(Author.class, name);
		if (author != null) {
			em.createQuery("DELETE FROM Book b WHERE :author MEMBER OF b.authors").setParameter("author", author)
					.executeUpdate();
		}
	}

	@Override
	public boolean existsById(String isbn) {

		return em.find(Book.class, isbn) != null;
	}

	@Override
	public Book save(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public Optional<Book> findById(String isbn) {
		return Optional.ofNullable(em.find(Book.class, isbn));
	}

	@Override
	public void delete(Book book) {
		em.remove(book);

	}

}
