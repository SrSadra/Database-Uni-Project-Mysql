public class AdminQueries {
    public final static String CREATE_USER_TABLE = "CREATE TABLE Users (" +
        "Username varchar(30) NOT NULL PRIMARY KEY,Last_name varchar(50),First_name varchar(50),Password varchar(50) NOT NULL,BirthDate Date);" ;
    public final static String CREATE_PROFILE_TABLE = "CREATE TABLE Profile (" +
        "Username varchar(30) NOT NULL,Id int PRIMARY KEY AUTO_INCREMENT,Intro varchar(100),Featured varchar(100),About nvarchar(250),Background_id int," +
        "FOREIGN KEY (Background_id) REFERENCES Image(Id));" ;
    public final static String CREATE_IMAGE_TABLE = "CREATE TABLE Image (" +
        "Id int NOT NULL PRIMARY KEY AUTO_INCREMENT,Image_name varchar(50) NOT NULL,Path nvarchar(4000) NOT NULL);" ;
    public final static String CREATE_PROFILE_LANG_TABLE = "CREATE TABLE Profile_lang (" +
        "Name varchar(30) NOT NULL,Id int NOT NULL,CONSTRAINT PK_profile_lang PRIMARY KEY (Id,Name));" ;   //proficiency
    public final static String CREATE_LANGUAGE_TABLE = "CREATE TABLE Language (" +
        "Name varchar(30) NOT NULL PRIMARY KEY);" ;
    public final static String CREATE_SKILL_TABLE = "CREATE TABLE Skills (" +
        "Id int PRIMARY KEY AUTO_INCREMENT,Name varchar(50) NOT NULL,Type varchar(50) NOT NULL);" ;
    public final static String CREATE_PROFILE_SKILLS_TABLE = "CREATE TABLE Profile_skills (" +
        "Skill_id int NOT NULL,Profile_id int NOT NULL,CONSTRAINT PK_profile_skills PRIMARY KEY (Profile_id,Skill_id));" ;
    public final static String CREATE_CONNECTION_TABLE = "CREATE TABLE connection (" +
        "profile_id int NOT NULL,friend_id int NOT NULL,accepted_date Date NOT NULL,CONSTRAINT PK_connection PRIMARY KEY (Profile_id,friend_id));" ;
    public final static String CREATE_INVITATIONS_TABLE = "CREATE TABLE invitations (" +
        "from_id int NOT NULL,to_id int NOT NULL,status varchar(30) NOT NULL,CONSTRAINT PK_invitations PRIMARY KEY (from_id,to_id));" ;
    public final static String CREATE_DIRECTS_TABLE = "CREATE TABLE directs (" +
        "direct_id int NOT NULL PRIMARY KEY AUTO_INCREMENT,user_id int NOT NULL,to_id int NOT NULL,is_readed boolean NOT NULL,is_archived boolean NOT NULL);";
    public final static String CREATE_MESSAGE_TABLE = "CREATE TABLE message (" +
        "message_id int NOT NULL PRIMARY KEY AUTO_INCREMENT,from_id int NOT NULL,to_id int NOT NULL,data nvarchar(4000),time Date NOT NULL);" ;
    public final static String CREATE_MESSAGE_DIRECT_TABLE = "CREATE TABLE message_direct (" +
        "message_id int NOT NULL ,direct_id int NOT NULL,CONSTRAINT PK_message_direct PRIMARY KEY (message_id, direct_id))" ;
    public final static String CREATE_POST_TABLE = "CREATE TABLE post (" +
        "post_id int NOT NULL PRIMARY KEY AUTO_INCREMENT,profile_id int NOT NULL,title varchar(50) NOT NULL,data nvarchar(4000) NOT NULL,time date NOT NULL)" ;
    public final static String CREATE_COMMENT_TABLE = "CREATE TABLE comment (" +
        "id int NOT NULL PRIMARY KEY AUTO_INCREMENT,profile_id int NOT NULL,post_id int NOT NULL,data nvarchar(4000) NOT NULL,time date NOT NULL,is_replied boolean NOT NULL,ref_comment_id int)" ;
    public final static String CREATE_COMMENT_LIKE_TABLE = "CREATE TABLE comment_like (" +
        "id int NOT NULL PRIMARY KEY AUTO_INCREMENT,comment_id int NOT NULL,profile_id int NOT NULL)" ;
    public final static String CREATE_POST_LIKE_TABLE = "CREATE TABLE post_like (" +
        "id int NOT NULL PRIMARY KEY AUTO_INCREMENT,post_id int NOT NULL,profile_id int NOT NULL,time date NOT NULL)" ;  
    public final static String CREATE_POST_SHARE_TABLE = "CREATE TABLE post_share (" +
        "post_id int NOT NULL,profile_id int NOT NULL,time date NOT NULL,CONSTRAINT PK_share PRIMARY KEY (post_id, profile_id))" ;
    public final static String CREATE_NOTIF_TABLE = "CREATE TABLE notif (" +
        "id int NOT NULL PRIMARY KEY AUTO_INCREMENT,reason nvarchar(4000) NOT NULL,caused_by int NOT NULL,time date NOT NULL)" ; 
    public final static String CREATE_PROFILE_NOTIF_TABLE = "CREATE TABLE profile_notif (" +
        "profile_id int NOT NULL,notif_id int NOT NULL,status varchar(30) NOT NULL,CONSTRAINT PK_share PRIMARY KEY (profile_id, notif_id))" ;
}
