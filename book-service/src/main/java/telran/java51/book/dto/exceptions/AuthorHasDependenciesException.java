package telran.java51.book.dto.exceptions;

public class AuthorHasDependenciesException extends RuntimeException {

	private static final long serialVersionUID = 8917624524092488737L;

	String message = "N/A to remove because of dependencies, brake relations first";

	public String getMessage() {
		return message;
	}

}
