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

import java.sql.*;

public class LoginController implements Initializable { 
	
	public static int ID;
	
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/timecard";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "abc123";	

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
    	ID = Integer.parseInt(username);
    	String pass = password.getText();
    	boolean checkPass = false;
    	boolean checkIsAdmin =false;
    	
    	Connection conn = null;
    	   Statement stmt = null;
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to database for login...");
    	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

    	      //STEP 4: Execute a query
    	      System.out.println("Creating statement for login...");
    	      stmt = conn.createStatement();
    	      String sql;
    	      sql = "select pass from emp where uid=" + Integer.parseInt(username);
    	      String isAdmin;
    	      isAdmin = "select type from emp where uid="+Integer.parseInt(username);
    	      ResultSet rs = stmt.executeQuery(sql);
    	      
    	      
    	      //STEP 5: Extract data from result set
    	      while(rs.next()){
    	         //Retrieve by column name
    	         String PassRecieve  = rs.getString("pass");
    	         if(PassRecieve.equals(pass)) {
    	        	 checkPass = true;
    	         }
    	      }
    	      ResultSet rsAdmin = stmt.executeQuery(isAdmin);
    	      while(rsAdmin.next()){
     	         //Retrieve by column name
     	         String AdminRecieve  = rsAdmin.getString("type");
     	         if(AdminRecieve.equals("a")) {
     	        	 checkIsAdmin = true;
     	         }
     	      }
    	      
    	      //STEP 6: Clean-up environment
    	      rsAdmin.close();
    	      rs.close();
    	      stmt.close();
    	      conn.close();
    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }finally{
    	      //finally block used to close resources
    	      try{
    	         if(stmt!=null)
    	            stmt.close();
    	      }catch(SQLException se2){
    	      }// nothing we can do
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }//end finally try
    	   }//end try
    	
    	
    	
    	
    	//System.out.println(temp);
    	if(checkPass && checkIsAdmin)
    	{
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Admin.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    	}
    	else if(checkPass && !checkIsAdmin)
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
		String username = userid.getText();
		ID = Integer.parseInt(username);
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Forgot.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
}
