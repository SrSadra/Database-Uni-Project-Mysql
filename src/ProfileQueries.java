public class ProfileQueries {
    public static final String CREATE_PROFILE = "INSERT INTO PROFILE " +
    "(background_id, username,intro, featured, about )" +
    "VALUES (? , ?, ?, ? ,?);";
    public static final String GET_PROFILE_ID = "SELECT id FROM profile where username = ?";
    public static final String UPDATE_ABOUT = "UPDATE profile SET about = ? where username = ?";
    public static final String UPDATE_BACKGROUND_PROFILE = "UPDATE profile SET background_id = ? where username = ?";

    public static final String CREATE_BACKGROUND = "INSERT INTO images (name, image) VALUES (?, ?)";

    public static final String CREATE_LANGUAGE_PROFILE = "INSERT INTO profile_lang (id, name) VALUES (?, ?)";//Proficiency
    public static final String GET_LANGUAGE = "SELECT * FROM language where name = ?";
}
