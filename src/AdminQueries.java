public class AdminQueries {
    public final static String CREATE_USER_TABLE = "CREATE TABLE Users (" +
        "Username varchar(30) NOT NULL PRIMARY KEY,Last_name varchar(50),First_name varchar(50),Password varchar(50) NOT NULL,BirthDate Date);" ;
    public final static String CREATE_PROFILE_TABLE = "CREATE TABLE Profile (" +
        "Username varchar(30) NOT NULL,Id int PRIMARY KEY AUTO_INCREMENT,Intro varchar(100),Featured varchar(100),About nvarchar(250),Background_id int," +
        "FOREIGN KEY (Background_id) REFERENCES Image(Id));" ;
    public final static String CREATE_IMAGE_TABLE = "CREATE TABLE Image (" +
        "Id int NOT NULL PRIMARY KEY AUTO_INCREMENT,Image_name varchar(50) NOT NULL,Path nvarchar(250) NOT NULL);" ;
    public final static String CREATE_PROFILE_LANG_TABLE = "CREATE TABLE Profile_lang (" +
        "Name varchar(30) NOT NULL,Id int NOT NULL,CONSTRAINT PK_profile_lang PRIMARY KEY (Id,Name));" ;   //proficiency
    public final static String CREATE_LANGUAGE_TABLE = "CREATE TABLE Language (" +
        "Name varchar(30) NOT NULL PRIMARY KEY);" ;
    public final static String CREATE_SKILL_TABLE = "CREATE TABLE Skills (" +
        "Id int PRIMARY KEY AUTO_INCREMENT,Name varchar(50) NOT NULL,Type varchar(50) NOT NULL);" ;
    public final static String CREATE_PROFILE_SKILLS_TABLE = "CREATE TABLE Profile_skills (" +
        "Skill_id int NOT NULL,Profile_id int NOT NULL,CONSTRAINT PK_profile_skills PRIMARY KEY (Profile_id,Skill_id));" ;
}
