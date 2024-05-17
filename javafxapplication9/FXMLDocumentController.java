/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication9;

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

/**
 *
 * @author ALI CHARARA
 */
public class FXMLDocumentController implements Initializable {
    
 
    
    @FXML
    private TextField searchfield;
    @FXML
    private TableColumn<Movie, String> directorColumn;

    @FXML
    private TableView<Movie> moviesTable;

    @FXML
    private TableColumn<Movie, String> summaryColumn;

    @FXML
    private TableColumn<Movie, String> titleColumn;
    @FXML
    private ComboBox<String> genre;
    @FXML
    private TableView<Movie> selectedmovies;
    
    @FXML
    private TableColumn<Movie, String> name;
    
      @FXML
    private Button profile;
    
    ObservableList<Movie> list;
    Connection c=null;
    private static int selectedcounter=0;
   
    
          

        
        private final User user=LoginController.user;
    
    protected static ObservableList<Movie> selectedlist = FXCollections.observableArrayList();
     
    public void show_movies() throws SQLException{
        c=dbconnection.Connect();
           Statement stmt;
      ResultSet rs ;
      
            stmt = c.createStatement();
         String sql = "SELECT movies.title, movies.summary, directors.name AS director_name " +
                      "FROM movies " +
                      "JOIN directors ON movies.director_id = directors.id";
         rs = stmt.executeQuery(sql);

         // Extract data from result set
         while(rs.next()) {
            // Retrieve by column name
            String title = rs.getString("title");
            String summary = rs.getString("summary");
            String directorName = rs.getString("director_name");
           Movie M=new Movie(title,directorName,summary);
               if(!list.contains(M)) list.add(M);
            
        }
            moviesTable.setItems(list);
}
  
    @Override
  
public void initialize(URL url, ResourceBundle rb) {
    try {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        summaryColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));
      
        name.setCellValueFactory(new PropertyValueFactory<>("title"));
        profile.setText(user.getUsername());
        ObservableList<String> List;
        List = FXCollections.observableArrayList("Action","Comedy","Drama");
       
        genre.setItems(List);
        list = FXCollections.observableArrayList();
        c=dbconnection.Connect();
        Statement stmt;
        ResultSet rs ;

        stmt = c.createStatement();
        String sql = """
                     SELECT movies.*, directors.name AS director_name
                     FROM movies
                     JOIN directors ON movies.director_id = directors.id
                     WHERE movies.rented = false AND movies.availability = true;""";
        rs = stmt.executeQuery(sql);

        // Extract data from result set
        while(rs.next()) {
            // Retrieve by column name
            String title = rs.getString("title"); // retrieves the title column from the movies table
            String summary = rs.getString("summary"); // retrieves the summary column from the movies table
            String directorName = rs.getString("director_name"); // retrieves the director_name column from the joined directors table
            int movieId = rs.getInt("id"); // retrieves the id column from the movies table
            boolean availability = rs.getBoolean("availability"); // retrieves the availability column from the movies table
            String theme = rs.getString("theme"); // retrieves the theme column from the movies table
            boolean rented = rs.getBoolean("rented"); // retrieves the rented column from the movies table
            boolean damaged = rs.getBoolean("damaged"); // retrieves the damaged column from the movies table

            list.add(new Movie(movieId,title,directorName,summary, availability,theme, rented, damaged));

        }
        moviesTable.setItems(list);
    } catch (SQLException ex) {
        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }
    searchFilter();
    
    // Initialize selectedmovies TableView
    TableColumn<Movie, String> selectedNameColumn = new TableColumn<>("Title");
    selectedNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    selectedmovies.getColumns().add(selectedNameColumn);

  

    moviesTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>() {
        @Override
        public void changed(ObservableValue<? extends Movie> obs, Movie oldSelection, Movie newSelection) {
            if (newSelection != null && selectedcounter<3 && user.getType()==1) {
                // Work with the selected row
                
                Movie M = moviesTable.getSelectionModel().getSelectedItem();
               
                
                if (!selectedlist.contains(M)) {
                    selectedlist.add(M);
                    selectedcounter++;
                   
                }
                 if (newSelection != null && selectedcounter<1 && user.getType()==2) {
                // Work with the selected row
                
                 M = moviesTable.getSelectionModel().getSelectedItem();
                // Do something with the selectedObj
                
                if (!selectedlist.contains(M)) {
                    selectedlist.add(M);
                    selectedcounter++;
                   
                }
                
                 }
                   selectedmovies.setItems(selectedlist);
            }
        }
    });
}
  
    public void checkout(ActionEvent event) throws SQLException
    {
        for(Movie m:selectedlist)
        {
            Connection C=dbconnection.Connect();
            String query ="INSERT INTO transactions (`user_id`, `movie_id`, `rental_date`) VALUES (?, ?, ?)";
       PreparedStatement stmt = (PreparedStatement) dbconnection.Connect().prepareStatement(query);
       stmt.setInt(1, user.getId());
        stmt.setInt(2, m.getId());
        
        
        // Get the current date
stmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
int rowsAffected = stmt.executeUpdate();

 PreparedStatement stmt1 = c.prepareStatement(
                "UPDATE movies SET rented = ? WHERE title = ?"
            );
            stmt1.setBoolean(1, true);
            stmt1.setString(2, m.getTitle());

            // Execute the update
            int rowsUpdated = stmt1.executeUpdate();

            if (rowsUpdated == 0) {
                // Handle the case where no rows were updated
                System.out.println("No rows were updated.");
            } else {
                System.out.println("Updated " + rowsUpdated + " rows.");
            }
System.out.println(rowsAffected);
        }
    }
   /*public void showtransaction(ActionEvent event) throws SQLException
    {
        transactions = FXCollections.observableArrayList();

         Connection C=dbconnection.Connect();
           String query = "SELECT t.id, m.title, t.rental_date, t.return_date FROM transactions t JOIN movies m ON t.movie_id = m.id WHERE t.user_id = ?";
PreparedStatement stmt = C.prepareStatement(query);
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
      
    }*/
    public void searchFilter()
    {
        FilteredList<Movie> flist=new FilteredList<>(list,e->true);
        searchfield.setOnKeyReleased(e->{
            
            searchfield.textProperty().addListener((observable,oldvalue,newvalue) ->{
                flist.setPredicate((Predicate<? super Movie>)cust->{
                    String toLowerCaseFilter = newvalue.toLowerCase();
                  if(cust.getTitle().toLowerCase().contains(toLowerCaseFilter)){
                    return true;
                }else  if(cust.getDirector().toLowerCase().contains(toLowerCaseFilter)){
                    return true;
                }else  if(cust.getSummary().toLowerCase().contains(toLowerCaseFilter)){
                    return true;
                }
                 return false;
                }) ;
            }) ;
            
              final SortedList<Movie> customers = new SortedList<>(flist);
           customers.comparatorProperty().bind(moviesTable.comparatorProperty());
           moviesTable.setItems(customers);
            
        });
       
    }
    public void showprofile(ActionEvent event) throws IOException
    {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
