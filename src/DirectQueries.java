public class DirectQueries {
    public static final String GET_INVITATIONS = "SELECT p.id, p.username FROM invitations i INNER JOIN profile p ON i.to_id = p.id where i.user_id = ?";
    public static final String CREATE_DIRECT = "INSERT INTO directs " +
    "(user_id,to_id,is_readed, is_archived ) " +
    "VALUES (?, ?,true, false);";
    public static final String GET_DIRECT = "SELECT d.*,p.username FROM directs d INNER JOIN profile p ON d.to_id = p.id where d.user_id = ? AND d.to_id = ?";
    public static final String GET_MESSAGES_BY_DIRECT_ID = "SELECT * FROM message m INNER JOIN message_direct md ON m.message_id = md.message_id where md.direct_id = ? ORDER BY m.time";
    public static final String CREATE_MESSAGE = "INSERT INTO message " +
    "(from_id,to_id,data, time ) " +
    "VALUES (?,?, ?, ?);";
    public static final String CREATE_MESSAGE_DIRECT = "INSERT INTO message_direct " +
    "(message_id, direct_id ) " +
    "VALUES (?, ?);";
    public static final String GET_USER_DIRECTS = "SELECT * FROM directs d where d.user_id = ?";
    public static final String MARK_DIRECT_UNREAD = "UPDATE directs d SET is_readed = false where d.direct_id = ?";
    public static final String ARCHIVE_DIRECT = "UPDATE directs d SET is_archived = true where d.direct_id = ?";
    public static final String DELETE_DIRECT = "DELETE FROM directs d where d.direct_id = ?";
    public static final String GET_DIRECT_UNREADED = "SELECT d.* , p.username FROM directs d INNER JOIN profile p ON d.to_id = p.id where d.user_id = ? AND d.is_readed = 0";
    public static final String GET_DIRECT_ARCHIVED = "SELECT d.* , p.username FROM directs d INNER JOIN profile p ON d.to_id = p.id where d.user_id = ? AND d.is_archived = 1";
    public static final String GET_DIRECT_BY_SEARCH = "SELECT distinct d.* , p.username  FROM message m INNER JOIN message_direct md ON m.message_id = md.message_id INNER JOIN directs d ON md.direct_id = d.direct_id INNER JOIN profile p ON d.user_id = p.id where m.data LIKE ? AND d.user_id = ?";
    public static final String GET_DIRECT_ID = "SELECT d.direct_id FROM directs d where d.user_id = ? and d.to_id = ?";
    public static final String DELETE_MESSAGE_DIRECT = "DELETE FROM message_direct md where md.direct_id = ?";
}
