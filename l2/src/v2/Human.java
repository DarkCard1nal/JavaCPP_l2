package v2;

public abstract class Human {

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
