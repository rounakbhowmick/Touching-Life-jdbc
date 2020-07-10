package businesslogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import dao.AcceptorDAO;
import model.Acceptor;

public class UserInputAcceptor {
	SignUpController signup = new SignUpController();

	public void input(Acceptor acceptorobj) throws IOException, ClassNotFoundException, SQLException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		/********************************** Name ***********************************/

		while (true) {
			System.out.println("Enter your first name");
			String firstName = sc.readLine();
			acceptorobj.setAFirstName(firstName);
			System.out.println("Enter your surname");
			String LastName = sc.readLine();
			acceptorobj.setALastName(LastName);
			// Validation of name
			Boolean name = signup.name(acceptorobj.getAFirstName(), acceptorobj.getALastName());
			if (name)
				break;
		}
		/************************************* Age ***********************************/

		// Input and Validation of age
		while (true) {
			try {
				System.out.println("Enter your age");
				int age = Integer.parseInt(sc.readLine());
				acceptorobj.setAage(age);
				signup.agecheck(acceptorobj.getAage());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Please enter your correct age");

			}
		}

		/*********************************** Blood Group ******************************/

		while (true) {
			System.out.println("Enter your bloodGroup");
			String bloodGroup = sc.readLine();
			acceptorobj.setABloodGroup(bloodGroup);
			Boolean bloodgroupcheck = signup.bloodgroup(acceptorobj.getABloodGroup());
			if (bloodgroupcheck)
				break;
			else
				System.out.println("Wrong value entered");
		}

		/********************************* Phone Number *******************************/

		while (true) {
			System.out.println("Enter your phone number");
			String phoneNumber = sc.readLine();
			acceptorobj.setAPhoneNumber(phoneNumber);
			Boolean phoneNumbercheck = signup.phoneNumbercheck(acceptorobj.getAPhoneNumber());
			if (phoneNumbercheck)
				break;
			else
				System.out.println("Please enter your correct 10 digit phone number");
		}

		/********************************* City *******************************/

		while (true) {
			System.out.println("Enter your City");
			String city = sc.readLine();
			acceptorobj.setACity(city);
			Boolean citycheck = signup.cityvalidate(acceptorobj.getACity());
			if (citycheck)
				break;
		}

		/************************** ID Generation *********************/

		String id = signup.ID(acceptorobj.getAFirstName());
		System.out.println();
		acceptorobj.setAcceptorID(id);

		/************************** Password Generation *********************/

		String newpassword = signup.password();
		acceptorobj.setAPassword(newpassword);

		/********************* Storing in Acceptor Database ********************/

		AcceptorDAO acceptordao = new AcceptorDAO();
		acceptordao.signup(acceptorobj);
	}
}
