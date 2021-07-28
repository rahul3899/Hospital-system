package com.hsm.dao;
import com.hsm.factory.DBConnection;
import com.hsm.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDAO {
	Connection connection;
	PreparedStatement prepareStatement;
	public void updateUser(Patient user) {

		try {

			connection=DBConnection.getConnection();

			PreparedStatement prepareStatement=connection.prepareStatement("INSERT INTO Patient_Details VALUES(?,?,?,?,?,?,?,?)");
			prepareStatement.setInt(1, user.getId());
			prepareStatement.setString(2, user.getName());
			prepareStatement.setString(3, user.getPhoneNumber());
			prepareStatement.setString(4, user.getEmailId());
			prepareStatement.setInt(5, user.getAge());
			prepareStatement.setString(6, user.getGender());
			prepareStatement.setString(7, user.getAddress());
			prepareStatement.setString(8, user.getMedicalHistory());
			prepareStatement.executeUpdate();

		} catch (SQLException |ClassNotFoundException e) {

			System.out.println(e);
		}
	}
	public List<Patient> getAllUser() {
		List<Patient> userList = new ArrayList<Patient>();
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStatement=connection.prepareStatement("SELECT * FROM  Patient_Details");
			ResultSet rs =prepareStatement.executeQuery();
			while(rs.next()) {
				Patient user= new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));

				userList.add(user);
			}

		} catch (ClassNotFoundException | SQLException e) {

			System.out.println(e);
		}
		return userList;


	}

	public Patient findUserByID(int ID){
		Patient patient=null;
		try {
			connection=DBConnection.getConnection();
			PreparedStatement ps=connection.prepareStatement("SELECT * from \"user\" WHERE ID=?");
			ps.setInt(1, ID);
			ResultSet resultset=ps.executeQuery();
			while(resultset.next())
			{
				patient=new Patient(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getString(4),resultset.getInt(5), resultset.getString(6), resultset.getString(7), resultset.getString(8));
			}
		}
		catch(SQLException |ClassNotFoundException s) {
			System.out.println(s);
			
		}
		return patient;
	}
	

}
