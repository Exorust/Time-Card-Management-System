package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgotController {
	

    
    @FXML
    private TextField answers;

    @FXML
    private PasswordField passField;
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/timecard";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "abc123";
	
	public void initialize() {
		
	}
	
	public void back (ActionEvent event) throws IOException
    {	
		
		String ans = answers.getText();
    	String pass = passField.getText();
    	
    	boolean checkSecAns;
    	
    	Connection conn = null;
    	Connection conn2 =null;
    	   Statement stmt = null;
    	   Statement stmt2 = null;
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to database for login...");
    	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
    	      conn2 = DriverManager.getConnection(DB_URL,USER,PASS);
    	      
    	      //STEP 4: Execute a query
    	      System.out.println("Creating statement for login...");
    	      stmt = conn.createStatement();
    	      stmt2 = conn2.createStatement();
    	      String sql;
    	      sql = "select sanswer from emp where uid=" + LoginController.ID;
    	      String updatePassword;
    	      updatePassword = "update emp set pass= '"+pass+"' where uid=" + LoginController.ID;
    	      ResultSet rs = stmt.executeQuery(sql);
    	      
    	          	      
    	      
    	      //STEP 5: Extract data from result set
    	      while(rs.next()){
    	         //Retrieve by column name
    	         String database_answer  = rs.getString("sanswer");
    	         if(database_answer.equals(ans)) {
    	        	 checkSecAns = true;
    	        	 stmt2.executeUpdate(updatePassword);
    	         }
    	      }
    	      
    	      
    	      //STEP 6: Clean-up environment
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
		
		
		Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
}
