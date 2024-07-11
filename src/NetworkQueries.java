public class NetworkQueries {
    public static final String GET_INVITATIONS = "SELECT p.id, p.username, p.about FROM invitations i INNER JOIN profile p on i.from_id = p.id  where i.to_id = ? AND i.status = 'pending' ";
    public static final String ACCEPT_INVITATIONS = "UPDATE  invitations i SET i.status = 'accepted' where i.from_id = ? AND i.to_id = ?";
    public static final String CREATE_CONNECTIONS = "INSERT INTO connection " +
    "(profile_id,friend_id,accepted_date) " +
    "VALUES (?, ?,?);";
    public static final String IGNORE_INVITATIONS = "UPDATE  invitations i SET i.status = 'ignored' where i.from_id = ? AND i.to_id = ?";
    public static final String GET_PEOPLE_YOU_KNOW = "SELECT p.id, p.username, p.about FROM connection c1 INNER JOIN connection c2 ON c1.friend_id = c2.profile_id"
    + "INNER JOIN profile p on c2.friend_id = p.id where c1.profile_id = ?";
    public static final String CREATE_INVITATION = "INSERT INTO invitations " +
    "(from_id,to_id,status) " +
    "VALUES (?, ?,?);";
}
