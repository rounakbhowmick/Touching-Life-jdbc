package model;

public class Acceptor {
//	public Acceptor(String acceptorID, String aFirstName, String aLastName, String aCity, String aPassword,
//			String aBloodGroup, int aage, long aPhoneNumber, long aPincode) {
//		AcceptorID = acceptorID;
//		AFirstName = aFirstName;
//		ALastName = aLastName;
//		ACity = aCity;
//		APassword = aPassword;
//		ABloodGroup = aBloodGroup;
//		Aage = aage;
//		APhoneNumber = aPhoneNumber;
//		APincode = aPincode;
//	}

	private String AcceptorID, AFirstName, ALastName, ACity, APhoneNumber, APassword, ABloodGroup;
	private int Aage;
	private long APincode;

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

	public long getAPincode() {
		return APincode;
	}

	public void setAPincode(long aPincode) {
		APincode = aPincode;
	}
}
