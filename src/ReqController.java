import java.util.Scanner;

import model.User;

public class ReqController {
    public static final Scanner inp = new Scanner(System.in);
    private AdminPanel adminPanel;
    private SignInUp signInUp;

    public ReqController(){
        adminPanel = new AdminPanel();
        signInUp = new SignInUp();
    }


    public void startingMenu(){
        while(true){
            System.out.println("Welcome\n1.Admin Mode\n2.User Mode");
            int button = inp.nextInt();
            if (button == 1){//admin
                adminMenu();
            }
            else if (button == 2){//user
                userMenu();
            }else{

            }
        }

    }

    public void adminMenu(){
        while (true){
            System.out.println("1.Start Tables\n2.");
            int button = inp.nextInt();
            if (button == 1){
                if (!adminPanel.startTables()){
                    System.out.println("AN ERROR OCCURED!");
                }
                else{
                    System.out.println("Tables started sucsesfully");
                    return;
                }
            }
        }
    }

    public void userMenu(){
        while (true){
            System.out.println("1.SignIn\n2.SignUp\n3.Back");
            int button = inp.nextInt();
            User user;
            if (button == 3){
                return;
            }
            else if (button == 1){
                user = signInUp.signIn();
                System.out.println("Welcome " + user.getName() + "!");
            }
            else if (button == 2){
                user = signInUp.signUp();
            }
        }
    }

}
