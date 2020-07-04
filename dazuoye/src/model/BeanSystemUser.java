package model;

public class BeanSystemUser {
    private String name;
    private  String pwd;
    private String type;
    private int id;
    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
