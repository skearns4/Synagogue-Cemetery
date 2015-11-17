import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

/**
 * Window in top half of main frame, houses all text fields and buttons
 */
public class MainWindow extends JPanel
{
    private static DisplayPanel dp;
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
    private JLabel plotLabel1;
    private JLabel plotLabel2;
    private JLabel plotLabel3;
    private JTextField plotField1;
    private JTextField plotField2;
    private JTextField plotField3;
    private JButton plotButton;

    /**
     * Constructor for MainWindow
     *
     * @param display DisplayPanel to be inserted in bottom half of grid layout
     */
    public MainWindow(DisplayPanel display)
    {
        //initialize dp to display
        dp = display;

        //Setup SearchPanel
        setLayout(new GridLayout(4, 1));

        //Deal with titlePanel
        titlePanel = new JPanel(new GridLayout(2, 1));
        Color panelColor = new Color(153, 204, 255); //initialize main color
        titlePanel.setBackground(panelColor); //set color of title panel
        Font mainHeadingFont = new Font("Serif", Font.PLAIN, 24); //font for the main heading
        mainHeading.setFont(mainHeadingFont); // set the font of the main heading
        mainHeading.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(mainHeading);
        Font subHeadingFont = new Font("Serif", Font.BOLD, 32); //font for the sub heading
        subHeading.setFont(subHeadingFont); // set the font of the sub heading
        subHeading.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(subHeading);
        add(titlePanel);

        //Initialize createPanel
        createPanel = new JPanel(new GridLayout(1, 1));
        createPanel.setBackground(panelColor);// set create panel color
        Font mainFont = new Font("Serif", Font.PLAIN, 20); //create main font for buttons, etc
        createButton = new JButton("Create New Entry");
        createButton.addActionListener(new createListener());
        createButton.setFont(mainFont); //set create button font
        createPanel.add(createButton);
        add(createPanel);
        createPanel.setVisible(true);

        //Initialize namePanel
        namePanel = new JPanel(new GridLayout(1, 3));
        namePanel.setBackground(panelColor); // set name panel color
        nameLabel = new JLabel("Search By Name:");
        nameLabel.setFont(mainFont);//set name label font
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER); //center name label
        nameField = new JTextField();
        nameField.addMouseListener(new fieldListener());
        nameButton = new JButton("Search!");
        nameButton.setFont(mainFont); // set name button font
        nameButton.addActionListener(new nameListener());
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.add(nameButton);
        add(namePanel);
        namePanel.setVisible(true);

