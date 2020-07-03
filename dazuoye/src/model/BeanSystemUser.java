package model;

public class BeanSystemUser {
    private String id_name;
    private  String pwd;
    private String type;
    public String getId_name() {
        return id_name;
    }

    public String getPwd() {
        return pwd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId_name(String id_name) {
        this.id_name = id_name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
