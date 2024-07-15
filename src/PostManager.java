import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.CommentModel;
import model.PostModel;

public class PostManager {
    
    private final Connection connection;

    public PostManager(){
        connection = Database.getCon();
    }


    public boolean createPost(PostModel postModel){
        try{
            PreparedStatement stm = connection.prepareStatement(PostQueries.CREATE_POST);

            Date tmp = new Date();
            stm.setInt(1, postModel.getProfile_id());
            stm.setString(2, postModel.getTitle());
            stm.setString(3, postModel.getData());
            stm.setDate(4, new java.sql.Date((tmp.getTime())));
            stm.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public ArrayList<PostModel> getUserPosts(int profile_id){
        try{
            PreparedStatement stm =  connection.prepareStatement(PostQueries.GET_USER_POSTS);
            stm.setInt(1, profile_id);
            ResultSet rs = stm.executeQuery();
            ArrayList<PostModel> arr = new ArrayList<>();
            while (rs.next()){
                PostModel tmp = new PostModel(profile_id, rs.getString(3), rs.getString(4));
                tmp.setId(rs.getInt(1));
                tmp.setTime(rs.getDate(5));
                arr.add(tmp);
            }
            return arr;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public int postLikeCount(int post_id){
        try {
            PreparedStatement stm = connection.prepareStatement(PostQueries.GET_POST_LIKES_COUNT);
            stm.setInt(1,post_id);
            ResultSet res = stm.executeQuery();
            if (res.next()){
                return res.getInt("counts");
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return -1;
    }


    public int postCommentCount(int post_id){
        try {
            PreparedStatement stm = connection.prepareStatement(PostQueries.GET_POST_COMMENTS_COUNT);
            stm.setInt(1,post_id);
            ResultSet res = stm.executeQuery();
            if (res.next()){
                return res.getInt("counts");
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return -1;
    }


    public ArrayList<CommentModel> getPostComments(int post_id){
        try{
            PreparedStatement stm =  connection.prepareStatement(PostQueries.GET_POST_COMMENTS);
            stm.setInt(1, post_id);
            ResultSet rs = stm.executeQuery();
            ArrayList<CommentModel> arr = new ArrayList<>();
            while (rs.next()){
                CommentModel tmp = new CommentModel(rs.getInt(2), post_id, rs.getString(4), rs.getBoolean(6));
                tmp.setId(rs.getInt(1));
                tmp.setTime(rs.getDate(5));
                tmp.setUsername(rs.getString("username"));
                arr.add(tmp);
            }
            return arr;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }



    public ArrayList<CommentModel> getCommentReply(int post_id, int comment_id){
        try{
            PreparedStatement stm =  connection.prepareStatement(PostQueries.GET_COMMENTS_REPLY);
            stm.setInt(1, post_id);
            stm.setInt(2, comment_id);
            ResultSet rs = stm.executeQuery();
            ArrayList<CommentModel> arr = new ArrayList<>();
            while (rs.next()){
                CommentModel tmp = new CommentModel(rs.getInt(2), post_id, rs.getString(4), rs.getBoolean(6));
                tmp.setId(rs.getInt(1));
                tmp.setTime(rs.getDate(5));
                tmp.setUsername(rs.getString("username"));
                arr.add(tmp);
            }
            return arr;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public boolean likeComment(int profile_id, int comment_id){
        try{
            PreparedStatement stm = connection.prepareStatement(PostQueries.CREATE_LIKE_COMMENT);
            stm.setInt(1, comment_id);
            stm.setInt(2, profile_id);
            stm.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public boolean sendComment(int profile_id,int post_id ,String text){
        try{
            PreparedStatement stm = connection.prepareStatement(PostQueries.CREATE_COMMENT);

            Date tmp = new Date();
            stm.setInt(1, profile_id);
            stm.setInt(2, post_id);
            stm.setString(3, text);
            stm.setDate(4, new java.sql.Date((tmp.getTime())));
            stm.setBoolean(5, false);
            stm.setInt(6, 0);
            stm.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public boolean likePost(int profile_id , int post_id){
        try{
            PreparedStatement stm = connection.prepareStatement(PostQueries.CREATE_POST_LIKE);

            Date tmp = new Date();
            stm.setInt(1, profile_id);
            stm.setInt(2, post_id);
            stm.setDate(3, new java.sql.Date((tmp.getTime())));
            stm.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public boolean sharePost(int profile_id, int post_id){
        try{
            PreparedStatement stm = connection.prepareStatement(PostQueries.CREATE_POST_SHARE);

            Date tmp = new Date();
            stm.setInt(1, profile_id);
            stm.setInt(2, post_id);
            stm.setDate(3, new java.sql.Date((tmp.getTime())));
            stm.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public ArrayList<PostModel> getFriendsPost(int profile_id){
        try{
            PreparedStatement stm =  connection.prepareStatement(PostQueries.GET_USER_FRIENDS_POSTS);
            stm.setInt(1, profile_id);
            ResultSet rs = stm.executeQuery();
            ArrayList<PostModel> arr = new ArrayList<>();
            while (rs.next()){
                PostModel tmp = new PostModel(profile_id, rs.getString(3), rs.getString(4));
                tmp.setId(rs.getInt(1));
                tmp.setTime(rs.getDate(5));
                tmp.setUsername(rs.getString("username"));
                arr.add(tmp);
            }
            return arr;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }


    public ArrayList<PostModel> getFriendsLikeCommentPost(int profile_id, boolean isliked){
        try{
            String qu = null;
            if (isliked){
                qu = PostQueries.GET_USER_FRIENDS_LIKED_POSTS;
            }
            else{
                qu = PostQueries.GET_USER_FRIENDS_COMMENTED_POSTS;
            }
            PreparedStatement stm =  connection.prepareStatement(qu);
            stm.setInt(1, profile_id);
            ResultSet rs = stm.executeQuery();
            ArrayList<PostModel> arr = new ArrayList<>();
            while (rs.next()){
                PostModel tmp = new PostModel(profile_id, rs.getString(3), rs.getString(4));
                tmp.setId(rs.getInt(1));
                tmp.setTime(rs.getDate(5));
                tmp.setUsername(rs.getString("username"));
                arr.add(tmp);
            }
            return arr;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }





}
