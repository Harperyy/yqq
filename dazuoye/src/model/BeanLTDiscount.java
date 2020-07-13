package model;

import java.util.Date;

public class BeanLTDiscount {
    private int lmd_id;
    private int fre_id;
    private double lmd_price;
    private int lmd_count;
    private Date lmd_start_time;
    private  Date lmd_end_time;
    private String fre_name;

    public void setFre_name(String fre_name) {
        this.fre_name = fre_name;
    }

    public String getFre_name() {
        return fre_name;
    }

    public int getLmd_count() {
        return lmd_count;
    }

    public void setFre_id(int fre_id) {
        this.fre_id = fre_id;
    }

    public int getFre_id() {
        return fre_id;
    }

    public Date getLmd_end_time() {
        return lmd_end_time;
    }

    public Date getLmd_start_time() {
        return lmd_start_time;
    }

    public double getLmd_price() {
        return lmd_price;
    }

    public int getLmd_id() {
        return lmd_id;
    }

    public int getLmt_count() {
        return lmd_count;
    }

    public void setLmd_id(int lmd) {
        this.lmd_id = lmd;
    }

    public void setLmd_end_time(Date lmd_end_time) {
        this.lmd_end_time = lmd_end_time;
    }

    public void setLmd_price(double lmd_price) {
        this.lmd_price = lmd_price;
    }

    public void setLmd_start_time(Date lmd_start_time) {
        this.lmd_start_time = lmd_start_time;
    }

    public void setLmd_count(int lmt_count) {
        this.lmd_count = lmt_count;
    }
}
