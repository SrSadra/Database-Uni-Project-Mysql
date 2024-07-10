public class UserQueries {
    public static final String CREATE_USER = "INSERT INTO Users " +
    "(username, first_name, last_name, birthdate, password) " +
    "VALUES (?, ?, ?, ?, ?);";
    public static final String UPDATE_USER = "UPDATE Users SET first_name = ?, last_name = ?, birthdate = ?";
    public static final String GET_USER = "SELECT * FROM Users WHERE username = ? AND password = ?";
    public static final String GET_USERNAME = "SELECT username FROM Users WHERE username = ? ";
    public static final String SET_FIRSTNAME = "UPDATE Users SET first_name = ? WHERE username = ?";
    public static final String SET_LASTNAME = "UPDATE Users SET last_name = ? WHERE username = ?";

}
