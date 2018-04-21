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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private PasswordField password;
    public void changeScreen(ActionEvent event) throws IOException  
    {
    	String username = userid.getText();
    	String pass = password.getText();
    	//System.out.println(temp);
    	if(username.equals("Admin") && pass.equals("12345"))
    	{
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Admin.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    	}
    	else if(username.equals("Employee") && pass.equals("12345"))
    	{
    		Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Emp.fxml"));
        	Scene tableViewscene = new Scene(tableView);
        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        	window.setScene(tableViewscene);
        	window.show();
    	}
    	else
    	{
    		Alert errorAlert = new Alert(AlertType.ERROR);
    		errorAlert.setHeaderText("Input not valid");
    		errorAlert.setContentText("Invalid Username or Password");
    		errorAlert.showAndWait();
    	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void forgot(ActionEvent event) throws IOException
    {
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Forgot.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
}

