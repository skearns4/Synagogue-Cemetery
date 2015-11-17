import javax.swing.*;
import java.awt.*;

/**
 * Created by brittanyregrut on 11/17/15.
 */
public class EditEntry extends JDialog{

    private JLabel firstNameLabel = new JLabel("First Name: ");
    private JLabel lastNameLabel = new JLabel("Last Name: ");
    private JLabel plotNumberLabel = new JLabel("Plot Number: ");
    private JLabel dateDeceasedLabel = new JLabel("Date Deceased: ");
    private JLabel sectionLabel = new JLabel("Section: ");
    private JLabel graveNumberLabel = new JLabel("Grave Number: ");
    private JLabel intermentNumberLabel = new JLabel("Interment Number: ");
    private JLabel pIntLabel;
    private JLabel linerLabel = new JLabel("Liner? ");
    private JLabel CGCLabel = new JLabel("Cemetery Ground Care? ");
    private JLabel RMFLabel = new JLabel("Road Maintenance Fee? ");
    private JLabel monumentLabel = new JLabel("Monument? ");
    private JLabel plantingLabel = new JLabel("Perpetual Planting Fee? ");
    private JLabel veteranLabel = new JLabel("Veteran? ");
    private JLabel crematedLabel = new JLabel("Cremated? ");
    private JLabel foundationsLabel;
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
    private JTextArea plantingNotes;


    /**
     * Opens a new window to display and edit a selected entry.
     *
     * @param en entry to be displayed for viewing/editing
     */
    public EditEntry(Entry en){
        //set basic functionality
        int width = 400, height = 200;
        Dimension min = new Dimension(width, height);
        setMinimumSize(min);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
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
        boolean liner = false;
        String linerText = " ";
        if (en.getLiner().equals("Liner")){
            liner = true;
        }
        else{
            linerText = en.getLiner();
        }
        linerBox = new JCheckBox("Liner: ", liner);
        linerNotes = new JTextArea(linerText);


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
        add(linerLabel);
        add(linerBox);
        add(linerNotes);
        pack();
    }
}
