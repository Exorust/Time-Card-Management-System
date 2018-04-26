
package application;

import java.io.IOException;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.text.DateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateEditEmpController { //CONFIG REQD : Add dept??
	
    @FXML
    private TextField Fname;

    @FXML
    private TextField Lname;

    @FXML
    private TextField DOB;

    @FXML
    private TextField Sex;

    @FXML
    private TextField UID;

    @FXML
    private PasswordField Pass;

    @FXML
    private TextField D1name;

    @FXML
    private TextField D1sex;

    @FXML
    private TextField D1DOB;

    @FXML
    private TextField D2Name;

    @FXML
    private TextField D2se;

    @FXML
    private TextField D2DOB;
    
    @FXML
    private Label relation1;

    @FXML
    private Label relation11;

    @FXML
    private TextField D1Relation;

    @FXML
    private TextField D2Relation;
    
    	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/timecard";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "abc123";

	public void back(ActionEvent event) throws IOException
    {
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Admin.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
	public void save(ActionEvent event) throws IOException
    {	
		System.out.println("In save");
    	String first = Fname.getText();
    	String last = Lname.getText();
    	String birth = DOB.getText();
    	String s = Sex.getText();
    	String id = UID.getText();
    	String pass = Pass.getText();
    	String name1 = D1name.getText();
    	String sex1 = D1sex.getText();
    	String birth1 = D1DOB.getText();
    	String rel1 = D1Relation.getText();
    	String name2 = D2Name.getText();
    	String sex2 = D2se.getText();
    	String birth2 = D2DOB.getText();
    	String rel2 = D1Relation.getText();
    	
    	Connection conn = null;
 	   Statement stmt1 = null;
 	   Statement stmt2 = null;
 	   Statement stmt3 = null;
 	  Statement stmt = null;
 	   try{
 	      //STEP 2: RegdateFormatister JDBC driver
 	      Class.forName("com.mysql.jdbc.Driver");

 	      //STEP 3: Open a connection
 	      System.out.println("Connecting to database for emp...");
 	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

 	      //STEP 4: Execute a query
 	      System.out.println("Creating statement for leave...");
 	      
 	     String sql1,sql2,sql3,sqlCheck;
 	     
 	      stmt = conn.createStatement();
	      sqlCheck = "select uid from emp";
	      ResultSet rs = stmt.executeQuery(sqlCheck);
	      
	      boolean checker = false;
	      //STEP 5: Extract data from result set
	      while(rs.next()){
	    	  int idtable = rs.getInt("uid");
	    	  if(idtable == Integer.parseInt(id)) {
	    		  checker = true;
	    		  
	    		  break;
	    		  
	    	  }
	      }
	      
 	      if(checker == true ) {
 	    	  System.out.println("ID Exists");
 	 	      stmt1 = conn.createStatement();
 	 	      sql1 = "update emp set f_name='"+first+"',l_name='"+last+"',sex='"+s+"',pass='"+pass+"' where uid="+id;
 	 	      stmt1.executeUpdate(sql1);
 	      }
 	      else {
 	    	  // Config reqd
 	    	 System.out.println("ID does not Exist");
 	 	      stmt1 = conn.createStatement();
 	 	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 	 	      String doj = df.format(java.time.LocalDate.now());
 	 	      int dno = 10;
 	 	      int pidx =102;
 	 	      String sanswer = "pet";
 	 	      sql1 = "insert into emp values("+id+",'"+first+"','"+last+"','"+s+"','"+pass+"','"+sanswer+"','"+doj+"',"+dno+","+pidx+");";       
 	 	      stmt1.executeUpdate(sql1);
 	      }

 	      if(name1 != null) {
 	 	      stmt2 = conn.createStatement();
 	 	      sql2 = "insert into dependents values("+id+",'"+name1+"','"+sex1+"','"+birth1+"','"+rel1+"');";
 	 	      stmt2.executeUpdate(sql2);  
 	      }

 	      if(name2 != null) {
 	    	 stmt3 = conn.createStatement();
 	 	      sql3 = "insert into dependents values("+id+",'"+name2+"','"+sex2+"','"+birth2+"','"+rel2+"');";
 	 	      stmt3.executeUpdate(sql3);
 	 	        
 	      }
 	      
 	      //STEP 6: Clean-up environment
 	      stmt1.close();
 	     stmt2.close();
 	    stmt3.close();
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
 	         if(stmt1!=null)
 	            stmt1.close();
 	      }catch(SQLException se2){
 	      }// nothing we can do
 	      try{
 	         if(conn!=null)
 	            conn.close();
 	      }catch(SQLException se){
 	         se.printStackTrace();
 	      }//end finally try
 	   }//end try 
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Admin.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
}
