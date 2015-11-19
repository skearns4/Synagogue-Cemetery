import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * DisplayPanel class designed to output database queries in MainWindow
 */
public class DisplayPanel extends JPanel
{
  //Model for the table of search results
  private DefaultTableModel model;

  //Arraylist to hold the entries in the table
  ArrayList<Entry> results = new ArrayList<Entry>();

  /**
   * DisplayPanel constructor
   */
  public DisplayPanel()
  {
    model = new DefaultTableModel()
    {
      //Switch statement specifying first 6 rows as strings, and the last row to be booleans (checkboxes)
      public Class<?> getColumnClass(int column)
      {
        switch (column)
        {
          case 0:
            return String.class;
          case 1:
            return String.class;
          case 2:
            return String.class;
          case 3:
            return String.class;
          case 4:
            return String.class;
          case 5:
            return String.class;
          case 6:
            return Boolean.class;
          default:
            return String.class;
        }
      }
    };

    //Initialize table and set model
    final JTable searchTable = new JTable();
    searchTable.setModel(model);

    //Add columns with appropriate headings to the table
    model.addColumn("First Name");
    model.addColumn("Last Name");
    model.addColumn("Section");
    model.addColumn("Plot Number");
    model.addColumn("Grave Number");
    model.addColumn("Date Deceased");
    model.addColumn("Select Entry");

    //Add the table to the display panel
    setLayout(new GridLayout(2, 1));
    add(new JScrollPane(searchTable));

    //Create panel to hold buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 3));

    //Create new buttons and add to the button panel
    JButton select = new JButton("View Entries");
    buttonPanel.add(select);

    JButton print = new JButton("Print Selected Entries");
    buttonPanel.add(print);

    JButton all = new JButton("Select All");
    buttonPanel.add(all);

    //Add buttons to the display panel
    add(buttonPanel);
    setVisible(true);

    //Action listener for the all button to select all entries
    all.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        //Set the value of column 6 to true for every row
        for (int i = 0; i < searchTable.getRowCount(); i++)
        {
          searchTable.setValueAt(true, i, 6);
        }
      }
    });

    //Action listener for the print button to print selected entries to a text file
    print.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        //determine if a row is selected
        for (int i = 0; i < searchTable.getRowCount(); i++)
        {
          Boolean checked = Boolean.valueOf(searchTable.getValueAt(i, 6).toString());

          //print each selected entry to a text file
          if (checked)
          {
            results.get(i).print();
          }
        }
      }
    });

    //Action listener for the select button to view checked search results
    select.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {

        //determine if a row is selected
        for (int i = 0; i < searchTable.getRowCount(); i++)
        {
          Boolean checked = Boolean.valueOf(searchTable.getValueAt(i, 6).toString());

          //display an entry view/edit box for each selected entry
          if (checked)
          {
            //New ContentPane
            EditEntry edit = new EditEntry(results.get(i));
            edit.setVisible(true);
          }
        }
      }
    });
  }

  /**
   * Called when a search is performed. Called once for ever?y result returned. Adds the fields
   * for the result to be displayed to the data matrix and updates the display.
   *
   * @param fn  first name
   * @param ln  last name
   * @param sn  section number
   * @param pn  plot number
   * @param gn  grave number
   * @param d   date
   * @param num number of rows
   * @param en  Entry object of the result being added
   */
  public void add(String fn, String ln, String sn, String pn, String gn, String d, int num, Entry en)
  {
    results.add(num, en); // Add the full entry data to the arraylist
    model.addRow(new Object[0]);
    model.setValueAt(fn, num, 0);
    model.setValueAt(ln, num, 1);
    model.setValueAt(sn, num, 2);
    model.setValueAt(pn, num, 3);
    model.setValueAt(gn, num, 4);
    model.setValueAt(d, num, 5);
    model.setValueAt(false, num, 6);
    updateUI();
  }

  /**
   * Clears the display panel(this) of most recent output
   */
  public void clear()
  {
    int numRows = model.getRowCount();
    for (int i = 0; i < numRows; i++)
    {
      model.removeRow(i);
    }
  }
}
