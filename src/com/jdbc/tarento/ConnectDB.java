package com.jdbc.tarento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.*;

public class ConnectDB {

    public Connection connectToDb(String username, String password){

        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/Company";

        

        try {
        con = DriverManager.getConnection(url, username, password);
        if(con!=null){
            System.out.println("Connection Established");
        }
        else{
            System.out.println("Not connected");
        }

        } catch (Exception e) {

            System.out.println(e);
        }
        return con;

    }

    public String getResult(String sql,Connection con, String tableName){
        Statement statement;
        String name=null;

        try {

            statement = con.createStatement();
            ResultSet rs= statement.executeQuery(sql);
            rs.next();

            name=rs.getString(1);            
        } catch (Exception e) {
            System.out.println(e);
        }
        return name;
    }

    public void insertData(Connection con, String emp_name, int emp_id ){
        Statement statement;

        try {
            String sql = String.format("INSERT INTO employee (emp_id, emp_name) VALUES (%d, '%s')", emp_id, emp_name);
            statement = con.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Data inserted successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createTable(Connection con,String sql){
        Statement statement;

        try {
            statement = con.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table created Successfully.");
            
        } catch (Exception e) {
            System.out.println(e);
        }

        
    }
    public void deleteData(Connection con ,String tableName){
        
    }
}


        
    
