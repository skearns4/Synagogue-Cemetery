/**
 * Created by brittanyregrut on 11/17/15.
 */
public class Entry
{
  private String firstName;
  private String lastName;
  private String plotNumber;
  private String dateDeceased;
  private String section;
  private String graveNumber;
  private String intermentNumber;
  private String pInt;
  private String liner;
  private String CGC;
  private String RMF;
  private String monument;
  private String planting;
  private String veteran;
  private String cremated;
  private String foundations;

  public Entry(String fn, String ln, String pn, String date, String sec, String grave, String intermentNum, String pnInt, String pnLiner, String pnCGC, String pnRMF, String mon, String ppPlanting, String vet, String crem, String found)
  {
    this.firstName = fn;
    this.lastName = ln;
    this.plotNumber = pn;
    this.dateDeceased = date;
    this.section = sec;
    this.graveNumber = grave;
    this.intermentNumber = intermentNum;
    this.pInt = pnInt;
    this.liner = pnLiner;
    this.CGC = pnCGC;
    this.RMF = pnRMF;
    this.monument = mon;
    this.planting = ppPlanting;
    this.veteran = vet;
    this.cremated = crem;
    this.foundations = found;
  }

  //Getter methods
  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getCGC()
  {
    return CGC;
  }

  public String getCremated()
  {
    return cremated;
  }

  public String getDateDeceased()
  {
    return dateDeceased;
  }

  public String getFoundations()
  {
    return foundations;
  }

  public String getGraveNumber()
  {
    return graveNumber;
  }

  public String getIntermentNumber()
  {
    return intermentNumber;
  }

  public String getLiner()
  {
    return liner;
  }

  public String getMonument()
  {
    return monument;
  }

  public String getpInt()
  {
    return pInt;
  }

  public String getPlanting()
  {
    return planting;
  }

  public String getPlotNumber()
  {
    return plotNumber;
  }

  public String getRMF()
  {
    return RMF;
  }

  public String getSection()
  {
    return section;
  }

  public String getVeteran()
  {
    return veteran;
  }

  /**
   * Prints entry to a text file
   */
  public void print()
  {

  }
}
