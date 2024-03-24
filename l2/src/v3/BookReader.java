package v3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class BookReader extends Human implements Externalizable {

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
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}
}
