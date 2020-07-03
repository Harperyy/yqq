package ui;

import control.SystemUserManager;
import model.BeanSystemUser;
import util.BaseException;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class FrmLogin extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private Button btnLogin = new Button("登陆");
    private Button btnCancel = new Button("退出");
    private JLabel labelUser = new JLabel("账号：");
    private JLabel labelPwd = new JLabel("密码：");
    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton randioButton1=new JRadioButton("管理员");
    private JRadioButton randioButton2=new JRadioButton("用户");
    private int type = -1;
    private JTextField edtUserId = new JTextField(20);
    private JPasswordField edtPwd = new JPasswordField(20);

    public FrmLogin(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bg.add(randioButton1);
        bg.add(randioButton2);
        toolBar.add(randioButton1);
        toolBar.add(randioButton2);
        toolBar.add(btnLogin);
        toolBar.add(btnCancel);


        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelUser);
        workPane.add(edtUserId);
        workPane.add(labelPwd);
        workPane.add(edtPwd);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(300, 140);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        randioButton1.addActionListener(this);
        randioButton2.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.btnLogin) {
            System.out.println(e.getSource()+""+this.randioButton1);
            SystemUserManager sum=new SystemUserManager();
            String userid=this.edtUserId.getText();
            String pwd=new String(this.edtPwd.getPassword());
            try {
                BeanSystemUser user;

                    user = sum.loadUser(userid,type);
                    if(pwd.equals(user.getPwd())){
                        SystemUserManager.currentUser=user;
                        setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,  "密码错误","错误提示",JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println("1");




                System.out.println(SystemUserManager.currentUser);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误提示",JOptionPane.ERROR_MESSAGE);
            }


        }
        else if (e.getSource() == this.btnCancel) {
            System.exit(0);
        }
        else if(e.getSource() == this.randioButton1){
            type = 0;
        }
        else if(e.getSource() == this.randioButton2){
            type = 1;
        }
    }

}
