package pp.pocket.entities;

public class Theme {
    private int id;
    private String name;
    private String type;
    private int userId;

    public Theme(){}

    public Theme(int id, String name, String type, int userId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.userId=userId;
    }

    public Theme(String name, String type) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
