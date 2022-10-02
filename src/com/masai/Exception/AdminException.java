package com.masai.Exception;

import com.masai.main.Main;

public class AdminException extends Exception {
	
	AdminException(){
		
	}
	
	
	public AdminException(String msg){
		super(msg);
		
		Main.main(null);
	}

}
