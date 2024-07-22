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
            stm.addBatch(AdminQueries.CREATE_CONNECTION_TABLE);
            stm.addBatch(AdminQueries.CREATE_DIRECTS_TABLE);
            stm.addBatch(AdminQueries.CREATE_INVITATIONS_TABLE);
            stm.addBatch(AdminQueries.CREATE_MESSAGE_TABLE);
            stm.addBatch(AdminQueries.CREATE_MESSAGE_DIRECT_TABLE);
            stm.addBatch(AdminQueries.CREATE_POST_TABLE);
            stm.addBatch(AdminQueries.CREATE_COMMENT_TABLE);
            stm.addBatch(AdminQueries.CREATE_COMMENT_LIKE_TABLE);
            stm.addBatch(AdminQueries.CREATE_POST_LIKE_TABLE);
            stm.addBatch(AdminQueries.CREATE_POST_SHARE_TABLE);
            stm.addBatch(AdminQueries.CREATE_NOTIF_TABLE);
            stm.addBatch(AdminQueries.CREATE_PROFILE_NOTIF_TABLE);
            // stm.addBatch(AdminQueries.CREATE_LIKES_TABLE);

            stm.executeBatch();
            return true;
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public boolean createSkill(String name, String type){
        try{
            PreparedStatement stm = connection.prepareStatement(AdminQueries.INSER_SKILL);

            stm.setString(1, name);
            stm.setString(2, type);
            stm.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
}
