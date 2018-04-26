package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProjectReportController { //CONFIG REQD
	
    @FXML
    private Label Name;



    @FXML
    private Label Days;

    @FXML
    private Label Deadline;
    
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/timecard";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "abc123";
	
		public void initialize() {
			System.out.println("In apply");

	    	Connection conn = null;
	 	   Statement stmt = null;
	 	   try{
	 	      //STEP 2: Register JDBC driver
	 	      Class.forName("com.mysql.jdbc.Driver");

	 	      //STEP 3: Open a connection
	 	      System.out.println("Connecting to database for emp...");
	 	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	 	      //STEP 4: Execute a query
	 	      System.out.println("Creating statement for leave...");
	 	      stmt = conn.createStatement();
	 	      String sql;
	 	      
		 	     sql = "select cust from projects where pid="+AdminController.PRID;
		 	     ResultSet rs = stmt.executeQuery(sql);
		 	     String dx = null;
		 	      //STEP 5: Extract data from result set
		 	      while(rs.next()){
		 	         //Retrieve by column name
		 	    	  dx = rs.getString("cust");
		 	      }
		 	      Name.setText(dx);
	 	      
	 	     sql = "select deadline from projects where pid="+AdminController.PRID;
	 	     rs = stmt.executeQuery(sql);
	 	     Date h = null;
	 	      //STEP 5: Extract data from result set
	 	      while(rs.next()){
	 	         //Retrieve by column name
	 	    	  h = rs.getDate("deadline");
	 	      }
	 	      Deadline.setText(h.toString());
		 	     
	 	      
	 	      sql = "select datediff(deadline,sysdate()) from projects where pid="+AdminController.PRID;
		 	     rs = stmt.executeQuery(sql);
		 	     int x =0;
		 	      //STEP 5: Extract data from result set
		 	      while(rs.next()){
		 	         //Retrieve by column name
		 	    	  x = rs.getInt("datediff(deadline,sysdate())");
		 	      }
		 	      Days.setText(Integer.toString(x));
	 	      
	 	      
	 	      //STEP 6: Clean-up environment
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
		}
	
	public void back(ActionEvent event) throws IOException
    {
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Admin.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }

}
