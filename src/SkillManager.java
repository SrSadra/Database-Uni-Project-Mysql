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
            if (res.next()){
                return res.getString("id");
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public boolean endorseSkill(Skill skill,int profile_id){
        try{
            PreparedStatement stm = connection.prepareStatement(SkillQueries.GET_ENDORSE_SKILL);
            stm.setInt(1,profile_id);
            stm.setInt(2, skill.getId());
            ResultSet res = stm.executeQuery();
            int endorse = 0;
            if (res.next()){
                endorse = res.getInt("endorse");
            }
            endorse += 1;

            PreparedStatement stm2 = connection.prepareStatement(SkillQueries.UPDATE_ENDORSE_SKILL);

            stm2.setInt(1, endorse);
            stm2.setInt(2, profile_id);
            stm2.setInt(3, skill.getId());
            stm2.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    
}
