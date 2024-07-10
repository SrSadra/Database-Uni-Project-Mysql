
import java.util.Date;
import java.util.Scanner;

import helper.Helper;
import model.User;

public class SignInUp {
    private Scanner inp = ReqController.inp;
    private Database db;
    private UserManager userManager;
    

    SignInUp(){
        userManager = new UserManager();
    }
    
    public User signUp(){
        String username;
        while (true){
            System.out.println("Enter Username:");
            username = inp.next();
            if (userManager.getUsername(username) == null){
                break;
            }
            System.out.println("This Username already taken");
        }        
        System.out.println("Enter password:");
        String pass = inp.next();
        System.out.println("Enter Name (skip):");
        String name = inp.next();
        if (name.equals("skip")){
            name = null;
        }
        System.out.println("Enter lastname (skip):");
        String lastname = inp.next();
        if (lastname.equals("skip")){
            lastname = null;
        }
        System.out.println("Enter Birthdate like 2002/10/3 (skip):");
        String birthdateTmp = inp.next();
        Date birthdate;
        if (birthdateTmp.equals("skip")){
            birthdate = null;
        }
        else{
            birthdate = Helper.birthDateCalculator(birthdateTmp);
        }
        
        // hashpassword

        User user = new User(name, username, lastname, pass, birthdate);
        if (!userManager.create(user)){
            System.out.println("Something went wrong!");
            return null;
        }
        return user;
    }

    public User signIn(){
        while (true){
            System.out.println("Enter Username: ");
            String username = inp.next();
            System.out.println("Enter Password: ");
            String password = inp.next();
            User user = userManager.getUser(username, password);
            if (user == null){
                System.out.println("Incorrect Username or Password");
            }
            else{
                return user;
            }
        }
        
    }
}
