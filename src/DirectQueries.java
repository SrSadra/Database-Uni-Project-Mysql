public class DirectQueries {
    public static final String GET_INVITATIONS = "SELECT p.id, p.username FROM invitations i INNER JOIN profile p ON i.to_id = p.id where i.user_id = ?";
    public static final String CREATE_DIRECT = "INSERT INTO directs " +
    "(user_id,to_id,is_readed, is_archived ) " +
    "VALUES (?, ?,true, false);";
    public static final String GET_DIRECT = "SELECT d.*,p.username FROM directs d INNER JOIN profile p ON d.to_id = p.id where d.user_id = ? AND d.to_id = ?";
    public static final String GET_MESSAGES_BY_DIRECT_ID = "SELECT * FROM message m where m.direct_id = ? ORDER BY m.time";
    public static final String CREATE_MESSAGE = "INSERT INTO message " +
    "(direct_id,from_id,to_id,data, time ) " +
    "VALUES (?, ?,?, ?, ?);";
    public static final String GET_USER_DIRECTS = "SELECT * FROM directs d where d.user_id = ?";
    public static final String MARK_DIRECT_UNREAD = "UPDATE directs d SET is_readed = false where d.direct_id = ?";
    public static final String ARCHIVE_DIRECT = "UPDATE directs d SET is_archived = true where d.direct_id = ?";
    public static final String DELETE_DIRECT = "DELETE FROM directs d where d.direct_id = ?";
    public static final String GET_DIRECT_UNREADED = "SELECT d.* , p.username FROM directs d INNER JOIN profile p ON d.to_id = p.id where d.direct_id = ? AND d.is_readed = false";
    public static final String GET_DIRECT_ARCHIVED = "SELECT d.* , p.username FROM directs d INNER JOIN profile p ON d.to_id = p.id where d.direct_id = ? AND d.is_archived = true";
    public static final String GET_DIRECT_BY_SEARCH = "SELECT d.* , p.username  FROM message m INNER JOIN directs d ON m.direct_id = d.direct_id INNER JOIN profile p ON d.to_id = p.id where m.data LIKE ? AND d.user_id = ?";
}
