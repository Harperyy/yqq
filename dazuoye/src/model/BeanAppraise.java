package model;

import java.util.Date;
import java.util.zip.DataFormatException;

public class BeanAppraise {
    private int fre_id;
    private String fre_name;
    private int cus_id;
    private String apr_text;
    private Date apr_time;
    private String grade;
    private String apr_pt;
    private int apr_id;

    public int getCus_id() {
        return cus_id;
    }

    public void setFre_name(String fre_name) {
        this.fre_name = fre_name;
    }

    public String getFre_name() {
        return fre_name;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public void setFre_id(int fre_id) {
        this.fre_id = fre_id;
    }

    public int getFre_id() {
        return fre_id;
    }

    public Date getApr_time() {
        return apr_time;
    }

    public int getApr_id() {
        return apr_id;
    }

    public String getApr_pt() {
        return apr_pt;
    }

    public String getApr_text() {
        return apr_text;
    }

    public String getGrade() {
        return grade;
    }

    public void setApr_id(int apr_id) {
        this.apr_id = apr_id;
    }

    public void setApr_pt(String apr_pt) {
        this.apr_pt = apr_pt;
    }

    public void setApr_text(String apr_text) {
        this.apr_text = apr_text;
    }

    public void setApr_time(Date apr_time) {
        this.apr_time = apr_time;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
