package ui;

import control.AppraiseManager;
import control.CouponManager;
import control.MenuManager;
import control.SystemUserManager;
import model.BeanAppraise;
import model.BeanCoupon;
import model.BeanMenu;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmMenuRec extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private  int id;
    private JLabel lab3 = new JLabel("请先登录");
    private Object tblTitle[]={"菜单名","材料","步骤","推荐内容"};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    private void reloadUserTable(){
        try {
            List<BeanMenu> users=(new MenuManager()).load(id);
            tblData =new Object[users.size()][4];
            for(int i=0;i<users.size();i++){
                tblData[i][0]=users.get(i).getMenu_name();
                tblData[i][1]=users.get(i).getMenu_material();
              //  tblData[i][2]=users.get(i).getMenu_pt();
                tblData[i][2]=users.get(i).getMenu_step();
                tblData[i][3]=users.get(i).getMenu_text();
               // tblData[i][5]=users.get(i).getApr_time();


            }
            tablmod.setDataVector(tblData,tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmMenuRec(Dialog f, String s, boolean b,int id) {
        super(f, s, b);
        this.id = id;
        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{

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


        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}

