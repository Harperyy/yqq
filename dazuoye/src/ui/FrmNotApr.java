package ui;

import control.AppraiseManager;
import control.SystemUserManager;
import model.BeanAppraise;
import model.BeanNotApr;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmNotApr extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JButton btnAdd = new JButton("评论");
   // private JButton btnResetPwd = new JButton("查找");
    //private JButton btnDelete = new JButton("删除");
    private JLabel lab3 = new JLabel("请先登录");
    private Object tblTitle[]={"商品编号","商品名称"};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    //private JTextField edt2 = new JTextField(20);
    private void reloadUserTable(){
        try {
            //String key = edt2.getText();
            List<BeanNotApr> users=(new AppraiseManager()).search();
            tblData =new Object[users.size()][2];
            for(int i=0;i<users.size();i++){

                tblData[i][0]=users.get(i).getFre_id();
                tblData[i][1]=users.get(i).getFre_name();



            }
            tablmod.setDataVector(tblData,tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmNotApr(Frame f, String s, boolean b) {
        super(f, s, b);

        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            //this.setLayout(new GridLayout(2,1));
            System.out.println("666");
            toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar.add(btnAdd);


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
            int i=this.userTable.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int userid= (int) this.tblData[i][0];
            FrmAddApr dlg=new FrmAddApr(this,"评论",true,userid);
            dlg.setVisible(true);


        }
    }
}

