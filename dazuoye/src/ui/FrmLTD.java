package ui;

import control.CouponManager;
import control.LTDiscountManager;
import control.SystemUserManager;
import model.BeanCoupon;
import model.BeanLTDiscount;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmLTD extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JButton btnAdd = new JButton("添加促销项目");
    //private JButton btnResetPwd = new JButton("修改商品信息");
    private JButton btnDelete = new JButton("删除促销项目");
    private JLabel lab3 = new JLabel("请先登录");
    private Object tblTitle[]={"编号","商品编号","商品名称","促销价格","促销数量","开始时间","结束时间"};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    private void reloadUserTable(){
        try {
            List<BeanLTDiscount> users=(new LTDiscountManager()).loadAll();
            tblData =new Object[users.size()][7];
            for(int i=0;i<users.size();i++){
                tblData[i][0]=users.get(i).getLmd_id();
                tblData[i][1]=users.get(i).getFre_id();
                tblData[i][2]=users.get(i).getFre_name();
                tblData[i][3]=users.get(i).getLmd_price();
                tblData[i][4]=users.get(i).getLmt_count();
                tblData[i][5]=users.get(i).getLmd_start_time();
                tblData[i][6]=users.get(i).getLmd_end_time();


            }
            tablmod.setDataVector(tblData,tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmLTD(Frame f, String s, boolean b) {
        super(f, s, b);

        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar.add(btnAdd);

            toolBar.add(this.btnDelete);
            this.getContentPane().add(toolBar, BorderLayout.NORTH);
            //提取现有数据
            this.reloadUserTable();
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
        this.btnDelete.addActionListener(this);
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
            FrmAddLTD dlg=new FrmAddLTD(this,"添加促销项目",true);
            dlg.setVisible(true);
            //刷新表格
            this.reloadUserTable();

        }
        else if(e.getSource()==this.btnDelete){
            int i=this.userTable.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择优惠券","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(JOptionPane.showConfirmDialog(this,"确定删除吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                int userid= (int) this.tblData[i][0];
                try {
                    (new LTDiscountManager()).DeleteLmd(userid);
                    this.reloadUserTable();
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                }

            }
        }

    }
}

