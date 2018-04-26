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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminController{

	public static int admin = 0;
	public static int admincheck = 0;
	public static int ID = 0;
	public static int PRID = 0;
	public static int EID = 0;

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
    private Label Namex;

    @FXML
    private Label idx;

    @FXML
    private TextField empID;
    private Label time;
    
    @FXML
    private TextField projID;

    @FXML
    public void initialize() {
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
    //     timeline.setCycleCount( Animation.INDEFINITE );
    //     timeline.play();


    	System.out.println("In initialize");
    	String first=null,last = null;
    	

    	Connection conn = null;
 	   Statement stmt = null;
 	   try{
 	      //STEP 2: Register JDBC driver
 	      Class.forName("com.mysql.jdbc.Driver");

 	      //STEP 3: Open a connection
 	      System.out.println("Connecting to database for Admin...");
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
 	   Namex.setText(names);
 	   idx.setText(Integer.toString(LoginController.ID));

    }
    public void logout(ActionEvent event) throws IOException
    {	
    	
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
    public void edit(ActionEvent event) throws IOException
    {	admincheck=1;
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("CreateEditEmp.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
    public void empstatus(ActionEvent event) throws IOException
    {	
    	admincheck=1;
    	EID = Integer.parseInt(empID.getText());
    	System.out.println(empID.getText());
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("EmpStatus.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    	admin = 1;
    }
    public void report(ActionEvent event) throws IOException
    {	
    	admincheck=1;
    	PRID = Integer.parseInt(projID.getText());
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("ProjectReport.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
    public void bonus(ActionEvent event) throws IOException
    {	
    	admincheck=1;
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Bonus.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
    public void delete(ActionEvent event) throws IOException
    {	
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Delete.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
    
    
    public void leaveapp(ActionEvent event) {
    	Parent tableView;
		try {
			tableView = FXMLLoader.load(getClass().getClassLoader().getResource("LeaveAdmin.fxml"));
			Scene tableViewscene = new Scene(tableView);
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	window.setScene(tableViewscene);
	    	window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
