package model;

public class BeanFresh {
    private int Fre_id;
    private String Fre_name;
    private double Fre_price;
    private double Fre_vip_price;
    private int Fre_count;
    private double Fre_size;
    private String Fre_remark;
    private int fp_id;

    public int getFp_id() {
        return fp_id;
    }

    public void setFp_id(int tf_id) {
        this.fp_id = tf_id;
    }

    public double getFre_price() {
        return Fre_price;
    }

    public double getFre_vip_price() {
        return Fre_vip_price;
    }

    public double getFre_size() {
        return Fre_size;
    }

    public int getFre_count() {
        return Fre_count;
    }

    public int getFre_id() {
        return Fre_id;
    }

    public String getFre_name() {
        return Fre_name;
    }

    public String getFre_remark() {
        return Fre_remark;
    }

    public void setFre_count(int fre_count) {
        Fre_count = fre_count;
    }

    public void setFre_id(int fre_id) {
        Fre_id = fre_id;
    }

    public void setFre_name(String fre_name) {
        Fre_name = fre_name;
    }

    public void setFre_price(double fre_price) {
        Fre_price = fre_price;
    }

    public void setFre_remark(String fre_remark) {
        Fre_remark = fre_remark;
    }

    public void setFre_size(double fre_size) {
        Fre_size = fre_size;
    }

    public void setFre_vip_price(double fre_vip_price) {
        Fre_vip_price = fre_vip_price;
    }
}
