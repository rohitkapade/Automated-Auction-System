package com.masai.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.masai.main.Main;
import com.masai.utility.DBUtil;

public class RegisterBuyer {

	public static void main(String[] args) {


		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter name");
		String name = sc.next();
		
		System.out.println("Enter e-mail id");
		String email = sc.next();
		
		System.out.println("Enter Password");
		String pass = sc.next();
		
		
		
		Connection conn = DBUtil.provideConnection();
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("insert into buyer(buyername,buyerpass,buyermail) values (?,?,?)");
			
			
			ps.setString(1, name);
			ps.setString(2, pass);
			ps.setString(3, email);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				System.out.println("Account successfully created");
				
				System.out.println("===============================");
				
				Main.main(args);
			}

			
		} catch (SQLException e) {
			
			System.out.println("Put valid details");
			
		}
		
		
		

	}

}
