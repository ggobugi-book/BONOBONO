package com.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConnectDB {
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }
    public ConnectDB() {  }

    // oracle 계정
    String jdbcUrl = "jdbc:oracle:thin:@192.168.62.1:1521:orcl";
    String userId = "ggbook";
    String userPw = "1234";

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;

    String sql = "";
    String sql2 = "";
    String returns = "a";

    public String connectionDB(String id, String pwd) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT userid FROM userinfo WHERE userid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                returns = "1";//로그인 성공
            } else {
                returns = "2";//로그인 실패
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns;
    }
    
    public void joinUser(String id,String pwd) {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            sql = "insert into userinfo(userid,userpwd) values(?,?)";
            
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            
            pstmt.executeQuery();
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    public JSONObject getBookList(String id) {
    	JSONObject returns=new JSONObject();
    	
    	JSONObject obj = new JSONObject();
        JSONArray componentArray = new JSONArray();
    	
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            stmt = conn.createStatement();
            
            sql="select * from books";
            rs = stmt.executeQuery(sql);
            
            
            while(rs.next()) {
            	JSONObject object = new JSONObject();
            	
            	object.put("title", rs.getString("title"));
            	object.put("filename", rs.getString("filename"));
            	object.put("page", rs.getString("page"));
            	componentArray.add(object);
            }
            
            obj.put("result", componentArray.toString());
            
            String colsql = "select COLUMN_NAME FROM COLS WHERE TABLE_NAME = 'PAGE'";
            ResultSet colrs = stmt.executeQuery(colsql);
            
            
            JSONArray pagearray = new JSONArray();
            
            while(colrs.next()) {
            	
            	String sqlpage = "select "+colrs.getString(1)+" from page where userid = ?";
            	pstmt = conn.prepareStatement(sqlpage);
            	
            	pstmt.setString(1,id);
            	
            	ResultSet pageresult = pstmt.executeQuery();
            	while(pageresult.next()) {
            		JSONObject objj = new JSONObject();
            		objj.put(colrs.getString(1), pageresult.getString(1));
            		pagearray.add(objj);
            	}
            }
            obj.put("page", pagearray);
            
            
            
            returns = obj;

    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	return returns;
    }
    
    public void updatePage(String userid,String title,String page) {
    	
   	
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            
            String sql = "update page set "+title+" = ? where userid = ? ";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, Integer.parseInt(page));
            pstmt.setString(2, userid);
            
            
            pstmt.executeUpdate();
            
            System.out.println("업데이트 완료");
            
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
 
    }
    
    public String relation() throws IOException {
    	
    	String s="";
    	String returns="";
    	
    	 Process process = Runtime.getRuntime().exec("python F:\\IT\\JAVA\\workspace\\AndroidWeb\\test.py");
         
         BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream(),"MS949"));
         BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream(),"MS949"));
         
         while((s= stdInput.readLine()) != null) {
        	 returns = returns.concat(s+"\n");
        	 //System.out.println(s);
         }
         
         
         while((s = stdError.readLine()) != null) {
            System.out.println(s);
         }
    	
    	return returns;
    }
    public void updateMemo(String userid,String title,String memo) {
    	
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            
            String sql = "update memo set "+title+" = ? where userid = ? ";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, memo);
            pstmt.setString(2, userid);
            
            System.out.println("memo : "+memo);
            
            pstmt.executeUpdate();
            
            System.out.println("업데이트 완료");
            
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    }
    public String getMemo(String userid,String title) {
    	
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            
            String sql = "select "+title+" from memo where userid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                returns = rs.getString(1);//로그인 성공
            } else {
                returns = "2";//로그인 실패
            }
            
            
            
            System.out.println("업데이트 완료");
            
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	
    	return returns;
    }
    
    
    
    
}
