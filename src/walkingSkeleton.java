import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
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
		//get dimension properties
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height * 2 / 3;
		int width = screenSize.width * 2 / 3;

		//initialize frame
		JFrame mainFrame = new JFrame("Cemetery Management System");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setMinimumSize(new Dimension(600, 400));

		//initialize panel
		JPanel testPanel = new JPanel(new GridBagLayout());
		testPanel.setBackground(Color.BLUE);

		//create JPanel components
		JLabel search = new JLabel("Search:");
		JTextField searchField = new JTextField(40);

		//add JPanel components to testPanel
		testPanel.add(search);
		testPanel.add(searchField);

		//add panel to frame
		mainFrame.add(testPanel);

		//pack and set visible
		mainFrame.pack();
		mainFrame.setVisible(true);

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