package com.masai.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.masai.Exception.SellerException;
import com.masai.main.Main;
import com.masai.utility.DBUtil;

public class Seller {
	
	public static void WelcomeSeller(String name, String pass, int id) throws SellerException{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome "+name);
		
		System.out.println("----------------------------");
		
		System.out.println("Enter the required operation...");
		
		System.out.println("1.List the items to sell");
		System.out.println("2.Update price / quantity");
		System.out.println("3.Remove items from the list");
		System.out.println("4.View the Product List");
		System.out.println("5.View the sold items history");
		System.out.println("6.Logout");
		
		int cho = sc.nextInt();
		
		if(cho==1) {
			Seller.ListItems(name,pass, id);
		}
		else if(cho==2) {
			Seller.UpdatePandQ(name,pass,id);
		}
		else if(cho==3) {
			Seller.RemoveItem(name,pass,id);
		}
		else if(cho==4) {
			Seller.ViewList(name,pass,id);
		}
		else if(cho==5) {
			Seller.SoldItemsHistory(name,pass,id);
		}
		else if(cho==6) {
			 Main.main(null);
		}
		
	}

	





	private static void SoldItemsHistory(String name, String pass, int id) {

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
			
			Seller.WelcomeSeller(name, pass, id);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}







	private static void UpdatePandQ(String name, String pass, int id) throws SellerException {
		
		
		System.out.println("1.Update price");
		System.out.println("2.Update quantity");
		
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		if(choice==1) {
			
			System.out.println("Enter the productId.");
			int pid = sc.nextInt();
			
			System.out.println("Enter updated price.");
			int price = sc.nextInt();
			
			
			try {
				Connection conn = DBUtil.provideConnection();
				
				PreparedStatement ps = conn.prepareStatement("update productForSale set productPrice=? where productId=? and sellerId=?");
				PreparedStatement ps1 = conn.prepareStatement("update soldProducts set productPrice=? where productId=? and sellerId=?");
				
				ps.setInt(1, price);
				ps.setInt(2, pid);
				ps.setInt(3, id);
				
				ps1.setInt(1, price);
				ps1.setInt(2, pid);
				ps1.setInt(3, id);
				
				
				int x =  ps.executeUpdate();
				ps1.executeUpdate();
				
				if(x>0) {
					System.out.println("Price updated successfully");
				}
				
			}
			catch(SQLException e) {
				System.out.println("error");
				
			}
		}
		else if(choice==2) {
			
			System.out.println("Enter the productId.");
			int pid = sc.nextInt();
			
			System.out.println("Enter updated quantity.");
			int quantity = sc.nextInt();
			
			
			try {
				Connection conn = DBUtil.provideConnection();
				
				PreparedStatement ps = conn.prepareStatement("update productForSale set quantity=? where productId=? and sellerId=?");
				PreparedStatement ps1 = conn.prepareStatement("update soldProducts set quantity=? where productId=? and sellerId=?");
				
				ps.setInt(1, quantity);
				ps.setInt(2, pid);
				ps.setInt(3, id);
				
				ps1.setInt(1, quantity);
				ps1.setInt(2, pid);
				ps1.setInt(3, id);
				
				
				int x =  ps.executeUpdate();
				ps1.executeUpdate();
				
				if(x>0) {
					System.out.println("Quantity updated successfully");
				}
				
			}
			catch(SQLException e) {
				System.out.println("Seller can update only his product details.");
				
			}
		}
		
		Seller.WelcomeSeller(name, pass, id);
		
	}

	private static void ListItems(String name, String pass,int id) throws SellerException {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of items to sell");
		int n = sc.nextInt();
		
		int x=0 ;
		
		int i = 0;
		while(i<n) {
			System.out.println("Enter detail of product "+(i+1));
			
			System.out.println("Enter product category");
			String cat = sc.next();
			
			System.out.println("Enter product name");
			String pro = sc.next();
			
			System.out.println("Enter product price");
			int price = sc.nextInt();
			
			System.out.println("Enter quantity of product");
			int quan = sc.nextInt();
			
			try {
				Connection conn = DBUtil.provideConnection();
				
				PreparedStatement ps = conn.prepareStatement("insert into productForSale(productcategory,productName,productPrice,quantity,sellerId)values(?,?,?,?,?)");
				PreparedStatement ps1 = conn.prepareStatement("insert into soldProducts(productcategory,productName,productPrice,quantity,sellerId)values(?,?,?,?,?)");
				
				ps.setString(1,cat);
				ps.setString(2, pro);
				ps.setInt(3, price);
				ps.setInt(4, quan);
				ps.setInt(5, id);
				
				
				ps1.setString(1,cat);
				ps1.setString(2, pro);
				ps1.setInt(3, price);
				ps1.setInt(4, quan);
				ps1.setInt(5, id);
				
				 x = ps.executeUpdate();
				 ps1.executeUpdate();
				
			}
			catch(SQLException e) {
				System.out.println("error");
				Seller.WelcomeSeller(name, pass, id);
				
			}
			
			i++;
		}
		
		if(x>0) {
			System.out.println("Product added to list");
			Seller.WelcomeSeller(name, pass, id);
			
		}
		else {
			System.out.println("Not added");
		}
		
		
	
	}
	
	
	
	private static void RemoveItem(String name, String pass, int id) {
		
		System.out.println("Enter the productId to remove the product");
		Scanner sc = new Scanner(System.in);
		int pid = sc.nextInt();
		
		try {
			Connection conn = DBUtil.provideConnection();
			
			PreparedStatement ps = conn.prepareStatement("delete from productForSale where productId=? and sellerId=?");
			ps.setInt(1, pid);
			ps.setInt(2, id);
			
			PreparedStatement ps1 = conn.prepareStatement("delete from soldProducts where productId=? and sellerId=?");
			ps1.setInt(1, pid);
			ps1.setInt(2, id);
			
			int x = ps.executeUpdate();
			ps1.executeUpdate();
			
			if(x>0) {
				System.out.println("Product successfully deleted");
				Seller.WelcomeSeller(name, pass, pid);
			}
			else {
				
				Seller.RemoveItem(name, pass, pid);
			}
			
			
		}
		catch(Exception e) {
			System.out.println("Error in delete operation");
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	
	private static void ViewList(String name, String pass, int id) {
		
		
		try {
			Connection conn = DBUtil.provideConnection();

			
			PreparedStatement ps = conn.prepareStatement("select * from productForSale");
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				int pid= rs.getInt("productId");
				String cat = rs.getString("productcategory");
				String pro = rs.getString("productName");
				int pri = rs.getInt("productPrice");
				int quan = rs.getInt("quantity");
				int sid = rs.getInt("sellerId");
				
				
				System.out.println("Productid : "+pid);
				System.out.println("Product category : "+cat);
				System.out.println("Product name : "+pro);
				System.out.println("Price : "+pri);
				System.out.println("Quantity : "+quan);
				System.out.println("Seller-Id : "+sid);
				
				System.out.println("==========================");
				
			}
		
			
			Seller.WelcomeSeller(name, pass, id);
			
		}
		catch(Exception e) {
			System.out.println("Product not found");
		}
			
		
		



	}
	
}


