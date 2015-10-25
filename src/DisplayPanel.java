import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DisplayPanel extends JPanel
{
  private JTextArea display;

  public DisplayPanel()
  {
    setLayout(new GridLayout(1, 1));
    display = new JTextArea();
    Border loweredbevel = BorderFactory.createLoweredBevelBorder();
    display.setBorder(loweredbevel);
    add(display);
    setVisible(true);
  }

  public void print(String s)
  {
    display.setText(s);
  }
}
