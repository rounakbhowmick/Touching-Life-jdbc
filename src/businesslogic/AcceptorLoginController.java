package businesslogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.AcceptorDAO;
import dao.DonorSearchDAO;
import model.Acceptor;
import model.Donor;

public class AcceptorLoginController extends SignUpController {
	AcceptorDAO acceptordao = new AcceptorDAO();
	DonorSearchDAO searchdao = new DonorSearchDAO();
	List<Acceptor> list = new ArrayList<Acceptor>();
	List<Donor> donorslist = new ArrayList<Donor>();
	SignUpController signup = new SignUpController();

	public void acceptorlogin(Acceptor obj) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your acceptorID");
		String acceptorid = sc.readLine();
		System.out.println("Enter your password");
		String password = sc.readLine();
		HashMap<String, Boolean> validate = acceptordao.login(acceptorid, password);
		if (validate.get(acceptorid)) {
			System.out.println("Login success" + "\n\n" + "You have successfully logged in.");
		} else {
			System.out.println(
					"Unable to Sign In\n" + "\n" + "Please make sure your acceptor id and password are correct.");
			return;
		}

		/************************ Acceptor Menu Profile *************************/

		while (true)

		{
			System.out.println("\n1.Update your Details\n2.Search ");
			int firstinput = 0;
			try {
				firstinput = Integer.parseInt(sc.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Opps!This input is not valid");
			}
			switch (firstinput) {
			case 1:
				/****************** Update Menu ******************/
				while (true) {
					System.out.println(
							"\n1.View your data\n2.Change your Password\n3.Change your Phone Number\n4.Change your City\n5.Delete your data\n6.Logout\n");
					int secondinput = Integer.parseInt(sc.readLine());
					switch (secondinput) {

					/*********************** Acceptor data in table format *****************/

					case 1:
						// BJ
						list = acceptordao.view(acceptorid);
						System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", "AcceptorId", "FirstName",
								"LastName", "Age", "Bloodgroup", "PhoneNumber", "City");

						for (Acceptor acceptor : list) {
							System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", acceptor.getAcceptorID(),
									acceptor.getAFirstName(), acceptor.getALastName(), acceptor.getAage(),
									acceptor.getABloodGroup(), acceptor.getAPhoneNumber(), acceptor.getACity());
						}
						break;

					case 2:
						int count = 0;
						while (true) {

							/* Kicking out acceptor if acceptor has entered thrice incorrect old password */

							count++;
							if (count == 3) {
								System.out.println("Your account has been logged out for security reason");
								return;
							}

							System.out.println("Please enter your old password");
							String oldpassword = sc.readLine();
							System.out.println("Please enter your new password");
							String newpassword = sc.readLine();
							boolean validateprocess = acceptordao.passwordchange(acceptorid, oldpassword, newpassword);
							if (validateprocess) {
								System.out.println("Success! Your Password has been changed!");
								break;
							} else
								System.out.println("The old password you have entered is incorrect");
						}
						break;
					case 3:
						while (true) {
							System.out.println("Enter your new phone number");
							String phoneNumber = sc.readLine();
							Boolean phoneNumbercheck = signup.phoneNumbercheck(phoneNumber);
							if (phoneNumbercheck) {
								boolean check = acceptordao.phonenumber(acceptorid, phoneNumber);
								if (check)
									obj.setAPhoneNumber(phoneNumber);
								break;
							} else
								System.out.println("Please enter your correct 10 digit phone number");
						}
						break;
					case 4:
						while (true) {
							System.out.println("Enter your new City");
							String city = sc.readLine();
							// acceptors.get(i).setCity(city);
							Boolean citycheck = signup.cityvalidate(city);
							if (citycheck) {
								boolean check = acceptordao.city(acceptorid, city);
								if (check)
									obj.setACity(city);
								break;
							}

						}
						break;
					case 5:
						acceptordao.delete(acceptorid);
						return;
					case 6:
						System.out.println("You have been successfully logged out");
						return;
					default:
						System.out.println("Wrong Input");
					}
				}
			case 2:
				bloodgroupSearch();
				break;
			}
		}
	}

	public void bloodgroupSearch() throws IOException, ClassNotFoundException, SQLException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\n1.Search with Blood Group\n2.Search with city");
		int input = 0;
		try {
			input = Integer.parseInt(sc.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Opps!This input is not valid");
		}
		switch (input) {
		case 1:
			/******** Search By Blood Group ***********/
			String bloodGroupsearch = "";
			while (true) {
				System.out.println("Enter Blood group");
				bloodGroupsearch = sc.readLine();
				Boolean bloodgroupcheck = signup.bloodgroup(bloodGroupsearch);
				if (bloodgroupcheck)
					break;
				else
					System.out.println("Wrong value entered");
			}
			donorslist = searchdao.searchBYBloodGroup(bloodGroupsearch);
			if (donorslist.size() == 0) {
				System.out.println("Opps!Currently no available donors are found.Please try again later\n");
				return;
			}
			System.out.format("%-15s%-15s%-15s%-15s%-15s%n", "FirstName", "LastName", "Bloodgroup", "Phonenumber",
					"City");

			for (Donor donor : donorslist) {
				System.out.format("%-15s%-15s%-15s%-15s%-15s%n", donor.getDFirstName(), donor.getDLastName(),
						donor.getDBloodGroup(), donor.getDPhoneNumber(), donor.getDCity());
			}
			donorslist.clear();
			break;

		/************************ Search By City *******************/

		case 2:
			String searchByCity = "";
			while (true) {
				System.out.println("Enter City name");
				searchByCity = sc.readLine();
				Boolean citycheck = signup.cityvalidate(searchByCity);
				if (citycheck)
					break;
				else
					System.out.println("Enter your correct city name");
			}
			donorslist = searchdao.searchBYCity(searchByCity);
			if (donorslist.size() == 0) {
				System.out.println("Opps!Currently no available donors are found.Please try again later\n");
				return;
			}
			System.out.format("%-15s%-15s%-15s%-15s%-15s%n", "FirstName", "LastName", "Bloodgroup", "Phonenumber",
					"City");
			for (Donor donor : donorslist) {
				System.out.format("%-15s%-15s%-15s%-15s%-15s%n", donor.getDFirstName(), donor.getDLastName(),
						donor.getDBloodGroup(), donor.getDPhoneNumber(), donor.getDCity());
			}

			donorslist.clear();/// Clearing the array list
			break;
		default:
			System.out.println("Wrong Input");
		}

	}
}
