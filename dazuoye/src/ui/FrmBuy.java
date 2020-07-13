package ui;

import control.AddressManager;
import control.FreshManager;
import control.MenuManager;
import control.OrdersManager;
import model.BeanAddress;
import model.BeanFresh;
import model.BeanFreshType;
import util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrmBuy extends JDialog implements ActionListener {
    private BeanFreshType user=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JPanel workPane2 = new JPanel();
    private JPanel workPane3 = new JPanel();
    private JPanel workPane4 = new JPanel();
    private JPanel workPane5 = new JPanel();
    private Map<String, BeanAddress> freshTypeMap_name=new HashMap<String,BeanAddress>();
    private Map<Integer,BeanAddress> freshTypeMap_id=new HashMap<Integer,BeanAddress>();
    private JComboBox cmbFreshType=null;
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    //private JLabel labelUserid = new JLabel("账：");
    private JLabel lab1 = new JLabel("购买数量：");
    private JLabel lab2 = new JLabel();
    private JLabel lab3 = new JLabel("收货地址");

    private int id;
    private JTextField edt2 = new JTextField(20);
    public void getCnt() {
        try {
            lab2.setText("数量必须小于等于"+(new FreshManager().queryCnt(id)));
        }catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    //private JComboBox cmbUsertype= new JComboBox(new String[] { "管理员", "借阅员"});
    public FrmBuy(JDialog f, String s, boolean b, int id)  {
        super(f, s, b);
        //提取读者类别信息
        this.id = id;
        getCnt();
        List<BeanAddress> types=null;
        try {
            types = (new AddressManager()).load();
            String[] strTypes=new String[types.size()+1];
            strTypes[0]="";
            for(int i=0;i<types.size();i++) {
                freshTypeMap_name.put(types.get(i).getAdd_peo_name(),types.get(i));
                freshTypeMap_id.put(types.get(i).getAdd_id(), types.get(i));
                strTypes[i+1]=types.get(i).getAdd_peo_name();
            }
            cmbFreshType=new JComboBox(strTypes);
        } catch (BaseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        this.setLayout(new GridLayout(4,1));
        this.add(workPane);
        this.add(workPane2);
        this.add(workPane3);
        this.add(toolBar);
        //this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.add(lab1);
        workPane.add(edt2);
        workPane2.add(lab2);
        workPane3.add(lab3);
        workPane3.add(cmbFreshType);


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
            int n = this.cmbFreshType.getSelectedIndex();
            int rtId=0;
            if(n<0)
                JOptionPane.showMessageDialog(null,  "请选择地址","提示",JOptionPane.ERROR_MESSAGE);
            String rtname=this.cmbFreshType.getSelectedItem().toString();
            BeanAddress rt=this.freshTypeMap_name.get(rtname);
            if(rt!=null) rtId=rt.getAdd_id();
            int orID=0;
            try {
                (new FreshManager()).buy(id,Integer.parseInt(edt2.getText()),rtId);

                this.setVisible(false);
                orID = (new OrdersManager()).getMaxId();

            } catch (BaseException e1) {
                this.user=null;
                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            }
            FrmVerifyDD dlg = new FrmVerifyDD(this,"订单详情",true,orID);
            dlg.setVisible(true);
        }

    }
    public BeanFreshType getUser() {
        return user;
    }
}
