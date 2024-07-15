import java.util.Scanner;

import model.ProfileModel;
import model.User;

public class ReqController {
    public static final Scanner inp = new Scanner(System.in);
    private User userModel;
    private ProfileModel profileModel;
    private AdminPanel adminPanel;
    private SignInUp signInUp;
    private Network network;
    private Direct direct;


    public ReqController(){
        adminPanel = new AdminPanel();
        signInUp = new SignInUp();
        network = new Network();
        direct = new Direct();
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
                userModel = signInUp.signIn();
                if (userModel == null){
                    System.out.println("AN ERROR OCCURED!");
                    continue;
                }
                profileModel = new ProfileManager().getProfileByUsername(userModel.getUsername());
                String tmp = userModel.getName() != null ? userModel.getName() : userModel.getUsername();
                System.out.println("Welcome " + tmp + "!");
                userDashboard();
            }
            else if (button == 2){
                user = signInUp.signUp();
            }
        }
    }


    public void userDashboard(){
        System.out.println("1.My Network\n2.Directs\n3.Edit Profile");
        int button = inp.nextInt();
        if (button == 1){
            myNetworkMenu();
        }
        else if (button == 2){
            directsMenu();
        }
    }


    public void myNetworkMenu(){
        while (true){
            System.out.println("1.Search User\n2.Your Invitation\n3.People You May Know\n4.Back");
            // int mutual = new NetworkManager().mutualConnections(profileModel.getProfile_id());
            // System.out.println("(Mutual Connections: " + mutual + " )");
            int button = inp.nextInt();
            inp.nextLine();
            if (button == 1){
                network.searchUser(profileModel);
            }
            else if (button == 2){
                network.getInvitation(profileModel);
            }
            else if (button == 3){
                network.peopleYouKnow(profileModel);
            }
            else if (button == 4){
                return;
            }
        }

    }


    public void directsMenu(){
        while (true){
            System.out.println("1.Start a Conversation\n2.Directs Folders\n3.Search in Directs\n4.Manage Directs\n5.Back");
            int button = inp.nextInt();
            if (button == 1){
                direct.startMessage(profileModel);
            }
            else if (button == 2){
                direct.directsParts(profileModel);
            }
            else if (button == 3){
                direct.searchDirects(profileModel);
            }
            else if (button == 4){
                direct.manageDirects(profileModel);
            }
            else{//back
                return;
            }
        }
    }
}
