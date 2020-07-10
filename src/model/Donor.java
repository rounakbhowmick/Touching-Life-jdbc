package model;

public class Donor {
	public Donor(String donorID, String dFirstName, String dLastName, int dAge, int dWeight, String dBloodGroup,
			String dPhoneNumber, String dCity, String dAvailable) {
		this.DonorID = donorID;
		this.DFirstName = dFirstName;
		this.DLastName = dLastName;
		this.DCity = dCity;
		this.DBloodGroup = dBloodGroup;
		this.DAvailable = dAvailable;
		this.DAge = dAge;
		this.DWeight = dWeight;
		this.DPhoneNumber = dPhoneNumber;
	}

	public Donor() {

	}

	private String DonorID, DFirstName, DLastName, DPhoneNumber, DCity, DPassword, DBloodGroup, DAvailable;
	private int DAge, DWeight;
	private long DPincode;

	public String getDonorID() {
		return DonorID;
	}

	public void setDonorID(String donorID) {
		DonorID = donorID;
	}

	public String getDFirstName() {
		return DFirstName;
	}

	public void setDFirstName(String dFirstName) {
		DFirstName = dFirstName;
	}

	public String getDLastName() {
		return DLastName;
	}

	public void setDLastName(String dLastName) {
		DLastName = dLastName;
	}

	public String getDCity() {
		return DCity;
	}

	public void setDCity(String dCity) {
		DCity = dCity;
	}

	public String getDPassword() {
		return DPassword;
	}

	public void setDPassword(String dPassword) {
		DPassword = dPassword;
	}

	public String getDBloodGroup() {
		return DBloodGroup;
	}

	public void setDBloodGroup(String dBloodGroup) {
		DBloodGroup = dBloodGroup;
	}

	public String getDAvailable() {
		return DAvailable;
	}

	public void setDAvailable(String dAvailable) {
		DAvailable = dAvailable;
	}

	public int getDAge() {
		return DAge;
	}

	public void setDAge(int dAge) {
		DAge = dAge;
	}

	public int getDWeight() {
		return DWeight;
	}

	public void setDWeight(int dWeight) {
		DWeight = dWeight;
	}

	public String getDPhoneNumber() {
		return DPhoneNumber;
	}

	public void setDPhoneNumber(String dPhoneNumber) {
		DPhoneNumber = dPhoneNumber;
	}

	public long getDPincode() {
		return DPincode;
	}

	public void setDPincode(long dPincode) {
		DPincode = dPincode;
	}

}