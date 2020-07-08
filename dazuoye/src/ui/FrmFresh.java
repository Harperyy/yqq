package ui;

import control.FreshManager;
import control.FreshTypeManager;
import control.SystemUserManager;
import model.BeanFresh;
import model.BeanFreshType;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmFresh extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JButton btnAdd = new JButton("添加商品");
    private JButton btnResetPwd = new JButton("修改商品信息");
    private JButton btnDelete = new JButton("删除商品");
    private JLabel lab3 = new JLabel("请先登录");
    private Object tblTitle[]={"编号","名称","原价","会员价","数量","规格","描述","类别"};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    private void reloadUserTable(){
        try {
            List<BeanFresh> users=(new FreshManager()).loadAll();
            tblData =new Object[users.size()][7];
            for(int i=0;i<users.size();i++){
                tblData[i][0]=users.get(i).getFre_id();
                tblData[i][1]=users.get(i).getFre_name();
                tblData[i][2]=users.get(i).getFre_price();
                tblData[i][3]=users.get(i).getFre_vip_price();
                tblData[i][4]=users.get(i).getFre_count();
                tblData[i][5]=users.get(i).getFre_size();
                tblData[i][6]=users.get(i).getFre_remark();

            }
            tablmod.setDataVector(tblData,tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmFresh(Frame f, String s, boolean b) {
        super(f, s, b);

        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar.add(btnAdd);
            toolBar.add(btnResetPwd);
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
            FrmAddFresh dlg=new FrmAddFresh(this,"添加商品",true);
            dlg.setVisible(true);
            //刷新表格
            this.reloadUserTable();

        }
        else if(e.getSource()==this.btnDelete){
            int i=this.userTable.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(JOptionPane.showConfirmDialog(this,"确定删除吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                int userid= (int) this.tblData[i][0];
                try {
                    (new FreshManager()).DeleteFre(userid);
                    this.reloadUserTable();
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        else if(e.getSource()==this.btnResetPwd){
            int i=this.userTable.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            //FreshManager.currentFre.setFre_id((int) this.tblData[i][0]);
            FrmUpFresh dlg=new FrmUpFresh(this,"修改商品",true,(int) this.tblData[i][0]);
            dlg.setVisible(true);
            //刷新表格
            this.reloadUserTable();

        }
    }
}

