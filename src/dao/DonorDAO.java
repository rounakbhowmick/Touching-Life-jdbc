package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import model.Donor;
import utility.ConnectionManager;

public class DonorDAO {
//	public DonorDAO() throws Exception {
//		// throw new Exception("a");
//	}

	List<Donor> list = new ArrayList<Donor>();
	HashMap<String, Boolean> map = new HashMap<>();

	/*************************** Donor Signup ***********************/

	public void signup(Donor donor) throws ClassNotFoundException {
		String sql = "INSERT INTO DONOR(DonorID,Password,FirstName,LastName,Age,Weight,BloodGroup,PhoneNumber,City,Available)VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {
			// Step 2:Create a statement using connection object
			PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(sql);
			ps.setString(1, donor.getDonorID());
			ps.setString(2, donor.getDPassword());
			ps.setString(3, donor.getDFirstName());
			ps.setString(4, donor.getDLastName());
			ps.setInt(5, donor.getDAge());
			ps.setInt(6, donor.getDWeight());
			ps.setString(7, donor.getDBloodGroup());
			ps.setString(8, donor.getDPhoneNumber());
			ps.setString(9, donor.getDCity());
			ps.setString(10, donor.getDAvailable());
			// Step 3: Execute the query or update query
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	/*********************** Donor Login **************************/

	public HashMap<String, Boolean> login(String donorid, String password) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Connection connection = ConnectionManager.GetConnection();

		// Step 2:Create a statement using connection object
		PreparedStatement preparedStatement = connection.prepareStatement("select password from donor where donorid=?");

		preparedStatement.setString(1, donorid);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String encrypted = rs.getString(1);
			String decryptedpassword = getDecryptedPassword(encrypted);
			if (decryptedpassword.equals(password)) {
				map.put(donorid, true);
				return map;
			} else {
				map.put(donorid, false);// key:donorid value:true/false
				return map;
			}
		}

		return map;
	}

	/*************** Displaying donor's personal data ***************/

	public List<Donor> view(String donorid) throws ClassNotFoundException, SQLException {
		Donor donor;
		String sql = "select donorid,firstname,lastname,age,weight,bloodgroup,phonenumber,city,available from donor where donorid=?";
		PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(sql);
		ps.setString(1, donorid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			donor = new Donor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			list.add(donor);
		}
		return list;
	}

	/***************** New Password Change *****************/

	public boolean passwordchange(String donorid, String oldpassword, String newpassword)
			throws ClassNotFoundException, SQLException {
		String oldpasswordcheck = "select password from donor where DONORID=?";
		String updatepassword = "UPDATE DONOR SET PASSWORD=? where DONORID=?";
		PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(oldpasswordcheck);
		ps.setString(1, donorid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String encrypted = rs.getString(1);
			String decryptedpassword = getDecryptedPassword(encrypted);
			String encryptedpassword = getEncryptedPassword(newpassword);
			if (oldpassword.equals(decryptedpassword)) {
				PreparedStatement pss = ConnectionManager.GetConnection().prepareStatement(updatepassword);
				pss.setString(1, encryptedpassword);
				pss.setString(2, donorid);
				boolean rss = pss.execute();
				return true;
			}
		}
		return false;
	}

	/***************** Encryption and Decryption *****************/

	private String getEncryptedPassword(String newpassword) {
		// TODO Auto-generated method stub
		return Base64.getEncoder().encodeToString(newpassword.getBytes());
	}

	private String getDecryptedPassword(String encrypted) {
		// TODO Auto-generated method stub
		return new String(Base64.getDecoder().decode(encrypted));
	}

	/***************** Available Status change *****************/

	public boolean changestatus(String donorid, String newstatus) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String currentstatuscheck = "select available from donor where DONORID=?";
		PreparedStatement prep = ConnectionManager.GetConnection().prepareStatement(currentstatuscheck);
		prep.setString(1, donorid);
		ResultSet rss = prep.executeQuery();
		while (rss.next()) {
			if (newstatus.equals(rss.getString(1))) {
				System.out.println("Opps!It seems to be current and new available status are same.");
				return false;
			} else {
				String sql = "UPDATE DONOR SET AVAILABLE=? WHERE DONORID=?";
				PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(sql);
				ps.setString(1, newstatus);
				ps.setString(2, donorid);
				ps.execute();
				System.out.println("Successfully changed");
				return true;
			}
		}
		return false;
	}

	/************************ NEW PHONE NO INSERTION ************************/

	public boolean phonenumber(String donorid, String phoneNumber) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String currentphonenocheck = "select phoneNumber from donor where DONORID=?";
		PreparedStatement prep = ConnectionManager.GetConnection().prepareStatement(currentphonenocheck);
		prep.setString(1, donorid);
		ResultSet rss = prep.executeQuery();
		while (rss.next()) {
			if (phoneNumber.equals(rss.getString(1))) {
				System.out.println("Opps!It seems to be current and new phone number are same.");
				return false;
			} else {
				String sql = "UPDATE DONOR SET PHONENUMBER=? WHERE DONORID=?";
				PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(sql);
				ps.setString(1, phoneNumber);
				ps.setString(2, donorid);
				ps.execute();
				System.out.println("Successfully changed");
				return true;
			}
		}
		return false;
	}

	/********************************** NEW CITY INSERTION ************************/

	public boolean city(String donorid, String newcity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String currentphonenocheck = "select city from donor where DONORID=?";
		PreparedStatement prep = ConnectionManager.GetConnection().prepareStatement(currentphonenocheck);
		prep.setString(1, donorid);
		ResultSet rss = prep.executeQuery();
		while (rss.next()) {
			if (newcity.equals(rss.getString(1))) {
				System.out.println("Opps!It seems to be current and new city are same.");
				return false;
			} else {
				String sql = "UPDATE DONOR SET CITY=? WHERE DONORID=?";
				PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(sql);
				ps.setString(1, newcity);
				ps.setString(2, donorid);
				ps.execute();
				System.out.println("Successfully changed");
				return true;
			}
		}
		return false;
	}

	/********************** DELETE DONOR's personal DATA *********************/

	public void delete(String donorid) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from donor where donorid=?";
		PreparedStatement prep = ConnectionManager.GetConnection().prepareStatement(sql);
		prep.setString(1, donorid);
		prep.executeUpdate();
		System.out.println("Your data is deleted from our system\n");
	}

	/************** Retriving last donor id details ******************/

	public String lastdonorid() throws ClassNotFoundException, SQLException {
		String sql = "Select donorid From donor Where rownum = 1 Order by 1 desc";
		PreparedStatement prep = ConnectionManager.GetConnection().prepareStatement(sql);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("DONORID"));
			return rs.getString("DONORID");
		}
		return "";
	}

}
