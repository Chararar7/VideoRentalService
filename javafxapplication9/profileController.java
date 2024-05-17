/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication9;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.*;
import java.time.*;
import java.util.Date;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import static javafxapplication9.LoginController.user;

import javafx.event.ActionEvent;

import javafx.collections.FXCollections;


public class profileController implements Initializable {
    
    
    @FXML
    private TableView<Transaction> history;

    @FXML
    private TableColumn<Transaction, String> movname;

    @FXML
    private TableColumn<Transaction, Date> rdate;

    @FXML
    private TableColumn<Transaction, Date> retdate;


ObservableList<Transaction> transactions=FXCollections.observableArrayList();
 @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        movname.setCellValueFactory(new PropertyValueFactory<>("MovieName"));
        rdate.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));
        retdate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }
       public void showtransaction(ActionEvent event) throws SQLException
    {
        transactions = FXCollections.observableArrayList();

         Connection C=dbconnection.Connect();
           String query = "SELECT t.id, m.title, t.rental_date, t.return_date FROM transactions t JOIN movies m ON t.movie_id = m.id WHERE t.user_id = ?";
PreparedStatement stmt = (PreparedStatement) C.prepareStatement(query);
stmt.setInt(1, user.getId());
ResultSet rs = stmt.executeQuery();

      
          while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("t.id"));
                transaction.setMovieName(rs.getString("m.title"));
               
                
                transaction.setRentalDate(rs.getDate("t.rental_date"));
                transaction.setReturnDate(rs.getDate("t.return_date"));
               
                transactions.add(transaction);
            }
          history.setItems(transactions);
      
    }
      public void returnmovies() throws SQLException
       {
            for(Movie m:FXMLDocumentController.selectedlist)
        {
            Connection C=dbconnection.Connect();
            String query ="INSERT INTO transactions (`user_id`, `movie_id`, `rental_date`) VALUES (?, ?, ?)";
       PreparedStatement stmt = (PreparedStatement) dbconnection.Connect().prepareStatement(query);
       stmt.setInt(1, user.getId());
        stmt.setInt(2, m.getId());
        
        
        // Get the current date
stmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));


 PreparedStatement stmt1 = C.prepareStatement(
                "UPDATE movies SET rented = ? WHERE title = ?"
            );
            stmt1.setBoolean(1, false);
            stmt1.setString(2, m.getTitle());

       }

}
}
