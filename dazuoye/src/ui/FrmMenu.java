package ui;

import control.CouponManager;
import control.MenuManager;
import control.SystemUserManager;
import model.BeanMenu;
import ui.FrmAddCou;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmMenu extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JButton btnAdd = new JButton("添加菜谱");
    private JButton btnResetPwd = new JButton("查找");
    private JButton btnDelete = new JButton("删除菜谱");
    private JButton btn = new JButton("修改菜谱");

    private JLabel lab3 = new JLabel("请先登录");
    private Object tblTitle[]={"编号","名称","材料","步骤"};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    private JTextField edt2 = new JTextField(20);
    private void reloadUserTable(){
        try {
            String key = edt2.getText();
            List<BeanMenu> users=(new MenuManager()).loadAll(key);
            tblData =new Object[users.size()][4];
            for(int i=0;i<users.size();i++){
                tblData[i][0]=users.get(i).getMenu_id();
                tblData[i][1]=users.get(i).getMenu_name();
                tblData[i][2]=users.get(i).getMenu_material();
                tblData[i][3]=users.get(i).getMenu_step();
                //tblData[i][4]=users.get(i).getMenu_pt();


            }
            tablmod.setDataVector(tblData,tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmMenu(Frame f, String s, boolean b) {
        super(f, s, b);

        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar.add(btnAdd);
            toolBar.add(btn);
            toolBar.add(this.btnDelete);
            toolBar.add(btnResetPwd);
            toolBar.add(edt2);
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
        this.btnDelete.addActionListener(this);
        this.btnResetPwd.addActionListener(this);
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
            FrmAddMenu dlg=new FrmAddMenu(this,"添加菜谱",true);
            dlg.setVisible(true);
            //刷新表格
            this.reloadUserTable();

        }
        else if(e.getSource()==this.btnDelete){
            int i=this.userTable.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择菜谱","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(JOptionPane.showConfirmDialog(this,"确定删除吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                int userid= (int) this.tblData[i][0];
                try {
                    (new MenuManager()).Delete(userid);
                    this.reloadUserTable();
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        else if(e.getSource()==this.btn){
            int i=this.userTable.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择菜谱","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int userid= (int) this.tblData[i][0];

                FrmUpMenu dlg=new FrmUpMenu(this,"添加菜谱",true,userid);
                dlg.setVisible(true);
                this.reloadUserTable();

        }

    }
}

