package ui;

import control.AdPerchaseManager;
import control.FreshManager;
import control.SystemUserManager;
import model.BeanAdPerchase;
import model.BeanFresh;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmAP extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JButton btnAdd = new JButton("采购商品");
    private JButton btnResetPwd = new JButton("发货");
    private JButton btnDelete = new JButton("到库");
    private JLabel lab3 = new JLabel("请先登录");
    private Object tblTitle[]={"编号","商品编号","数量","状态","名称"};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    private void reloadUserTable(){
        try {
            List<BeanAdPerchase> users=(new AdPerchaseManager()).loadAll();
            tblData =new Object[users.size()][5];
            for(int i=0;i<users.size();i++){
                tblData[i][0]=users.get(i).getSp_id();
                tblData[i][1]=users.get(i).getFre_id();
                tblData[i][2]=users.get(i).getSp_count();
                tblData[i][3]=users.get(i).getSp_state();
                tblData[i][4]=users.get(i).getName();


            }
            tablmod.setDataVector(tblData,tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmAP(Frame f, String s, boolean b) {
        super(f, s, b);

        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar.add(btnAdd);
            toolBar.add(btnResetPwd);
            toolBar.add(btnDelete);
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
        this.btnResetPwd.addActionListener(this);
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
            FrmAddAP dlg=new FrmAddAP(this,"采购商品",true);
            dlg.setVisible(true);
            //刷新表格
            this.reloadUserTable();

        }
        else if(e.getSource()==this.btnResetPwd){
            int i=this.userTable.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }

                int userid= (int) this.tblData[i][0];
                try {
                    (new AdPerchaseManager()).onWay(userid);
                    this.reloadUserTable();
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                }


        }
        else if(e.getSource()==this.btnDelete){
            int i=this.userTable.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }

            int userid= (int) this.tblData[i][0];
            try {
                (new AdPerchaseManager()).Complete(userid,(int)this.tblData[i][1],(int)this.tblData[i][2]);
                this.reloadUserTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            }


        }
    }
}

