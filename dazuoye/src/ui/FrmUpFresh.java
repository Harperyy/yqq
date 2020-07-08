package ui;

import control.FreshManager;
import control.FreshTypeManager;
import model.BeanFreshType;
import util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmUpFresh extends JDialog implements ActionListener {

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JPanel workPane2 = new JPanel();
    private JPanel workPane3 = new JPanel();
   // private JPanel workPane4 = new JPanel();
    private JPanel workPane5 = new JPanel();
    private JPanel workPane6 = new JPanel();

    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    //private JLabel labelUserid = new JLabel("账：");
    private JLabel lab1= new JLabel("名称：");
    private JLabel lab2 = new JLabel("原价：");
    private JLabel lab3 = new JLabel("会员价：");
    //private JLabel lab4 = new JLabel("类别：");
    private JLabel lab5 = new JLabel("规格：");
    private JLabel lab6 = new JLabel("描述：");

    private int id;
    private JTextField edt1 = new JTextField(20);
    private JTextField edt2 = new JTextField(20);
    private JTextField edt3 = new JTextField(20);
    //private JTextField edt4 = new JTextField(20);
    private JTextField edt5 = new JTextField(20);
    private JTextField edt6 = new JTextField(20);

    //private JComboBox cmbUsertype= new JComboBox(new String[] { "管理员", "借阅员"});
    public FrmUpFresh(JDialog f, String s, boolean b,int id) {
        super(f, s, b);
        this.id = id;
        this.setLayout(new GridLayout(7,1));
        this.add(workPane);
        this.add(workPane2);
        this.add(workPane3);
        //this.add(workPane4);
        this.add(workPane5);
        this.add(workPane6);
        this.add(toolBar);


        //this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.add(lab1);
        workPane.add(edt1);
        workPane2.add(lab2);
        workPane2.add(edt2);
        workPane3.add(lab3);
        workPane3.add(edt3);

        workPane5.add(lab5);
        workPane5.add(edt5);
        workPane6.add(lab6);
        workPane6.add(edt6);
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        //workPane.add(cmbUsertype);
        //this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(350, 300);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel) {
            this.setVisible(false);
            return;
        }
        else if(e.getSource()==this.btnOk){

            String name=this.edt1.getText();
            double price = Double.parseDouble(this.edt2.getText());
            double vipprice = Double.parseDouble(this.edt3.getText());
            double size = Double.parseDouble(this.edt5.getText());
            String remark = this.edt6.getText();
            try {
                (new FreshManager()).UpFre(id,name,price,vipprice,size,remark);
                System.out.println("11111");
                this.setVisible(false);
            } catch (BaseException e1) {

                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
