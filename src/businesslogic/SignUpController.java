package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import dao.DonorDAO;

public class SignUpController {

	/*************************** Name Validation ************************/

	public Boolean name(String firstName, String lastName) {
		boolean first = Pattern.compile("[a-zA-Z]{3,18}+").matcher(firstName).matches();
		boolean second = Pattern.compile("[a-zA-Z]{3,18}+").matcher(lastName).matches();
		if (first && second)
			return true;
		else {
			System.out.println("Please enter your correct name");
			return false;
		}
	}

	/*************************** Weight Validation ************************/

	public void weightcheck(int weight) {
		// TODO Auto-generated method stub
		if (!(weight > 45)) {
			System.out.println("Sorry, you are ineligible to donate.");
			programexit();
		}
	}

	/*************************** Age Validation ************************/

	public void agecheck(int age) {
		// TODO Auto-generated method stub
		if (!(age >= 18 && age <= 60)) {
			System.out.println("Sorry, you are ineligible to donate.");
			programexit();
		}
	}

	/********************** Generating an ID ************************/

	public String ID(String name) throws ClassNotFoundException, SQLException {

		int index = 0;

		/// Getting last donor id
		DonorDAO donordao = new DonorDAO();
		String c = donordao.lastdonorid();

		// If no donor is available in database current donor id starts from 1000
		if (c == "")
			index = 1000;

		else {
			System.out.println("done");
			index = Integer.parseInt(c.substring(2, 6));
			index++;
		}
		String newname = name.substring(0, 2).toUpperCase();
		String newid = newname + index;
		return newid;
	}

	/*************************** Blood Group Validation ************************/
	public Boolean bloodgroup(String type) {
		ArrayList<String> bloodtypes = new ArrayList<String>(
				Arrays.asList("O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"));
		if (bloodtypes.contains(type)) {
			return true;
		} else {
			System.out.println("\nPlease enter your correct Blood Group\n");
			System.out.println("Enter your Blood Group in this format:  O+  O-  A+  A-  B+  B-  AB+  AB-\n");
			return false;
		}
	}

	/*************************** Phone Number Validation ************************/

	public Boolean phoneNumbercheck(String number) {
		boolean no = Pattern.compile("\\d{10}+").matcher(number).matches();
		if (no)
			return true;
		else
			return false;

	}

	/*************************** City Validation ************************/

	public Boolean cityvalidate(String city) {
		boolean citycheck = Pattern.compile("[a-zA-Z]{3,18}+").matcher(city).matches();
		if (citycheck)
			return true;
		else {
			System.out.println("Please enter your correct City name");
			return false;
		}
	}

	/**************************** Generating a password ***************************/
	public String password() {
		String passwordSet = "ABCDEFGHIZKLMNOPQRSTUPWXYZ123456789!@#$%^&*";
		int length = 5;
		char[] password = new char[length];
		for (int i = 0; i < length; i++) {
			int rand = (int) (Math.random() * passwordSet.length());
			password[i] = passwordSet.charAt(rand);
		}
		return new String(password);
	}

	/********************************* Exiting Function ***************************/

	void programexit() {
		System.out.println("THANK YOU FOR YOUR INTEREST IN BECOMING A VOLUNTEER");
		System.exit(0);
	}

}