        //Initialize plotPanel
        plotPanel = new JPanel(new GridLayout(1, 7));
        plotPanel.setBackground(panelColor);//set plot panel color
        plotLabel1 = new JLabel("Section:");
        plotLabel1.setFont(mainFont);//set plot label font
        plotLabel1.setHorizontalAlignment(SwingConstants.CENTER); //center plot label
        plotLabel2 = new JLabel("Plot:");
        plotLabel2.setFont(mainFont);//set plot label font
        plotLabel2.setHorizontalAlignment(SwingConstants.CENTER); //center plot label
        plotLabel3 = new JLabel("Grave:");
        plotLabel3.setFont(mainFont);//set plot label font
        plotLabel3.setHorizontalAlignment(SwingConstants.CENTER); //center plot label
        plotField1 = new JTextField();
        plotField1.addMouseListener(new fieldListener());
        plotField2 = new JTextField();
        plotField2.addMouseListener(new fieldListener());
        plotField3 = new JTextField();
        plotField3.addMouseListener(new fieldListener());
        plotButton = new JButton("Search!");
        plotButton.setFont(mainFont); // set plot button font
        plotButton.addActionListener(new plotListener());
        plotPanel.add(plotLabel1);
        plotPanel.add(plotField1);
        plotPanel.add(plotLabel2);
        plotPanel.add(plotField2);
        plotPanel.add(plotLabel3);
        plotPanel.add(plotField3);
        plotPanel.add(plotButton);
        add(plotPanel);
        plotPanel.setVisible(true);
    }

    /**
     * Class to house actionListener for createButton
     */
    class createListener implements ActionListener
    {
        /**
         * Initialize and set visible a NewEntry dialog
         *
         * @param actionEvent button click event
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            NewEntry entry = new NewEntry();
            entry.setVisible(true);
        }
    }

    /**
     * Class to house actionListener for nameField
     */
    class fieldListener implements MouseListener
    {
        /**
         * Set nameButton to "Enter key"
         *
         * @param mouseEvent button click event
         */
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            JTextField temp = (JTextField)mouseEvent.getSource();
            if(temp == nameField) {
                JRootPane rootPane = SwingUtilities.getRootPane(nameButton);
                rootPane.setDefaultButton(nameButton);
            }
            if(temp == plotField1 || temp == plotField2 || temp == plotField3) {
                JRootPane rootPane = SwingUtilities.getRootPane(plotButton);
                rootPane.setDefaultButton(plotButton);
            }

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    /**
     * Class housing actionListener for nameButton
     */
    class nameListener implements ActionListener
    {
        /**
         * Queries database for name on button click
         *
         * @param actionEvent button click event
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            String fullName = nameField.getText();
            fullName = fullName.toLowerCase();

            if (fullName.contains(" "))
            {
                // Split name if contains spaces - has a first and last name
                String[] splitStr = fullName.split("\\s");

                //set separate strings for first and last name
                String firstName = splitStr[0];
                String lastName = splitStr[1];

                //clean up input to make first char of string uppercase
                firstName = capitalize(firstName);
                lastName = capitalize(lastName);

                try // Both first and last name match entry in database
                {
                    dp.clear();
                    queryDb("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'" + firstName + "\' AND DECEASED_LNAME like \'" + lastName + "\'");
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            else // Is only a first or only a last name
            {
                //clean up input to make first char of string uppercase
                fullName = capitalize(fullName);

                try // Matches entry's first or last name field in database
                {
                    dp.clear();
                    queryDb("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'" + fullName + "\' OR DECEASED_LNAME like \'" + fullName + "\'");
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Class housing actionListener for plotButton
     */
    class plotListener implements ActionListener
    {
        /**
         * Queries database for plot on button click
         *
         * @param actionEvent button click event
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            String section = plotField1.getText();
            String plot = plotField2.getText();
            String grave = plotField3.getText();
            StringBuilder query = new StringBuilder("");
            boolean added = false;
            if(!section.equals("")) {
                query.append("SELECT * FROM PLOTS WHERE SECTION like \'"+ section + "\'");
                added = true;
            }
            if(!plot.equals("")){
                if (added)
                    query.append(" AND PLOT_NUMBER like " + plot);
                else {
                    query.append("SELECT * FROM PLOTS WHERE PLOT_NUMBER like " + plot);
                    added = true;
                }

            }
            if(!grave.equals("")){
                if(added)
                    query.append(" AND GRAVE like " + grave);
                else
                    query.append("SELECT * FROM PLOTS WHERE GRAVE like " + plot);
            }
            try // Search for plot number
            {
                dp.clear();
                queryDb(query.toString());
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * A ResultSet is table of data representing a database.
     * Generated by executing a statement that queries the database.
     *
     * @param rs result set
     * @return true if rs is empty, false otherwise
     */
    public boolean isEmpty(ResultSet rs) throws java.sql.SQLException
    {
        return !rs.first();
    }

    /**
     * Returns whether or not given result set has more than 1 row
     *
     * @param rs result set
     * @return true if more than 1 row, false otherwise
     * @throws java.sql.SQLException
     */
    public boolean hasMoreThanOneRow(ResultSet rs) throws java.sql.SQLException
    {
        return rs.first() && rs.next();
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

    /**
     * Helper method to query the DB
     *
     * @param query string used to query database
     * @returns number of results found by database
     */
    public static int queryDb(String query) throws java.sql.SQLException
    {
        int numEntries = 0; // Number of entries - used primarily for JUnit tests

        try
        {
            //establishes connection to our DB
            Class.forName("org.h2.Driver");
            //totally should not have password in plain text...
            Connection con = DriverManager.getConnection("jdbc:h2:./h2/cemetery;IFEXISTS=TRUE", "laboon", "bethshalom");
            Statement stmt = con.createStatement();

            //sql statement to collect all the data in a certain row where the first name
            //matches whatever entered into s
            ResultSet rs = stmt.executeQuery(query);

            //i is a counter for number of results in resultset
            int i = 0;

            while (rs.next())
            {
                numEntries++; // Increment number of entries

                //tokenizes the results of select statement into individual strings corresponding
                //to their columns
                String fname = rs.getString("DECEASED_FNAME");
                String lname = rs.getString("DECEASED_LNAME");
                String plotNum = rs.getString("PLOT_NUMBER");
                String date = rs.getString("DATE_DECEASED");
                String sectionNum = rs.getString("SECTION");
                String graveNum = rs.getString("GRAVE");
                String intermentNumber = rs.getString("INTERMENT_NUMBER");
                String pInt = rs.getString("PN_INT");
                String liner = rs.getString("PN_LINER");
                String CGC = rs.getString("PN_CGC");
                String RMF = rs.getString("PN_RMF");
                String monument = rs.getString("MONUMENT");
                String planting = rs.getString("PP_PLANTING");
                String veteran = rs.getString("VETERAN");
                String cremated = rs.getString("CREMATED");
                String foundations = rs.getString("FOUNDATIONS");

                //Create a new entry object for this result
                Entry en = new Entry(fname, lname, plotNum, date, sectionNum, graveNum, intermentNumber, pInt, liner, CGC, RMF, monument, planting, veteran, cremated, foundations);
                dp.add(fname, lname, sectionNum, plotNum, graveNum, date, i, en); //add the current result to the table data
                i++; //increment the row in the table so if multiple results returned, each is displayed in a new row
            }
            stmt.close();
            con.close();

            return numEntries;
        }
        catch (Exception er)
        {
            System.out.println(er.getMessage());
            return numEntries;
        }
    }

}
