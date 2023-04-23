package project;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
class display
{
    Scanner ds=new Scanner(System.in);
    public void diplaymenu() throws SQLException, ClassNotFoundException, IOException {
        System.out.println("\n*=====>Hi sir welcome to faculty Display details portal <=====*\n");
        System.out.println("Display Total Faculty Details   : (1)");
        System.out.println("Search Faculty Details          : (2)");
        System.out.println("Know Total Faculty Member Count : (3)");
        System.out.println("Bact To Main Menu               : (4)");
        int display=ds.nextInt();
        switch (display)
        {
            case 1:                                                 //step-5(A) -it will getdetails method to display total data
                getDetails();
                diplaymenu();
                break;
            case 2:                                                  //step-5(B) - it will cal searchfac()id method to display particular data
                System.out.println("Please Enter Fid To Search ");
                int idd=ds.nextInt();
                searchFac(idd);
                diplaymenu();
                break;
            case 3:                                                 //step-5(C) - it will display total faculty member cpunt
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql:///batch8416","root","12345");
                Statement st=con.createStatement();
                String query="select count(*) from facultydata";
                ResultSet r=st.executeQuery(query);
                r.next();
                int count=r.getInt(1);
                System.out.println("\nNumber of records in the Faculty data : "+"[ "+count+" ]\n");
                diplaymenu();
                break;
            case 4:
               new ManageMenu2().getFaculty();                     //step-5(D)- back to main menu
                break;
            default:
                System.out.println("Please Enter Valid Input");
        }
    }
    public void getDetails() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql:///batch8416","root","12345");
        String query2="select * from facultydata";
        Statement st=con.createStatement();
        ResultSet r=st.executeQuery(query2);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Faculty Details~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------+");

        System.out.printf("| %-10s | %-13s | %-15s | %-15s | %-15s | %-15s | %-20s |","Faculty ID","Name","Qualification","Department","Address","Number","Email");

        System.out.println("\n+---------------------------------------------------------------------------------------------------------------------------+");
        while (r.next())
        {
            int id=r.getInt("Fid");
            String name= r.getString("Fname");
            String quali=r.getString("Fqualification");
            String dept=r.getString("Fdepartment");
            String addr=r.getString("Faddress");
            Long  num=r.getLong("Fnumber");
            String email=r.getString("Femail");

            System.out.format("| %-10s | %-13s | %-15s | %-15s | %-15s | %-15s | %-20s | %n",id,name,quali,dept,addr,num,email);
            // System.out.println();
        }
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------+\n");
        System.out.println("Records Fetched Successfully.\n");

    }
    public void searchFac(int id) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql:///batch8416","root","12345");
        String query2=" select *from facultydata where Fid=?";
        PreparedStatement st=con.prepareStatement(query2);

        st.setInt(1,id);
        ResultSet r=st.executeQuery();

        if(r.next()) {
            System.out.println("+---------------------------------------------------------------------------------------------------------------------------+");

            System.out.printf("| %-10s | %-13s | %-15s | %-15s | %-15s | %-15s | %-20s |","Faculty ID","Name","Qualification","Department","Address","Number","Email");

            System.out.println("\n+---------------------------------------------------------------------------------------------------------------------------+");

            int idd = r.getInt("Fid");
                String name = r.getString("Fname");
                String quali = r.getString("Fqualification");
                String dept = r.getString("Fdepartment");
                String addr = r.getString("Faddress");
                Long num = r.getLong("Fnumber");
                String email = r.getString("Femail");
                System.out.format("| %-10s | %-13s | %-15s | %-15s | %-15s | %-15s | %-20s | %n", idd, name, quali, dept, addr, num, email);
            System.out.println("+---------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("Records Fetched Successfully");

        }
        else System.out.println("Record Not found with FId: "+id);
    }

}

