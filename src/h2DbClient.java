import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class h2DbClient
{
  public static void main(String[] args)
  {
    try
    {
      Class.forName("org.h2.Driver");
      Connection con = DriverManager.getConnection("jdbc:h2:./h2/cemetery;IFEXISTS=TRUE", "laboon", "bethshalom");
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT * FROM PLOTS");
      while (rs.next())
      {
        String name = rs.getString("DECEASED_FNAME");
        System.out.println(name);
      }
      stmt.close();
      con.close();
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }

  }
}