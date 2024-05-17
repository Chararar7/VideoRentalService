/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication9;


import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Statement;
import java.sql.Connection;
/**
 *
 * @author ALI CHARARA
 */
public class dbconnection {
    
  private static Connection C;
       private final static String DB_URL = "jdbc:mysql://localhost:3306/movies_db";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "ali811";
    public static Connection Connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if(C==null) C = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            return C; 
             

        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("a7a");
        }
        return null;
    }
    
 
        
}
    
    

