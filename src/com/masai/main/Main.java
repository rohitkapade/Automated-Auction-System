package com.masai.main;

import java.util.Scanner;

import com.masai.Exception.BuyerException;
import com.masai.Exception.SellerException;
import com.masai.Login.AdminLogin;
import com.masai.Login.BuyerLogin;
import com.masai.Login.RegisterBuyer;
import com.masai.Login.RegisterSeller;
import com.masai.Login.SellerLogin;

public class Main {

	public static void main(String[] args) {

		System.out.println("Welcome to homepage of Automated Auction System");
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. Login as Admin");
		System.out.println("2. Login as Buyer");
		System.out.println("3. Login as Seller");
		System.out.println("4. Register as Buyer");
		System.out.println("5. Register as Seller");
		
		int choice = sc.nextInt();
		
		if(choice==1) {
			AdminLogin.main(args);
			
		}
		else if(choice==2) {
			
			try {
				BuyerLogin.main(args);
			} catch (BuyerException e) {
				e.getMessage();
				
			}
			
			
		}
		else if(choice==3) {
			
			try {  
				SellerLogin.main(args);
			} catch (SellerException e) {
				System.out.println(e.getMessage());
			}
			
		}
		else if(choice==4) {
			
			RegisterBuyer.main(args);
			
		}
		else if(choice==5) {
			RegisterSeller.main(args);
		}
		
		
	}

}
