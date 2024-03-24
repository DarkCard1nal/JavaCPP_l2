package v1;

import java.io.Serializable;

public abstract class Human implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _firstName;
	private String _lastName;
	
	public Human(String firstName, String lastName) {
		setName(firstName, lastName);
	}
	
	public void setName(String firstName, String lastName) {
		this._firstName = firstName.trim();
		this._lastName = lastName.trim();
	}
	
	public String getFirstName() {
		return _firstName;
	}
	
	public String getLastName() {
		return _lastName;
	}
	
	@Override
	public String toString() {
		return _firstName + " " + _lastName;
	}
	
}
