package ui;

import control.MenuManager;
import model.BeanFreshType;
import util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmUpMenu extends JDialog implements ActionListener {
    private BeanFreshType user=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JPanel workPane2 = new JPanel();
    private JPanel workPane3 = new JPanel();
    private JPanel workPane4 = new JPanel();
    private JPanel workPane5 = new JPanel();

    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    //private JLabel labelUserid = new JLabel("账：");
    private JLabel lab1 = new JLabel("名称：");
    private JLabel lab2 = new JLabel("材料：");
    private JLabel lab3 = new JLabel("步骤：");
    //private JLabel lab4 = new JLabel("照片：");



    private JTextField edt1 = new JTextField(20);
    private JTextField edt2 = new JTextField(20);
    private JTextField edt3 = new JTextField(20);
    private JTextField edt4 = new JTextField(20);
    private JTextField edt5 = new JTextField(20);
    private int id;

    //private JComboBox cmbUsertype= new JComboBox(new String[] { "管理员", "借阅员"});
    public FrmUpMenu(JDialog f, String s, boolean b,int id) {
        super(f, s, b);
        this.id = id;
        this.setLayout(new GridLayout(4,1));
        this.add(workPane);
        this.add(workPane2);
        this.add(workPane3);



        //  this.add(workPane2);

        this.add(toolBar);


        //this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.add(lab1);
        workPane.add(edt1);
        workPane2.add(lab2);
        workPane2.add(edt2);
        workPane3.add(lab3);
        workPane3.add(edt3);



        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        //workPane.add(cmbUsertype);
        //this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(350, 280);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel) {
            this.setVisible(false);
            return;
        }
        else if(e.getSource()==this.btnOk){


            try {
                (new MenuManager()).Up(id,edt1.getText(),edt2.getText(),edt3.getText(),edt4.getText());
                System.out.println("11111");
                this.setVisible(false);
            } catch (BaseException e1) {
                this.user=null;
                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    public BeanFreshType getUser() {
        return user;
    }
}
