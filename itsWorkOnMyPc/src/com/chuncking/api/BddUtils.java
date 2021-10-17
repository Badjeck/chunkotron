package com.chuncking.api;

import java.sql.*;

public class BddUtils {
    private static Connection conn;
    public BddUtils(String IP,String port,String bddName,String username,String password) {
        String dbURL = "jdbc:mysql://"+ IP + ":"+port+"/"+bddName+"?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false";

        try {
            conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insert(String id, byte[] data) throws SQLException {
        String sql = "INSERT into dataChunk (id,chunk)" + " VALUES (?,?)";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setString (1, id);
        preparedStmt.setBytes (2, data);
        preparedStmt.execute();

    }

    public void selectAll() throws SQLException {
        String query = "SELECT * FROM dataChunk";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
            String id = rs.getString("id");
            byte[] data = rs.getBytes("chunk");

            // print the results
            System.out.format("%s, %s\n", id, data);
        }

    }

    public byte[] selectById(String id) throws SQLException {
        String query = "SELECT * FROM dataChunk WHERE id = ?";
        byte[] data = new byte[0];
                
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1,id);
        ResultSet rs = preparedStmt.executeQuery();
        while (rs.next())
        {
            String ok = rs.getString("id");
            data = rs.getBytes("chunk");
        }
        return data;
    }

    public void delete(String id) throws SQLException {
        String query = "DELETE FROM dataChunk WHERE id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, id);
        preparedStmt.execute();
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM dataChunk";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();
    }


    public void updateOnId(String id,byte[] data) throws SQLException {
        String query = "update dataChunk set chunk = ? where id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setBytes(1, data);
        preparedStmt.setString   (2, id);

        // execute the java prepared statement
        preparedStmt.executeUpdate();
    }
}
