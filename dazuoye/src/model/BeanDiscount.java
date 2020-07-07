package model;

import java.util.Date;

public class BeanDiscount {
    private int disc_id;
    private String disc_text;
    private int count;
    private double disc_discount;
    private Date disc_start_time;
    private Date disc_end_time;

    public int getDisc_id() {
        return disc_id;
    }

    public String getDisc_text() {
        return disc_text;
    }

    public Date getDisc_end_time() {
        return disc_end_time;
    }

    public Date getDisc_start_time() {
        return disc_start_time;
    }

    public double getDisc_discount() {
        return disc_discount;
    }

    public void setDisc_id(int disc_id) {
        this.disc_id = disc_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDisc_end_time(Date disc_end_time) {
        this.disc_end_time = disc_end_time;
    }

    public void setDisc_discount(double disc_discount) {
        this.disc_discount = disc_discount;
    }

    public void setDisc_start_time(Date disc_start_time) {
        this.disc_start_time = disc_start_time;
    }

    public void setDisc_text(String disc_text) {
        this.disc_text = disc_text;
    }
}
