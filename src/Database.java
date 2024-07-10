import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
// import com.mysql.cj.protocol.Resultset;

import io.github.cdimascio.dotenv.Dotenv;

public class Database {

    private static Connection con = null;

    static void databaseInit(){
        try{
            Dotenv dotenv = Dotenv.load();

            String url = dotenv.get("URL");
            String username = dotenv.get("USERNAME_DB");
            String pass = dotenv.get("PASS");
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            // System.out.println(url + username + pass);
            con = DriverManager.getConnection(url, username, pass);

        }catch(ClassNotFoundException | SQLException ec){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE , null, ec);
        }
    }
    

    public static Connection getCon() {
        return con;
    }

    // public void create(String username , String pass, String name){
    //     try{
    //         Statement stmt = con.createStatement();

    //         Resultset res = stmt.executequery
    //     }catch(SQLException ec){
    //         Logger.getLogger(Database.class.getName()).log(Level.SEVERE , null, ec);
    //     }
        
    // }


}
