package telran.java51.book.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.java51.book.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String> {

	@Query("select distinct p.publisherName from Book b join b.authors join b.pusblisher p where a.name=?1")
	Set<String> findByPublishersByAuthor(String authorName);

}
