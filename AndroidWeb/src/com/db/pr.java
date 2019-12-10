package com.db;

import java.io.IOException;

import org.json.simple.JSONObject;

public class pr {

	public static void main(String[] args) throws IOException {
		ConnectDB connectDB = ConnectDB.getInstance();
		
		JSONObject returns = connectDB.getBookList("admin");
		
		connectDB.relation();
	}

}
