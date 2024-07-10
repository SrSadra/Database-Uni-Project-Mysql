package model;

class SkillType {
    static final String t1 = "Industry_Knowledge";
    static final String t2 = "Tools_Technologies";
    static final String t3 = "Languages";
}

public class Skill {
    private String id;
    private String name;
    private String type;

    
    public Skill(String id, String name , String type ){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
