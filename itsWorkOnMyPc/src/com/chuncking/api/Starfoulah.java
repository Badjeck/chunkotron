package com.chuncking.api;

import java.sql.*;

public class Starfoulah {
    private static Connection conn;
    public static void connect(String IP,String port,String base,String username,String password) {
        String dbURL = "jdbc:mysql://"+ IP + ":"+port+"/"+base+"?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false";

        try {
            conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insert(int id, String data) throws SQLException {
        String sql = "INSERT into dataChunk (id,chunk)" + " VALUES (?,?)";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setInt (1, id);
        preparedStmt.setString (2, data);
        preparedStmt.execute();

    }

    public static void selectAll() throws SQLException {
        String query = "SELECT * FROM dataChunk";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
            int id = rs.getInt("id");
            String data = rs.getString("chunk");

            // print the results
            System.out.format("%s, %s\n", id, data);
        }

    }

    public static void selectById(int id) throws SQLException {
        String query = "SELECT * FROM dataChunk WHERE id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1,id);
        ResultSet rs = preparedStmt.executeQuery();
        while (rs.next())
        {
            int ok = rs.getInt("id");
            String data = rs.getString("chunk");

            // print the results
            System.out.format("%s, %s\n", ok, data);
        }
    }

    public static void delete(int id) throws SQLException {
        String query = "DELETE FROM dataChunk WHERE id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, id);
        preparedStmt.execute();
    }

    public static void deleteAll() throws SQLException {
        String query = "DELETE FROM dataChunk";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();
    }


    public static void updateOnId(int id,String data) throws SQLException {
        String query = "update dataChunk set chunk = ? where id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, data);
        preparedStmt.setInt   (2, id);

        // execute the java prepared statement
        preparedStmt.executeUpdate();
    }
}
