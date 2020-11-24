/* Creates connection to our database at westsuly.beget.com */



package connectivity;




import whsystem.Login_Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.TimeZone;

public class DB_Connection {


    static Connection con=null;
    public static Connection getConnection()
    {
        if (con != null) return con;
        String databaseURL = "jdbc:mysql://westsuly.beget.tech:3306/westsuly_arh?serverTimezone=UTC";
        String user = "westsuly_arh";
        String pass = "S@12345ss";
        return getConnection(databaseURL, user, pass);
    }

    private static Connection getConnection(String db_databaseURL, String user_name,String password)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver" );


            con=DriverManager.getConnection(db_databaseURL,user_name, password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return con;
    }
}




