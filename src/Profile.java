import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.NotifModel;
import model.ProfileModel;
import model.Skill;
import model.User;

public class Profile {
    private UserManager userManager;
    private ProfileManager profileManager;
    private LanguageManager languageManager;
    private SkillManager skillManager;
    private NotifManager notifManager;
    private Scanner inp = ReqController.inp;

    // Profile(UserManager userManager){
    //     this.userManager = userManager;
    // }

    Profile(){
        userManager = new UserManager();
        profileManager = new ProfileManager();
        languageManager = new LanguageManager();
        skillManager = new SkillManager();
        notifManager = new NotifManager();
    }



    public void editIntro(int button, User user){
        inp.nextLine();
        switch (button){
            case 1:
            System.out.println("Enter new Fisrt name: ");
            userManager.setFirstnameLastname(user.getUsername(), inp.nextLine(),button);
            System.out.println("Your firstname has changed!");
            break;
            case 2:
            System.out.println("Enter new Last name: ");
            userManager.setFirstnameLastname(user.getUsername(), inp.nextLine(), button);
            System.out.println("Your last name hast changed!");
            break;
        }
    }

    public void editSkill(User user){
        while (true){
            System.out.println("1.Edit Skills\n2.Add new Skill");
            int button = inp.nextInt();
            if (button == 1){
                ArrayList<Skill> arr = profileManager.getUserSkills(user.getUsername());
                for (int i = 0; i < arr.size() ; i++){
                    System.out.println(i + 1 + "." + arr.get(i).getName() + " " + arr.get(i).getType());
                }
                System.out.println("Choose which one you want to delete:");
                button = inp.nextInt();
                Skill tmp = arr.get(button - 1);
                if (profileManager.deleteUserSkills(user.getUsername(), tmp.getId())){
                    System.out.println(tmp.getName() + " sucsesfully deleted!");
                    return;
                }
                else{
                    System.out.println("AN ERROR OCCURED!");
                }
            }
            else if (button == 2){
                inp.nextLine();
                while (true){
                    System.out.println("Enter Skill name: ");
                    String skillname = inp.nextLine();
                    if (profileManager.createUserSkill(user.getUsername(), skillname)){
                        System.out.println(skillname + " sucsefully added!");
                        return;
                    }
                }
            }
            else{
                System.out.println("INCORRECT INPUT");
            }
        }
    }

    public void addAbout(User user){
        inp.nextLine();
        while (true){
            System.out.println("You can write about your years of experience, industry, or skills: ");
            String abouttext = inp.nextLine();
            if (!profileManager.createAbout(user.getUsername(), abouttext)){
                System.out.println("AN ERROR OCCURED!");
                continue;
            }
            System.out.println("About updated suscessfully!");
            return;
        }
    }

    public void addBackground(User user){ // nvarchar biniary for type nvarchar(MAX)
        while(true){
            System.out.println("Enter Image path for your background");
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            String path = null;
            String imageName = null;
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                path = chooser.getSelectedFile().getPath();
                imageName = chooser.getSelectedFile().getName();
                System.out.println("You chose to open this file: " + path);
            }
            else{
                continue;
            }

    //         CREATE TABLE images (
    //     id INT AUTO_INCREMENT PRIMARY KEY,
    //     name VARCHAR(255) NOT NULL,
    //     image LONGBLOB NOT NULL
    // );
            try {
                File imageFile = new File(path);
                FileInputStream inputStream = new FileInputStream(imageFile);
                if (!profileManager.createBackground(user, (Blob) inputStream, imageName)){
                    System.out.println("AN ERROR OCCURED!");
                    continue;
                }
                else{
                    System.out.println("About updated sucsessfully!");
                    return;
                }

            }catch (FileNotFoundException e){
                System.out.println(e);
            }
        }
    }


    public void addProfileLang(User user){
        inp.nextLine();
        while(true){
            System.out.println("Enter Your New Language: ");
            String lang = inp.nextLine();
            if (languageManager.getLanguage(lang)){
                if(!languageManager.addUserLanguage(user.getUsername(), lang)){
                    System.out.println("AN ERROR OCCURED!");
                    continue;
                }
                System.out.println(lang + " added Sucsessfully!");
                return;
            }
        }
    }


    public void showUserProfile(ProfileModel user,ProfileModel profileModel){
        notifManager.createNotif(profileModel.getProfile_id(), new NotifModel("a user has seen the profile", -1, 2, null), 2); //cause username is null for security

        while(true){
            System.out.println(profileModel.getUsername() +"\nId: " + profileModel.getProfile_id() + "\nAbout: " + profileModel.getAbout());
            System.out.println("----------------");
            System.out.println("1.Show Skills\n2.Back");
            int button = inp.nextInt();
            if (button == 1){
                while (true){
                    ArrayList<Skill> arr = profileManager.getUserSkills(profileModel.getUsername());
                    if (arr.size() == 0){
                        System.out.println("No Skills Found...");
                        break;
                    }
                    else{
                        for (int i = 0 ; i < arr.size() ; i++){
                            Skill skill = arr.get(i);
                            System.out.println(i + 1 + " " + skill.getName()+ ": " + skill.getType() + " 1.Endorse");
                        }
                        inp.nextLine();
                        String butt  = inp.nextLine();
                        String[] buttArr = butt.split(" ");
                        if (buttArr[1].equals("1")){//endorse
                            Skill skill = arr.get(Integer.valueOf(buttArr[0]) - 1);
                            if (skillManager.endorseSkill(skill, profileModel.getProfile_id())){
                                String stm = "You have endorse " + skill.getName() + " for " + profileModel.getUsername();
                                String stm2 = user.getUsername() + " have endorsed your " + skill.getName() + " skill!";
                                System.out.println(stm);
                                notifManager.createNotif(profileModel.getProfile_id(), new NotifModel(stm2, user.getProfile_id(), 6, user.getUsername()), 6);
                                return;
                            }
                            System.out.println("AN ERROR OCCURED!");
                        }
                    }

                }
            }
            else if (button == 2){
                return;
            }
        }
    }


    public void addPosition(ProfileModel profileModel){
        inp.nextLine();
        while (true){
            System.out.println("Enter your Current position: ");
            String pos = inp.nextLine();
            if (profileManager.addPosition(profileModel.getProfile_id(), pos) && notifManager.positionNotif(profileModel.getProfile_id(), profileModel.getUsername())){
                
                return;
            }
            System.out.println("AN ERROR OCCURED!");
        }
    }


}
