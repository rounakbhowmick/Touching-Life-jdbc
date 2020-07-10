package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Acceptor;
import utility.ConnectionManager;

public class AcceptorDAO {
	List<Acceptor> list = new ArrayList<Acceptor>();
	HashMap<String, Boolean> map = new HashMap<>();

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

	public HashMap<String, Boolean> login(String acceptorid, String password) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		boolean status = false;
		try {
			Connection connection = ConnectionManager.GetConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from acceptor where acceptorid = ? and password = ? ");

			preparedStatement.setString(1, acceptorid);
			preparedStatement.setString(2, password);

			// System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			/* Checking whether acceptor has entered correct data or not */
			rs.next();
			try {
				if (rs.getString(1) != "A") {
					map.put(acceptorid, true);
					return map;
				}
			} catch (Exception e) {
				map.put(acceptorid, false);
				return map;
			}

		} catch (SQLException e) {
			// process sql exception
			System.out.println(e);
		}
		return map;
	}

	public List<Acceptor> view(String acceptorid) throws ClassNotFoundException, SQLException {
		Acceptor acceptor;
		String sql = "select acceptorid,firstname,lastname,age,bloodgroup,phonenumber,city from acceptor where acceptorid=?";
		PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(sql);
		ps.setString(1, acceptorid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			acceptor = new Acceptor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
					rs.getString(6), rs.getString(7));
			list.add(acceptor);
		}
		return list;
	}

	public boolean passwordchange(String acceptorid, String oldpassword, String newpassword)
			throws ClassNotFoundException, SQLException {
		String oldpasswordcheck = "select * from acceptor where acceptorID=?";
		String updatepassword = "UPDATE acceptor SET PASSWORD=? where acceptorID=?";
		PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(oldpasswordcheck);
		ps.setString(1, acceptorid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (oldpassword.equals(rs.getString("password"))) {
				PreparedStatement pss = ConnectionManager.GetConnection().prepareStatement(updatepassword);
				pss.setString(1, newpassword);
				pss.setString(2, acceptorid);
				boolean rss = pss.execute();
				return true;
			}

		}
		return false;
	}

	public boolean phonenumber(String acceptorid, String phoneNumber) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String currentphonenocheck = "select phoneNumber from acceptor where acceptorID=?";
		PreparedStatement prep = ConnectionManager.GetConnection().prepareStatement(currentphonenocheck);
		prep.setString(1, acceptorid);
		ResultSet rss = prep.executeQuery();
		while (rss.next()) {
			if (phoneNumber.equals(rss.getString(1))) {
				System.out.println("Opps!It seems to be current and new phone number are same.");
				return false;
			} else {
				String sql = "UPDATE acceptor SET PHONENUMBER=? WHERE acceptorID=?";
				PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(sql);
				ps.setString(1, phoneNumber);
				ps.setString(2, acceptorid);
				ps.execute();
				System.out.println("Successfully changed");
				return true;
			}
		}
		return false;
	}

	public boolean city(String acceptorid, String newcity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String currentphonenocheck = "select city from acceptor where acceptorID=?";
		PreparedStatement prep = ConnectionManager.GetConnection().prepareStatement(currentphonenocheck);
		prep.setString(1, acceptorid);
		ResultSet rss = prep.executeQuery();
		while (rss.next()) {
			if (newcity.equals(rss.getString(1))) {
				System.out.println("Opps!It seems to be current and new city are same.");
				return false;
			} else {
				String sql = "UPDATE acceptor SET CITY=? WHERE acceptorID=?";
				PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(sql);
				ps.setString(1, newcity);
				ps.setString(2, acceptorid);
				ps.execute();
				System.out.println("Successfully changed");
				return true;
			}
		}
		return false;
	}

	public void delete(String acceptorid) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from acceptor where acceptorid=?";
		PreparedStatement prep = ConnectionManager.GetConnection().prepareStatement(sql);
		prep.setString(1, acceptorid);
		prep.executeUpdate();
		System.out.println("Your data is deleted from our system\n");
	}

}
