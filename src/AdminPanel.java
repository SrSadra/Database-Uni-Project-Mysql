import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminPanel {
    private final Connection connection;

    public AdminPanel(){
        connection = Database.getCon();
    }

    public boolean startTables(){
        try{
            Statement stm = connection.createStatement();
            stm.addBatch(AdminQueries.CREATE_IMAGE_TABLE);
            stm.addBatch(AdminQueries.CREATE_PROFILE_LANG_TABLE);
            stm.addBatch(AdminQueries.CREATE_PROFILE_SKILLS_TABLE);
            stm.addBatch(AdminQueries.CREATE_PROFILE_TABLE);
            stm.addBatch(AdminQueries.CREATE_SKILL_TABLE);
            stm.addBatch(AdminQueries.CREATE_USER_TABLE);
            stm.addBatch(AdminQueries.CREATE_LANGUAGE_TABLE);

            stm.executeBatch();
            return true;
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
}
