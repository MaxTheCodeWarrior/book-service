package telran.java51.book.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java51.book.model.Book;
import telran.java51.book.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public Set<String> findByPublishersAuthor(String authorName) {
		Set<String> publishers = new HashSet<>();
		List<Book> books = em
				.createQuery("SELECT b FROM Book b JOIN b.authors a WHERE a.name = :authorName", Book.class)
				.setParameter("authorName", authorName).getResultList();

		for (Book book : books) {
			publishers.add(book.getPublisher().getPublisherName());
		}

		return publishers;
	}

	public Stream<Publisher> findDistinctByBooksAuthorsName(String authorName) {
		 return em.createQuery(
		            "SELECT DISTINCT b.publisher FROM Book b JOIN b.authors a WHERE a.name = :authorName", 
		            Publisher.class
		        )
		        .setParameter("authorName", authorName)
		        .getResultStream();
		    }

	@Override
	public Optional<Publisher> findById(String publisher) {

		return Optional.ofNullable(em.find(Publisher.class, publisher));

	}

	@Override
	public Publisher save(Publisher publisher) {
		em.persist(publisher);
		return publisher;
	}

}
