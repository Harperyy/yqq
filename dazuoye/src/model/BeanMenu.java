package model;

public class BeanMenu {
    private int Menu_id;
    private String Menu_name;
    private String Menu_material;
    private String Menu_step;
    private String Menu_pt;

    public int getMenu_id() {
        return Menu_id;
    }

    public String getMenu_material() {
        return Menu_material;
    }

    public String getMenu_name() {
        return Menu_name;
    }

    public String getMenu_step() {
        return Menu_step;
    }

    public String getMenu_pt() {
        return Menu_pt;
    }

    public void setMenu_id(int menu_id) {
        Menu_id = menu_id;
    }

    public void setMenu_material(String menu_material) {
        Menu_material = menu_material;
    }

    public void setMenu_name(String menu_name) {
        Menu_name = menu_name;
    }

    public void setMenu_pt(String menu_pt) {
        Menu_pt = menu_pt;
    }

    public void setMenu_step(String menu_step) {
        Menu_step = menu_step;
    }
}
