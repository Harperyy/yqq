package ui;

import control.OrdersManager;
import control.SystemUserManager;
import model.BeanOrder;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmOrdC extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel toolBar2 = new JPanel();

    private JButton btnAdd = new JButton("查询");
    private JButton key = new JButton("关键词");

    private JTextField edtUserId = new JTextField(20);
    private JButton btn = new JButton("订单详情");
    //private JButton btnDelete = new JButton("删除优惠券");
    private JLabel lab3 = new JLabel("请先登录");
    private Object tblTitle[]={"编号","商品名称","原价","最后价格","下单时间","订单状态"};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    private void reloadUserTable(){
        try {
            List<BeanOrder> users = null;
            String k = edtUserId.getText();
            users = (new OrdersManager()).loadC(k);

            tblData =new Object[users.size()][6];
            for(int i=0;i<users.size();i++){
                tblData[i][0]=users.get(i).getOrd_id();
                tblData[i][1]=users.get(i).getFre_name();
                tblData[i][2]=users.get(i).getOrd_start_price();
                tblData[i][3]=users.get(i).getOrd_final_price();
                tblData[i][4]=users.get(i).getOrd_time();
                tblData[i][5]=users.get(i).getOrd_state();


            }
            tablmod.setDataVector(tblData,tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmOrdC(Frame f, String s, boolean b) {
        super(f, s, b);

        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            //this.setLayout(new GridLayout(3,1));


            toolBar.add(key);toolBar.add(edtUserId);

            toolBar.add(btnAdd);

            toolBar.add(this.btn);
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
        this.btn.addActionListener(this);
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
        else if(e.getSource()==this.btn){
            int i=this.userTable.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择订单","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int userid= (int) this.tblData[i][0];
            FrmOrdDetail dlg = new FrmOrdDetail(this,"订单详情",true,userid);
            dlg.setVisible(true);
        }

    }
}

