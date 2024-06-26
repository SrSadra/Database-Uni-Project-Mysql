public class UserQueries {
    public static final String CREATE_USER = "INSERT INTO Users " +
    "(username, name, last_name, birthdate, password) " +
    "VALUES (?, ?, ?, ?, ?);";
    public static final String UPDATE_USER = "UPDATE Users SET name = ?, last_name = ?, birthdate = ?";
    public static final String GET_USER = "SELECT * FROM Users WHERE name = ? AND password = ?";
    public static final String GET_USERNAME = "SELECT username FROM Users WHERE username = ? ";
    

}
