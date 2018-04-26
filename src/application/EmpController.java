package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EmpController {
	public static  int emp = 0;
	public static  int empcheck = 0;
	public static int ID = 0;

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	   static final String DB_URL = "jdbc:mysql://localhost/timecard";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "abc123";

    @FXML
    private ResourceBundle resources;

    @FXML
    private Label Namez;

    @FXML
    private Label idz;
    
    @FXML
    private Label project_id;

    @FXML
    private URL location;
    private Label time;
    long endTime = 1000000000;

    @FXML
    void initialize() {
    	DateFormat format = new SimpleDateFormat( "HH:mm:ss" );
    //     final Timeline timeline = new Timeline(
    //         new KeyFrame(
    //         		Duration.millis(1000),
    //         		event -> {
    //         			Calendar cal = Calendar.getInstance();
    //         			time.setText(format.format(cal.getTime()));
    //         		}
    //         		)
    // );
    //    timeline.setCycleCount( Animation.INDEFINITE );
       // timeline.play();
    	System.out.println("In initialize");
    	String first=null,last = null;

    	ID = LoginController.ID;

    	Connection conn = null;
 	   Statement stmt = null;
 	   try{
 	      //STEP 2: Register JDBC driver
 	      Class.forName("com.mysql.jdbc.Driver");

 	      //STEP 3: Open a connection
 	      System.out.println("Connecting to database for emp...");
 	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

 	      //STEP 4: Execute a query
 	      System.out.println("Creating statement for name...");
 	      stmt = conn.createStatement();
 	      String sql;
 	      sql = "select f_name,l_name from emp where uid=" + LoginController.ID;
 	      ResultSet rs = stmt.executeQuery(sql);

 	      //STEP 5: Extract data from result set
 	      while(rs.next()){
 	         //Retrieve by column name
 	         first = rs.getString("f_name");
 	         last = rs.getString("l_name");
 	      }
 	      
 	     sql = "select pno from emp where uid="+ LoginController.ID;
 	    rs = stmt.executeQuery(sql);
	      while(rs.next()){
	 	         //Retrieve by column name
	    	  int x= rs.getInt("pno");
	    	  System.out.println(x);
	 	         project_id.setText(Integer.toString(x));
	 	         
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

 	   String names = first + " " + last;
 	   Namez.setText(names);
 	   idz.setText(Integer.toString(LoginController.ID));
    }
    public void logout(ActionEvent event) throws IOException
    {
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
    public void leave(ActionEvent event) throws IOException
    {
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Leave.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
    public void in (ActionEvent event) throws IOException
    {
    	// Enter the code here for Time IN
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, 1);
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	String date = format1.format(cal.getTime());
    	SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
    	String time = format2.format(cal.getTime());
    	Connection conn = null;
  	   Statement stmt = null;
  	   try{
  	      //STEP 2: Register JDBC driver
  	      Class.forName("com.mysql.jdbc.Driver");

  	      //STEP 3: Open a connection
  	      System.out.println("Connecting to database for emp...");
  	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

  	      //STEP 4: Execute a query
  	      System.out.println("Creating statement for name...");
  	      stmt = conn.createStatement();
  	      String sql,sql1;
  	      sql1 = "select count(*) from works where eid="+LoginController.ID+" and w_date='"+date+"';";
  	      ResultSet rs = stmt.executeQuery(sql1);
  	      
  	      boolean b=false;
  	      //STEP 5: Extract data from result set
  	      while(rs.next()){
  	         //Retrieve by column name
  	         int idz  = rs.getInt("count(*)");
  	         System.out.println(idz+" ");
  	         if(idz ==0) {
  	        	 b = true;
  	         }
  	      }
  	      
  	      if(b) {
  	  	      sql = "insert into works values("+LoginController.ID+",(select pno from emp where uid="+LoginController.ID+"),'"+date+"','"+time+"',null);";
  	  	      stmt.executeUpdate(sql); 
  	      }


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
    public void out (ActionEvent event) throws IOException
    {
    	// Enter the code for Time OUT
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, 1);
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	String date = format1.format(cal.getTime());
    	SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
    	String time = format2.format(cal.getTime());

    	Connection conn = null;
 	   Statement stmt = null;
   	   try{
   	      //STEP 2: Register JDBC driver
   	      Class.forName("com.mysql.jdbc.Driver");

   	      //STEP 3: Open a connection
   	      System.out.println("Connecting to database for emp...");
   	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

   	      //STEP 4: Execute a query
   	      System.out.println("Creating statement for name...");
   	      stmt = conn.createStatement();
   	      String sql;
   	      sql = "update works set checkout='"+time+"' where eid="+ID+" and w_date='"+date+"';";
   	      stmt.executeUpdate(sql);




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
    public void status (ActionEvent event) throws IOException
    {
    	empcheck =1;
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("EmpStatus.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    	emp = 1;
    }
}
