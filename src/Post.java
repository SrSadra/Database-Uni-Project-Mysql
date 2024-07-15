import java.util.ArrayList;
import java.util.Scanner;

import model.CommentModel;
import model.PostModel;
import model.ProfileModel;

public class Post {
    private Scanner inp = ReqController.inp;
    private PostManager postManager;

    Post(){
        postManager = new PostManager();
    }


    public void createPost(ProfileModel profileModel){
        while (true){
            System.out.println("Enter your post title: ");
            String title = inp.nextLine();
            System.out.println("Enter your Post Text: ");
            String text = inp.nextLine();
            if (!postManager.createPost(new PostModel(profileModel.getProfile_id(), title, text))){
                System.out.println("AN ERROR OCCURED");
                continue;
            }
            System.out.println("New Post has been created!");
            return;
        }
    }


    public void showMyPosts(ProfileModel profileModel){
        ArrayList<PostModel> arr = postManager.getUserPosts(profileModel.getProfile_id());
        postList(arr, profileModel);
    }

    public void showPost(ProfileModel profileModel,PostModel postModel, int likesCnt, int commentCnt){
        while(true){
            System.out.println(postModel.getTitle());
            System.out.println("description: " + postModel.getData());
            System.out.println(postModel.getTime());
            System.out.println("-------------------");
            System.out.println("likes: " + likesCnt);
            System.out.println("comments: " + commentCnt);
            System.out.println("1.Show Comments  2.Like  3.Share");
            int button = inp.nextInt();
            if (button == 1){//show comments
                showComments(profileModel, postModel.getId());
                return;
            }
            else if (button == 2){
                if(postManager.likePost(profileModel.getProfile_id(), postModel.getId())){
                System.out.println("Post has been liked!"); 
                return;
                }
                System.out.println("AN ERROR OCCURED!");
            }
            else{//share
                if (postManager.sharePost(profileModel.getProfile_id(), postModel.getId())){
                    System.out.println("This post has been Shared ");
                    return;
                }
                System.out.println("AN ERROR OCCURED!");
            }
        }
    }

    public void showComments(ProfileModel profileModel,int post_id){
        ArrayList<CommentModel> arr = postManager.getPostComments(post_id);
        if (arr.size() == 0){
            System.out.println("There Is no comments yet...");
        }
        else{
            for (int i = 0 ; i < arr.size() ; i++){
                CommentModel tmp = arr.get(i);
                System.out.println(tmp.getUsername() + ": " + tmp.getData() + "  " + tmp.getTime() + "  1.like 2.reply");
                if (tmp.getIs_replied()){
                    String space = "   ";
                        ArrayList<CommentModel> replies = postManager.getCommentReply(post_id, tmp.getId());
                        //work on this shit
                }
            }
            String button = inp.nextLine();
            if (button.equals("skip")){//skip like or replying
                sendComment(profileModel, post_id);
            }
            String[] butt = button.split(" ");
            CommentModel tmp = arr.get(Integer.valueOf(butt[0]) - 1);// imple
            if (butt[1].equals("1")){//like
                if (postManager.likeComment(profileModel.getProfile_id(), post_id)){
                    System.out.println("You liked " + tmp.getUsername() + " message!");
                }
            }else if (butt[1].equals("2")){// reply
                System.out.println("Enter your text for reply: ");
                String text = inp.nextLine();

            }

        }
    }


    public void sendComment(ProfileModel profileModel,int post_id){
        System.out.println("Enter your Comment: ");
        while(true){
            String text = inp.nextLine();
            if(text.equals("skip")){
                return;
            }
            if (!postManager.sendComment(profileModel.getProfile_id(), post_id, text)){
                System.out.println("AN ERROR OCCURED!");
                continue;
            }
            System.out.println(profileModel.getUsername() + ": " + text);
        }
    }

    public void postList(ArrayList<PostModel> arr, ProfileModel profileModel){
        if (arr.size() == 0){
            System.out.println("No posted yet...");
        }
        else{
            System.out.println("Select a Post to show: ");
            for (int i = 0 ; i < arr.size(); i++){
                System.out.println(i + 1 + " " + arr.get(i).getUsername() + ": " + arr.get(i).getTitle());
            }
            int button = inp.nextInt();
            PostModel tmp = arr.get(button - 1);
            int likeCnt = postManager.postLikeCount(tmp.getId());
            int commentCnt = postManager.postCommentCount(tmp.getId());
            showPost(profileModel,tmp, likeCnt, commentCnt);
        }
    }


    public void HomePage(ProfileModel profileModel){
        int profile_id = profileModel.getProfile_id();
        ArrayList<PostModel> arr = postManager.getFriendsPost(profile_id);
        postList(arr, profileModel);
        ArrayList<PostModel> arr2 = postManager.getFriendsLikeCommentPost(profile_id, true);
        postList(arr2, profileModel);
        ArrayList<PostModel> arr3 = postManager.getFriendsLikeCommentPost(profile_id, false);
        postList(arr3, profileModel);
    }


}