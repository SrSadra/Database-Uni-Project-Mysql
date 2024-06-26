import java.util.Scanner;
import model.User;

public class SignInUp {
    private Scanner inp = ReqController.inp;
    private Database db;
    private UserManager userManager;
    

    SignInUp(Database db, UserManager userManager){
        this.db = db;
        this.userManager = userManager;
    }
    
    public void signUp(){
        String username;
        while (true){
            System.out.println("Enter Username:");
            username = inp.next();
            if (userManager.getUsername(username) == null){
                break;
            }
            System.out.println("This Username already taken");
        }
        System.out.println("Enter Name:");
        String name = inp.next();
        System.out.println("Enter password:");
        String pass = inp.next();
        System.out.println("Enter lastname:");
        String lastname = inp.next();
        // birth day
        
        // hashpassword

        User user = new User(name, username, lastname, pass, null);
        if (!userManager.create(user)){
            System.out.println("Something went wrong!");
            return;
        }
    }

    public void signIn(){
        while (true){
            System.out.println("Enter Username: ");
            String username = inp.next();
            System.out.println("Enter Password: ");
            String password = inp.next();
            if (userManager.getUser(username, password) == null){
                System.out.println("Incorrect Username or Password");
            }
            else{
                break;
            }
        }
        
    }
}
