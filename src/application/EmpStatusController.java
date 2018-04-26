package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EmpStatusController { //CONFIG REQD
	

    @FXML
    private Label Name;

    @FXML
    private Label ID;

    @FXML
    private Label Salary;

    @FXML
    private Label Days;
	
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/timecard";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "abc123";
	   public int currentID =0;
	
	public void initialize() {
		System.out.println("In Status");
    	Connection conn = null;
 	   Statement stmt = null;
 	   
 	   
 	   if(AdminController.admincheck == 1) {
 		   currentID = AdminController.EID;
 	   }
 	   else if(EmpController.empcheck == 1) {
 		   currentID= EmpController.ID;
 	   }
 	  ID.setText(Integer.toString(currentID));
 	   
 	   
 	   try{
 	      //STEP 2: Register JDBC driver
 	      Class.forName("com.mysql.jdbc.Driver");

 	      //STEP 3: Open a connection
 	      System.out.println("Connecting to database for emp...");
 	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

 	      //STEP 4: Execute a query
 	      System.out.println("Creating statement for emp status...");
 	      stmt = conn.createStatement();
 	      String sqlDays;
 	      
 	      sqlDays = "select count(*) from works where eid ="+currentID+" and month(w_date)=month(sysdate())";
 	      ResultSet rs = stmt.executeQuery(sqlDays);
 	      
 	      int dayz = 0;
 	      while(rs.next()){
 	         //Retrieve by column name
 	         dayz  = rs.getInt("count(*)");
 	      }
 	      Days.setText(Integer.toString(dayz));
 	      
 	     String Hours;
	      
 	     
 	     //CHANGES IF PROJECT IS CHANGED
	      Hours = "select timediff(checkout,checkin) from works where month(w_date)=month(sysdate()) and eid="+currentID+";";
	      rs = stmt.executeQuery(Hours);
	      int Thours=0,Tmin=0,Tsec=0;
	      Time h;
	      String str;
 	      while(rs.next()){
  	         //Retrieve by column name
  	         h = rs.getTime("timediff(checkout,checkin)");
  	         str = h.toString();
  	         System.out.println(str);	
  	       String[] tokens = str.split(":");
  	       Thours += Integer.parseInt(tokens[0]);
  	       Tmin += Integer.parseInt(tokens[0]);
  	       Tsec += Integer.parseInt(tokens[0]);
 	      }
 	      Tmin += Tsec/60;
 	      Tsec = Tsec%60;
 	      Thours += Tmin/60;
 	      Tmin = Tmin%60;
 	      
 	      sqlDays = "select p.bonus from projects p,works w where w.pno = p.pid and w.eid="+currentID;
 	      rs = stmt.executeQuery(sqlDays);
 	      
 	      int bonus = 0;
 	      while(rs.next()){
 	         //Retrieve by column name
 	         bonus = rs.getInt("bonus");
 	      }
 	     
 	      sqlDays = "select f_name,l_name from emp where uid="+currentID;
 	      rs = stmt.executeQuery(sqlDays);
 	      
 	      String finalstring=null;
 	      while(rs.next()){
 	         //Retrieve by column name
 	         String f = rs.getString("f_name");
 	         String l = rs.getString("l_name");
 	         finalstring = f+" "+l;
 	         
 	      }
 	      Name.setText(finalstring);
 	      
 	     sqlDays = "select p.salary from projects p,works w where w.pno = p.pid and w.eid="+currentID;
	     rs = stmt.executeQuery(sqlDays);
 	     int salary = 0;
	     while(rs.next()){
	         //Retrieve by column name
	         salary = rs.getInt("salary");
	     }
 	     System.out.println(Salary+" "+Thours+" "+bonus); 
	     int Total = salary*Thours + bonus;
	     Salary.setText(Integer.toString(Total));
	     
	     
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
		
		
		if(EmpController.emp == 1)
		{
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Emp.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    	EmpController.emp = 0;
		}
		if(AdminController.admin == 1)
		{
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Admin.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    	AdminController.admin = 0;
		}
    }

}
