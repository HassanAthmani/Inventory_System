/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system.all_items;

import static inventory_system.InstockDocumentController.password;
import static inventory_system.InstockDocumentController.userName;
import inventory_system.instock;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class All_itemsUIController implements Initializable {

   @FXML
    private TableView<all_items> tableAll;

    @FXML
    private TableColumn<all_items, String> columnId;

    @FXML
    private TableColumn<all_items, String> columnMac;

    @FXML
    private TableColumn<all_items, String> columnBrand;

    @FXML
    private TableColumn<all_items, String> columnModel;

    @FXML
    private TableColumn<all_items, String> columnVersion;

    @FXML
    private TableColumn<all_items, String> columnDate;

    @FXML
    private Button LoaData;

    @FXML
    private Button back;
    
    public static String userName = "root";
    public static String password = "";

    public Connection connection;
    //Initialize observable list 29:57
    private ObservableList<all_items> data;

    @FXML
    void DashboardBoard(ActionEvent event) throws IOException {
        //setting scene variable
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/inventory_system/dashboard.fxml"));
        Scene newScene = new Scene(sceneFxml);

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //setting scene on stage
        window.setScene(newScene);
        window.show();

    }

    @FXML
    void LoadDataFrmDB(ActionEvent event) throws SQLException {
        //DB connection details
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "inventory_project";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM all_items");

            while (rs.next()) {
                //get string from db
                data.add(new all_items(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMac.setCellValueFactory(new PropertyValueFactory<>("mac_address"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("addition_date"));

        tableAll.setItems(null);
        tableAll.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
