package inventory_system.login;

import static inventory_system.maintanance.InMaintananceController.password;
import static inventory_system.maintanance.InMaintananceController.userName1;
import inventory_system.maintanance.maintanance;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

public class login implements Initializable {
    
    @FXML
    private TextField username1;

    @FXML
    private PasswordField password1;

    @FXML
    private Button login;
    
     public Connection connection;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;




    @FXML
    void loginToDashboard(ActionEvent event) throws SQLException {
        
        String usrname = username1.getText();
        String pass = password1.getText();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "inventory_project";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName1, password);
            
            try{
            String sql = "SELECT * FROM login WHERE username = ? and password = ?";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usrname );
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                
                int response = JOptionPane.showConfirmDialog(
                    null, "Please enter correct username and Password", "Login Failed!", JOptionPane.DEFAULT_OPTION);
            }else{
                
                //infoBox("Login Successful",null,"Success" );
                int response = JOptionPane.showConfirmDialog(
                    null, "Login Successful", "Success", JOptionPane.DEFAULT_OPTION);
                
             //setting scene variable
                
            Parent sceneFxml = FXMLLoader.load(getClass().getResource("/inventory_system/dashboard.fxml"));
           Scene newScene = new Scene(sceneFxml);

            //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //setting scene on stage
            window.setScene(newScene);
            window.show();
            
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

            

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }
    
        
        
        /*lightcyan #E0FFFF
         * CYAN #00FFFF
          * AQUA #00FFFF
          **/
        

    }
    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    //username1.getScene().getStylesheets().add("/stylesheet/background.css");
    }
}