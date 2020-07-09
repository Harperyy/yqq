package ui;

import control.AppraiseManager;
import control.SystemUserManager;
import util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmUpApr extends JDialog implements ActionListener {
    private JButton j1 = new JButton("确认追评");
    private JButton j2 = new JButton("取消");
    private JPanel toolBar = new JPanel();
    private JPanel toolBar2 = new JPanel();
    private JTextField edt1 = new JTextField(20);
    private JLabel lab = new JLabel("追加内容");
    private JLabel lab3 = new JLabel("请先登录");

    private int id;
    public FrmUpApr(Dialog f, String s, boolean b,int id) {
        super(f, s, b);
        this.id= id;
        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            this.setLayout(new GridLayout(2,1));
            this.add(toolBar);
            this.add(toolBar2);
            toolBar.add(lab);toolBar.add(edt1);
            toolBar2.add(j1);toolBar2.add(j2);
        }


        // 屏幕居中显示
        this.setSize(1000, 600);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        this.j1.addActionListener(this);

        this.j1.addActionListener(this);
        //this.btnResetPwd.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==j2){
            this.setVisible(false);

        }
        else if(e.getSource()==j1){
            try {
                (new AppraiseManager()).UpApr(id,edt1.getText());
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);

            }
        }
    }


}
