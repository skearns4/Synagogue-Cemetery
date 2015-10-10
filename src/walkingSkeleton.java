import java.awt.*;
import javax.swing.*;
import java.sql.*;
//import java.io.*;
import java.lang.*;
//import org.h2.tools.DeleteDbFiles;


public class walkingSkeleton
{

	public static void main(String[] args) throws Exception
	{
		initGUI();
		//connToDB();
	}

	public static void initGUI()
	{

		//initialize panel
		JDialog dialog = new test();
		dialog.setVisible(true);

	}

	public static void connToDB() throws Exception
	{
		Class.forName("org.h2.Driver");
		//establish connection to DB ////////////////////////////////username  password
		Connection conn = DriverManager.getConnection("jdbc:h2:/h2", "laboon", "bethshalom");
		Statement stat = conn.createStatement();
		// add application code here
		conn.close();

	}
}