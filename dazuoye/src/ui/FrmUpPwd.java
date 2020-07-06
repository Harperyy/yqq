package ui;

import control.SystemUserManager;
import util.BaseException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class FrmUpPwd extends JDialog implements ActionListener{
	private JPanel toolBar1 = new JPanel();
    private JPanel toolBar2 = new JPanel();
    private JPanel toolBar3 = new JPanel();
    private JPanel toolBar4 = new JPanel();
    private JLabel lab1 = new JLabel("请输入新的密码");
    private JLabel lab2 = new JLabel("再次输入密码：");
    private JLabel lab3 = new JLabel("请先登录！！！");
    private JLabel lab4 = new JLabel("(长度不少于8个字符)");
    private JPasswordField edtPwd = new JPasswordField(20);
    private JPasswordField edtPwd2 = new JPasswordField(20);
    private int type = -1;
    private JButton btnOk = new JButton("修改");
    private JButton btnCancel = new JButton("取消");
    public FrmUpPwd(Frame f, String s, boolean b) {
    	super(f, s, b);
    	if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
    	else {
    		this.setLayout(new GridLayout(4,1));

	        this.add(toolBar1);
	        this.add(toolBar2);
	
	        this.add(lab4);
	        this.add(toolBar3);
	        
	        toolBar1.add(lab1);
	        toolBar1.add(edtPwd);
	
	        toolBar2.add(lab2);
	        toolBar2.add(edtPwd2);
	        
	        toolBar3.add(btnOk);
	        toolBar3.add(btnCancel);
	        
	        this.setSize(350, 230);

            double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            this.setLocation((int) (width - this.getWidth()) / 2,
                    (int) (height - this.getHeight()) / 2);

	        this.btnCancel.addActionListener(this);
	        this.btnOk.addActionListener(this);
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    //System.exit(0);
                }
            });
        }

        
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnCancel)
            this.setVisible(false);
		else if(e.getSource() == this.btnOk) {
			SystemUserManager sum=new SystemUserManager();
            String pwd1=new String(this.edtPwd.getPassword());
            String pwd2=new String(this.edtPwd2.getPassword());
            try {
                if("管理员".equals(SystemUserManager.currentUser.getType()))sum.upPwdAd(pwd1,pwd2);
                else sum.upPwdCus(pwd1,pwd2);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
		}
	}
    
    
}
