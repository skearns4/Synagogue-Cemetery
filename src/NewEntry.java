import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Popup JDialog extension to add new entry to database
 */
public class NewEntry extends JDialog
{
  private JPanel contentPane;
  private JTextField firstNameText;  //text field for first name
  private JLabel first = new JLabel("First Name:");
  private JTextField lastNameText;   //text field for last name
  private JLabel last = new JLabel("Last Name:");
  private JTextField plotNumberText; //text field for plot number
  private JLabel plot = new JLabel("Plot Number:");
  private JTextField dateText;       //text field for date
  private JLabel date = new JLabel("Date (yyyy-mm-dd):");
  private JButton add;               //button to add entry with text field arguments
  private JButton cancel;            //cancel new entry

  /**
   * NewEntry class that constructor
   */
  public NewEntry()
  {
    //set basic functionality
    int width = 400, height = 200;
    Dimension min = new Dimension(width, height);
    setMinimumSize(min);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new GridLayout(5, 2));
    setModal(true);
    //init components
    add = new JButton("Add Entry");
    cancel = new JButton("Cancel");
    firstNameText = new JTextField();
    lastNameText = new JTextField();
    plotNumberText = new JTextField();
    dateText = new JTextField();
    //stylize
    Color backgroundColor = new Color(153, 204, 255); //initialize main color
    Font mainFont = new Font("Serif", Font.PLAIN, 20); //create main font for buttons
    add.setFont(mainFont);
    cancel.setFont(mainFont);
    setBackground(backgroundColor);
    //add components
    add(first);
    add(firstNameText);
    add(last);
    add(lastNameText);
    add(plot);
    add(plotNumberText);
    add(date);
    add(dateText);
    add(add);
    add(cancel);
    pack();
    //set default enter function
    JRootPane rootPane = SwingUtilities.getRootPane(add);
    rootPane.setDefaultButton(add);
    //LISTENERS
    firstNameText.addMouseListener(new textFieldListener());
    lastNameText.addMouseListener(new textFieldListener());
    plotNumberText.addMouseListener(new textFieldListener());
    dateText.addMouseListener(new textFieldListener());
    add.addActionListener(new addEntryListener());
    cancel.addActionListener(new cancelButtonListener());
  }

  /**
   * Class housing actionListener for add button
   */
  class addEntryListener implements ActionListener
  {
    /**
     * Adds current text in text fields as new database entry
     *
     * @param e button click action event
     */
    public void actionPerformed(ActionEvent e)
    {
      //int day = 0, month = 0, year = 0;
      String firstName = firstNameText.getText();
      String lastName = lastNameText.getText();
      String plotNumber = plotNumberText.getText();
      String date = dateText.getText();

      if (firstName.equals(""))
      {
        firstNameText.setForeground(Color.RED);
        firstNameText.setText("PLEASE ENTER A FIRST NAME");
      }
      else
      {
        //TODO error handling for first name
      }
      if (lastName.equals(""))
      {
        lastNameText.setForeground(Color.RED);
        lastNameText.setText("PLEASE ENTER A LAST NAME");
      }
      else
      {
        //TODO error handling for last name
      }
      if (plotNumber.equals(""))
      {
        plotNumberText.setForeground(Color.RED);
        plotNumberText.setText("PLEASE ENTER A PLOT NUMBER");
      }
      else
      {
        //TODO error handling for plot
      }
      if (date.equals(""))
      {
        dateText.setForeground(Color.RED);
        dateText.setText("PLEASE ENTER A DATE");
      }
      else if (dateText.getForeground() == Color.BLACK)
      {
        //TODO error handling for date
      }
      try
      {
        Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:./h2/cemetery;IFEXISTS=TRUE", "laboon", "bethshalom");
        Statement stmt = con.createStatement();

        //ensure that we enter new data in titlecase
        firstName = capitalize(firstName);
        lastName = capitalize(lastName);

        //execute an insert into our DB
        boolean rs = stmt.execute("INSERT INTO PLOTS VALUES ('" + firstName + "'" + "," + "'" + lastName + "'" + "," + plotNumber + "," + "'" + date + "'" + ")");

        stmt.close();
        con.close();
      }
      catch (Exception er)
      {
        System.out.println(er.getMessage());
      }
    }
  }

  /**
   * Class housing actionListener for cancel button
   */
  class cancelButtonListener implements ActionListener
  {
    /**
     * Disposes of NewEntry(this)
     *
     * @param e button click action event
     */
    public void actionPerformed(ActionEvent e)
    {
      setVisible(false);
      dispose();
    }
  }

  /**
   * Class housing mouseListener for textFields
   */
  class textFieldListener implements MouseListener
  {

    /**
     * Resets text fields of error messages on click
     *
     * @param e mouse click event
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
      JTextField curr = (JTextField) e.getSource();
      if (curr.getForeground() == Color.RED)
      {
        curr.setForeground(Color.BLACK);
        curr.setText("");
      }
    }

    /**
     * Unused
     *
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
      return;
    }

    /**
     * Unused
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
      return;
    }

    /**
     * Unused
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {
      return;
    }

    /**
     * Unused
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e)
    {
      return;
    }
  }

  /**
   * Capitalizes first letter of string s
   * Name changed from cleanUp to capitalize on 10/26 by Louie
   *
   * @param s string to capitalize
   * @return capitalized string
   */
  public String capitalize(String s)
  {
    char[] sc = s.toCharArray();
    sc[0] = Character.toUpperCase(sc[0]);
    s = new String(sc);
    return s;
  }
}
