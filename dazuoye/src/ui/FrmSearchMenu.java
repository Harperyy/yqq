package ui;

import control.MenuManager;
import control.SystemUserManager;
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

public class FrmSearchMenu  extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel toolBar2 = new JPanel();
    private JPanel toolBar3= new JPanel();

    //private JButton btnAdd = new JButton("添加菜谱");
    private JButton btnResetPwd = new JButton("查找");
    //private JButton btnDelete = new JButton("删除菜谱");
    private JLabel lab1 = new JLabel("名称");
    private JLabel lab2 = new JLabel("材料");
    private JLabel lab4 = new JLabel("步骤");

    private JLabel lab3 = new JLabel("请先登录");
    private Object tblTitle[]={"编号","名称","材料","步骤","图片"};
    private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable userTable=new JTable(tablmod);
    private JTextField edt1 = new JTextField(20);
    private JTextField edt2 = new JTextField(20);
    private JTextField edt3 = new JTextField(20);

    private void reloadUserTable(){
        try {
            String key1 = edt1.getText();
            String key2 = edt2.getText();
            String key3 = edt3.getText();

            List<BeanMenu> users=(new MenuManager()).search(key1,key2,key3);
            tblData =new Object[users.size()][5];
            for(int i=0;i<users.size();i++){
                tblData[i][0]=users.get(i).getMenu_id();
                tblData[i][1]=users.get(i).getMenu_name();
                tblData[i][2]=users.get(i).getMenu_material();
                tblData[i][3]=users.get(i).getMenu_step();
                tblData[i][4]=users.get(i).getMenu_pt();


            }
            tablmod.setDataVector(tblData,tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmSearchMenu(Frame f, String s, boolean b) {
        super(f, s, b);

        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            this.setLayout(new GridLayout(4,1));
            this.add(toolBar);
            this.add(toolBar2);
            this.add(toolBar3);
            this.add(btnResetPwd);
            toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
            //toolBar.add(btnAdd);

            //toolBar.add(this.btnDelete);
            toolBar.add(lab1);
            toolBar.add(edt1);
            toolBar.add(lab2);
            toolBar.add(edt2);
            toolBar.add(lab4);
            toolBar.add(edt3);
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
        if(e.getSource()==this.btnResetPwd){

            this.reloadUserTable();

        }


    }
}

