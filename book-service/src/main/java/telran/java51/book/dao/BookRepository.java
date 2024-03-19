package telran.java51.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import telran.java51.book.model.Author;
import telran.java51.book.model.Book;
import telran.java51.book.model.Publisher;

public interface BookRepository {

	Stream<Book> findBooksByAuthors(Author author);

	Stream<Book> findBooksByPublisher(Publisher publisher);

	void deleteByAuthorsName(String name);

	boolean existsById(String isbn);

	Book save(Book book);

	Optional<Book> findById(String isbn);

//	void delete(Book book);

	void deleteById(String isbn);

}
