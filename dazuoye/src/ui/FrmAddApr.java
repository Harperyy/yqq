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

public class FrmAddApr extends JDialog implements ActionListener {
    private JButton j1 = new JButton("确认评价");
    private JButton j2 = new JButton("取消");
    private JPanel toolBar = new JPanel();
    private JPanel toolBar2 = new JPanel();
   // private JPanel toolBar3 = new JPanel();
    private JPanel toolBar4 = new JPanel();
    private JPanel toolBar5 = new JPanel();

    private JTextField edt1 = new JTextField(20);
    //private JTextField edt2 = new JTextField(20);

    private JRadioButton jr1 = new JRadioButton("一星");
    private JRadioButton jr2 = new JRadioButton("二星");
    private JRadioButton jr3 = new JRadioButton("三星");
    private JRadioButton jr4 = new JRadioButton("四星");
    private JRadioButton jr5 = new JRadioButton("五星");
    private ButtonGroup bg = new ButtonGroup();


    private JLabel lab = new JLabel("内容");
    private JLabel lab2 = new JLabel("等级");
   // private JLabel lab4 = new JLabel("照片");

    private JLabel lab3 = new JLabel("请先登录");

    private int id;
    String g;
    public FrmAddApr(Dialog f, String s, boolean b, int id) {
        super(f, s, b);
        this.id= id;
        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            this.setLayout(new GridLayout(4,1));
            this.add(toolBar);
            this.add(toolBar2);
        //    this.add(toolBar3);
           this.add(toolBar4);
            //this.add(toolBar5);

            toolBar.add(lab);toolBar.add(edt1);
            toolBar2.add(lab2);
            bg.add(jr1); bg.add(jr2);bg.add(jr3);bg.add(jr4);bg.add(jr5);
            toolBar2.add(jr1);toolBar2.add(jr2);toolBar2.add(jr3);toolBar2.add(jr4);toolBar2.add(jr5);
           // toolBar3.add(lab4);toolBar3.add(edt2);
            toolBar4.add(j1);toolBar4.add(j2);
        }


        // 屏幕居中显示
        this.setSize(400, 350);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        this.j1.addActionListener(this);

        this.j2.addActionListener(this);
        this.jr1.addActionListener(this);
        this.jr3.addActionListener(this);
        this.jr4.addActionListener(this);
        this.jr5.addActionListener(this);
        this.jr2.addActionListener(this);
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


        }
        else if(e.getSource()==j1){
            try {
                (new AppraiseManager()).addApr(id,edt1.getText(),g,"无");
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);

            }
        }
        else if(e.getSource()==jr1){
            g = "一星";
        }
        else if(e.getSource()==jr2){
            g = "二星";
        }
        else if(e.getSource()==jr3){
            g = "三星";
        }
        else if(e.getSource()==jr4){
            g = "四星";
        }
        else if(e.getSource()==jr5){
            g = "五星";
        }
    }


}
