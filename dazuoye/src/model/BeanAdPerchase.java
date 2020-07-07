package model;

public class BeanAdPerchase {
    private int fre_id;
    private int sp_id;
    private int sp_count;
    private String sp_state;
    private String name;

    public int getFre_id() {
        return fre_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFre_id(int fre_id) {
        this.fre_id = fre_id;
    }

    public int getSp_count() {
        return sp_count;
    }

    public int getSp_id() {
        return sp_id;
    }

    public String getSp_state() {
        return sp_state;
    }

    public void setSp_count(int sp_count) {
        this.sp_count = sp_count;
    }

    public void setSp_id(int sp_id) {
        this.sp_id = sp_id;
    }

    public void setSp_state(String sp_state) {
        this.sp_state = sp_state;
    }
}
