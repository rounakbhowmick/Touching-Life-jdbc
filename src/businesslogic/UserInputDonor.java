package businesslogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import dao.DonorDAO;
import model.Donor;

public class UserInputDonor {
	SignUpController signup = new SignUpController();

	public void input(Donor obj) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		/********************************** Name ***********************************/

		while (true) {
			System.out.println("Enter your first name");
			String firstName = sc.readLine();
			obj.setDFirstName(firstName);
			System.out.println("Enter your surname");
			String LastName = sc.readLine();
			obj.setDLastName(LastName);
			// Validation of name
			Boolean name = signup.name(obj.getDFirstName(), obj.getDLastName());
			if (name)
				break;
		}
		/************************************* Age ***********************************/

		// Input and Validation of age
		while (true) {
			try {
				System.out.println("Enter your age");
				int age = Integer.parseInt(sc.readLine());
				obj.setDAge(age);
				signup.agecheck(obj.getDAge());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Please enter your correct age");

			}
		}

		/************************************* Weight *********************************/

		while (true) {
			try {
				System.out.println("Enter your weight");
				int weight = Integer.parseInt(sc.readLine());
				obj.setDWeight(weight);
				signup.weightcheck(obj.getDWeight());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Please enter your correct weight");
			}
		}
		/*********************************** Blood Group ******************************/

		while (true) {
			System.out.println("Enter your bloodGroup");
			String bloodGroup = sc.readLine();
			obj.setDBloodGroup(bloodGroup);
			Boolean bloodgroupcheck = signup.bloodgroup(obj.getDBloodGroup());
			if (bloodgroupcheck)
				break;
			else
				System.out.println("Wrong value entered");
		}

		/********************************* Phone Number *******************************/

		while (true) {
			System.out.println("Enter your phone number");
			String phoneNumber = sc.readLine();
			obj.setDPhoneNumber(phoneNumber);
			Boolean phoneNumbercheck = signup.phoneNumbercheck(obj.getDPhoneNumber());
			if (phoneNumbercheck)
				break;
			else
				System.out.println("Please enter your correct 10 digit phone number");
		}

		/********************************* City *******************************/

		while (true) {
			System.out.println("Enter your City");
			String city = sc.readLine();
			obj.setDCity(city);
			Boolean citycheck = signup.cityvalidate(obj.getDCity());
			if (citycheck)
				break;
		}
		/*************************** Availability ***********************************/

		while (true) {
			System.out.println("Are you available for Blood Donation?\n\nType YES if you are available else type NO\n");
			String availablestatus = sc.readLine().toUpperCase();
			if ((availablestatus.equals("YES") || (availablestatus.equals("NO")))) {
				obj.setDAvailable(availablestatus);
				break;
			}
		}

		/************************** ID Generation *********************/

		String id = signup.ID(obj.getDFirstName());
		obj.setDonorID(id);
		/************************** Password Generation *********************/

		String newpassword = signup.password();
		obj.setDPassword(newpassword);

		/************ Display Login Credentials ******************/

		System.out.println(
				"\n\nSuccessfully Registered.\n\nThank you for enrolling to donate blood.\n\n\nYour donations will make a big difference in the lives of many patients.\n\nYOUR LOGIN CREDENTIALS\n\n"
						+ "USER ID: " + obj.getDonorID() + "\n\n" + "Your default password : " + obj.getDPassword()
						+ "\n");
		/*************************** Storing in Database *************************/
		DonorDAO donordao = new DonorDAO();
		donordao.signup(obj);

	}
}
