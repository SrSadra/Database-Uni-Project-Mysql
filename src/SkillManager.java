import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Skill;


public class SkillManager {
    
    private final Connection connection;

    public SkillManager(){
        connection = Database.getCon();
    }


    boolean addSkill(Skill skill){
        try{
            PreparedStatement stm = connection.prepareStatement(SkillQueries.CREATE_SKILL);

            stm.setString(1, skill.getName());
            stm.setString(2, skill.getType());
            stm.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    String getSkillId(String skill_name){
        try {
            PreparedStatement stm = connection.prepareStatement(SkillQueries.GET_SKILL_ID);
            stm.setString(1,skill_name);
            ResultSet res = stm.executeQuery();
            return res.getString("id");
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    
}
