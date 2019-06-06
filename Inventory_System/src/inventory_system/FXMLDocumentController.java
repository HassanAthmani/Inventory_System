/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import inventory_system.validator.Validator;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TableView<instock> tableInstock;

    @FXML
    private TableColumn<instock, String> columnId;

    @FXML
    private TableColumn<instock, String> columnMac;

    @FXML
    private TableColumn<instock, String> columnBrand;

    @FXML
    private TableColumn<instock, String> columnModel;

    @FXML
    private TableColumn<instock, String> columnVersion;

    @FXML
    private TableColumn<instock, String> columnDate;

    @FXML
    private TextField textBrand;

    @FXML
    private TextField textMac;

    @FXML
    private TextField textModel;

    @FXML
    private TextField textVersion;

    @FXML
    private Button addRecord;

    @FXML
    private Button loadData;

    public static String userName = "root";
    public static String password = "";

    public Connection connection;
    //Initialize observable list 29:57
    private ObservableList<instock> data;

    @FXML
    void addData(ActionEvent event) throws SQLException {

        String txtBrand = textBrand.getText();
        String txtMac = textMac.getText();
        String txtModel = textModel.getText();
        String txtVersion = textVersion.getText();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();

            

            String sql = "INSERT INTO `inventory_project`.`instock` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`) VALUES (NULL,'" + txtMac + "','" + txtBrand + "','" + txtModel + "','" + txtVersion + "', CURDATE() );";

            statement.executeUpdate(sql);

            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM inventory_project.instock");

            while (rs.next()) {
                //get string from db
                data.add(new instock(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMac.setCellValueFactory(new PropertyValueFactory<>("mac_address"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("addition_date"));

        tableInstock.setItems(null);
        tableInstock.setItems(data);

    }

    @FXML
    void loadFromDB(ActionEvent event) throws SQLException {
        //DB connection details
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "inventory_project";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM instock");

            while (rs.next()) {
                //get string from db
                data.add(new instock(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
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

        tableInstock.setItems(null);
        tableInstock.setItems(data);

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        //setting scene variable
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene newScene = new Scene(sceneFxml);

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //setting scene on stage
        window.setScene(newScene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
