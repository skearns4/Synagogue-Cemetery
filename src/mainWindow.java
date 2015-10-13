import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class mainWindow extends JPanel
{
  private DisplayPanel dp;
  private JPanel titlePanel;
  private JLabel mainHeading = new JLabel("Beth Shalom Synagogue");
  private JLabel subHeading = new JLabel("Cemetery Management System");
  private JPanel createPanel;
  private JButton createButton;
  private JPanel namePanel;
  private JLabel nameLabel;
  private JTextField nameField;
  private JButton nameButton;
  private JPanel plotPanel;
  private JLabel plotLabel;
  private JTextField plotField;
  private JButton plotButton;

  public mainWindow(DisplayPanel display)
  {
    dp = display;
    //Setup SearchPanel
    setLayout(new GridLayout(4,1));

    //Deal with titlePanel
    titlePanel = new JPanel(new FlowLayout());
    Color panelColor = new Color(153,204,255); //initialize main color
    titlePanel.setBackground(panelColor); //set color of title panel
    Font mainHeadingFont = new Font("Serif", Font.PLAIN, 24); //font for the main heading
    mainHeading.setFont(mainHeadingFont); // set the font of the main heading
    titlePanel.add(mainHeading);
    Font subHeadingFont = new Font("Serif", Font.BOLD, 32); //font for the sub heading
    subHeading.setFont(subHeadingFont); // set the font of the sub heading
    titlePanel.add(subHeading);
    add(titlePanel);

    //Initialize createPanel
    createPanel = new JPanel(new GridLayout(1,1));
    createPanel.setBackground(panelColor);// set create panel color
    Font mainFont = new Font("Serif", Font.PLAIN,20); //create main font for buttons, etc
    createButton = new JButton("Create New Entry");
    createButton.addActionListener(new createListener());
    createButton.setFont(mainFont); //set create button font
    createPanel.add(createButton);
    add(createPanel);
    createPanel.setVisible(true);
    //Initialize namePanel
    namePanel = new JPanel(new GridLayout(1,3));
    namePanel.setBackground(panelColor); // set name panel color
    nameLabel = new JLabel("Search By Name:");
    nameLabel.setFont(mainFont);//set name label font
    nameLabel.setHorizontalAlignment(SwingConstants.CENTER); //center name label
    nameField = new JTextField();
    nameButton = new JButton("Search!");
    nameButton.setFont(mainFont); // set name button font
    nameButton.addActionListener(new nameListener());
    namePanel.add(nameLabel);
    namePanel.add(nameField);
    namePanel.add(nameButton);
    add(namePanel);
    namePanel.setVisible(true);
    //Initialize plotPanel
    plotPanel = new JPanel(new GridLayout(1,3));
    plotPanel.setBackground(panelColor);//set plot panel color
    plotLabel = new JLabel("Search By Plot:");
    plotLabel.setFont(mainFont);//set plot label font
    plotLabel.setHorizontalAlignment(SwingConstants.CENTER); //center plot label
    plotField = new JTextField();
    plotButton = new JButton("Search!");
    plotButton.setFont(mainFont); // set plot button font
    plotButton.addActionListener(new plotListener());
    plotPanel.add(plotLabel);
    plotPanel.add(plotField);
    plotPanel.add(plotButton);
    add(plotPanel);
    plotPanel.setVisible(true);



  }

//  public static void main(String[] args)
//  {
//    mainWindow dialog = new mainWindow();
//    dialog.setVisible(true);
//    System.exit(0);
//  }

  class createListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      newEntry entry = new newEntry();
      entry.setVisible(true);
    }
  }

  class nameListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      try
      {
        Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:./h2/cemetery;IFEXISTS=TRUE", "laboon", "bethshalom");
        Statement stmt = con.createStatement();

        //retrieves the text in the first nameField
        String s = nameField.getText();

        //VERY BASIC
        //sql statement to collect all the data in a certain row where the first name
        //matches whatever entered into s
        ResultSet rs = stmt.executeQuery("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'" + s + "\'");
        while (rs.next())
        {
          //tokenizes the results of select statement into individual strings corresponding
          //to their columns
          String fname = rs.getString("DECEASED_FNAME");
          String lname = rs.getString("DECEASED_LNAME");
          String plotNum = rs.getString("PLOT_NUMBER");
          String date = rs.getString("DATE_DECEASED");

          //System.out.println(fname + ' ' + lname + ' ' + plotNum + ' ' + date);
          dp.print(fname + ' ' + lname + ' ' + plotNum + ' ' + date);
        }
        stmt.close();
        con.close();
      } catch (Exception er)
      {
        System.out.println(er.getMessage());
      }
    }
  }

  class plotListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      try
      {
        Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:./h2/cemetery;IFEXISTS=TRUE", "laboon", "bethshalom");
        Statement stmt = con.createStatement();

        //retrieves the text in the first nameField
        String s = plotField.getText();

        //VERY BASIC
        //sql statement to collect all the data in a certain row where the first name
        //matches whatever entered into s
        ResultSet rs = stmt.executeQuery("SELECT * FROM PLOTS WHERE PLOT_NUMBER like \'" + s + "\'");
        while (rs.next())
        {
          //tokenizes the results of select statement into individual strings corresponding
          //to their columns
          String fname = rs.getString("DECEASED_FNAME");
          String lname = rs.getString("DECEASED_LNAME");
          String plotNum = rs.getString("PLOT_NUMBER");
          String date = rs.getString("DATE_DECEASED");

          //System.out.println(fname + ' ' + lname + ' ' + plotNum + ' ' + date);
          dp.print(fname + ' ' + lname + ' ' + plotNum + ' ' + date);
        }
        stmt.close();
        con.close();
      } catch (Exception er)
      {
        System.out.println(er.getMessage());
      }
    }
  }

}



