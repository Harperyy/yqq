package ui;

import control.FreshTypeManager;
import model.BeanFreshType;
import util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddFT extends JDialog implements ActionListener {
    private BeanFreshType user=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JPanel workPane2 = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    //private JLabel labelUserid = new JLabel("账：");
    private JLabel labelUsername = new JLabel("名称：");
    private JLabel labelUserid = new JLabel("描述：");

    private JTextField edtUserid = new JTextField(20);
    private JTextField edtUsername = new JTextField(20);
    //private JComboBox cmbUsertype= new JComboBox(new String[] { "管理员", "借阅员"});
    public FrmAddFT(JDialog f, String s, boolean b) {
        super(f, s, b);
        this.setLayout(new GridLayout(3,1));
        this.add(workPane);
        this.add(workPane2);
        this.add(toolBar);


        //this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.add(labelUsername);
        workPane.add(edtUsername);
        workPane2.add(labelUserid);
        workPane2.add(edtUserid);
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        //workPane.add(cmbUsertype);
        //this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(300, 180);
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

            String userid=this.edtUserid.getText();
            String username=this.edtUsername.getText();
            try {
                (new FreshTypeManager()).addFP(username,userid);
                System.out.println("11111");
                this.setVisible(false);
            } catch (BaseException e1) {
                this.user=null;
                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    public BeanFreshType getUser() {
        return user;
    }
}
