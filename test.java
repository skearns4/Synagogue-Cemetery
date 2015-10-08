import javax.swing.*;

public class test extends JDialog
{
    private JPanel contentPane;
    private JTextField plotField;
    private JTextField nameField;
    private JButton plotButton;
    private JButton nameButton;
    private JButton createButton;
    private JLabel mainHeading;
    private JLabel subHeading;
    private JLabel nameSearch;
    private JLabel plotSearch;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;

    public test()
    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(createButton);

    }

    public static void main(String[] args)
    {
        test dialog = new test();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
