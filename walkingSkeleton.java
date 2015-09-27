import java.awt.*;
import javax.swing.*;


public class walkingSkeleton{

	public static void main(String [] args)
	{

		//get dimension properties
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height * 2 / 3;
		int width = screenSize.width * 2 / 3;

		//initialize frame
		JFrame mainFrame = new JFrame("Cemetary Management System");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setMinimumSize(new Dimension(600,400));
		
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

}