/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system.maintanance;

import static inventory_system.InstockDocumentController.userName;
import static inventory_system.giveUser.ShowGivenMachinesController.password;
import inventory_system.giveUser.giveUser;
import static inventory_system.maintanance.maintananceController.password;
import static inventory_system.maintanance.maintananceController.userName1;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class InMaintananceController implements Initializable {

    @FXML
    private TableView<maintanance> tableInMaintanance;

    @FXML
    private TableColumn<maintanance, String> columnId2;

    @FXML
    private TableColumn<maintanance, String> columnMac2;

    @FXML
    private TableColumn<maintanance, String> columnBrand2;

    @FXML
    private TableColumn<maintanance, String> columnModel2;

    @FXML
    private TableColumn<maintanance, String> columnVersion2;

    @FXML
    private TableColumn<maintanance, String> columnAddition_date2;

    @FXML
    private TableColumn<maintanance, String> columnWorker_name2;

    @FXML
    private TableColumn<maintanance, String> columnAssigned_date;

    @FXML
    private TableColumn columnEdit2;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMac;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtVersion;

    @FXML
    private TextField txtDate;

    @FXML
    private Button btnLoad;
    
    public static String userName1 = "root";
    public static String password = "";
    
    public Connection connection;
    //Initialize observable list 29:57
    private ObservableList<maintanance> data;

    @FXML
    void back(ActionEvent event) throws IOException {
        //setting scene variable
        Parent sceneFxml= FXMLLoader.load(getClass().getResource("/inventory_system/maintanance/MaintananceForm.fxml"));
        Scene newScene=new Scene(sceneFxml);
        
        //getting stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        //setting scene on stage
        window.setScene(newScene);
        window.show();

    }

    @FXML
    void loadMachines(ActionEvent event) throws SQLException {
        
        //ENABLE USER TO EDIT TEXTFIELDS                               
        txtBrand.setEditable(false);
        txtMac.setEditable(false);
        txtModel.setEditable(false);
        txtVersion.setEditable(false);
        txtId.setEditable(false);
        txtDate.setEditable(false);
        //DB connection details
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "inventory_project";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName1, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM in_maintanance");

            while (rs.next()) {
                //get string from db
                data.add(new maintanance(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        columnId2.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMac2.setCellValueFactory(new PropertyValueFactory<>("mac_address"));
        columnBrand2.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columnModel2.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnVersion2.setCellValueFactory(new PropertyValueFactory<>("version"));
        columnAddition_date2.setCellValueFactory(new PropertyValueFactory<>("addition_date"));
        columnWorker_name2.setCellValueFactory(new PropertyValueFactory<>("worker_name"));
        columnAssigned_date.setCellValueFactory(new PropertyValueFactory<>("date_assigned"));

        tableInMaintanance.setItems(null);
       tableInMaintanance.setItems(data);
       
       //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<maintanance,String>,TableCell<maintanance,String>> cellFactory=(param)->{
         
            //make the tablecell containing button
            final TableCell<maintanance,String> cell=new TableCell<maintanance,String>(){
                
                //override updateItem method
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    
                    //ensure that cell is created only on non-empty rows
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        //Now we create the action button
                        final Button editButton=new Button("EDIT");
                        //attach listener on button
                        editButton.setOnAction(event ->{
                            
                                //Extract the clicked object
                                maintanance p=getTableView().getItems().get(getIndex());
                                
                                //DATA 
                                //System.out.println(p.getMac_address());  String.valueOf(p.getId())
                                
                               txtBrand.setText(String.valueOf(p.getBrand()));
                               txtMac.setText(String.valueOf(p.getMac_address()));
                               txtModel.setText(String.valueOf(p.getModel()));
                               txtVersion.setText(String.valueOf(p.getVersion()));
                               txtId.setText(String.valueOf(p.getId()));
                               txtDate.setText(String.valueOf(p.getAddition_date()));
                               
                               //PREVENT USER FROM EDITING TEXTFIELDS                               
                               txtBrand.setEditable(false);
                               txtMac.setEditable(false);
                               txtModel.setEditable(false);
                               txtVersion.setEditable(false);  
                               txtId.setEditable(false); 
        

                            
             
                    });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }
                
                };
                    
            };
            
            return cell;
        };  
        //set the custom factory to action column
        columnEdit2.setCellFactory(cellFactory);  

    }

    @FXML
    void returnToInstock(ActionEvent event) throws SQLException, InterruptedException {
        String textMac = txtMac.getText();
        
        if(txtMac.getText().isEmpty()){
             int response = JOptionPane.showConfirmDialog(
        null,"no field should be empty","Required Input",JOptionPane.DEFAULT_OPTION);
        }
        else{

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement(); 
 
            
            String instock = "INSERT INTO `inventory_project`.`instock` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`) SELECT `id`, `mac_address`, `brand`, `model`, `version`, `addition_date`  FROM `inventory_project`.`in_maintanance` WHERE mac_address="+textMac;
            statement.executeUpdate(instock);
            
            TimeUnit.SECONDS.sleep(1);  
            
            String instock2 = "DELETE FROM `inventory_project`.`in_maintanance` WHERE mac_address="+textMac;
            statement.executeUpdate(instock2);
            
             txtBrand.setText("");
             txtMac.setText("");
             txtModel.setText("");
             txtVersion.setText("");
             txtId.setText("");
             txtDate.setText("");
            
             data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `inventory_project`.`in_maintanance`");

            while (rs.next()) {
                //get string from db
                data.add(new maintanance(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8) ));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        columnId2.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMac2.setCellValueFactory(new PropertyValueFactory<>("mac_address"));
        columnBrand2.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columnModel2.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnVersion2.setCellValueFactory(new PropertyValueFactory<>("version"));
        columnAddition_date2.setCellValueFactory(new PropertyValueFactory<>("addition_date"));        
        columnWorker_name2.setCellValueFactory(new PropertyValueFactory<>("worker_name"));
        
        tableInMaintanance.setItems(null);
        tableInMaintanance.setItems(data);
        
        //SET TEXTFIELDS TO BE BLANK
        txtBrand.setText("");
        txtMac.setText("");
        txtModel.setText("");
        txtVersion.setText("");
        txtId.setText("");
        txtDate.setText("");

        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<maintanance, String>, TableCell<maintanance, String>> cellFactory = (param) -> {

            //make the tablecell containing button
            final TableCell<maintanance, String> cell = new TableCell<maintanance, String>() {

                //override updateItem method
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Now we create the action button
                        final Button editButton = new Button("EDIT");
                        //attach listener on button
                        editButton.setOnAction(event -> {

                            //Extract the clicked object
                            maintanance p = getTableView().getItems().get(getIndex());

                            //DATA 
                            //System.out.println(p.getMac_address());  String.valueOf(p.getId())
                            txtBrand.setText(String.valueOf(p.getBrand()));
                            txtMac.setText(String.valueOf(p.getMac_address()));
                            txtModel.setText(String.valueOf(p.getModel()));
                            txtVersion.setText(String.valueOf(p.getVersion()));
                            txtId.setText(String.valueOf(p.getId()));
                            txtDate.setText(String.valueOf(p.getAddition_date()));

                            //PREVENT USER FROM EDITING TEXTFIELDS                               
                            txtBrand.setEditable(false);
                            txtMac.setEditable(false);
                            txtModel.setEditable(false);
                            txtVersion.setEditable(false);
                            txtId.setEditable(false);
                            txtDate.setEditable(false);

                        });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }

                }
            ;

            };
            
            return cell;
        };
        //set the custom factory to action column
        columnEdit2.setCellFactory(cellFactory);
        }

    }

    @FXML
    void sendToDeferred(ActionEvent event) throws SQLException, InterruptedException {
        String textMac = txtMac.getText();
        
        if(txtMac.getText().isEmpty()){
             int response = JOptionPane.showConfirmDialog(
        null,"no field should be empty","Required Input",JOptionPane.DEFAULT_OPTION);
        }
        else{

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement(); 
 
            
            String deferred = "INSERT INTO `inventory_project`.`deferred` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`,`deferred_date`) SELECT `id`, `mac_address`, `brand`, `model`, `version`, `addition_date`,CURDATE()  FROM `inventory_project`.`in_maintanance` WHERE mac_address="+textMac;
            statement.executeUpdate(deferred);
            
            TimeUnit.SECONDS.sleep(1);  
            
            String deferred2 = "DELETE FROM `inventory_project`.`in_maintanance` WHERE mac_address="+textMac;
            statement.executeUpdate(deferred2);
            
            txtBrand.setText("");
             txtMac.setText("");
             txtModel.setText("");
             txtVersion.setText("");
             txtId.setText("");
             txtDate.setText("");
            
             data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `inventory_project`.`in_maintanance`");

            while (rs.next()) {
                //get string from db
                data.add(new maintanance(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        columnId2.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMac2.setCellValueFactory(new PropertyValueFactory<>("mac_address"));
        columnBrand2.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columnModel2.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnVersion2.setCellValueFactory(new PropertyValueFactory<>("version"));
        columnAddition_date2.setCellValueFactory(new PropertyValueFactory<>("addition_date"));
        columnWorker_name2.setCellValueFactory(new PropertyValueFactory<>("worker_name"));
        

        tableInMaintanance.setItems(null);
        tableInMaintanance.setItems(data);

        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<maintanance, String>, TableCell<maintanance, String>> cellFactory = (param) -> {

            //make the tablecell containing button
            final TableCell<maintanance, String> cell = new TableCell<maintanance, String>() {

                //override updateItem method
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Now we create the action button
                        final Button editButton = new Button("EDIT");
                        //attach listener on button
                        editButton.setOnAction(event -> {

                            //Extract the clicked object
                            maintanance p = getTableView().getItems().get(getIndex());

                            //DATA 
                            //System.out.println(p.getMac_address());  String.valueOf(p.getId())
                            txtBrand.setText(String.valueOf(p.getBrand()));
                            txtMac.setText(String.valueOf(p.getMac_address()));
                            txtModel.setText(String.valueOf(p.getModel()));
                            txtVersion.setText(String.valueOf(p.getVersion()));
                            txtId.setText(String.valueOf(p.getId()));
                            txtDate.setText(String.valueOf(p.getAddition_date()));

                            //PREVENT USER FROM EDITING TEXTFIELDS                               
                            txtBrand.setEditable(false);
                            txtMac.setEditable(false);
                            txtModel.setEditable(false);
                            txtVersion.setEditable(false);
                            txtId.setEditable(false);
                            txtDate.setEditable(false);

                        });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }

                }
            ;

            };
            
            return cell;
        };
        //set the custom factory to action column
        columnEdit2.setCellFactory(cellFactory);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
