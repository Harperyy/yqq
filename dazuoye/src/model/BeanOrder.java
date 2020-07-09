package model;

import java.util.Date;

public class BeanOrder {
    private int ord_id;
    private int cus_id;
    private double ord_start_price;
    private double ord_final_price;
    private int cp_id;
    private Date ord_time;
    private int add_id;
    private String ord_state;

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public double getOrd_start_price() {
        return ord_start_price;
    }

    public Date getOrd_time() {
        return ord_time;
    }

    public double getOrd_final_price() {
        return ord_final_price;
    }

    public int getAdd_id() {
        return add_id;
    }

    public int getCp_id() {
        return cp_id;
    }

    public int getOrd_id() {
        return ord_id;
    }

    public String getOrd_state() {
        return ord_state;
    }

    public void setAdd_id(int add_id) {
        this.add_id = add_id;
    }

    public void setCp_id(int cp_id) {
        this.cp_id = cp_id;
    }

    public void setOrd_final_price(double ord_final_price) {
        this.ord_final_price = ord_final_price;
    }

    public void setOrd_id(int ord_id) {
        this.ord_id = ord_id;
    }

    public void setOrd_start_price(double ord_start_price) {
        this.ord_start_price = ord_start_price;
    }

    public void setOrd_state(String ord_state) {
        this.ord_state = ord_state;
    }

    public void setOrd_time(Date ord_time) {
        this.ord_time = ord_time;
    }
}
