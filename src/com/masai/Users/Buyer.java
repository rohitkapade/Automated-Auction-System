package com.masai.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.masai.main.Main;
import com.masai.utility.DBUtil;

public class Buyer {
	
	public static void Buyerwelcome(String name, String pass,int id){
		System.out.println("Welcome "+name);
		

		System.out.println("----------------------------");
		
		System.out.println("Enter the required operation...");
		
		System.out.println("1.View items by category");
		System.out.println("2.View all the buyers");
		System.out.println("3.View all the items");
		System.out.println("4.Select item to buy");
		System.out.println("5.Log-out");
		
		Scanner sc = new Scanner(System.in);
		
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:{
			Buyer.viewItemByCat(name,pass,id);
		}
		case 2:{
			Buyer.viewAllBuyers(name,pass,id);
		}
		case 3:{
			Buyer.ViewAllItems(name,pass,id);
		}
		case 4:{
			Buyer.BuyItems(name,pass,id);
		}
		case 5:{
			Main.main(null);
		}
		}
		
	}


	



	






	private static void viewItemByCat(String name, String pass,int id) {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Categories available");
		
		List<String> category = null;
		
		try {
			Connection conn = DBUtil.provideConnection();
			
			PreparedStatement ps = conn.prepareStatement("select productcategory from productForSale group by productcategory");
			
			ResultSet rs = ps.executeQuery();
			
			 category = new ArrayList<>();
			
			int i=1;
			while(rs.next()) {
				
				
				String pc = rs.getString("productcategory");
				category.add(pc);
				System.out.println(i+". "+pc);
				i++;
			}
			
		}
		catch(Exception e) {
			System.out.println("Error in product category");
		}
		
		
		
		
		System.out.println("Choose a category you want to look product of");
		int cat = sc.nextInt();
		
		int count = 1;
		for(String s:category) {
			
			
			if(cat==count) {
				
				
			
				Connection conn1 = DBUtil.provideConnection();
				
				PreparedStatement ps1;
				try {
					ps1 = conn1.prepareStatement("select * from productForSale where productcategory=?");
					
					
					ps1.setString(1,s);
					
					ResultSet rs = ps1.executeQuery();
					
					while(rs.next()) {
						
						int pid= rs.getInt("productId");
						String cate = rs.getString("productcategory");
						String pro = rs.getString("productName");
						int pri = rs.getInt("productPrice");
						int quan = rs.getInt("quantity");
						int sid = rs.getInt("sellerId");
						
						
						System.out.println("Productid : "+pid);
						System.out.println("Product category : "+cate);
						System.out.println("Product name : "+pro);
						System.out.println("Price : "+pri);
						System.out.println("Quantity : "+quan);
						System.out.println("Seller-Id : "+sid);
						
						System.out.println("==========================");
						
					}
					
					break;
					
					
				} catch (SQLException e) {
					
					System.out.println("Error in printng category product");
				}
					
			}
			
			count++;
		}
		
		Buyer.Buyerwelcome(name, pass, id);
		
	}
	
	
	
	
	private static void viewAllBuyers(String name, String pass, int id) {
		
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
			
			Buyer.Buyerwelcome(name, pass, id);
			
		}
		catch(SQLException se){
			
		}
		
	}
	
	
	private static void BuyItems(String name, String pass, int bid) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the productid to buy");
		int pid = sc.nextInt();
		
		
		try {
			Connection conn = DBUtil.provideConnection();
			
			PreparedStatement ps = conn.prepareStatement("update productForSale set sellerId=null , buyerId=? where productId=?");
			PreparedStatement ps1 = conn.prepareStatement("update soldProducts set buyerId=? where productId=?");
			
			ps.setInt(1, bid);
			ps.setInt(2, pid);
			ps1.setInt(1, bid);
			ps1.setInt(2, pid);
			
			int q = ps.executeUpdate();
			int r = ps1.executeUpdate();
			
			if(q>0 && r>0) {
				System.out.println("Product Bought successfully");
				
				System.out.println("-------------------------------");
				
				Buyer.Buyerwelcome(name, pass, pid);
			}
			else {
				System.out.println("Try again with valid productId");
			}
			
			
			
		}
		catch (Exception ex) {
			
			System.out.println("Try again with valid product id");
		}
		
		
		
	}
	
	
	private static void ViewAllItems(String name, String pass, int id) {
		
		Connection conn1 = DBUtil.provideConnection();
		
		PreparedStatement ps1;
		try {
			ps1 = conn1.prepareStatement("select * from productForSale ");
			
			
			ResultSet rs = ps1.executeQuery();
			
			while(rs.next()) {
				
				int pid= rs.getInt("productId");
				String cate = rs.getString("productcategory");
				String pro = rs.getString("productName");
				int pri = rs.getInt("productPrice");
				int quan = rs.getInt("quantity");
				int sid = rs.getInt("sellerId");
				
				
				System.out.println("Productid : "+pid);
				System.out.println("Product category : "+cate);
				System.out.println("Product name : "+pro);
				System.out.println("Price : "+pri);
				System.out.println("Quantity : "+quan);
				System.out.println("Seller-Id : "+sid);
				
				System.out.println("==========================");
				
			}
	
			
		} catch (SQLException e) {
			
			System.out.println("Error in printng category product");
		}
		
	}
	
	

	

}
