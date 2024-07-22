import java.util.Scanner;

import helper.Helper;
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
    private Post post;
    private Profile profile;
    private Notif notif;


    public ReqController(){
        adminPanel = new AdminPanel();
        signInUp = new SignInUp();
        network = new Network();
        direct = new Direct();
        post = new Post();
        profile = new Profile();
        notif = new Notif();
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
            }
        }

    }

    public void adminMenu(){
        while (true){
            System.out.println("1.Start Tables\n2.Add Skill");
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
            else if (button == 2){
                inp.nextLine();
                while(true){
                    System.out.println("Enter Skill name (skip):");
                    String tmp = inp.nextLine();
                    if (tmp.equals("skip")){
                        return;
                    }
                    System.out.println("Enter Skill type");
                    String type = inp.nextLine();
                    if (!adminPanel.createSkill(tmp, type)){
                        System.out.println("AN ERROR OCCURED!");
                    }
                    else{
                        System.out.println("New Skill " + tmp + " Added!");
                    }

                }


            }
        }
    }

    public void userMenu(){
        while (true){
            System.out.println("1.SignIn\n2.SignUp\n3.Back");
            int button = inp.nextInt();
            Helper.clearConsole();
            User user;
            if (button == 3){
                return;
            }
            else if (button == 1){
                userModel = signInUp.signIn();
                Helper.clearConsole();
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
                Helper.clearConsole();
            }
        }
    }


    public void userDashboard(){
        while (true){
            System.out.println("1.My Network\n2.Directs\n3.Edit Profile\n4.Homepage\n5.Create Post\n6.Your notif\n7.Sign Out");
            int button = inp.nextInt();
            if (button == 1){
                myNetworkMenu();
            }
            else if (button == 2){
                directsMenu();
            }
            else if (button == 3){
                settingMenu();
            }
            else if (button == 4){
                post.HomePage(profileModel);
            }
            else if (button == 5){
                post.createPost(profileModel);
            }
            else if (button == 6){
                notif.showNotif(profileModel);
            }
            else{
                signOut();
            }
        }
    }


    public void signOut(){
        System.out.println("See You later aligator!");
        profileModel = null;
        userModel = null;
        userMenu();
    }


    public void settingMenu(){
        System.out.println("1.Edit Intro\n2.Skills\n3.Edit About\n4.Edit background\n5.Edit Profile Language\n6.Edit Position");
        int button = inp.nextInt();
        if (button == 1){
            System.out.println("1.Edit first name\n2.Edit lastname");
            button = inp.nextInt();
            profile.editIntro(button, userModel);
        }
        else if (button == 2){
            profile.editSkill(userModel);
        }
        else if (button == 3){
            profile.addAbout(userModel);
        }
        else if (button == 4){
            profile.addBackground(userModel);
        }
        else if (button == 5){
            profile.addProfileLang(userModel);
        }
        else if (button == 6){
            profile.addPosition(profileModel);
        }
    }


    public void myNetworkMenu(){
        while (true){
            System.out.println("1.Search User\n2.Your Invitation\n3.People You May Know\n4.Search General User\n5.Back");
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
                network.searchGeneral(profileModel);
            }
            else{
                return;
            }
        }

    }


    public void directsMenu(){
        while (true){
            System.out.println("1.Start a Conversation\n2.Directs Folders\n3.Search in Directs\n4.Manage Directs\n5.back");
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
