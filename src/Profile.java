import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Skill;
import model.User;

public class Profile {
    private UserManager userManager;
    private ProfileManager profileManager;
    private LanguageManager languageManager;
    private Scanner inp = ReqController.inp;

    // Profile(UserManager userManager){
    //     this.userManager = userManager;
    // }

    Profile(){
        userManager = new UserManager();
        profileManager = new ProfileManager();
        languageManager = new LanguageManager();
    }



    public void editIntro(int button, User user){
        switch (button){
            case 1:
            System.out.println("Enter new Fisrt name: ");
            userManager.setFirstnameLastname(user.getUsername(), inp.nextLine(),button);
            break;
            case 2:
            System.out.println("Enter new Last name: ");
            userManager.setFirstnameLastname(user.getUsername(), inp.nextLine(), button);
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

    // public void editFirstname(User user){
    //     userManager.setFirstname(null, null)
    // }


}
