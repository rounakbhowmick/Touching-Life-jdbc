package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import businesslogic.AcceptorLoginController;
import businesslogic.DonorLoginController;
import businesslogic.UserInputAcceptor;
import businesslogic.UserInputDonor;
import model.Acceptor;
import model.Donor;

public class Main {
	static List<Donor> donors = new ArrayList<Donor>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println(
					"1.BECOME A DONOR  --- SIGN UP\n2.REQUEST BLOOD --- SIGNUP\n3.DONOR LOGIN\n4.ACCEPTOR LOGIN\n");
			Scanner sc = new Scanner(System.in);
			/// Exception handling is used to prevent from String inputs
			try {
				int main_input = sc.nextInt();
				switch (main_input) {
				case 1:
					UserInputDonor obj1 = new UserInputDonor();
					Donor obj = new Donor();
					obj1.input(obj);
					donors.add(obj);
					break;
				case 2:
					UserInputAcceptor obj2 = new UserInputAcceptor();
					Acceptor acceptorobj = new Acceptor();
					obj2.input(acceptorobj);
					break;
				case 3:
					DonorLoginController donorloginobj = new DonorLoginController();
					Donor donor = new Donor();
					donorloginobj.donorlogin(donor);
					break;
				case 4:
					AcceptorLoginController acceptorloginobj = new AcceptorLoginController();
					Acceptor acceptor = new Acceptor();
					acceptorloginobj.acceptorlogin(acceptor);
					break;
//				case 4:
//					if (donors.size() != 0) {
//						for (int i = 0; i < donors.size(); i++)
//							System.out.println(donors.get(i));
//					} else
//						System.out.println("No donors found");
//					break;
				case 5:
					System.out.println("THANK YOU FOR YOUR INTEREST IN BECOMING A VOLUNTEER");
					System.exit(0);

				default:
					System.out.println("Wrong Input");
				}
			} catch (Exception e) {
				e.printStackTrace();
				// System.out.println("Please Enter only numeric value\n\n");
			}
		}
	}

}
