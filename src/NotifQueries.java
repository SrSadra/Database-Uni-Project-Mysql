public class NotifQueries {
    public static final String GET_USER_NOTIF = "SELECT n.*, p.username FROM prof_notif pn INNER JOIN notif n ON pn.notif_id = n.notif_id INNER JOIN profile p ON p.id = n.caused_by_id where pn.profile_id = ? AND pn.status = 'unseen' ORDER BY n.time";
}
