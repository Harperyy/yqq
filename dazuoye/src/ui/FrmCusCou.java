package ui;

import control.CouponManager;
import control.SystemUserManager;
import model.BeanCoupon;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmCusCou extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel toolBar2 = new JPanel();

    private JButton btnAdd = new JButton("查询");
    private JButton key = new JButton("关键词");
    private JRadioButton btn1 = new JRadioButton("已开始");
    private JRadioButton btn2 = new JRadioButton("未开始");
    private JRadioButton btn3 = new JRadioButton("已过期");

    private ButtonGroup bg = new ButtonGroup();
    private JTextField edtUserId = new JTextField(20);
    //private JButton btnResetPwd = new JButton("修改商品信息");
    //private JButton btnDelete = new JButton("删除优惠券");
    private JLabel lab3 = new JLabel("请先登录");
    private Object tblTitle[]={"编号","内容","适用金额","减免金额","开始时间","结束时间"};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    private void reloadUserTable(){
        try {
            List<BeanCoupon> users = null;
            String k = edtUserId.getText();
            if(btn1.isSelected()) users = (new CouponManager()).loadUncp(k);
            if(btn2.isSelected()) users = (new CouponManager()).loadNotSt(k);
            if(btn3.isSelected()) users = (new CouponManager()).loadCp(k);

            tblData =new Object[users.size()][6];
            for(int i=0;i<users.size();i++){
                tblData[i][0]=users.get(i).getCu_id();
                tblData[i][1]=users.get(i).getCu_text();
                tblData[i][2]=users.get(i).getCp_need_price();
                tblData[i][3]=users.get(i).getCp_discount();
                tblData[i][4]=users.get(i).getCp_start_time();
                tblData[i][5]=users.get(i).getCp_end_time();


            }
            tablmod.setDataVector(tblData,tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmCusCou(Frame f, String s, boolean b) {
        super(f, s, b);

        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            //this.setLayout(new GridLayout(3,1));


            toolBar.add(key);toolBar.add(edtUserId);
            bg.add(btn1);bg.add(btn2);bg.add(btn3);
            toolBar.add(btn1);toolBar.add(btn2);toolBar.add(btn3);
            toolBar.add(btnAdd);

            //toolBar.add(this.btnDelete);
            this.getContentPane().add(toolBar, BorderLayout.NORTH);
            //提取现有数据
            //this.reloadUserTable();
            this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);
        }


        // 屏幕居中显示
        this.setSize(1000, 600);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        this.btnAdd.addActionListener(this);
        //this.btnResetPwd.addActionListener(this);
        //this.btnDelete.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==this.btnAdd){

            //刷新表格
            this.reloadUserTable();

        }


    }
}

