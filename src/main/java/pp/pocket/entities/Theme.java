package pp.pocket.entities;

public class Theme {
    private int id;
    private String name;
    private int type;
    private int userId;

    public Theme(){}

    public Theme(int id, String name, int type, int userId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.userId=userId;
    }

    public Theme(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public int getUserId(){
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