class ManageMenu2
{
    Scanner sc= new Scanner(System.in);
    public void getLogin() throws IOException, SQLException, ClassNotFoundException
    {
                                            //step - 2 Here im checking the user id and password for login to the portal
        System.out.println("********************> Welcome To Faculty Management System <*********************\n");
        Scanner pn=new Scanner(System.in);
        do {
            String userId="admin";
            String user="shanker";
            int password=12345;
            int pas=778077;

            System.out.println("Enter Faculty User Id : ");
            String uId=pn.nextLine();
            if(uId.equals(userId)||uId.equals(user))
            {
                System.out.println("Please Enter The Password :");
                int pass=pn.nextInt();
                pn.nextLine();
                if(pass==password ||pass==pas )
                {
                    System.out.println("~Login Successfull~(^_^) \n");
                    do {
                          getFaculty(); //step 3  If login succussfull it will getFaculty method or else print the else part
                                        //this loop will run until the the use get login or else need to terminate programm ;/
                       }while(true);
                }
                else
                    System.out.println("Invalid Password!..(?_?) Please Try Again....\n");
            }
            else
                System.out.println("Invalid User Id!!.. ('_') Try Again\n");
        }while(true);
    }
    //  ********************* Main Menu *************************
  public void getFaculty() throws IOException, SQLException, ClassNotFoundException
  {
      System.out.println("~^^^^^^^^^^^^^^^^^^^= Welcome To Faculty Portal =^^^^^^^^^^^^^^^^^~\n");
      System.out.println("Know Faculty Details Enter (1)");
      System.out.println("Modify faculty Details     (2)");
      System.out.println("Contact us.(^-^).          (3)");
      System.out.println("For Exit Enter             (4)");
                                                        // step- 4 giving option for menu user will enter on the option from menu
      int selection = sc.nextInt();
      switch (selection)
      {
          case 1:                                      //step-5 if user enter 1 it will call Display class to display the menu of display data
            //  getDetails();
              new display().diplaymenu();

              break;
          case 2:                                    //step-6 if user enter 2 it will call editDetails method to Edit the data
              editDetails();
              System.out.println("\n Thank you :)\n");
              break;

          case 3:                                    //step-7 if enter 3 it will display the conctat details
              System.out.println("Here's Our Faculty Management Details : \n");
              System.out.println("Contact : +91112233445566");
              System.out.println("Email   : faculty@gmail.com\n");
              System.out.println("~~~~~~~~~~Bact to Main menu\n");
              getFaculty();
              break;
          case 4:                                 //step -8 if user enter 4 it will call the getLogin meth starts from first
              getLogin();
              break;
          default:
              System.out.println("Wrong Input");
              break;
      }
  }
  // case 1

