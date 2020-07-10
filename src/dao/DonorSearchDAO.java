package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Donor;
import utility.ConnectionManager;

public class DonorSearchDAO {
	List<Donor> list = new ArrayList<Donor>();

	public List<Donor> searchBYBloodGroup(String bloodgroup) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Donor donor;
		String sql = "select firstname,lastname,bloodgroup,phonenumber,city from donor where bloodgroup=? and available='YES'";
		PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(sql);
		ps.setString(1, bloodgroup);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			donor = new Donor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			list.add(donor);
		}
		return list;
	}

	public List<Donor> searchBYCity(String city) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Donor donor;
		String sql = "select firstname,lastname,bloodgroup,phonenumber,city from donor where city=? and available='YES'";
		PreparedStatement ps = ConnectionManager.GetConnection().prepareStatement(sql);
		ps.setString(1, city);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			donor = new Donor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			list.add(donor);
		}
		return list;
	}
}
