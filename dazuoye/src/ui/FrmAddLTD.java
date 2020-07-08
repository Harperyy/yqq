package ui;

import control.DiscountManager;
import control.FreshManager;
import control.LTDiscountManager;
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

public class FrmAddLTD extends JDialog implements ActionListener {
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
    private JLabel lab1 = new JLabel("商品：");
    private JLabel lab2 = new JLabel("促销价格：");
    private JLabel lab3 = new JLabel("促销数量：");
    private JLabel lab4 = new JLabel("开始时间：");
    private JLabel lab5 = new JLabel("结束时间：");


    private Map<String, BeanFresh> freshTypeMap_name=new HashMap<String,BeanFresh>();
    private Map<Integer,BeanFresh> freshTypeMap_id=new HashMap<Integer,BeanFresh>();
    private JComboBox cmbFreshType=null;
    private JTextField edt2 = new JTextField(20);
    private JTextField edt3 = new JTextField(20);
    private JTextField edt4 = new JTextField(20);
    private JTextField edt5 = new JTextField(20);

    //private JComboBox cmbUsertype= new JComboBox(new String[] { "管理员", "借阅员"});
    public FrmAddLTD(JDialog f, String s, boolean b) {
        super(f, s, b);
        //提取读者类别信息
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
        this.setLayout(new GridLayout(6,1));
        this.add(workPane);
        this.add(workPane2);
        this.add(workPane3);

        this.add(workPane4);
        this.add(workPane5);
        //  this.add(workPane2);

        this.add(toolBar);


        //this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.add(lab1);
        workPane.add(cmbFreshType);
        workPane2.add(lab2);
        workPane2.add(edt2);
        workPane3.add(lab3);
        workPane3.add(edt3);
        workPane4.add(lab4);
        workPane4.add(edt4);
        workPane5.add(lab5);
        workPane5.add(edt5);

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
                JOptionPane.showMessageDialog(null,  "请选择订单","提示",JOptionPane.ERROR_MESSAGE);
            String rtname=this.cmbFreshType.getSelectedItem().toString();
            BeanFresh rt=this.freshTypeMap_name.get(rtname);
            if(rt!=null) rtId=rt.getFre_id();

            try {
                (new LTDiscountManager()).addLmd(rtId,Integer.parseInt(edt2.getText()),Integer.parseInt(edt3.getText()),edt4.getText(),edt5.getText());
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
