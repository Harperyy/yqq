package model;

public class BeanAddress {
    private int add_id;
    private int cus_id;
    private String province;
    private String city;
    private String block;
    private String add_text;
    private String add_phone;
    private String add_peo_name;

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setAdd_id(int add_id) {
        this.add_id = add_id;
    }

    public int getAdd_id() {
        return add_id;
    }

    public String getAdd_peo_name() {
        return add_peo_name;
    }

    public String getAdd_phone() {
        return add_phone;
    }

    public String getAdd_text() {
        return add_text;
    }

    public String getBlock() {
        return block;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public void setAdd_peo_name(String add_peo_name) {
        this.add_peo_name = add_peo_name;
    }

    public void setAdd_phone(String add_phone) {
        this.add_phone = add_phone;
    }

    public void setAdd_text(String add_text) {
        this.add_text = add_text;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
