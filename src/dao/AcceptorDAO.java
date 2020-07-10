package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Acceptor;
import utility.ConnectionManager;

public class AcceptorDAO {

	public void signup(Acceptor acceptorobj) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Acceptor(AcceptorID,Password,FirstName,LastName,Age,BloodGroup,PhoneNumber,City)VALUES(?,?,?,?,?,?,?,?)";

		try {
			// Step 2:Create a statement using connection object
			PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(sql);
			ps.setString(1, acceptorobj.getAcceptorID());
			ps.setString(2, acceptorobj.getAPassword());
			ps.setString(3, acceptorobj.getAFirstName());
			ps.setString(4, acceptorobj.getALastName());
			ps.setInt(5, acceptorobj.getAage());
			ps.setString(6, acceptorobj.getABloodGroup());
			ps.setString(7, acceptorobj.getAPhoneNumber());
			ps.setString(8, acceptorobj.getACity());
			// Step 3: Execute the query or update query
			ps.executeUpdate();

			System.out.println(
					"Hey" + acceptorobj.getAFirstName() + "\n" + "Thank you for filling out your information!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
