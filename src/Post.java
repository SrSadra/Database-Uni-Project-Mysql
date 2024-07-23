import java.util.ArrayList;
import java.util.Scanner;

import helper.Helper;
import model.CommentModel;
import model.NotifModel;
import model.PostModel;
import model.ProfileModel;

public class Post {
    private Scanner inp = ReqController.inp;
    private PostManager postManager;
    private NotifManager notifManager;

    Post(){
        postManager = new PostManager();
        notifManager = new NotifManager();
    }


    public void createPost(ProfileModel profileModel){
        inp.nextLine();
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
            System.out.println("1.Show Comments  2.Like  3.Share  4.Back");
            int button = inp.nextInt();
            if (button == 1){//show comments
                showComments(profileModel, postModel.getId(), postModel.getProfile_id());
                return;
            }
            else if (button == 2){
                System.out.println("--" + postModel.getProfile_id());
                if(postManager.likePost(profileModel.getProfile_id(), postModel.getId())){
                notifManager.createNotif(postModel.getProfile_id(),new NotifModel("your " + postModel.getTitle() + " post has been liked by " + profileModel.getUsername(), profileModel.getProfile_id(), 3, profileModel.getUsername()), 3);
                System.out.println("Post has been liked!"); 
                }
                else{
                    System.out.println("AN ERROR OCCURED!");
                }
            }
            else if (button == 3){//share
                if (postManager.sharePost(profileModel.getProfile_id(), postModel.getId())){
                    System.out.println("This post has been Shared ");
                    return;
                }
                System.out.println("AN ERROR OCCURED!");
            }

        }
    }

    public void showComments(ProfileModel profileModel,int post_id,int postWriter_id){
        ArrayList<CommentModel> arr = postManager.getPostComments(post_id);
        if (arr.size() == 0){
            System.out.println("There Is no comments yet...");
            sendComment(profileModel, post_id,postWriter_id );
            return;
        }
        else{
            int cnt = 1;
            ArrayList<CommentModel> tmpArr = new ArrayList<>();
            System.out.println("Comments:\nLike or reply (skip)");
            for (int i = 0 ; i < arr.size() ; i++){
                CommentModel tmp = arr.get(i);
                System.out.println(cnt + "-" + tmp.getUsername() + ": " + tmp.getData() + "  " + tmp.getTime() + "  1.like 2.reply");
                cnt++;
                tmpArr.add(tmp);
                if(tmp.getIs_replied()){
                    ArrayList<CommentModel> replies = postManager.getCommentReply(post_id, tmp.getId());
                    for (int j = 0 ; j < replies.size() ; j++){
                        CommentModel tmp2 = replies.get(j);
                        System.out.print("     ");
                        System.out.println(cnt + "-" + tmp2.getUsername() + ": " + tmp2.getData() + "  " + tmp2.getTime() + "  1.like 2.reply");
                        cnt++;
                        tmpArr.add(tmp2);
                    }
                }
            }
            String button = inp.nextLine();
            if (button.equals("skip")){//skip like or replying
                sendComment(profileModel, post_id,postWriter_id );
            }
            String[] butt = button.split(" ");
            CommentModel tmp = tmpArr.get(Integer.valueOf(butt[0]) - 1);
            if (butt[1].equals("1")){//like
                if (postManager.likeComment(profileModel.getProfile_id(), post_id)){
                    notifManager.createNotif(tmp.getProfile_id(), new NotifModel( profileModel.getUsername() + " liked your message!", profileModel.getProfile_id(), 5, profileModel.getUsername()), 5);
                    System.out.println("You liked " + tmp.getUsername() + " message!");
                }
            }else if (butt[1].equals("2")){// reply
                System.out.println("Enter your text for reply: ");
                String text = inp.nextLine();
                postManager.sendComment(profileModel.getProfile_id(), post_id, text, 1, tmp.getId());
                notifManager.createNotif(tmp.getProfile_id(), new NotifModel( profileModel.getUsername() + " replied to your message!", profileModel.getProfile_id(), 5, profileModel.getUsername()), 5);
            }

        }
    }


    public void sendComment(ProfileModel profileModel,int post_id,int postWriter_id){
        System.out.println("Enter your Comment (skip): ");
        while(true){
            String text = inp.nextLine();
            if(text.equals("skip")){
                return;
            }
            if (!postManager.sendComment(profileModel.getProfile_id(), post_id, text, 0 , -1)){
                System.out.println("AN ERROR OCCURED!");
                continue;
            }
            notifManager.createNotif(postWriter_id, new NotifModel(profileModel.getUsername() + " commented on your post!", profileModel.getProfile_id(), 4, profileModel.getUsername()), 4);
            System.out.println(profileModel.getUsername() + ": " + text);
        }
    }

    public void postList(ArrayList<PostModel> arr, ProfileModel profileModel){
        if (arr.size() == 0){
            System.out.println("No posted yet...");
        }
        else{
            System.out.println("Select a Post to show (0): ");
            for (int i = 0 ; i < arr.size(); i++){
                System.out.println(i + 1 + " " + arr.get(i).getUsername() + ": " + arr.get(i).getTitle());
            }
            int button = inp.nextInt();
            Helper.clearConsole();
            if (button == 0){
                return;
            }
            PostModel tmp = arr.get(button - 1);
            System.out.println("---" + tmp.getProfile_id());
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
