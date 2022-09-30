package com.masai.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.masai.Exception.BuyerException;
import com.masai.Exception.SellerException;
import com.masai.Users.Buyer;
import com.masai.main.Main;
import com.masai.utility.DBUtil;

public class BuyerLogin {

	public static void main(String[] args) throws BuyerException {


		Scanner sc = new Scanner(System.in);
		

		System.out.println("Enter Buyer credentials");
		
		System.out.println("---------------------------");
		
		System.out.println("Enter username");
		String un = sc.next();
		
		System.out.println("Enter password");
		String pass = sc.next();
		
		
		Connection conn = DBUtil.provideConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement("select * from buyer");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String um = rs.getString("buyername");
				String pas = rs.getString("buyerpass");
				
				if(um.equals(un) && pas.equals(pass)) {
					
					Buyer.Buyerwelcome(un,pass);
					break;
				}
				else {
					System.out.println("Wrong credentials");
					Main.main(args);
				}
				
			}
			
			
		} catch (SQLException e ) {
			
			throw new BuyerException("Invalid credentials");

			
		}

	}

}
