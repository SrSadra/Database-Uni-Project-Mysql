public class SkillQueries {
    public static final String CREATE_SKILL = "INSERT INTO Skills " +
    "(name, type) " +
    "VALUES (?, ?);";
    public static final String GET_USER_SKILL = "SELECT s.* FROM profile_skills sp INNER JOIN Skills s ON sp.skill_id = s.id where sp.prof_id = ?";
    public static final String CREATE_USER_SKILL = "INSERT INTO profile_skills " +
    "(prof_id,skill_id ) " +
    "VALUES (?, ?);";
    public static final String GET_SKILL_ID = "SELECT id FROM Skills where name = ?";
    public static final String DELETE_USER_SKILL = "DELETE FROM profile_skills where prof_id = ? AND skill_id = ?";

}
