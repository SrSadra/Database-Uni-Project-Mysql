import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App {
    public static void main(String[] args) throws Exception {
        // Map<String, String> env_var = System.getenv();

        // // Loop through all environment variables
        // for (String envName : env_var.keySet()) {
        //     // Print environment variable name and value to console
        //     System.out.format("%s=%s", envName, env_var.get(envName));
        //     System.out.println();
        // }
        Database.databaseInit();
        new ReqController().startingMenu();
    }
}
