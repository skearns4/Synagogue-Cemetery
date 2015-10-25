import javax.swing.*;
import java.awt.*;


public class driver
{
  private static JFrame mainWindow;
  private static mainWindow mw;
  private static DisplayPanel dp;

  public static void main(String[] args)
  {
    //Handle arguments and other init issues
    run();//
  }

  public static void run()
  {
    //Initialize main JWindow to house search and display panels
    mainWindow = new JFrame();
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainWindow.setLayout(new GridLayout(2, 1)); //(2,1) If display included as bottom half
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) ((.66) * (screenSize.getWidth()));
    int height = (int) ((.66) * (screenSize.getHeight()));
    Dimension min = new Dimension(width, height);
    mainWindow.setMinimumSize(min);
    //Initialize display panel
    dp = new DisplayPanel();
    //Initialize search panel
    mw = new mainWindow(dp);
    //add
    mainWindow.add(mw);
    mainWindow.add(dp);
    //Make them visible
    mw.setVisible(true);
    dp.setVisible(true);
    mainWindow.setVisible(true);
    //Possibly add display window as row 2 in mainWindow beneath mw
  }
}
