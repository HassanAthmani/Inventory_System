/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DashboardController implements Initializable {
    
     @FXML
    private AnchorPane anchor;

    @FXML
    private Button AllItems;
    @FXML
    private Button Maintain;
    @FXML
    private Button instock;
    @FXML
    private Button Deferred;
    @FXML
    private Button withUser;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //  
        // TODO
    }    

    @FXML
    private void ShowAllItems(ActionEvent event) throws IOException {
        //setting scene variable
        Parent sceneFxml= FXMLLoader.load(getClass().getResource("all_items/all_itemsUI.fxml"));
        Scene newScene=new Scene(sceneFxml);
        
        //getting stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        //setting scene on stage
        window.setScene(newScene);
        window.show();
        
        
    }

    @FXML
    private void ShowMaintain(ActionEvent event) throws IOException {
        
        //setting scene variable
        Parent sceneFxml= FXMLLoader.load(getClass().getResource("maintanance/MaintananceForm.fxml"));
        
        Scene newScene=new Scene(sceneFxml);
        
        //getting stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        //setting scene on stage
        window.setScene(newScene);
        window.show();
    }

    
    

    @FXML
    private void ShowInstock(ActionEvent event) throws IOException {
        //setting scene variable
        Parent sceneFxml= FXMLLoader.load(getClass().getResource("instock.fxml"));
        Scene newScene=new Scene(sceneFxml);
        
        //getting stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        //setting scene on stage
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void ShowDeferred(ActionEvent event) throws IOException {
        //setting scene variable
        Parent sceneFxml= FXMLLoader.load(getClass().getResource("/inventory_system/EditUI/deferredForm.fxml"));
        Scene newScene=new Scene(sceneFxml);
        
        //getting stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        //setting scene on stage
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void ShowWithUser(ActionEvent event) throws IOException {
        //setting scene variable
        Parent sceneFxml= FXMLLoader.load(getClass().getResource("/inventory_system/giveUser/giveUser.fxml"));
        Scene newScene=new Scene(sceneFxml);
        
        //getting stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        //setting scene on stage
        window.setScene(newScene);
        window.show();
        
    }
    
    @FXML
    void LogOut(ActionEvent event) throws IOException {
         //setting scene variable
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/inventory_system/login/loginForm.fxml"));
        Scene newScene = new Scene(sceneFxml);

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //setting scene on stage
        window.setScene(newScene);
        window.show();
    }

}
