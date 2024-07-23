public class SkillQueries {
    public static final String CREATE_SKILL = "INSERT INTO Skills " +
    "(name, type) " +
    "VALUES (?, ?);";
    public static final String GET_USER_SKILL = "SELECT s.*,sp.endorse_count FROM profile_skills sp INNER JOIN Skills s ON sp.skill_id = s.id where sp.profile_id = ?";
    public static final String CREATE_USER_SKILL = "INSERT INTO profile_skills " +
    "(profile_id,skill_id,endorse_count ) " +
    "VALUES (?, ?,?);";
    public static final String GET_SKILL_ID = "SELECT id FROM Skills where name = ?";
    public static final String DELETE_USER_SKILL = "DELETE FROM profile_skills where profile_id = ? AND skill_id = ?";
    public static final String UPDATE_ENDORSE_SKILL = "UPDATE profile_skills SET endorse_count = ? where profile_id = ? AND skill_id = ?";
    public static final String GET_ENDORSE_SKILL = "SELECT endorse_count from profile_skills where profile_id = ? AND skill_id = ?";
}
