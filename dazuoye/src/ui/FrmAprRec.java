package ui;

import control.AppraiseManager;
import control.SystemUserManager;
import model.BeanAppraise;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmAprRec extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private  int id;
    private JLabel lab3 = new JLabel("请先登录");
    private Object tblTitle[]={"评价编号","用户编号","评价内容","评价等级","评价时间"};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    private void reloadUserTable(){
        try {
            List<BeanAppraise> users=(new AppraiseManager()).load(id);
            tblData =new Object[users.size()][5];
            for(int i=0;i<users.size();i++){
                tblData[i][0]=users.get(i).getApr_id();
                tblData[i][1]=users.get(i).getCus_id();
                tblData[i][2]=users.get(i).getApr_text();
                tblData[i][3]=users.get(i).getGrade();
                //tblData[i][4]=users.get(i).getApr_pt();
                tblData[i][4]=users.get(i).getApr_time();


            }
            tablmod.setDataVector(tblData,tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmAprRec(Dialog f, String s, boolean b, int id) {
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

