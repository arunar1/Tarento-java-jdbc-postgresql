package com.jdbc.tarento;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GetTable {

    public String getTable(Connection con, Scanner sc) {
        ArrayList<String> tableNames = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        String selectedTable = null;

        try {
            statement = con.createStatement();
            String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';";
            rs = statement.executeQuery(query);

            while (rs.next()) {
                String tableName = rs.getString("table_name");
                tableNames.add(tableName);
            }

            System.out.println("Tables in the database:");
            for (int i = 0; i < tableNames.size(); i++) {
                System.out.println((i + 1) + " : " + tableNames.get(i));
            }

            System.out.print("Enter the table name index: ");
            int index = sc.nextInt();
            sc.nextLine(); 
            selectedTable = tableNames.get(index - 1);
        } catch (SQLException e) {
            System.out.println("Error retrieving table names: " + e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }

        return selectedTable;
    }
}
