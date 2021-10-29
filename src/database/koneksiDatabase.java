/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author hp
 */
public class koneksiDatabase {
    public static String escape(String input) {
        return input.replace("\\", "\\\\")
                .replace("\b", "\\b")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t")
                .replace("\"", "\\\"")
                .replace("'", "\\'");
    }
    public static java.sql.Connection conn;
    private static Properties propert = new Properties();
    public static void insert(String TableName, Object[][] Values) throws SQLException, IOException {
        getKoneksi();
        ArrayList<String> questionMark = new ArrayList<String>();
        ArrayList<String> field = new ArrayList<String>();

        for (int i = 0; i < Values.length; i++) {
            if (Values[i][0].toString().matches("^[\\w ]+$")) {
                field.add("`" + Values[i][0].toString() + "`");
            } else {
                throw new SQLException("Invalid field format");
            }
        }

        for (int i = 0; i < Values.length; i++) {
            questionMark.add("?");
        }

        String query = "INSERT INTO `" + TableName + "` (" + String.join(",", field) + ") VALUES ( " + String.join(",", questionMark) + " );";
        System.out.println(query);
        PreparedStatement ps = conn.prepareStatement(query);
        int index = 1;
        for (Object obj[] : Values) {
            if(obj[1] == null){
                 ps.setString(index, null);
            }
            else if (obj[1] instanceof String) {
                ps.setString(index, obj[1].toString());
            } else if (obj[1] instanceof Integer) {
                ps.setInt(index, (int) obj[1]);
            } else if (obj[1] instanceof Double) {
                ps.setDouble(index, (double) obj[1]);
            } else if (obj[1] instanceof Boolean) {
                ps.setBoolean(index, (boolean) obj[1]);
            } else if (obj[1] instanceof Long) {
                ps.setLong(index, (long) obj[1]);
            } else if (obj[1] instanceof java.sql.Date) {
                ps.setDate(index, (java.sql.Date) obj[1]);
            } else if (obj[1] instanceof Float) {
                ps.setFloat(index, (float) obj[1]);
            }
            index++;
        }
        ps.executeUpdate();
    }
    
    public static java.sql.Connection getKoneksi() throws FileNotFoundException, IOException, SQLException {
        if (conn == null) {
            try {
                propert.load(new FileInputStream("C:\\Users\\hp\\Documents\\NetBeansProjects\\Get Your Dimsum\\src\\database\\konfigurasiDatabase.properties"));
            conn = (java.sql.Connection) DriverManager.getConnection(propert.getProperty("jdbc.url"),
                    propert.getProperty("jdbc.username"), propert.getProperty("jdbc.password"));
            } catch(SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            }
            
        }
        
        return conn;
    }
    public static ResultSet get_where(String TableName, Object[][] Condition) throws SQLException, IOException {
        
        getKoneksi();
        ArrayList<String> condition = new ArrayList<>();
        for (Object obj[] : Condition) {
            StringBuilder sb = new StringBuilder();
            if (obj[0].getClass() == String.class) {
                sb.append("`" + escape(obj[0].toString()) + "` = ");
                sb.append("'" + escape(obj[1].toString()) + "'");
                condition.add(sb.toString());
            } else {
                throw new SQLException("Table field must be a string");
            }
        }

        String query = "SELECT * FROM `" + TableName + "` WHERE " + String.join(" AND ", condition);
        return conn.createStatement().executeQuery(query);
    }
    public static void main(String[] args) throws IOException, FileNotFoundException, SQLException {
        if (getKoneksi().equals(conn)) {
            System.out.println("Sukses Terkoneksi");
        } else {
            System.out.println("Gagal Terkoneksi");
        }
    }
    
}
