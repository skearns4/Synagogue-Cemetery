import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class newEntry extends JDialog
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

  public newEntry()
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
    //LISTENERS
    firstNameText.addMouseListener(new textFieldListener());
    lastNameText.addMouseListener(new textFieldListener());
    plotNumberText.addMouseListener(new textFieldListener());
    dateText.addMouseListener(new textFieldListener());
    add.addActionListener(new addEntryListener());
    cancel.addActionListener(new cancelButtonListener());
  }

  //class housing add button listener
  //should parse each field available
  ///////////////////////////////////////////////////
  //based on parsed fields it should check for errors
  //~~~~ good place for unit testing ~~~~
  class addEntryListener implements ActionListener
  {
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
        System.out.println("mainWindow");
        Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:./h2/cemetery;IFEXISTS=TRUE", "laboon", "bethshalom");
        Statement stmt = con.createStatement();

        //ensure that we enter new data in titlecase
        firstName = cleanUp(firstName);
        lastName = cleanUp(lastName);

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

  //class housing cancel button listener
  //created in order to exit dialog
  class cancelButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      setVisible(false);
      dispose();
    }
  }

  //class housing text field mouse listeners
  //created in order to clear fields of error messages
  class textFieldListener implements MouseListener
  {
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

    @Override
    public void mousePressed(MouseEvent e)
    {
      return;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
      return;
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
      return;
    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
  }

  //accepts a string and returns a string that is formatted with the first character
  //caputalized and the rest lowercase
  public String cleanUp(String s)
  {
    char[] sc = s.toCharArray();
    sc[0] = Character.toUpperCase(sc[0]);
    s = new String(sc);
    return s;
  }
}



