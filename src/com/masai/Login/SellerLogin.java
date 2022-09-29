package com.masai.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.masai.Exception.BuyerException;
import com.masai.Exception.SellerException;
import com.masai.Users.Buyer;
import com.masai.Users.Seller;
import com.masai.utility.DBUtil;

public class SellerLogin {

	public static void main(String[] args) throws SellerException {

		Scanner sc = new Scanner(System.in);
		

		System.out.println("Enter Seller credentials");
		
		System.out.println("---------------------------");
		
		System.out.println("Enter username");
		String un = sc.next();
		
		System.out.println("Enter password");
		String pass = sc.next();
		
		
		Connection conn = DBUtil.provideConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement("select * from seller");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String um = rs.getString("sellername");
				String pas = rs.getString("sellerpass");
				int id = rs.getInt("sellerid");
				
				if(um.equals(un) && pas.equals(pass)) {
					
					Seller.WelcomeSeller(un,pass,id);
					break;
				}
				
			}
			
			
		} catch (Exception e ) {
			
			throw new SellerException("Invalid credentials");

			
		}
		
	}

}
