package model;

import java.util.Date;

public class BeanCoupon {
    private int cu_id;
    private String cu_text;
    private double cp_need_price;
    private double cp_discount;
    private Date cp_start_time;
    private Date cp_end_time;
    public int getCu_id() {
        return cu_id;
    }

    public String getCu_text() {
        return cu_text;
    }

    public Date getCp_end_time() {
        return cp_end_time;
    }

    public Date getCp_start_time() {
        return cp_start_time;
    }

    public double getCp_discount() {
        return cp_discount;
    }

    public double getCp_need_price() {
        return cp_need_price;
    }

    public void setCp_discount(double cp_discount) {
        this.cp_discount = cp_discount;
    }

    public void setCp_end_time(Date cp_end_time) {
        this.cp_end_time = cp_end_time;
    }

    public void setCp_need_price(double cp_need_price) {
        this.cp_need_price = cp_need_price;
    }

    public void setCp_start_time(Date cp_start_time) {
        this.cp_start_time = cp_start_time;
    }

    public void setCu_text(String cu_text) {
        this.cu_text = cu_text;
    }

    public void setCu_id(int cu_id) {
        this.cu_id = cu_id;
    }
}
