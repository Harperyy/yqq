package ui;

import control.SystemUserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmMain extends JFrame implements ActionListener {
    private JMenuBar menubar=new JMenuBar(); ;



    private FrmLogin dlgLogin=null;
    private JPanel statusBar = new JPanel();
    public FrmMain() {
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("图书管理系统");
        dlgLogin = new FrmLogin(this, "登陆", true);
        dlgLogin.setVisible(true);
        if("管理员".equals(SystemUserManager.currentUser.getType())){

        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
