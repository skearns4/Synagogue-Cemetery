import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by brittanyregrut on 11/17/15.
 */
public class EditEntry extends JDialog
{

  private JLabel firstNameLabel = new JLabel("First Name: ");
  private JLabel lastNameLabel = new JLabel("Last Name: ");
  private JLabel plotNumberLabel = new JLabel("Plot Number: ");
  private JLabel dateDeceasedLabel = new JLabel("Date Deceased: ");
  private JLabel sectionLabel = new JLabel("Section: ");
  private JLabel graveNumberLabel = new JLabel("Grave Number: ");
  private JLabel intermentNumberLabel = new JLabel("Interment Number: ");
  private JTextField firstNameField;
  private JTextField lastNameField;
  private JTextField plotNumberField;
  private JTextField dateDeceasedField;
  private JTextField sectionField;
  private JTextField graveNumberField;
  private JTextField intermentNumberField;
  private JCheckBox linerBox;
  private JCheckBox CGCBox;
  private JCheckBox RMFBox;
  private JCheckBox monumentBox;
  private JCheckBox plantingBox;
  private JCheckBox veteranBox;
  private JCheckBox crematedBox;
  private JTextArea linerNotes;
  private JTextArea CGCNotes;
  private JTextArea RMFNotes;
  private JTextArea monumentNotes;
  private JButton save;


  /**
   * Opens a new window to display and edit a selected entry.
   *
   * @param en entry to be displayed for viewing/editing
   */
  public EditEntry(Entry en)
  {
    //set basic functionality
    int width = 400, height = 200;
    Dimension min = new Dimension(width, height);
    setMinimumSize(min);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new GridLayout(13, 2));
    setModal(true);

    //Initialize Text Fields
    firstNameField = new JTextField(en.getFirstName());
    lastNameField = new JTextField(en.getLastName());
    plotNumberField = new JTextField(en.getPlotNumber());
    dateDeceasedField = new JTextField(en.getDateDeceased());
    sectionField = new JTextField(en.getSection());
    graveNumberField = new JTextField(en.getGraveNumber());
    intermentNumberField = new JTextField(en.getIntermentNumber());

    //Initialize Checkboxes
    //Initialize Liner
    boolean liner = false;
    String linerText = " ";
    if (en.getLiner().equals("Liner"))
    {
      liner = true;
    }
    else
    {
      linerText = en.getLiner();
    }
    linerBox = new JCheckBox("Liner?", liner);
    linerNotes = new JTextArea(linerText);

    //Initialize CGC
    boolean cgc = false;
    String cgcText = " ";
    if (en.getCGC().equals("CGC"))
    {
      cgc = true;
    }
    else
    {
      cgcText = en.getCGC();
    }
    CGCBox = new JCheckBox("Cemetery Ground Care?", cgc);
    CGCNotes = new JTextArea(cgcText);

    //Initialize RMF
    boolean rmf = false;
    String rmfText = " ";
    if (en.getRMF().equals("RMF")){
      rmf = true;
    }
    else
    {
      rmfText = en.getRMF();
    }
    RMFBox = new JCheckBox("Road Maintenance Fee?", rmf);
    RMFNotes = new JTextArea(rmfText);

    //Initialize Monument
    boolean monument = false;
    String monumentText = " ";
    if (!(en.getMonument().equals(" "))){
      monument = true;
      monumentText = en.getMonument();
    }
    monumentBox = new JCheckBox("Monument?", monument);
    monumentNotes = new JTextArea(monumentText);

    //Initialize Perpetual Planting
    boolean planting = false;
    if (en.getPlanting().equals("PP")){
      planting = true;
    }
    plantingBox = new JCheckBox("Perpetual Planting?", planting);

    //Initialize Veteran
    boolean veteran = false;
    if (en.getVeteran().equals("Veteran")){
      veteran = true;
    }
    veteranBox = new JCheckBox("Veteran?", veteran);

    //Initialize Cremated
    boolean cremated = false;
    if (en.getCremated().equals("Cremated")){
      cremated = true;
    }
    crematedBox = new JCheckBox("Cremated?", cremated);

    //Initialize Button
    save = new JButton("Save Changes");

    //stylize
    Color backgroundColor = new Color(153, 204, 255); //initialize main color
    Font mainFont = new Font("Serif", Font.PLAIN, 20); //create main font for buttons
    setBackground(backgroundColor);

    //Add components
    add(firstNameLabel);
    add(firstNameField);
    add(lastNameLabel);
    add(lastNameField);
    add(dateDeceasedLabel);
    add(dateDeceasedField);
    add(sectionLabel);
    add(sectionField);
    add(plotNumberLabel);
    add(plotNumberField);
    add(graveNumberLabel);
    add(graveNumberField);
    add(intermentNumberLabel);
    add(intermentNumberField);
    add(linerBox);
    add(linerNotes);
    add(CGCBox);
    add(CGCNotes);
    add(RMFBox);
    add(RMFNotes);
    add(monumentBox);
    add(monumentNotes);
    add(plantingBox);
    add(veteranBox);
    add(crematedBox);
    add(save);
    pack();

    //Action Listener
    save.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //Save changes back to database
      }
    });
  }
}
