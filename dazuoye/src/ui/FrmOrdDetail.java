package ui;

import control.OrdersManager;
import control.SystemUserManager;
import model.BeanCustomer;
import model.BeanOrdDetail;
import util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmOrdDetail extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel toolBar1 = new JPanel();
    private JPanel toolBar2 = new JPanel();
    private JPanel toolBar3 = new JPanel();
    private JPanel toolBar4 = new JPanel();
    private JPanel toolBar5 = new JPanel();
    private JPanel toolBar6 = new JPanel();
    private JPanel toolBar7 = new JPanel();
    private JPanel toolBar8 = new JPanel();
    private JPanel toolBar9 = new JPanel();
    private JPanel toolBar10 = new JPanel();



    private JLabel lab1 = new JLabel("地址：");
    private JLabel lab2 = new JLabel("商品名称：");
    private JLabel lab3 = new JLabel("数量：");
    private JLabel lab4 = new JLabel("原价：");
    private JLabel lab5 = new JLabel("优惠券编号：");
    private JLabel lab6 = new JLabel("优惠价格:");
    private JLabel lab7 = new JLabel("满减编号");
    private JLabel lab8 = new JLabel("满减价格：");
    private JLabel lab10 = new JLabel("最终价格：");
    private JLabel lab11 = new JLabel("订单时间：");


    //private JLabel lab2 = new JLabel("�ˣ�");
    private JLabel lab9 = new JLabel("请先登录！！！");

    private JLabel id = new JLabel();
    private JLabel name = new JLabel();
    private JLabel sex = new JLabel();
    private JLabel phone = new JLabel();
    private JLabel email = new JLabel();
    private JLabel city = new JLabel();
    private JLabel time = new JLabel();
    private JLabel vip = new JLabel();
    private JLabel vip1 = new JLabel();
    private JLabel vip2 = new JLabel();
    private int ord_id;

    private void reloadUserTable(){
        try {
            BeanOrdDetail users=(new OrdersManager()).loadDetail(ord_id);
            System.out.println(users);
            id.setText(users.getAddress()+"");
            name.setText(users.getFre_name());
            sex.setText(users.getCount()+"");
            phone.setText(users.getStart_price()+"");
            email.setText(users.getCou_id()+"");
            city.setText(users.getCou_dis()+"");
            time.setText(users.getDisc_id()+"");
            vip.setText(users.getDisc_dis()+"");
            vip1.setText(users.getFinal_price()+"");
            vip2.setText(users.getTime().toString());

        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmOrdDetail(Dialog f, String s, boolean b,int Id) {
        super(f, s, b);
        this.ord_id=Id;
        if(SystemUserManager.currentUser==null) {
            this.add(lab9);
        }
        else{
            reloadUserTable();
            this.setLayout(new GridLayout(11,1));
            this.add(toolBar1);
            this.add(toolBar2);
            this.add(toolBar3);
            this.add(toolBar4);
            this.add(toolBar5);
            this.add(toolBar6);
            this.add(toolBar7);
            this.add(toolBar8);
            this.add(toolBar9);
            this.add(toolBar10);
            this.add(toolBar);
            toolBar1.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar2.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar3.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar4.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar5.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar6.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar7.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar8.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar9.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar10.setLayout(new FlowLayout(FlowLayout.LEFT));

            toolBar1.add(lab1);
            toolBar1.add(id);
            toolBar2.add(lab2);
            toolBar2.add(name);
            toolBar3.add(lab3);
            toolBar3.add(sex);
            toolBar4.add(lab4);
            toolBar4.add(phone);
            toolBar5.add(lab5);
            toolBar5.add(email);
            toolBar6.add(lab6);
            toolBar6.add(city);
            toolBar7.add(lab7);
            toolBar7.add(time);
            toolBar8.add(lab8);
            toolBar8.add(vip);
            toolBar9.add(vip1);
            toolBar10.add(vip2);


        }


        // ��Ļ������ʾ
        this.setSize(400, 400);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);




        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
