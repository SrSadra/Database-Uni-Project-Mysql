import java.util.ArrayList;
import java.util.Scanner;

import model.NotifModel;
import model.ProfileModel;

public class Notif {
    private Scanner inp = ReqController.inp;
    private NotifManager notifManager;

    public Notif(){
        notifManager = new NotifManager();
    }

    public void showNotif(ProfileModel profileModel){
        ArrayList<NotifModel> arr = notifManager.getUserNotif(profileModel.getProfile_id());
        if (arr.size() == 0){
            System.out.println("There is no Notification yet...");
        }
        else{
            for (int i = 0 ; i < arr.size() ; i++){
                NotifModel tmp = arr.get(i);
                int cause_no = tmp.getCause_no();
                switch(cause_no){
                    case 1://birthday
                    System.out.println("It's " +tmp.getCaused_username() + " Birthday!" );
                    break;
                    case 2: 
                    System.out.println("Your profile has been seen by " + tmp.getCaused_username());
                    break;
                    case 3:
                    System.out.println(tmp.getWhy());
                    break;
                    case 4:
                    System.out.println(tmp.getCaused_username() + " Commented on your post!");
                    break;
                    case 5:
                    System.out.println(tmp.getWhy());
                    break;
                    case 6:
                    System.out.println(tmp.getWhy());
                    break;
                    case 7:
                    System.out.println(tmp.getWhy());//change position
                    break;
                }
            }
        }
    }


}
