package v1;

import java.io.Serializable;
import java.util.ArrayList;

public class BookReader extends Human implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _id;
	private ArrayList<Book> _receiveBooks;
	
	public BookReader (String firstName, String lastName, int id) {
		super(firstName, lastName);
		this._id = id;
		this._receiveBooks = new ArrayList<>();
	}
	
	public BookReader (String firstName, String lastName, int id, ArrayList<Book> receiveBooks) {
		super(firstName, lastName);
		this._id = id;
		this._receiveBooks = receiveBooks;
	}
	
	public int getId() {
		return _id;
	}
	
	public ArrayList<Book> getReceiveBooks() {
		return _receiveBooks;
	}
	
	public void addReceiveBook(Book book) {
		_receiveBooks.add(book);
	}
	
	public void delReceiveBook(Book book) {
		_receiveBooks.remove(book);
	}
}
