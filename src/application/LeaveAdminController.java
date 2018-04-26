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
    private TableView<LeaveTable> myTable;
	
    @FXML
    private TableColumn<LeaveTable, Integer> leaveid;

    @FXML
    private TableColumn<LeaveTable, String> reason;

    @FXML
    private TableColumn<LeaveTable, Integer> numberofdays;

    @FXML
    private TableColumn<LeaveTable, String> accept_reject;
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/timecard";
    @FXML
    private ObservableList<LeaveTable> data = FXCollections.observableArrayList();
    
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
    		sql = "SELECT eno, days, reason, status from leav;";
    		ResultSet rs = stmt.executeQuery(sql);
    		while(rs.next())
    		{
    			data.add(new LeaveTable(rs.getInt("eno"), rs.getInt("days"), rs.getString("reason"), rs.getString("status")));
    		}
    		
    		
    	}
    	catch(Exception e)
    	{
    		
    	}
    	leaveid.setCellValueFactory(new PropertyValueFactory<LeaveTable, Integer>("eno"));
		numberofdays.setCellValueFactory(new PropertyValueFactory<LeaveTable, Integer>("days"));
		reason.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("reason"));
		accept_reject.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("status"));
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

}
