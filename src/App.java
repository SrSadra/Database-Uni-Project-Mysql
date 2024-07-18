import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App {
    public static void main(String[] args) throws Exception {
        Database.databaseInit();
        new ReqController().startingMenu();
    }
}
