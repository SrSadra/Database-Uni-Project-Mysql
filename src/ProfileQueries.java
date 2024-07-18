public class ProfileQueries {
    public static final String CREATE_PROFILE = "INSERT INTO PROFILE " +
    "(background_id, username,intro, featured, about,position ,location, company)" +
    "VALUES (? , ?, ?, ? ,?,?,? ,?);";
    public static final String GET_PROFILE_ID = "SELECT id FROM profile where username = ?";
    public static final String GET_PROFILE_BY_ID = "SELECT * FROM profile where id = ?";
    // public static final String GET_PROFILE_BY_USERNAME = "SELECT id FROM profile where username = ?";
    public static final String UPDATE_ABOUT = "UPDATE profile SET about = ? where username = ?";
    public static final String UPDATE_BACKGROUND_PROFILE = "UPDATE profile SET background_id = ? where username = ?";

    public static final String CREATE_BACKGROUND = "INSERT INTO images (name, image) VALUES (?, ?)";

    public static final String CREATE_LANGUAGE_PROFILE = "INSERT INTO profile_lang (id, name) VALUES (?, ?)";//Proficiency
    public static final String GET_LANGUAGE = "SELECT * FROM language where name = ?";

    public static final String GET_PROFILE_BY_USERNAME = "SELECT * FROM profile where username = ?";
    public static final String GET_PROFILE_BY_LIKE_USERNAME = "SELECT * FROM profile where username LIKE ?";
    public static final String GET_PROFILE_BY_LOCATION = "SELECT * FROM profile where location = ?";
    public static final String GET_PROFILE_BY_COMPANY = "SELECT * FROM profile where company = ?";

    public static final String GET_PROFILE_SKILLS = "SELECT s.* FROM profile_skills ps INNER JOIN skills s ON ps.skill_id = s.id where ps.profile_id = ?";

    public static final String UPDATE_POSITION = "UPDATE profile SET position = ? where id = ?";
}
