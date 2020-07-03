package model;

import java.util.Date;

public class BeanCustomer {
    private int cus_id;
    private String cus_name;
    private String cus_sex;
    private String cus_pwd;
    private String cus_phone;
    private String cus_email;
    private String cus_city;
    private Date cus_reg_time;
    private String cus_vip;
    private Date cus_vip_end_time;

    public Date getCus_reg_time() {
        return cus_reg_time;
    }

    public Date getCus_vip_end_time() {
        return cus_vip_end_time;
    }

    public int getCus_id() {
        return cus_id;
    }

    public String getCus_city() {
        return cus_city;
    }

    public String getCus_email() {
        return cus_email;
    }

    public String getCus_name() {
        return cus_name;
    }

    public String getCus_phone() {
        return cus_phone;
    }

    public String getCus_pwd() {
        return cus_pwd;
    }

    public String getCus_sex() {
        return cus_sex;
    }

    public String getCus_vip() {
        return cus_vip;
    }

    public void setCus_city(String cus_city) {
        this.cus_city = cus_city;
    }

    public void setCus_email(String cus_email) {
        this.cus_email = cus_email;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public void setCus_phone(String cus_phone) {
        this.cus_phone = cus_phone;
    }

    public void setCus_pwd(String cus_pwd) {
        this.cus_pwd = cus_pwd;
    }

    public void setCus_reg_time(Date cus_reg_time) {
        this.cus_reg_time = cus_reg_time;
    }

    public void setCus_sex(String cus_sex) {
        this.cus_sex = cus_sex;
    }

    public void setCus_vip(String cus_vip) {
        this.cus_vip = cus_vip;
    }

    public void setCus_vip_end_time(Date cus_vip_end_time) {
        this.cus_vip_end_time = cus_vip_end_time;
    }
}
