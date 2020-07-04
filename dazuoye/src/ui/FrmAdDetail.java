package ui;

import control.SystemUserManager;
import model.BeanAdministrater;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmAdDetail extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JButton btnAdd = new JButton("添加用户");
    private JButton btnResetPwd = new JButton("重置密码");
    private JButton btnDelete = new JButton("删除用户");
    private Object tblTitle[][]={{"账号"},{"姓名"},{"类别"}};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    private void reloadUserTable(){
        try {
            List<BeanAdministrater> users=(new SystemUserManager()).loadAllAd();
            tblData =new Object[users.size()][3];
            for(int i=0;i<users.size();i++){
                tblData[0][i]=users.get(i).getAd_id();
                tblData[1][i]=users.get(i).getAd_name();
                tblData[2][i]="管理员";

            }
            tablmod.setDataVector(tblData,tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmAdDetail(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar.add(btnAdd);
        toolBar.add(btnResetPwd);
        toolBar.add(this.btnDelete);
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        //提取现有数据
        this.reloadUserTable();
        this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);

        // 屏幕居中显示
        this.setSize(800, 600);
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

    }
}
