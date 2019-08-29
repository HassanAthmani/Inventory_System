
package inventory_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class Inventory_System extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/inventory_system/login/loginForm.fxml"));
        
        Scene scene = new Scene(root);
        
        
        //scene.getStylesheets().add(getClass().getResource("/stylesheet/background.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.resizableProperty();
        /*Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth()-stage.getWidth())/2);
        stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);*/
        stage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
