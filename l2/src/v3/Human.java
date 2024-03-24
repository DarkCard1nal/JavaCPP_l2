package v3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class Human implements Externalizable {

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
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}
}
