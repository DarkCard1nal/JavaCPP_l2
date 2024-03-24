package v2;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Author extends Human {

	public Author(String firstName, String lastName) {
		super(firstName, lastName);
	}

	public ArrayList<Book> findBooks(Library lib) {
		ArrayList<Book> books = lib.getBooksList();

		return new ArrayList<Book>(books.stream().filter(book -> book.getAuthors().stream()
				.anyMatch(author -> author.equals(this))).collect(Collectors.toList()));
	}

}
