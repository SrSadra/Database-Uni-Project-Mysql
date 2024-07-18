package model;

class SkillType {
    static final String t1 = "Industry_Knowledge";
    static final String t2 = "Tools_Technologies";
    static final String t3 = "Languages";
}

public class Skill {
    private int id;
    private String name;
    private String type;
    private int endorse_count;

    
    public Skill(int id, String name , String type, int endorse_count ){
        this.id = id;
        this.name = name;
        this.type = type;
        this.endorse_count = endorse_count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getEndorse_count() {
        return endorse_count;
    }
}
