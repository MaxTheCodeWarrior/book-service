package telran.java51.book.dao;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java51.book.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public Set<String> findByPublishersAuthor(String authorName) {

		return new HashSet<>(em.createQuery(
				"select distinct b.publisher.pusblisherName from Book b join authors a where a.name=:authorName",
				String.class).setParameter("authorName", authorName).getResultList());

	}

	public Stream<Publisher> findDistinctByBooksAuthorsName(String authorName) {
		return em.createQuery("SELECT DISTINCT b.publisher FROM Book b JOIN b.authors a WHERE a.name = :authorName",
				Publisher.class).setParameter("authorName", authorName).getResultStream();
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
