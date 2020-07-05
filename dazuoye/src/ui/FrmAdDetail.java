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
    private JPanel toolBar1 = new JPanel();
    private JPanel toolBar2 = new JPanel();
    private JButton btnDelete = new JButton("删除该账号");
    private JLabel lab1 = new JLabel("账号编码：");
    private JLabel lab2 = new JLabel("账号：");
    private JLabel lab3 = new JLabel("请先登录");

    private JLabel id = new JLabel();
    private JLabel name = new JLabel();

    private void reloadUserTable(){
        try {
            BeanAdministrater users=(new SystemUserManager()).loadAllAd();
            id.setText(users.getAd_id()+"");
            name.setText(users.getAd_name());
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmAdDetail(Frame f, String s, boolean b) {
        super(f, s, b);
        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else{
            this.setLayout(new GridLayout(3,1));
            this.add(toolBar1);
            this.add(toolBar2);
            this.add(toolBar);
            reloadUserTable();
            toolBar1.add(lab1);
            toolBar1.add(id);
            toolBar2.add(lab2);
            toolBar2.add(name);
            toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
            toolBar.add(this.btnDelete);
            this.getContentPane().add(toolBar, BorderLayout.NORTH);

        }


        // 屏幕居中显示
        this.setSize(200, 200);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);



        this.btnDelete.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnDelete){
            try{
                new SystemUserManager().deleteUser();
                if(SystemUserManager.currentUser==null){
                    setVisible(false);
                }
            } catch (BaseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

    }
}
