package model;

public class Acceptor {
	public Acceptor() {

	}

	public Acceptor(String acceptorID, String aFirstName, String aLastName, int aage, String aBloodGroup,
			String aPhoneNumber, String aCity) {
		AcceptorID = acceptorID;
		AFirstName = aFirstName;
		ALastName = aLastName;
		Aage = aage;
		ABloodGroup = aBloodGroup;
		APhoneNumber = aPhoneNumber;
		ACity = aCity;
	}

	private String AcceptorID, AFirstName, ALastName, ABloodGroup, APhoneNumber, ACity, APassword;
	private int Aage;

	public String getAcceptorID() {
		return AcceptorID;
	}

	public void setAcceptorID(String acceptorID) {
		AcceptorID = acceptorID;
	}

	public String getAFirstName() {
		return AFirstName;
	}

	public void setAFirstName(String aFirstName) {
		AFirstName = aFirstName;
	}

	public String getALastName() {
		return ALastName;
	}

	public void setALastName(String aLastName) {
		ALastName = aLastName;
	}

	public String getACity() {
		return ACity;
	}

	public void setACity(String aCity) {
		ACity = aCity;
	}

	public String getAPassword() {
		return APassword;
	}

	public void setAPassword(String aPassword) {
		APassword = aPassword;
	}

	public String getABloodGroup() {
		return ABloodGroup;
	}

	public void setABloodGroup(String aBloodGroup) {
		ABloodGroup = aBloodGroup;
	}

	public int getAage() {
		return Aage;
	}

	public void setAage(int aage) {
		Aage = aage;
	}

	public String getAPhoneNumber() {
		return APhoneNumber;
	}

	public void setAPhoneNumber(String aPhoneNumber) {
		APhoneNumber = aPhoneNumber;
	}

}
