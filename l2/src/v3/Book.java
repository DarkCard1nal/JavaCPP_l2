package v3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class Book implements Externalizable {

	private String _title;
	private ArrayList<Author> _authors;
	private int _year;
	private int _edition;
	
	public Book(String title, ArrayList<Author> authors, int year, int edition) {
		this._title = title.trim();
		this._authors = authors;
		this._year = year;
		this._edition = edition;
	}
	
	public String getTitle() {
		return _title;
	}
	
	public ArrayList<Author> getAuthors() {
		return _authors;
	}
	
	public int getYear() {
		return _year;
	}
	
	public int getEdition() {
		return _edition;
	}
	
	@Override
	public String toString() {
		return "Book: title - " + this._title + ", authors - " + this._authors.toString() + ", year - " 
				+ this._year + ", edition - " + this._edition + ";";
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
