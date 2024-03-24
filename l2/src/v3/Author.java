package v3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Author extends Human implements Externalizable {

	public Author(String firstName, String lastName) {
		super(firstName, lastName);
	}

	public ArrayList<Book> findBooks(Library lib) {
		ArrayList<Book> books = lib.getBooksList();

		return new ArrayList<Book>(books.stream().filter(book -> book.getAuthors().stream()
				.anyMatch(author -> author.equals(this))).collect(Collectors.toList()));
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}
}
