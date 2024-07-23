import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import model.DirectModel;
import model.Message;
import model.ProfileModel;
import model.User;

public class Direct {
    private DirectManager directManager;
    private ProfileManager profileManager;
    private Scanner inp = ReqController.inp;

    public Direct(){
        directManager = new DirectManager();
        profileManager = new ProfileManager();
    }

    

    public void startMessage(ProfileModel profile){
        while(true){
            System.out.println("Choose a User to start (0 for back): ");
            ArrayList<ProfileModel> conn = directManager.getConnections(profile.getProfile_id());
            if (conn == null){
                return;
            }
            else if (conn.size() == 0){
                System.out.println("There Is No Connections!");
                return;
            }
            for (int i = 0 ; i < conn.size() ; i++){
                System.out.println(i + 1 + " " + conn.get(i).getUsername());
            }
            int button = inp.nextInt();
            inp.nextLine();
            if (button == 0){
                return;
            }
            ProfileModel to = conn.get(button - 1);
            int tmp = directManager.createDirect(profile.getProfile_id(), to.getProfile_id());
            if (tmp == 0){
                System.out.println("AN ERROR OCCURED!");
                continue;
            }
            goDirect(tmp, profile, to);
        }
    }


    public void goDirect(int direct_id,ProfileModel profile, ProfileModel to){
        // inp.nextLine();
        ArrayList<Message> arr = directManager.getDirectMessages(direct_id);
        if (arr.size() == 0){
            System.out.println("There is no message...\nStart conversation (skip):");
        }
        else{ 
            // int user_id = profile.getProfile_id();
            // int to_id = to.getProfile_id();
            for (int i = 0 ; i < arr.size() ; i++){
                Message message = arr.get(i);
                if (message.getFrom_id() == to.getProfile_id()){ // if sender is our contact
                    System.out.print("               ");
                    System.out.println(message.getData() + "  " + message.getTime() + " : " + to.getUsername());
                } 
                else{//if us
                    System.out.println(profile.getUsername() + " : " + message.getData() + "  " + message.getTime());
                }
            }
        }
            while(true){
                String text = inp.nextLine();
                if (text.equals("skip")){
                    return;
                }
                if(!directManager.createMessage(new Message(0, profile.getProfile_id(), to.getProfile_id(), text,new Date()))){  
                    System.out.println("AN ERROR OCCURED!");
                    continue;
                }
                System.out.println(profile.getUsername() + " :" + text);
            }
    }


    public void manageDirects(ProfileModel profileModel){
        inp.nextLine();
        ArrayList<DirectModel> arr = directManager.getDirects(profileModel.getProfile_id(), 0);
        while (true){
            if (arr.size() == 0){
                System.out.println("There is No direct!");
                return;
            }
            for (int i = 0 ; i < arr.size() ; i++){
                DirectModel tmp = arr.get(i);
                System.out.print(i + 1 + " " + tmp.getTo_id());
                if (tmp.getIs_readed()){
                    System.out.print(" 1.Mark as Unread ");
                }
                if (!tmp.getIs_archived()){
                    System.out.print("2.Archive ");
                }
                System.out.println("3.Delete Chat");
                System.out.println("------------------");
            }
            String button = inp.nextLine();
            String[] butt = button.split(" ");
            DirectModel dm = arr.get(Integer.valueOf(butt[0]) - 1);
            if (butt[1].equals("1")){//mark as unread
                if(!directManager.markUnread(dm.getDirect_id())){
                    System.out.println("AN ERROR OCCURED!");
                    continue;
                }
                return;
            }else if (butt[1].equals("2")){ // archive
                if(!directManager.archieveDirect(dm.getDirect_id())){
                    System.out.println("AN ERROR OCCURED!");
                    continue;
                }
                System.out.println("This chat has been archived");
                return;
            }else if (butt[1].equals("3")){ // delete
                if(!directManager.archieveDirect(dm.getDirect_id())){
                    System.out.println("AN ERROR OCCURED!");
                    continue;
                }
                System.out.println("This chat has been deleted");
                return;
            }
        }
    }


    public void directsParts(ProfileModel profileModel){
        System.out.println("1.Unreaded Chats\n2.Archived Chats");
        int button = inp.nextInt();
        if (button == 1){//unreaded
            System.out.println("Unreaded Chats: ");
        }            
        else {
            System.out.println("Archived Chats: ");
        }
        ArrayList<DirectModel> arr = directManager.getDirects(profileModel.getProfile_id(), button);
        if (arr.size() == 0){
            System.out.println("There is no unreaded/archived chats");
            return;
        }
        for (int i = 0 ; i < arr.size() ; i++){
            System.out.println(i + 1 + " " + arr.get(i).getTo_username());
        }
        button = inp.nextInt();
        DirectModel tmp  = arr.get(button - 1);
        ProfileModel to = profileManager.getProfileById(tmp.getTo_id());
        goDirect(tmp.getDirect_id(), profileModel, to);
    }


    public void searchDirects(ProfileModel profileModel){
        inp.nextLine();
        while (true){
            System.out.println("Enter something to Search: ");
            String text = inp.nextLine();
            ArrayList<DirectModel> arr  = directManager.searchMessage(text, profileModel.getProfile_id());
            if (arr.size() == 0){
                System.out.println("NO SUCH MESSAGE EXIST!");
                continue;
            }
            System.out.println("Select Direct to continue (0 for back):");
            for (int i = 0 ; i < arr.size() ; i++){
                System.out.println(i + 1 + " " + arr.get(i).getTo_username());
            }
            int button = inp.nextInt();
            if (button == 0){
                return;
            }
            DirectModel tmp  = arr.get(button - 1);
            ProfileModel to = profileManager.getProfileById(tmp.getTo_id());
            goDirect(tmp.getDirect_id(), profileModel, to);
            return;
        }

    }



}
