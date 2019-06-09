/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system.maintanance;



import static inventory_system.giveUser.giveUserController.password;
import static inventory_system.giveUser.giveUserController.userName1;
import inventory_system.giveUser.giveUser_1;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/**
 *
 * @author User
 */
public class maintananceController implements Initializable {
    
    @FXML
    private TableView<maintanance_1> tableMaintain;

    @FXML
    private TableColumn<maintanance_1, String> columnId2;

    @FXML
    private TableColumn<maintanance_1, String> columnMac2;

    @FXML
    private TableColumn<maintanance_1, String> columnBrand2;

    @FXML
    private TableColumn<maintanance_1, String> columnModel2;

    @FXML
    private TableColumn<maintanance_1, String> columnVersion2;

    @FXML
    private TableColumn<maintanance_1, String> columnAddition_date2;

    @FXML
    private TableColumn columnEdit2;

    @FXML
    private TextField textBrand;

    @FXML
    private TextField textMac;

    @FXML
    private TextField textModel;

    @FXML
    private TextField textVersion;

    @FXML
    private TextField workerName;

    @FXML
    private TextField textDate;

    @FXML
    private TextField textID;
    
    public static String userName1 = "root";
    public static String password = "";
    
    public Connection connection;
    //Initialize observable list 29:57
    private ObservableList<maintanance_1> data;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        //setting scene variable
        Parent sceneFxml= FXMLLoader.load(getClass().getResource("/inventory_system/dashboard.fxml"));
        Scene newScene=new Scene(sceneFxml);
        
        //getting stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        //setting scene on stage
        window.setScene(newScene);
        window.show();

    }

    @FXML
    void loadMachines(ActionEvent event) throws SQLException {
        //DB connection details
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "inventory_project";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName1, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM instock");

            while (rs.next()) {
                //get string from db
                data.add(new maintanance_1(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
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

        tableMaintain.setItems(null);
       tableMaintain.setItems(data);
       
       //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<maintanance_1,String>,TableCell<maintanance_1,String>> cellFactory=(param)->{
         
            //make the tablecell containing button
            final TableCell<maintanance_1,String> cell=new TableCell<maintanance_1,String>(){
                
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
                                maintanance_1 p=getTableView().getItems().get(getIndex());
                                
                                //DATA 
                                //System.out.println(p.getMac_address());  String.valueOf(p.getId())
                                
                               textBrand.setText(String.valueOf(p.getBrand()));
                               textMac.setText(String.valueOf(p.getMac_address()));
                               textModel.setText(String.valueOf(p.getModel()));
                               textVersion.setText(String.valueOf(p.getVersion()));
                               textID.setText(String.valueOf(p.getId()));
                               textDate.setText(String.valueOf(p.getAddition_date()));
                               
                               //PREVENT USER FROM EDITING TEXTFIELDS                               
                               textBrand.setEditable(false);
                               textMac.setEditable(false);
                               textModel.setEditable(false);
                               textVersion.setEditable(false);                                    
        

                            
             
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
    void sendToMaintanance(ActionEvent event) throws SQLException, ClassNotFoundException, InterruptedException {
        
        String txtMac=textMac.getText();        
        String txtId=textID.getText();
        String txtBrand=textBrand.getText();
        String txtModel=textModel.getText();
        String txtVersion=textVersion.getText();
        String txtDate=textDate.getText();
        String txtWorker=toUpperCase(workerName.getText());
        
        
        if(workerName.getText().isEmpty()|| textMac.getText().isEmpty()){
            int response = JOptionPane.showConfirmDialog(
        null,"no field should be empty","Required Input",JOptionPane.DEFAULT_OPTION);
        }
        else{
            try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName1, password);
            Statement statement = connection.createStatement(); 
           
                       
            
            String sql = "INSERT INTO `inventory_project`.`in_maintanance` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`,`worker_name`,`date`) VALUES ('"+txtId+"','" + txtMac + "','" + txtBrand + "','" + txtModel + "','" + txtVersion + "','"+txtDate+"','"+txtWorker+"', CURDATE() );";
            statement.executeUpdate(sql);
            TimeUnit.SECONDS.sleep(1);  
            
            String instock2 = "DELETE FROM `inventory_project`.`instock` WHERE mac_address="+txtMac;
            statement.executeUpdate(instock2);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `inventory_project`.`instock`");

            while (rs.next()) {
                //get string from db
                data.add(new maintanance_1(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
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

        tableMaintain.setItems(null);
       tableMaintain.setItems(data);
       
       //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<maintanance_1,String>,TableCell<maintanance_1,String>> cellFactory=(param)->{
         
            //make the tablecell containing button
            final TableCell<maintanance_1,String> cell=new TableCell<maintanance_1,String>(){
                
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
                                maintanance_1 p=getTableView().getItems().get(getIndex());
                                
                                //DATA 
                                //System.out.println(p.getMac_address());  String.valueOf(p.getId())
                                
                               textBrand.setText(String.valueOf(p.getBrand()));
                               textMac.setText(String.valueOf(p.getMac_address()));
                               textModel.setText(String.valueOf(p.getModel()));
                               textVersion.setText(String.valueOf(p.getVersion()));
                               textID.setText(String.valueOf(p.getId()));
                               textDate.setText(String.valueOf(p.getAddition_date()));
                               
                               //PREVENT USER FROM EDITING TEXTFIELDS                               
                               textBrand.setEditable(false);
                               textMac.setEditable(false);
                               textModel.setEditable(false);
                               textVersion.setEditable(false);                                    
        

                            
             
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
        textMac.setText("");        
        textID.setText("");
        textBrand.setText("");
        textModel.setText("");
        textVersion.setText("");
        textDate.setText("");
        workerName.setText("");

    }

    @FXML
    void showMachines(ActionEvent event) throws IOException {
        //setting scene variable
        Parent sceneFxml= FXMLLoader.load(getClass().getResource("/inventory_system/maintanance/inMaintanance.fxml"));
        Scene newScene=new Scene(sceneFxml);
        
        //getting stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        //setting scene on stage
        window.setScene(newScene);
        window.show();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
