import java.util.ArrayList;
import java.util.Scanner;

import model.NotifModel;
import model.ProfileModel;

public class Network {
    private Scanner inp = ReqController.inp;
    private NetworkManager networkManager; 
    private ProfileManager profileManager;
    private NotifManager notifManager;
    private Profile profileClass;

    public Network(){
        networkManager = new NetworkManager();
        profileManager = new ProfileManager();
        profileClass = new Profile();
    }


    public void searchUser(ProfileModel profile){// need work
        int profile_id = profile.getProfile_id();
        while (true){
            System.out.println("Search Username to send invitation: ");
            String username = inp.next();
            ProfileModel to = profileManager.getProfileByUsername(username);
            if (to == null){
                System.out.println("There is no such User");
                continue;
            }
            if (networkManager.createInvitation(profile_id, to.getProfile_id())){
                System.out.println("An Invitation has been sent to " + to.getUsername());
                profileClass.showUserProfile(profile, to);
                return;
            }
            System.out.println("AN ERROR OCCURED!");
        }
    }

    public void searchGeneral(ProfileModel profileModel){
        System.out.println("Select your filtering:\n1.basic search\nbased on:\n2.location 3.profile language 4.current company");
        int button = inp.nextInt();
        inp.nextLine();
        if (button == 1){
            basicSearch(profileModel);
        }
        else if (button == 2){
            searchBaseLocation(profileModel);
        }
        else if (button == 3){

        }
        else {
            searchBaseCompany(profileModel);
        }
    }

    public void basicSearch(ProfileModel profileModel){
        System.out.println("Enter username: ");
        String username = inp.nextLine();
        username = "%" + username + "%";
        ProfileModel target = profileManager.getProfileByLikeUsername(username);
        profileClass.showUserProfile(profileModel, target);
    }

    public void searchBaseLocation(ProfileModel profileModel){
        while(true){
            System.out.println("Enter location to search");
            String location = inp.nextLine();
            ArrayList<ProfileModel> target = profileManager.getUserBaseLocation(location);
            if (target.size() == 0){
                System.out.println("There is No user With this Location provided...");
                continue;
            }
            for (int i = 0 ; i< target.size() ; i++){
                System.out.println(i + 1 + "-" + target.get(i).getUsername());
            }
            int button = inp.nextInt();
            ProfileModel tmp = target.get(button - 1);
            profileClass.showUserProfile(profileModel, tmp);
            return;
        }
    }


    public void searchBaseCompany(ProfileModel profileModel){
        while(true){
            System.out.println("Enter Company to search");
            String company = inp.nextLine();
            ArrayList<ProfileModel> target = profileManager.getUserBaseCompany(company);
            if (target.size() == 0){
                System.out.println("There is No user With this company provided...");
                continue;
            }
            for (int i = 0 ; i< target.size() ; i++){
                System.out.println(i + 1 + "-" + target.get(i).getUsername());
            }
            int button = inp.nextInt();
            ProfileModel tmp = target.get(button - 1);
            profileClass.showUserProfile(profileModel, tmp);
            return;
        }
    }

    public void getInvitation(ProfileModel profile){
        System.out.println("Your Invitations");
        ArrayList<ProfileModel> arr = networkManager.getInvitations(profile.getProfile_id());
        if (arr.size() == 0){
            System.out.println("There is no Invitations");
            return;
        }
        while(true){
            for (int i = 0 ; i < arr.size() ; i++){
                ProfileModel tmp = arr.get(i);

                System.out.println(i + 1 + " " + tmp.getUsername() + " 1.Accept 2.Ignore");
                if (tmp.getAbout() != null){
                    System.out.println(tmp.getAbout());
                }
                int mutual = networkManager.mutualConnections(tmp.getProfile_id(), tmp.getProfile_id());
                if (mutual >= 0){
                    System.out.println("You Have " + mutual + " Mutual Friends!");
                }
                System.out.println("----------------------");
            }
            String button = inp.nextLine();
            if (button.equals("skip")){
                return;
            }
            String[] butt = button.split(" ");
            if (butt[1].equals("1")){
                ProfileModel tmp = arr.get(Integer.valueOf(butt[0]) - 1);
                if(!networkManager.acceptInvitation(profile.getProfile_id(), tmp.getProfile_id())){
                    System.out.println("AN ERROR OCCURED!");
                    continue;
                }
                System.out.println("You have accept " + tmp.getUsername() + "invitation!");
                return;
            }
            else {// ignote
                networkManager.ignoreInvitation(profile.getProfile_id(), arr.get(Integer.valueOf(butt[0]) - 1).getProfile_id());
                return;
            }
        }
    }


    public void peopleYouKnow(ProfileModel profile){
        while(true){
            int id = profile.getProfile_id();
            ArrayList<ProfileModel> arr = networkManager.getPeopleYouKnow(id);
            if (arr.size() == 0){
                System.out.println("No User Found...");
                return;
            }
            System.out.println("Choose People you may know to Send Connect Req:");
            for (int i = 0 ; i < arr.size() ; i++){
                ProfileModel tmp = arr.get(i);
                System.out.println(i + 1 + " " + tmp.getUsername());
            }
            int button = inp.nextInt();
            ProfileModel to = arr.get(button - 1);
            if (!networkManager.createInvitation(id, to.getProfile_id())){
                System.out.println("AN ERORR OCCURED!");
                continue;
            }
            System.out.println("An Invitation has been sent to " + to.getUsername());
            return;
        }
    }


    public void createInvitation(int profile_id, int to_id){

    }
}
