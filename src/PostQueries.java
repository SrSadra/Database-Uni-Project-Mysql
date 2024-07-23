public class PostQueries {
    public static final String CREATE_POST = "INSERT INTO post " +
    "(profile_id,title,data,time)" +
    "VALUES (? , ?, ?, ?);";
    public static final String GET_USER_POSTS = "SELECT * FROM post where profile_id = ? ORDER BY time";
    public static final String GET_POST_LIKES_COUNT = "SELECT count(distinct profile_id) as counts FROM post_like where post_id = ?";
    public static final String GET_POST_COMMENTS_COUNT = "SELECT count(profile_id) as counts FROM comment where post_id = ?";
    public static final String GET_POST_COMMENTS = "SELECT c.* , p.username FROM comment c INNER JOIN profile p ON p.id = c.profile_id where c.post_id = ? and c.ref_comment_id = null ORDER BY c.time";
    public static final String GET_COMMENTS_REPLY = "SELECT c.* , p.username FROM comment c INNER JOIN profile p ON p.id = c.profile_id where c.post_id = ? and  c.ref_comment_id = ?  ORDER BY c.time";
    public static final String CREATE_LIKE_COMMENT = "INSERT INTO comment_like (comment_id, profile_id) VALUES (?, ?)";
    public static final String CREATE_COMMENT = "INSERT INTO comment " +
    "(profile_id,post_id,data,time,is_replied,ref_comment_id)" +
    "VALUES (? , ?, ?, ?,? ,?);";
    public static final String CREATE_POST_LIKE = "INSERT INTO post_like " +
    "(profile_id,post_id,time)" +
    "VALUES (? , ?, ?);";
    public static final String CREATE_POST_SHARE = "INSERT INTO share " +
    "(profile_id,post_id,time)" +
    "VALUES (? , ?,?);";
    public static final String GET_USER_FRIENDS_POSTS = "SELECT p.*,pro.username FROM post p INNER JOIN connection c ON p.profile_id = c.profile_id INNER JOIN profile pro ON pro.id = c.profile_id where c.friend_id = ? ORDER BY p.time";
    public static final String GET_USER_FRIENDS_LIKED_POSTS = "SELECT p.*,pro.username FROM post p INNER JOIN post_like l ON p.post_id = l.post_id INNER JOIN connection c ON l.profile_id = c.profile_id INNER JOIN profile pro ON pro.id = p.profile_id where c.friend_id = ? ORDER BY p.time";
    public static final String GET_USER_FRIENDS_COMMENTED_POSTS = "SELECT p.*,pro.username FROM post p INNER JOIN comment com ON p.post_id = com.post_id INNER JOIN connection c ON com.profile_id = c.profile_id INNER JOIN profile pro ON pro.id = p.profile_id where c.friend_id = ? ORDER BY p.time";
    public static final String GET_USER_ID_BY_POST_ID = "SELECT profile_id FROM post where post_id = ?";
    public static final String UPDATE_MAIN_COMMENT_ISREPLIED = "UPDATE comment SET is_replied = true where id = ?";
    public static final String GET_POST_LIKE = "SELECT id FROM post_like where profile_id = ? and post_id = ?";
}
