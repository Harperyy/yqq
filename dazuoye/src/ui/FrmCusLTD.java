package ui;

import control.DiscountManager;
import control.FreshManager;
import control.LTDiscountManager;
import control.SystemUserManager;
import model.BeanDiscount;
import model.BeanFresh;
import model.BeanLTDiscount;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrmCusLTD extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel toolBar2 = new JPanel();

    private JButton btnAdd = new JButton("查询");
    private JLabel key = new JLabel("商品");
    private JRadioButton btn1 = new JRadioButton("已开始");
    private JRadioButton btn2 = new JRadioButton("未开始");
    private JRadioButton btn3 = new JRadioButton("已过期");
    private Map<String, BeanFresh> freshTypeMap_name=new HashMap<String,BeanFresh>();
    private Map<Integer,BeanFresh> freshTypeMap_id=new HashMap<Integer,BeanFresh>();
    private JComboBox cmbFreshType=null;
    private ButtonGroup bg = new ButtonGroup();
    private JTextField edtUserId = new JTextField(20);
    //private JButton btnResetPwd = new JButton("修改商品信息");
    //private JButton btnDelete = new JButton("删除优惠券");
    private JLabel lab3 = new JLabel("请先登录");
    private Object tblTitle[]={"编号","商品编号","商品名称","促销价格","促销数量","开始时间","结束时间"};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    private void reloadUserTable(){
        try {
            List<BeanLTDiscount> users = null;
            int n = this.cmbFreshType.getSelectedIndex();
            int rtId=0;
            if(n<0)
                JOptionPane.showMessageDialog(null,  "请选择订单","提示",JOptionPane.ERROR_MESSAGE);
            String rtname=this.cmbFreshType.getSelectedItem().toString();
            BeanFresh rt=this.freshTypeMap_name.get(rtname);
            if(rt!=null) rtId=rt.getFre_id();
            if(btn1.isSelected()) users = (new LTDiscountManager()).loadUnCp(rtId);
            if(btn2.isSelected()) users = (new LTDiscountManager()).loadNotSt(rtId);
            if(btn3.isSelected()) users = (new LTDiscountManager()).loadCp(rtId);

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

    public FrmCusLTD(Frame f, String s, boolean b) {
        super(f, s, b);
        List<BeanFresh> types=null;
        try {
            types = (new FreshManager()).loadAll();
            String[] strTypes=new String[types.size()+1];
            strTypes[0]="";
            for(int i=0;i<types.size();i++) {
                freshTypeMap_name.put(types.get(i).getFre_name(),types.get(i));
                freshTypeMap_id.put(types.get(i).getFre_id(), types.get(i));
                strTypes[i+1]=types.get(i).getFre_name();
            }
            cmbFreshType=new JComboBox(strTypes);
        } catch (BaseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            //this.setLayout(new GridLayout(3,1));


            toolBar.add(key);toolBar.add(cmbFreshType);
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

