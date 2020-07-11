package businesslogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.DonorDAO;
import model.Donor;

public class DonorLoginController extends SignUpController {
	DonorDAO donordao = new DonorDAO();
	List<Donor> list = new ArrayList<Donor>();
	SignUpController signup = new SignUpController();

	public void donorlogin(Donor obj) throws IOException, ClassNotFoundException, SQLException {

		// TODO Auto-generated method stub
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your donorID");
		String donorid = sc.readLine();
		System.out.println("Enter your password");
		String password = sc.readLine();
		HashMap<String, Boolean> validate = donordao.login(donorid, password);
		// System.out.println("Before validate");
		if (validate.get(donorid)) {
			System.out.println("Login success" + "\n\n" + "You have successfully logged in.");
		} else {
			System.out
					.println("Unable to Sign In\n" + "\n" + "Please make sure your donor id and password are correct.");
			return;
		}
		/************************ Donors Menu Profile *************************/

		while (true) {
			System.out.println(
					"\n1.View your data\n2.Change your Password\n3.Change your Availability status\n4.Change your Phone Number\n5.Change your City\n6.Delete your data\n7.Logout\n");
			int userinput = Integer.parseInt(sc.readLine());
			switch (userinput) {

			/*********************** DONOR data in table format *****************/

			case 1:
				list = donordao.view(donorid);

				System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", "DonorID", "FirstName", "LastName",
						"Age", "Weight", "Bloodgroup", "PhoneNumber", "City", "Available");

				for (Donor donor : list) {
					System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", donor.getDonorID(),
							donor.getDFirstName(), donor.getDLastName(), donor.getDAge(), donor.getDWeight(),
							donor.getDBloodGroup(), donor.getDPhoneNumber(), donor.getDCity(), donor.getDAvailable());
				}

				break;
			case 2:
				int count = 0;
				while (true) {

					/* Kicking out donor if donor has entered thrice incorrect old password */

					count++;
					if (count == 3) {
						System.out.println("Your account has been logged out for security reason");
						return;
					}

					System.out.println("Please enter your old password");
					String oldpassword = sc.readLine();
					System.out.println("Please enter your new password");
					String newpassword = sc.readLine();
					boolean validateprocess = donordao.passwordchange(donorid, oldpassword, newpassword);
					if (validateprocess) {
						System.out.println("Success! Your Password has been changed!");
						break;
					} else
						System.out.println("The old password you have entered is incorrect");
				}

				break;
			case 3:
				while (true) {
					System.out.println("Enter your new status");
					System.out.println(
							"Are you available for Blood Donation?\n\nType YES if you are available else type NO\n");
					String status = sc.readLine();
					if ((status.equals("YES") || (status.equals("NO")))) {
						boolean check = donordao.changestatus(donorid, status);
						if (check)
							obj.setDAvailable(status);
						break;
					}
				}
				break;
			case 4:
				while (true) {
					System.out.println("Enter your new phone number");
					String phoneNumber = sc.readLine();
					Boolean phoneNumbercheck = signup.phoneNumbercheck(phoneNumber);
					if (phoneNumbercheck) {
						boolean check = donordao.phonenumber(donorid, phoneNumber);
						if (check)
							obj.setDPhoneNumber(phoneNumber);
						break;
					} else
						System.out.println("Please enter your correct 10 digit phone number");
				}
				break;
			case 5:
				while (true) {
					System.out.println("Enter your new City");
					String city = sc.readLine();
					// donors.get(i).setCity(city);
					Boolean citycheck = signup.cityvalidate(city);
					if (citycheck) {
						boolean check = donordao.city(donorid, city);
						if (check)
							obj.setDCity(city);
						break;
					}

				}
				break;
			case 6:
				donordao.delete(donorid);
				return;
			case 7:
				System.out.println("You have been successfully logged out");
				return;
			default:
				System.out.println("Wrong Input");
			}
		}
	}
}
