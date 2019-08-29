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
import inventory_system.EditUI.deferred;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;


public class InstockDocumentController implements Initializable {
    
    //DB CONNECTION TOOLS
    public Connection connection;
    public static String userName = "root";
    public static String password = "";
    
     
    
    
///INSTOCK
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
    private TableColumn columnEdit;

    @FXML
    private TextField textBrand;

    @FXML
    private TextField textMac;

    @FXML
    private TextField textModel;

    @FXML
    private TextField textVersion;
    
    @FXML
    private TextField textDate;

    @FXML
    private TextField textId;


    @FXML
    private Button addRecord;

    @FXML
    private Button loadData;
    
    @FXML
    private Button deleteRow;
    
     @FXML
    private Button change;

    @FXML
    private Button remove;
    
    
    ////////////////////////////////////////
   
     
  /* public InstockDocumentController(String txtId,String txtDate){
       this.txtId=txtId;
       this.txtDate=txtDate;
   }
    
    
    //GETTER///
    /////
    public String getId(){   
       return txtId;
        
    }
    
    //////
    public String getDate(){                  
        return txtDate;
    }
    
    //SETTER///
    ///
    public void setId(String Value) {
        this.txtId=Value;
    }
    
    public void setDate(String Value) {
        txtDate=Value;
    }*/
   /////////////////////////////////////////////
    
    ///DEFERRED
    
    @FXML
    private TableView<deferred> tableDeferred;
    
    @FXML
    private TableColumn<deferred, String> columnId4;

    @FXML
    private TableColumn<deferred, String> columnMac4;

    @FXML
    private TableColumn<deferred, String> columnBrand4;

    @FXML
    private TableColumn<deferred, String> columnModel4;

    @FXML
    private TableColumn<deferred, String> columnVersion4;

    @FXML
    private TableColumn<deferred, String> columnAddition_date4;

    @FXML
    private TableColumn<deferred, String> columnDeferred_date4;
    
    @FXML
    private TableColumn<deferred, String> columnReason;
    
   @FXML
    private Button back4;

    @FXML
    private Button loadData4;
    
    @FXML
    private TextField txtReason;

    @FXML
    void GoBack(ActionEvent event) throws IOException {
        //setting scene variable
        Parent sceneFxml= FXMLLoader.load(getClass().getResource("/inventory_system/dashboard.fxml"));
        Scene newScene=new Scene(sceneFxml);
        
        //getting stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        //setting scene on stage
        window.setScene(newScene);
        window.show();

    }


    
    
    //Initialize observable list 29:57
    private ObservableList<deferred> list4;
    
