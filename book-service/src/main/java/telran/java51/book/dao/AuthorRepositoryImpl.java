package telran.java51.book.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java51.book.model.Author;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public Optional<Author> findById(String name) {

		return Optional.ofNullable(em.find(Author.class, name));
	}

	
	@Override
	public Author save(Author author) {
		em.persist(author);
		return author;
	}


	@Override
	public void deleteById(String authorName) {
		Author author = em.find(Author.class, authorName);
		if (author != null) {
			em.remove(author);
		}

	}

}
