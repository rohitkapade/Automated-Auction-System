package com.masai.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.masai.main.Main;
import com.masai.utility.DBUtil;

public class Admin {

	

	public static void WecomeAdmin(String un, String pass) {
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome, I am admin of the application");
		
		System.out.println("----------------------------------");
		
		System.out.println("1.View registered buyer list");
		System.out.println("2.View registered seller list");
		System.out.println("3.View sold product report");
		System.out.println("4.Log-out");
		
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1:
			Admin.showBuyer(un,pass);
		case 2:
			Admin.showSeller(un,pass);
		case 3:
			Admin.viewSoldProduct(un,pass);
		case 4:
			Main.main(null);
		}
		
		
	}

	private static void viewSoldProduct(String un, String pass) {
		
		try {
			Connection conn = DBUtil.provideConnection();
			
			PreparedStatement ps = conn.prepareStatement("select * from soldProducts,seller,buyer where  soldProducts.buyerId is not null and soldProducts.buyerId=buyer.buyerId and soldProducts.sellerId=seller.sellerId order by productId");
			
			ResultSet rs = ps.executeQuery();
			
			System.out.println("------------------------------");
			
			while(rs.next()) {
				 
				
				int pid = rs.getInt("ProductId");
				String pname = rs.getString("productName");
				String sname = rs.getString("sellerName");
				String bname = rs.getString("buyerName");
				
				System.out.println("ProductId : "+pid);
				System.out.println("Product Name : "+pname);
				System.out.println("Seller name : "+sname);
				System.out.println("Buyer name : "+bname);
				
				System.out.println("===========================");
				
				
			}
			
			Admin.WecomeAdmin(un, pass);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void showBuyer(String un, String pass) {
		
		try {
			Connection conn = DBUtil.provideConnection();
			
			PreparedStatement ps = conn.prepareStatement("select * from buyer");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int bid = rs.getInt("buyerid");
				String name1 = rs.getString("buyername");
				String mail = rs.getString("buyermail");
				
				
				System.out.println("Name : "+name1);
				System.out.println("BuyerId : "+bid);
				System.out.println("Mail : "+mail);
				
				System.out.println("------------------------");
				
			}
			
			Admin.WecomeAdmin(un, pass);
			
		}
		catch(SQLException se){
			
		}
		
	}

	private static void showSeller(String un, String pass) {
		
		try {
			Connection conn = DBUtil.provideConnection();
			
			PreparedStatement ps = conn.prepareStatement("select * from seller");
			
			ResultSet rs = ps.executeQuery();
			
			
			
			while(rs.next()) {
				
				int bid = rs.getInt("sellerid");
				String name1 = rs.getString("sellername");
				String mail = rs.getString("sellermail");
				
				
				System.out.println("Name : "+name1);
				System.out.println("BuyerId : "+bid);
				System.out.println("Mail : "+mail);
				
				System.out.println("------------------------");
				
			}
			
			Admin.WecomeAdmin(un, pass);			
		}
		catch(SQLException se){
			
		}
		
	}

}
