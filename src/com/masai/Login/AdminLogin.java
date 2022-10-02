package com.masai.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.masai.Exception.AdminException;
import com.masai.Users.Admin;
import com.masai.main.Main;
import com.masai.utility.DBUtil;

public class AdminLogin {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter admin credentials");
		
		System.out.println("---------------------------");
		
		System.out.println("Enter username");
		String un = sc.next();
		
		System.out.println("Enter password");
		String pass = sc.next();
		
		
		Connection conn = DBUtil.provideConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement("select * from admin");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String um = rs.getString("name");
				String pas = rs.getString("password");
				
				if(um.equals(un) && pas.equals(pass)) {
					
					Admin.WecomeAdmin(un,pass);
					break;
				}
				else {
					throw new AdminException("Invalid credentials");
				}
				
			
				
			}
			
			
		} catch (Exception e ) {
			
			System.out.println("Wrong credentials. Try again with valid credentials");
			
			Main.main(args);
		}
		
	
		
		

	}

}
