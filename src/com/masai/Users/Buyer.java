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
		System.out.println("3.Select items to buy");
<<<<<<< day4
=======
		System.out.println("4.Log-out");
>>>>>>> local
		
		Scanner sc = new Scanner(System.in);
		
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
<<<<<<< day4
			Buyer.viewItemByCat(name,pass);
		case 2:
			Buyer.viewAllBuyers();
		}
	}



	private static void viewItemByCat(String name, String pass) {
=======
			Buyer.viewItemByCat(name,pass,id);
		case 2:
			Buyer.viewAllBuyers(name,pass,id);
		case 3:
			Buyer.BuyItems(name,pass,id);
		}
		
	}


	



	private static void viewItemByCat(String name, String pass,int id) {
>>>>>>> local
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
		
<<<<<<< day4
		Buyer.Buyerwelcome(name, pass);
		
	}
	
	
	
	
	private static void viewAllBuyers() {
		
		
=======
		Buyer.Buyerwelcome(name, pass, id);
>>>>>>> local
		
	}
	
	
	
	
	private static void viewAllBuyers(String name, String pass, int id) {
		
		try {
			Connection conn = DBUtil.provideConnection();
			
			PreparedStatement ps = conn.prepareStatement("select * from buyer");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("buyerid");
				String name1 = rs.getString("buyername");
				String mail = rs.getString("buyermail");
				
				
				System.out.println("Name : "+name1);
				System.out.println("BuyerId : "+id);
				System.out.println("Mail : "+mail);
				
			}
			
			Buyer.Buyerwelcome(name, pass, id);
			
		}
		catch(SQLException se){
			
		}
		
	}
	
	
	private static void BuyItems(String name, String pass, int id) {
		
		
	}
	
	

	

}
