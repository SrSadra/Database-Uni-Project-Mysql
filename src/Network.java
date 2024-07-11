import java.util.ArrayList;
import java.util.Scanner;

import model.ProfileModel;

public class Network {
    private Scanner inp = ReqController.inp;
    private NetworkManager networkManager; 

    public Network(){
        networkManager = new NetworkManager();
    }


    public void searchUser(ProfileModel profile){// need work
        System.out.println("Search Username to send invitation: ");
        String username = inp.next();
    }

    public void getInvitation(ProfileModel profile){
        System.out.println("Your Invitations");
        ArrayList<ProfileModel> arr = networkManager.getInvitations(profile.getProfile_id());
        if (arr.size() == 0){
            System.out.println("There is no Invitations");
        }
        while(true){
            for (int i = 0 ; i < arr.size() ; i++){
                ProfileModel tmp = arr.get(i);
                System.out.println(i + 1 + " " + tmp.getUsername() + " 1.Accept 2.Ignore");
                System.out.println(tmp.getAbout());
                System.out.println("----------------------");
            }
            String button = inp.nextLine();
            if (button.equals("skip")){
                return;
            }
            String[] butt = button.split(" ");
            if (butt[1].equals("1")){
                if(!networkManager.acceptInvitation(profile.getProfile_id(), arr.get(Integer.valueOf(butt[0]) - 1).getProfile_id())){
                    System.out.println("AN ERROR OCCURED!");
                    continue;
                }
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
            System.out.println("Choose People you may know to Send Connect Req:");
            int id = profile.getProfile_id();
            ArrayList<ProfileModel> arr = networkManager.getPeopleYouKnow(id);
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
            System.out.println("An Initation has been sent to " + to.getUsername());
            return;
        }
    }


    public void createInvitation(int profile_id, int to_id){

    }
}
