import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Louie on 10/11/15.
 */
public class driver {


    private static JFrame mainWindow;
    private  static mainWindow mw;

    public static void main (String [] args)
    {
        //Handle arguments and other init issues

        run();//
    }

    public static void run()
    {
        //Initialize main JWindow to house search and display panels
        mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLayout(new GridLayout(1,1)); //(2,1) If display included as bottom half
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((.66) * (screenSize.getWidth()));
        int height = (int) ((.66) * (screenSize.getHeight()));
        Dimension min = new Dimension(width, height);
        mainWindow.setMinimumSize(min);
        //Initialize search panel
        mw = new mainWindow();
        mainWindow.add(mw);
        //Make them visible
        mw.setVisible(true);
        mainWindow.setVisible(true);
        //Possibly add display window as row 2 in mainWindow beneath mw

    }
}
