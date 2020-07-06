package ui;

import control.SystemUserManager;
import util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmCusUp extends JDialog implements ActionListener {
    private JPanel toolBar1 = new JPanel();
    private JPanel toolBar2 = new JPanel();
    private JPanel toolBar3 = new JPanel();
    private JPanel toolBar4 = new JPanel();
    private JPanel toolBar5 = new JPanel();

    private JLabel lab1 = new JLabel("性别");
    private JLabel lab2 = new JLabel("电话");
    private JLabel lab3 = new JLabel("邮箱");
    private JLabel lab4 = new JLabel("城市");

    private JRadioButton randioButton1=new JRadioButton("男");
    private JRadioButton randioButton2=new JRadioButton("女");
    private ButtonGroup bg = new ButtonGroup();

    private JTextField edtPhone = new JTextField("（123-4567-8901）",14);
    private JTextField edtEmail = new JTextField("（123@qq.com）",20);
    private JTextField edtCity = new JTextField("（杭州）",10);

    private JButton btnOk = new JButton("修改");
    private JButton btnNext = new JButton("取消");

    private String Sex;

    public FrmCusUp(Dialog f, String s, boolean b){
        super(f, s, b);
        this.setLayout(new GridLayout(5,1));
        toolBar1.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar2.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar3.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar4.setLayout(new FlowLayout(FlowLayout.LEFT));


        this.add(toolBar1);
        this.add(toolBar2);
        this.add(toolBar3);
        this.add(toolBar4);
        this.add(toolBar5);

        bg.add(randioButton1);
        bg.add(randioButton2);
        toolBar1.add(lab1);
        toolBar1.add(randioButton1);
        toolBar1.add(randioButton2);

        toolBar2.add(lab2);
        toolBar2.add(edtPhone);
        toolBar3.add(lab3);
        toolBar3.add(edtEmail);
        toolBar4.add(lab4);
        toolBar4.add(edtCity);

        toolBar5.add(btnOk);
        toolBar5.add(btnNext);

        this.setSize(300, 400);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
        this.btnNext.addActionListener(this);
        this.btnOk.addActionListener(this);
        this.randioButton1.addActionListener(this);
        this.randioButton2.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnNext) this.setVisible(false);
        else if(e.getSource() == randioButton1) Sex = "男";
        else if(e.getSource() == randioButton2) Sex = "女";
        else if(e.getSource() == btnOk){
            SystemUserManager sum=new SystemUserManager();
            String phone = this.edtPhone.getText();
            String email = this.edtEmail.getText();
            String city = this.edtCity.getText();
            try{
                sum.regCus(SystemUserManager.currentUser.getName(),Sex,phone,email,city);
                this.setVisible(false);
            }catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }
}
