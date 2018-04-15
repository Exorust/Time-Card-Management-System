package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }
    @FXML 
    private Button Login;
    @FXML 
    private Button ForgotPass;
    @FXML 
    private TextField userid;
    @FXML 
    private PasswordField passowrd;
    public void changeScreen(ActionEvent event) throws IOException  
    {
    	
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Emp.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    	
    }
    /*void onLoginPress(ActionEvent event) 
    {
    		
    	System.out.println("Hello");
    }*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}