  //case2    ********************* Modify Menu *************************
  public void editDetails() throws IOException, SQLException, ClassNotFoundException
  {                                                         //step-6(A) Entered Modify Menu ....
      System.out.println("*=====>Hi sir welcome to faculty Edit details portal <=====*\n");
      System.out.println(" Add Faculty Details    (1)");
      System.out.println(" Update Faculty Details (2)");
      System.out.println(" Delete Faculty Details (3)");
      System.out.println(" Back to Main Menu      (4)");
     int edit=sc.nextInt();
      switch (edit)
      {
          case 1:                                          //step-6(B) it will call editFac methof for adding faculty data
              editfac();
              break;
          case 2:                                          //step-6(C) providing input data to (updateFac Method)
                                                                                //to update the particular id details
              System.out.println("Enter Faculty Id :");
              int id= sc.nextInt();
              sc.nextLine();
              System.out.println("Enter Faculty Name : ");
              String name=sc.nextLine();
              System.out.println("Enter Faculty Qualification");
              String quali=sc.nextLine();
              System.out.println("Enter Faculty Department");
              String dept=sc.nextLine();
              System.out.println("Enter Faculty Address");
              String addr=sc.nextLine();
              System.out.println("Enter Faculty Mobile Number :");
              long num= sc.nextLong();
              sc.nextLine();
              System.out.println("Enter Faculty EmailID:");
              String email=sc.nextLine();
              updateFac(id,name,quali,dept,addr,num,email);
              break;
          case 3:                                         //step-6(D) it will call the delFac method and we can delete
                                                                                    // the faculty data with faculty Id
              System.out.println("please enter Fid to delete : ");
              int idd= sc.nextInt();
              delFac(idd);  //provinding which id to del
              break;
          case 4:                                         //step-6(F) - calling getFaculty method to go back main menu
              getFaculty(); //back to previous menu
              break;
              default:
              System.out.println("Wrong Input");//if wrong input enter
              break;
      }
  }
  //case 2edit
  public void editfac() throws SQLException, ClassNotFoundException, IOException
  {                                                       //step-6(B)-I
                                                          // providing input to add faculty details
      System.out.println("Enter Faculty Id :");
      int id= sc.nextInt();
      sc.nextLine();
      System.out.println("Enter Faculty Name : ");
      String name=sc.nextLine();
      System.out.println("Enter Faculty Qualification");
      String quali=sc.nextLine();
      System.out.println("Enter Faculty Department");
      String dept=sc.nextLine();
      System.out.println("Enter Faculty Address");
      String addr=sc.nextLine();
      System.out.println("Enter Faculty Mobile Number :");
      long num= sc.nextLong();
      sc.nextLine();
      System.out.println("Enter Faculty EmailID:");
      String email=sc.nextLine();
      addFac(id,name,quali,dept,addr,num,email);
                                                          //step-6(B)-II  sending input to details to addFac methof
      System.out.println("Faculty Records Added Succesfully\n3");
      editDetails();          //====>back to modify menu
  }
    //case 2edit and prepare sql
    public static void addFac(int id, String name, String quali, String dept, String addr, long num, String email) throws ClassNotFoundException, SQLException
    {                                                        //step-6(B)-III adding details to database
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con= DriverManager.getConnection("jdbc:mysql:///batch8416","root","12345");
      String query="insert into facultydata value(?,?,?,?,?,?,?)";
      PreparedStatement st=con.prepareStatement(query);
      st.setInt(1, id);
      st.setString(2,name);
      st.setString(3,quali);
      st.setString(4,dept);
      st.setString(5,addr);
      st.setLong(6,num);
      st.setString(7,email);
      st.executeUpdate();
    }
    // case 2 updating faculty details
    public void updateFac(int id, String name, String quali, String dept, String addr, long num, String email) throws ClassNotFoundException, SQLException, IOException
    {                                                       // step-6(C)-Updating faculty details in database
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql:///batch8416","root","12345");
        String query="update facultydata set Fname=?,Fqualification=?,Fdepartment=?,Faddress=?,Fnumber=?,Femail=? where Fid=?";
        PreparedStatement st=con.prepareStatement(query);
       // st.setInt(1, id);
        st.setString(1,name);
        st.setString(2,quali);
        st.setString(3,dept);
        st.setString(4,addr);
        st.setLong(5,num);
        st.setString(6,email);
        st.setInt(7, id);
        st.executeUpdate();
        System.out.println("Record Updated Successfully :) \n");
        editDetails();  // bacl to modify menu
    }
    //case 3 delete
    public void delFac(int idd) throws ClassNotFoundException, SQLException, IOException
    {
                                                        //step-6(F)-I Deleting the data from database
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql:///batch8416", "root", "12345");
        String del = "delete from facultydata where Fid=?";
        PreparedStatement pt = con.prepareStatement(del);
        pt.setInt(1, idd);
        int rowdeleted = pt.executeUpdate();
        if (rowdeleted > 0)
        {
            System.out.println("Records deleted successfully\n");
        } else System.out.println("Record Not Found With Id: " + idd + "\n");

        editDetails();                              ///back to modify menu
    }
}
public class FacultyMangement2
{
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException
    {
        ManageMenu2 mng=new ManageMenu2();
        mng.getLogin();
                                                             //step 1 starting from here
                                                             // directly goes to getLoginmethod..
    }
}
