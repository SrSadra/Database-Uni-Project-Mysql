import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
// import com.mysql.cj.protocol.Resultset;

public class Database {

    private static Connection con = null;

    static void databaseInit(){
        try{
            String url = System.getenv("URL");
            String username = System.getenv("USERNAME");
            String pass = System.getenv("PASS");
            
            Class.forName("com.mysql.jdbc.Driver");
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
