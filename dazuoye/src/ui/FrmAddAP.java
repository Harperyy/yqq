package ui;

import control.AdPerchaseManager;
import control.FreshManager;
import control.FreshTypeManager;
import model.BeanAdPerchase;
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

public class FrmAddAP extends JDialog implements ActionListener {
    private BeanFresh user=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private JPanel workPane4 = new JPanel();


    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    //private JLabel labelUserid = new JLabel("账：");
    private JLabel lab1= new JLabel("数量：");
    private JLabel lab4 = new JLabel("商品：");

    private Map<String, BeanFresh> freshTypeMap_name=new HashMap<String,BeanFresh>();
    private Map<Integer,BeanFresh> freshTypeMap_id=new HashMap<Integer,BeanFresh>();
    private JComboBox cmbFreshType=null;


    private JTextField edt1 = new JTextField(20);


    //private JComboBox cmbUsertype= new JComboBox(new String[] { "管理员", "借阅员"});
    public FrmAddAP(JDialog f, String s, boolean b) {
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
        this.setLayout(new GridLayout(3,1));
        this.add(workPane4);
        this.add(workPane);
        this.add(toolBar);
        //this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane4.add(lab4);
        workPane4.add(cmbFreshType);
        workPane.add(lab1);
        workPane.add(edt1);
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
            int cnt = Integer.parseInt(this.edt1.getText());

            try {
                (new AdPerchaseManager()).add(rtId,cnt);

                this.setVisible(false);
            } catch (BaseException e1) {
                this.user=null;
                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    public BeanFresh getUser() {
        return user;
    }
}
