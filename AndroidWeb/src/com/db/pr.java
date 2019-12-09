package com.db;

import org.json.simple.JSONObject;

public class pr {

	public static void main(String[] args) {
		ConnectDB connectDB = ConnectDB.getInstance();
		
		JSONObject returns = connectDB.getBookList("admin");
		
		
		
		
		System.out.println(returns);
		

	}

}
