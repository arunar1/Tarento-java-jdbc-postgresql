package com.jdbc.tarento;

import java.sql.*;
import java.util.ArrayList;

public class GetTable {

    public ArrayList<String> getTable(Connection con) {
        ArrayList<String> tableNames = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = con.createStatement();
            String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                String tableName = rs.getString("table_name");
                tableNames.add(tableName);
            }
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
        return tableNames;
    }
}
