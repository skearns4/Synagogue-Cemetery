import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel
{
  private Object[][] data = new Object[10][4];//data for the search result table

  public DisplayPanel()
  {
    String[] columns = new String[]{//headers for the search result table
        "First Name", "Last Name", "Plot Number", "Date Deceased"
    };
    JTable searchTable = new JTable(data, columns); //create search result table
    setLayout(new GridLayout(1, 1));
    add(new JScrollPane(searchTable)); //add table to the display panel
    setVisible(true);
  }

  /*
  Called when a search is performed. Called once for every result returned. Adds the fields
  for the result to be displayed to the data matrix and updates the display.
  fn = first name, ln = last name, pn = plot number, d = date, num = row the result is to be displayed on.
   */
  public void add(String fn, String ln, String pn, String d, int num)
  {
    data[num][0] = fn;
    data[num][1] = ln;
    data[num][2] = pn;
    data[num][3] = d;
    updateUI();
  }

  public void clear()
  {
    for (int i = 0; i < 10; i++)
      for (int j = 0; j < 4; j++)
        this.data[i][j] = " ";
  }
}
