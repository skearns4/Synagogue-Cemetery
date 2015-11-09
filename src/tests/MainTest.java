import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
    Unit tests for our implemented user stories
    Had to adapt because JUnit is not really designed for GUIs
    Most of these tests rely on our currently existing database and will need to be updated when the real database is created
    For example, one test searches for "Mary", which exists in our current database but may not in our final database
 */
public class MainTest
{
  /*
     Unit test that checks that there is an entry with the first name of Mary
  */
  @Test
  public void testFirstNameAnna()
  {
    int numEntries = 0;

    try // Search for valid first name
    {
      numEntries = MainWindow.queryDb("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'" + "Anna" + "\' OR DECEASED_LNAME like \'" + "Mary" + "\'");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    assertTrue(numEntries > 0); // Should be an entry
  }


  /*
     Unit test that checks that there is an entry with the last name of Sanders
  */
  @Test
  public void testLastNameAaron()
  {
    int numEntries = 0;

    try // Search for valid last name
    {
      numEntries = MainWindow.queryDb("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'" + "Aaron" + "\' OR DECEASED_LNAME like \'" + "Mary" + "\'");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    assertTrue(numEntries > 0); // Should be an entry
  }

  /*
     Unit test that checks that there is an entry with the full name of Mary Sanders
  */
  @Test
  public void testFullNameAnnaAaron()
  {
    int numEntries = 0;

    try // Search for valid full name
    {
      numEntries = MainWindow.queryDb("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'" + "Anna" + "\' AND DECEASED_LNAME like \'" + "Aaron" + "\'");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    assertTrue(numEntries > 0); // Should be an entry
  }

  /*
     Unit test that checks that there is not an entry with the first or last name of Invalid
  */
  @Test
  public void testNameInvalid()
  {
    int numEntries = 0;

    try // Search for illegal name
    {
      numEntries = MainWindow.queryDb("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'" + "Invalid" + "\' OR DECEASED_LNAME like \'" + "Invalid" + "\'");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    assertFalse(numEntries > 0); // Should not be an entry
  }

  /*
     Unit test that checks that there is not an entry with the full name of Invalid Sanders
  */
  @Test
  public void testFullNameInvalidSanders()
  {
    int numEntries = 0;

    try // Search for illegal full name
    {
      numEntries = MainWindow.queryDb("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'" + "Invalid" + "\' AND DECEASED_LNAME like \'" + "Sanders" + "\'");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    assertFalse(numEntries > 0); // Should not be an entry
  }

  /*
      Unit test that checks that there is a plot number 175
   */
  @Test
  public void testPlotNumber175()
  {
    int numEntries = 0;

    try // Search for valid plot number
    {
      numEntries = MainWindow.queryDb("SELECT * FROM PLOTS WHERE PLOT_NUMBER like \'" + 175 + "\'");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    assertTrue(numEntries > 0); // Should be an entry
  }


  /*
      Unit test that checks that there is not a plot number 0
   */
  @Test
  public void testPlotNumberZero()
  {
    int numEntries = 0;

    try // Search for illegal plot number
    {
      numEntries = MainWindow.queryDb("SELECT * FROM PLOTS WHERE PLOT_NUMBER like \'" + 0 + "\'");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    assertFalse(numEntries > 0); // Should not be an entry
  }

  /*
      Unit test that checks that there is not a negative plot number (-1)
   */
  @Test
  public void testPlotNumberNegative()
  {
    int numEntries = 0;

    try // Search for illegal plot number
    {
      numEntries = MainWindow.queryDb("SELECT * FROM PLOTS WHERE PLOT_NUMBER like \'" + -1 + "\'");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    assertFalse(numEntries > 0); // Should not be an entry
  }


}
