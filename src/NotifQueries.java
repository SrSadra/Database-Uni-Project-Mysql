public class NotifQueries {
    public static final String GET_USER_NOTIF = "SELECT n.*, p.username FROM profile_notif pn INNER JOIN notif n ON pn.notif_id = n.id INNER JOIN profile p ON p.id = n.caused_by_id where pn.profile_id = ? AND pn.status = 'unseen' ORDER BY n.time";
    public static final String CREATE_NOTIF = "INSERT INTO notif " +
    "(reason,caused_by_id,time,cause_no)" +
    "VALUES (? , ?, ?, ?);";
    public static final String CREATE_PROF_NOTIF = "INSERT INTO profile_notif " +
    "(profile_id,notif_id,status)" +
    "VALUES (? , ?, ?);";
    public static final String GET_SPECIFIC_NOTIF = "SELECT id FROM notif n INNER JOIN profile_notif pn ON n.id = pn.notif_id where n.caused_by_id = ? and n.cause_no = ? and pn.profile_id = ?";
}
