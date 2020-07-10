package model;

import java.util.Date;

public class BeanOrdDetail {
    private int fre_id;
    private int count;
    private double start_price;
    private double final_price;
    private double discount;
    private String address;
    private Date time;
    private String fre_name;
    private int cou_id;
    private int disc_id;
    private double disc_dis;
    private double cou_dis;

    public double getCou_dis() {
        return cou_dis;
    }

    public double getDisc_dis() {
        return disc_dis;
    }

    public void setCou_dis(double cou_dis) {
        this.cou_dis = cou_dis;
    }

    public void setDisc_dis(double disc_dis) {
        this.disc_dis = disc_dis;
    }

    public int getDisc_id() {
        return disc_id;
    }

    public void setDisc_id(int disc_id) {
        this.disc_id = disc_id;
    }

    public int getCou_id() {
        return cou_id;
    }

    public void setCou_id(int cou_id) {
        this.cou_id = cou_id;
    }

    public String getFre_name() {
        return fre_name;
    }

    public void setFre_name(String fre_name) {
        this.fre_name = fre_name;
    }

    public void setFre_id(int fre_id) {
        this.fre_id = fre_id;
    }

    public int getFre_id() {
        return fre_id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public double getFinal_price() {
        return final_price;
    }

    public Date getTime() {
        return time;
    }

    public double getStart_price() {
        return start_price;
    }

    public double getDiscount() {
        return discount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setFinal_price(double final_price) {
        this.final_price = final_price;
    }

    public void setStart_price(double start_price) {
        this.start_price = start_price;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
