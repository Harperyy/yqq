package ui;

import control.SystemUserManager;
import util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmRegister extends JDialog implements ActionListener {
    private JPanel toolBar1 = new JPanel();
    private JPanel toolBar2 = new JPanel();
    private JPanel toolBar3 = new JPanel();
    private JPanel toolBar4 = new JPanel();
    private JPanel toolBar5 = new JPanel();
    private JPanel toolBar6 = new JPanel();

    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("注册");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelUser = new JLabel("账号：");
    private JLabel labelPwd = new JLabel("密码：");
    private JLabel labelPwd2 = new JLabel("密码：");
    private JLabel lab1 = new JLabel("(长度不少于8个字符)");
    private JLabel lab2 = new JLabel("(再次输入密码)");

    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton randioButton1=new JRadioButton("管理员");
    private JRadioButton randioButton2=new JRadioButton("用户");

    private JTextField edtUserId = new JTextField(20);
    private JPasswordField edtPwd = new JPasswordField(20);
    private JPasswordField edtPwd2 = new JPasswordField(20);

    private int type = -1;

    public FrmRegister(Dialog f, String s, boolean b) {
        super(f, s, b);
        this.setLayout(new GridLayout(7,1));

        this.add(toolBar1);
        this.add(toolBar2);

        this.add(lab1);
        this.add(toolBar3);


        this.add(lab2);
        this.add(toolBar4);
        this.add(toolBar5);

        toolBar1.add(labelUser);
        toolBar1.add(edtUserId);



        toolBar2.add(labelPwd);
        toolBar2.add(edtPwd);

        toolBar3.add(labelPwd2);
        toolBar3.add(edtPwd2);

        bg.add(randioButton1);
        bg.add(randioButton2);
        toolBar4.add(randioButton1);
        toolBar4.add(randioButton2);

        toolBar5.add(btnOk);
        toolBar5.add(btnCancel);



        this.setSize(350, 230);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
        this.randioButton1.addActionListener(this);
        this.randioButton2.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnCancel)
            this.setVisible(false);
        else if(e.getSource() == this.btnOk){
            SystemUserManager sum=new SystemUserManager();
            String userid=this.edtUserId.getText();
            String pwd1=new String(this.edtPwd.getPassword());
            String pwd2=new String(this.edtPwd2.getPassword());
            try {
                SystemUserManager.currentUser = sum.regUser(userid,pwd1,pwd2,type);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(type == 1) {
                FrmRegCus dlg = new FrmRegCus(this,"完善用户资料",true);
                dlg.setVisible(true);
            }

        }
        else if(e.getSource() == this.randioButton1){
            type = 0;
        }
        else if(e.getSource() == this.randioButton2){
            type = 1;
        }


    }

}