    @FXML
    void loadFrmDB(ActionEvent event) throws SQLException {
        //DB connection details
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "inventory_project";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            list4 = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM deferred");

            while (rs.next()) {
                //get string from db
                list4.add(new deferred(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
       columnId4.setCellValueFactory(new PropertyValueFactory<>("id"));
       columnMac4.setCellValueFactory(new PropertyValueFactory<>("mac_address"));
       columnBrand4.setCellValueFactory(new PropertyValueFactory<>("brand"));
       columnModel4.setCellValueFactory(new PropertyValueFactory<>("model"));
       columnVersion4.setCellValueFactory(new PropertyValueFactory<>("version"));
        columnAddition_date4.setCellValueFactory(new PropertyValueFactory<>("addition_date"));
        columnDeferred_date4.setCellValueFactory(new PropertyValueFactory<>("deferred_date"));
         columnReason.setCellValueFactory(new PropertyValueFactory<>("reason"));

        tableDeferred.setItems(null);
       tableDeferred.setItems(list4);
        

    }


    
    ///DEFERRED FORM IS UP 
       
    
    //INSTOCK METHODS FOR ALL ITS BUTTONS

    
    //Initialize observable list 29:57
    private ObservableList<instock> data;
    

    @FXML
    void addData(ActionEvent event) throws SQLException, InterruptedException {

        String txtBrand =toUpperCase( textBrand.getText());
        String txtMac = textMac.getText();
        String txtModel = toUpperCase(textModel.getText());
        String txtVersion = toUpperCase(textVersion.getText());
        
        if( textBrand.getText().isEmpty() || textMac.getText().isEmpty() || textModel.getText().isEmpty() || textVersion.getText().isEmpty() ){
            int response = JOptionPane.showConfirmDialog(
        null,"no field should be empty","Required Input",JOptionPane.DEFAULT_OPTION);
            
        }
        else{

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();

            

            String sql = "INSERT INTO `inventory_project`.`all_items` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`) VALUES (NULL,'" + txtMac + "','" + txtBrand + "','" + txtModel + "','" + txtVersion + "', CURDATE() );";

            statement.executeUpdate(sql);
             
            TimeUnit.SECONDS.sleep(1);  
            
            String instock1 = "INSERT INTO `inventory_project`.`instock` SELECT * FROM `inventory_project`.`all_items` WHERE mac_address="+txtMac;
            statement.executeUpdate(instock1);
            
            

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
        
         //SET TEXTFIELDS TO BE BLANK
        textBrand.setText("");
        textMac.setText("");
        textModel.setText("");
        textVersion.setText("");
        }
        
    }

    @FXML
    void loadFromDB(ActionEvent event) throws SQLException, IOException {
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
        
        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<instock,String>,TableCell<instock,String>> cellFactory=(param)->{
         
            //make the tablecell containing button
            final TableCell<instock,String> cell=new TableCell<instock,String>(){
                
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
                        final Button editButton=new Button("+");
                        //attach listener on button
                        editButton.setOnAction(event ->{
                            
                                //Extract the clicked object
                                instock p=getTableView().getItems().get(getIndex());
                                
                                //DATA 
                                //System.out.println(p.getMac_address());  String.valueOf(p.getId())
                                
                               textBrand.setText(String.valueOf(p.getBrand()));
                               textMac.setText(String.valueOf(p.getMac_address()));
                               textModel.setText(String.valueOf(p.getModel()));
                               textVersion.setText(String.valueOf(p.getVersion()));
                               textDate.setText(String.valueOf(p.getAddition_date()));
                               textId.setText(String.valueOf(p.getId()));
                               
                               
                               
                               
                               //PREVENT USER FROM EDITING TEXTFIELDS                               
                               textBrand.setEditable(true);
                               textMac.setEditable(true);
                               textModel.setEditable(true);
                               textVersion.setEditable(true);                                    
        

                            
             
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
        columnEdit.setCellFactory(cellFactory);        



};
     @FXML
    void deleteSelectedRow(ActionEvent event) throws SQLException, InterruptedException {
        
        String txtMac = textMac.getText();
        if(textMac.getText().isEmpty() || txtReason.getText().isEmpty()){
            int response = JOptionPane.showConfirmDialog(
        null,"no field should be empty","Required Input",JOptionPane.DEFAULT_OPTION);            
        }
        else{
        

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement(); 
 
            //String txtMac
            String txtBrand=textBrand.getText();
            String txtModel=textModel.getText();
            String txtVersion=textVersion.getText();
            String reason=txtReason.getText();
            String txtId=textId.getText();
            String txtDate=textDate.getText();
            
            
            
            
            //String instock1 = "INSERT INTO `inventory_project`.`deferred` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`,`deferred_date`) SELECT `id`, `mac_address`, `brand`, `model`, `version`, `addition_date`,CURDATE()  FROM `inventory_project`.`instock` WHERE mac_address="+txtMac;
            String instock1="INSERT INTO `inventory_project`.`deferred` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`,`deferred_date`,`reason`) VALUES ('" + txtId + "','" + txtMac + "','" + txtBrand + "','" + txtModel + "','" + txtVersion + "','" + txtDate + "', CURDATE(),'" + reason + "' );";
            statement.executeUpdate(instock1);
            
            TimeUnit.SECONDS.sleep(1);  
            
            String instock2 = "DELETE FROM `inventory_project`.`instock` WHERE mac_address="+txtMac;
            statement.executeUpdate(instock2);
            
            

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
        
        //SET TEXTFIELDS TO BE BLANK
        textBrand.setText("");
        textMac.setText("");
        textModel.setText("");
        textVersion.setText("");
        txtReason.setText("");
        textId.setText("");
        textDate.setText("");
        

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
    
    @FXML
    void changeItem(ActionEvent event) throws SQLException, InterruptedException {
        
        String txtBrand =toUpperCase( textBrand.getText());
        String txtMac = textMac.getText();
        String txtModel = toUpperCase(textModel.getText());
        String txtVersion = toUpperCase(textVersion.getText());
        int txtId=Integer.valueOf(textId.getText());
        
        if( textBrand.getText().isEmpty() || textMac.getText().isEmpty() || textModel.getText().isEmpty() || textVersion.getText().isEmpty() ){
            int response = JOptionPane.showConfirmDialog(
        null,"no field should be empty","Required Input",JOptionPane.DEFAULT_OPTION);
            
        }
        else{

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();

            

            String sql = "UPDATE `inventory_project`.`all_items` SET `mac_address`='"+txtMac+"', `brand`='"+txtBrand+"', `model`='"+txtModel+"', `version`='"+txtVersion+"', `addition_date`=CURDATE() WHERE id ='"+txtId+"' ;";

            statement.executeUpdate(sql);
            
            String delete="DELETE FROM `inventory_project`.`instock` WHERE id="+txtId+" " ;
            
            statement.executeUpdate(delete);
            TimeUnit.SECONDS.sleep((long) 0.5);  
            
            String instock1 = "INSERT INTO `inventory_project`.`instock` SELECT * FROM `inventory_project`.`all_items` WHERE `id`="+txtId;
            statement.executeUpdate(instock1);
            
            

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
        
         //SET TEXTFIELDS TO BE BLANK
        textBrand.setText("");
        textMac.setText("");
        textModel.setText("");
        textVersion.setText("");
        }
        

    }
    
      @FXML
    void removeItem(ActionEvent event) throws ClassNotFoundException, SQLException  {
        
        String txtMac = textMac.getText();
        int txtId=Integer.valueOf(textId.getText());
        
        
        if( textBrand.getText().isEmpty() || textMac.getText().isEmpty() || textModel.getText().isEmpty() || textVersion.getText().isEmpty() ){
            int response = JOptionPane.showConfirmDialog(
        null,"no field should be empty","Required Input",JOptionPane.DEFAULT_OPTION);
            
        }
        else{
             try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            
            String delete="DELETE FROM `inventory_project`.`instock` WHERE `id` ='"+txtId+"' " ;
            String delete1="DELETE FROM `inventory_project`.`all_items` WHERE `id` ='"+txtId+"' " ;
            
            statement.executeUpdate(delete);
            statement.executeUpdate(delete1);
            
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
        
         //SET TEXTFIELDS TO BE BLANK
        textBrand.setText("");
        textMac.setText("");
        textModel.setText("");
        textVersion.setText("");
        
        }
        
        
         
       

    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
