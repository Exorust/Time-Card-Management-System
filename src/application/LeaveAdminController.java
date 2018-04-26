package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class LeaveAdminController {
	
	@FXML
    private TableView<LeaveTable> myTable = new TableView<LeaveTable>();
	
    @FXML
    private TableColumn<LeaveTable, Integer> leaveid = new TableColumn<LeaveTable, Integer>("EMP ID");

    @FXML
    private TableColumn<LeaveTable, String> datex = new TableColumn<LeaveTable, String>("Date");
    
    @FXML
    private TableColumn<LeaveTable, String> reason = new TableColumn<LeaveTable, String>("Reason");

    @FXML
    private TableColumn<LeaveTable, Integer> numberofdays = new TableColumn<LeaveTable, Integer>("No. of days");

    @FXML
    private TableColumn<LeaveTable, String> accept_reject = new TableColumn<LeaveTable, String>("Accept/Reject");
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/timecard";
    @FXML
    private ObservableList<LeaveTable> data = FXCollections.observableArrayList();
    
    @FXML
    private TextField empid;
    
    static final String USER = "root";
    static final String PASS = "abc123";
    public void initialize()
    {	
    	System.out.println("IN leave Admin");
    	Connection conn = null;
    	Statement stmt = null;
    	try
    	{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		String sql;
    		sql = "SELECT eno, start, days, reason, status from leav;";
    		ResultSet rs = stmt.executeQuery(sql);
    		while(rs.next())
    		{
    			int e = rs.getInt("eno");
    			String x = rs.getString("start");
    			int d = rs.getInt("days");

    			String r = rs.getString("reason");
    			String s = rs.getString("status");
    			if(s.equals("0"))
    				s = "Rejected";
    			else
    				s = "Accepted";
    				
    			data.add(new LeaveTable(e,x,d,r,s));
    		}
    		
    		
    	}
    	catch(Exception e)
    	{
    		
    	}
    	leaveid.setCellValueFactory(new PropertyValueFactory<>("eno"));
    	datex.setCellValueFactory(new PropertyValueFactory<>("date"));
		numberofdays.setCellValueFactory(new PropertyValueFactory<>("days"));
		reason.setCellValueFactory(new PropertyValueFactory<>("reason"));
		accept_reject.setCellValueFactory(new PropertyValueFactory<>("status"));
		myTable.setItems(data);
    }

    @FXML
    void accept_reject_after_edit(ActionEvent event) {

    }
    public void back(ActionEvent event) throws IOException
    {
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Admin.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
    public void accept(ActionEvent event) throws IOException
    {
    	Connection conn = null;
    	   Statement stmt = null;
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to database...");
    	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

    	      //STEP 4: Execute a query
    	      System.out.println("Creating statement...");
    	      stmt = conn.createStatement();
    	      String sql;
    	      sql = "update leav set status=1 where eno="+Integer.parseInt(empid.getText());
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
    public void reject(ActionEvent event) throws IOException
    {
    	Connection conn = null;
 	   Statement stmt = null;
 	   try{
 	      //STEP 2: Register JDBC driver
 	      Class.forName("com.mysql.jdbc.Driver");

 	      //STEP 3: Open a connection
 	      System.out.println("Connecting to database...");
 	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

 	      //STEP 4: Execute a query
 	      System.out.println("Creating statement...");
 	      stmt = conn.createStatement();
 	      String sql;
 	      sql = "update leav set status=0 where eno="+Integer.parseInt(empid.getText());
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
    public void refresh(ActionEvent event) throws IOException
    {
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("LeaveAdmin.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }

}
