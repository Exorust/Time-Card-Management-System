package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LeaveTable {
	
	private SimpleIntegerProperty eno, days;
	private SimpleStringProperty reason, status,date;
	
	public LeaveTable()
	{
		
	}
	public LeaveTable(int eno,String date, int days,String reason, String status)
	{
		this.eno = new SimpleIntegerProperty(eno);
		this.days = new SimpleIntegerProperty(days);
		this.reason = new SimpleStringProperty(reason);
		this.status = new SimpleStringProperty(status);
		this.date = new SimpleStringProperty(date);	
	}
	public int getEno()
	{
		return eno.get();
	}
	public void setEno(int eno)
	{
		this.eno.set(eno);
	}
	
	public int getDays()
	{
		return days.get();
	}
	public void setDays(int days)
	{
		this.days.set(days);
	}
	
	public String getReason()
	{
		return reason.get();
	}
	public void setReason(String reason)
	{
		this.reason.set(reason);
	}
	
	
	public String getDate()
	{
		return date.get();
	}
	public void setDate(String date)
	{
		this.date.set(date);
	}
	
	
	
	public String getStatus()
	{
		return status.get();
	}
	public void setStatus(String status)
	{
		this.status.set(status);
	}
	public SimpleIntegerProperty enoProperty()
	{
		return eno;
	}
	public SimpleIntegerProperty daysProperty()
	{
		return days;
	}
	public SimpleStringProperty reasonProperty()
	{
		return reason;
	}
	public SimpleStringProperty statusProperty()
	{
		return status;
	}

}
