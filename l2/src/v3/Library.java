package v3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library implements Externalizable {

	private String _name;
	private ArrayList<Book> _booksList;
	private ArrayList<BookReader> _bookReadersList;
	private transient ArrayList<Author> _authorsList;

	public Library(String name) {
		setName(name);
		this._booksList = new ArrayList<>();
		this._bookReadersList = new ArrayList<>();
		this._authorsList = new ArrayList<>();
	}

	public Library(String name, ArrayList<Book> booksList, ArrayList<BookReader> bookReadersList) {
		setName(name);
		this._booksList = booksList;
		this._bookReadersList = bookReadersList;
		refreshAuthorsList();
	}

	public void setName(String name) {
		this._name = name.trim();
	}

	public String getName() {
		return this._name;
	}

	public ArrayList<Book> getBooksList() {
		return _booksList;
	}

	public ArrayList<BookReader> getBookReadersList() {
		return _bookReadersList;
	}

	public ArrayList<Author> getAuthorsList() {
		refreshAuthorsList();
		return _authorsList;
	}

	public void addBookReader(BookReader bookReader) {
		this._bookReadersList.add(bookReader);
	}

	public void addBookReader(String firstName, String lastName, int id) {
		addBookReader(new BookReader(firstName, lastName, id));
	}

	public void addBook(Book book) {
		this._booksList.add(book);
		refreshAuthorsList();
	}

	public void addBook(String title, ArrayList<Author> authors, int year, int edition) {
		addBook(new Book(title, authors, year, edition));
	}

	public Author findAuthor(String firstName, String lastName) {
		refreshAuthorsList();
		return findAuthorNoRefresh(firstName, lastName);
	}

	public BookReader findBookReader(int id) {
		Optional<BookReader> foundBookReader = _bookReadersList.stream().filter(bookReader -> bookReader.getId() == id)
				.findFirst();

		if (foundBookReader.isPresent())
			return foundBookReader.get();

		return null;
	}

	public boolean borrowBook(BookReader bookReader, Book book) {
		if (!this._bookReadersList.contains(bookReader) || !this._booksList.contains(book))
			return false;

		this._booksList.remove(book);
		bookReader.addReceiveBook(book);
		refreshAuthorsList();

		return true;
	}

	public boolean returnBorrowBook(BookReader bookReader, Book book) {
		if (!this._bookReadersList.contains(bookReader) || !bookReader.getReceiveBooks().contains(book))
			return false;

		bookReader.delReceiveBook(book);
		addBook(book);

		return true;
	}

	@Override
	public String toString() {
		refreshAuthorsList();
		return "Library: name - '" + this._name + "', booksList - " + this._booksList.size() + ", bookReadersList - "
				+ this._bookReadersList.size() + ", authorsList - " + this._authorsList.size();
	}

	private void refreshAuthorsList() {
		if (this._booksList.isEmpty()) {
			this._authorsList = new ArrayList<>();
			return;
		}
		
		this._authorsList = new ArrayList<>(this._booksList.stream().flatMap(book -> book.getAuthors().stream())
				.distinct().collect(Collectors.toList()));
	}
	
	private Author findAuthorNoRefresh(String firstName, String lastName) {
		final String searchFirstName = firstName.trim();
		final String searchLastName = lastName.trim();
		
		if (!this._authorsList.isEmpty()) {
			Optional<Author> foundAuthor = this._authorsList.stream()
					.filter(author -> author.getFirstName().equalsIgnoreCase(searchFirstName)
							&& author.getLastName().equalsIgnoreCase(searchLastName))
					.findFirst();
	
			if (foundAuthor.isPresent())
				return foundAuthor.get();
		}
		return new Author(firstName, lastName);
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this._name);

		out.writeInt(this._booksList.size());
		for (Book b : this._booksList) {
			out.writeInt(b.getAuthors().size());
			for (Author a : b.getAuthors()) {
				out.writeObject(a.getFirstName());
				out.writeObject(a.getLastName());
			}
			out.writeObject(b.getTitle());
			out.writeInt(b.getYear());
			out.writeInt(b.getEdition());
		}

		out.writeInt(this._bookReadersList.size());
		for (BookReader br : this._bookReadersList) {

			out.writeInt(this._booksList.size());
			for (Book b : this._booksList) {
				out.writeInt(b.getAuthors().size());
				for (Author a : b.getAuthors()) {
					out.writeObject(a.getFirstName());
					out.writeObject(a.getLastName());
				}
				out.writeObject(b.getTitle());
				out.writeInt(b.getYear());
				out.writeInt(b.getEdition());
			}

			out.writeObject(br.getFirstName());
			out.writeObject(br.getLastName());
			out.writeInt(br.getId());
		}
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		int size, size1, authorSize, i, j, x;
		
		this._name = (String) in.readObject();
		this._booksList = new ArrayList<Book>();
		this._bookReadersList = new ArrayList<BookReader>();
		this._authorsList = new ArrayList<Author>();
		ArrayList<Author> authors;
		ArrayList<Book> books;
		
		size = in.readInt();
		
		for (i = 0; i < size; i++) {
			authorSize = in.readInt();
			authors = new ArrayList<Author>(authorSize);

			for (j = 0; j < authorSize; j++) {
				authors.add(findAuthorNoRefresh((String) in.readObject(), (String) in.readObject()));
			}

			addBook((String) in.readObject(), authors, in.readInt(), in.readInt());
		}

		size1 = in.readInt();
		for (x = 0; x < size1; x++) {
			size = in.readInt();
			books = new ArrayList<Book>(size);
			
			for (i = 0; i < size; i++) {
				authorSize = in.readInt();
				authors = new ArrayList<Author>(authorSize);

				for (j = 0; j < authorSize; j++) {
					authors.add(findAuthorNoRefresh((String) in.readObject(), (String) in.readObject()));
				}

				books.add(new Book((String) in.readObject(), authors, in.readInt(), in.readInt()));

				this._authorsList.addAll(authors);
			}
			
			addBookReader(new BookReader((String) in.readObject(), (String) in.readObject(), in.readInt(), books));
		}
		
		refreshAuthorsList();
	}

}
