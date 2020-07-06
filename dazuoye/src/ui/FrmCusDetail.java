package ui;

import control.SystemUserManager;
import model.BeanCustomer;
import util.BaseException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FrmCusDetail extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
    private JPanel toolBar1 = new JPanel();
    private JPanel toolBar2 = new JPanel();
    private JPanel toolBar3 = new JPanel();
    private JPanel toolBar4 = new JPanel();
    private JPanel toolBar5 = new JPanel();
    private JPanel toolBar6 = new JPanel();
    private JPanel toolBar7 = new JPanel();
    private JPanel toolBar8 = new JPanel();
    private JButton btnDelete = new JButton("删除该账户");
    private JButton btnUp = new JButton("修改个人信息");
    private JLabel lab1 = new JLabel("账号编号：");
    private JLabel lab2 = new JLabel("账号：");
    private JLabel lab3 = new JLabel("性别：");
    private JLabel lab4 = new JLabel("电话：");
    private JLabel lab5 = new JLabel("邮箱：");
    private JLabel lab6 = new JLabel("城市:");
    private JLabel lab7 = new JLabel("注册时间");
    private JLabel lab8 = new JLabel("vip：");
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

    private void reloadUserTable(){
        try {
            BeanCustomer users=(new SystemUserManager()).loadAllCus();
            id.setText(users.getCus_id()+"");
            name.setText(users.getCus_name());
            sex.setText(users.getCus_sex());
            phone.setText(users.getCus_phone());
            email.setText(users.getCus_email());
            city.setText(users.getCus_city());
            time.setText(users.getCus_reg_time()+"");
            vip.setText(users.getCus_vip());
            
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmCusDetail(Frame f, String s, boolean b) {
        super(f, s, b);
        if(SystemUserManager.currentUser==null) {
            this.add(lab9);
        }
        else{
            reloadUserTable();
            this.setLayout(new GridLayout(9,1));
            this.add(toolBar1);
            this.add(toolBar2);
            this.add(toolBar3);
            this.add(toolBar4);
            this.add(toolBar5);
            this.add(toolBar6);
            this.add(toolBar7);
            this.add(toolBar8);
            this.add(toolBar);
            toolBar1.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar2.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar3.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar4.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar5.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar6.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar7.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar8.setLayout(new FlowLayout(FlowLayout.LEFT));
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
            toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
            toolBar.add(this.btnDelete);
            toolBar.add(this.btnUp);
            this.getContentPane().add(toolBar, BorderLayout.NORTH);

        }


        // ��Ļ������ʾ
        this.setSize(400, 400);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);



        this.btnDelete.addActionListener(this);
        this.btnUp.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnDelete){
            try{
                new SystemUserManager().deleteUser();
                if(SystemUserManager.currentUser==null){
                    setVisible(false);
                }
            } catch (BaseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
        else if(e.getSource()==btnUp){

            FrmCusUp dlg = new FrmCusUp(this,"修改个人信息",true);
            dlg.setVisible(true);



        }
    }
}
