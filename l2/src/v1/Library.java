package v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _name;
	private ArrayList<Book> _booksList;
	private ArrayList<BookReader> _bookReadersList;
	private transient  ArrayList<Author> _authorsList;

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
		final String searchFirstName = firstName.trim();
		final String searchLastName = lastName.trim();
		refreshAuthorsList();
		
		Optional<Author> foundAuthor = _authorsList.stream()
				.filter(author -> author.getFirstName().equalsIgnoreCase(searchFirstName)
						&& author.getLastName().equalsIgnoreCase(searchLastName))
				.findFirst();

		if (foundAuthor.isPresent())
			return foundAuthor.get();

		return new Author(firstName, lastName);
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
		return "Library: name - '" + this._name + "', booksList - " 
				+ this._booksList.size() + ", bookReadersList - " + this._bookReadersList.size() 
				+ ", authorsList - " + this._authorsList.size();
	}

	private void refreshAuthorsList() {
		this._authorsList = new ArrayList<>(this._booksList.stream().flatMap(book -> book.getAuthors().stream())
				.distinct().collect(Collectors.toList()));
	}
}
