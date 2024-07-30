package com.jdbc.tarento;

import java.sql.*;
import java.util.ArrayList;

public class GetAttribute {

    public ArrayList<String> getAttribute(Connection con, String tableName) {
        ArrayList<String> tableAttri = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;

        String sql = "SELECT column_name FROM information_schema.columns WHERE table_name = '" + tableName + "';";

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                String attribute = rs.getString("column_name");
                tableAttri.add(attribute);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving table attributes: " + e.getMessage());
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

        return tableAttri;
    }
}
