package ui;

import control.SystemUserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmMain extends JFrame implements ActionListener {
    private JMenuBar menubar=new JMenuBar(); ;
    private JLabel lab1 = new JLabel("管理员");
    private JLabel lab2 = new JLabel("用户");
    private JMenu menu_Manager=new JMenu("个人中心");
    private JMenu menu_d=new JMenu("优惠管理");
    private JMenu menu_fr=new JMenu("商品管理");
    private JMenu menu_e=new JMenu("评价中心");
    private JMenu menu_od=new JMenu("我的订单");
    private JMenu menu_me=new JMenu("菜谱中心");
    private JMenu menu_cu=new JMenu("消费中心");
    private JMenu menu_ch=new JMenu("优惠查询");


    private JMenuItem  menuItem_allGoods=new JMenuItem("所有商品");
    private JMenuItem  menuItem_clGoods=new JMenuItem("所有分类");

    private JMenuItem  menuItem_allEv=new JMenuItem("评价查询");
    private JMenuItem  menuItem_writeEv=new JMenuItem("写评价");

    private JMenuItem  menuItem_allOd = new JMenuItem("所有订单");
    private JMenuItem  menuItem_notPay=new JMenuItem("代付款");
    private JMenuItem  menuItem_notTran=new JMenuItem("代发货");
    private JMenuItem  menuItem_notCp=new JMenuItem("待收货");
    private JMenuItem  menuItem_notEv=new JMenuItem("待评价");

    private JMenuItem  menuItem_menuRec=new JMenuItem("增加菜谱");
    private JMenuItem  menuItem_seeMenu=new JMenuItem("查询菜谱");

    private JMenuItem  menuItem_seeCou=new JMenuItem("优惠券");
    private JMenuItem  menuItem_seeDis=new JMenuItem("满减");
    private JMenuItem  menuItem_seeLt=new JMenuItem("限时促销");

    private JMenuItem  menuItem_changePw=new JMenuItem("修改密码");
    private JMenuItem  menuItem_cusDetail=new JMenuItem("个人资料");
    private JMenuItem  menuItem_adDetail=new JMenuItem("个人资料");
    private JMenuItem  menuItem_vip=new JMenuItem("vip会员");

    private JMenuItem  menuItem_goods=new JMenuItem("商品信息管理");
    private JMenuItem  menuItem_pc=new JMenuItem("商品采购管理");
    private JMenuItem  menuItem_fr=new JMenuItem("生鲜信息管理");


    private JMenuItem  menuItem_lt=new JMenuItem("限时促销管理");
    private JMenuItem  menuItem_d=new JMenuItem("满减管理");
    private JMenuItem  menuItem_cou=new JMenuItem("优惠券管理");





    private FrmLogin dlgLogin=null;
    private JPanel statusBar = new JPanel();
    public FrmMain() {
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("生鲜网超");
        dlgLogin = new FrmLogin(this, "登陆", true);
        dlgLogin.setVisible(true);

        if("管理员".equals(SystemUserManager.currentUser.getType())){
            menubar.add(lab1);
            menu_Manager.add(menuItem_changePw);
            menuItem_changePw.addActionListener(this);
            menu_Manager.add(menuItem_adDetail);
            menuItem_adDetail.addActionListener(this);
            menubar.add(menu_Manager);
            menu_d.add(menuItem_goods);
            menuItem_goods.addActionListener(this);
            menu_d.add(menuItem_pc);
            menuItem_pc.addActionListener(this);
            menu_d.add(menuItem_fr);
            menuItem_fr.addActionListener(this);
            menubar.add(menu_d);
            menu_fr.add(menuItem_lt);
            menuItem_lt.addActionListener(this);
            menu_fr.add(menuItem_d);
            menuItem_d.addActionListener(this);
            menu_fr.add(menuItem_cou);
            menuItem_cou.addActionListener(this);
            menubar.add(menu_fr);
            menu_ch.add(menuItem_seeLt);
            menuItem_seeLt.addActionListener(this);
            menubar.add(menu_ch);


        }
        else{
            menubar.add(lab1);
            menu_Manager.add(menuItem_changePw);
            menuItem_changePw.addActionListener(this);
            menu_Manager.add(menuItem_cusDetail);
            menuItem_cusDetail.addActionListener(this);
            menu_Manager.add(menuItem_vip);
            menuItem_vip.addActionListener(this);
            menubar.add(menu_Manager);
            menu_cu.add(menuItem_allGoods);
            menuItem_allGoods.addActionListener(this);
            menu_cu.add(menuItem_clGoods);
            menuItem_clGoods.addActionListener(this);
            menubar.add(menu_cu);
            menu_e.add(menuItem_allEv);
            menuItem_allEv.addActionListener(this);
            menu_e.add(menuItem_writeEv);
            menubar.add(menu_e);
            menu_od.add(menuItem_allOd);
            menuItem_allOd.addActionListener(this);
            menu_od.add(menuItem_notPay);
            menuItem_notPay.addActionListener(this);
            menu_od.add(menuItem_notTran);
            menuItem_notTran.addActionListener(this);
            menu_od.add(menuItem_notCp);
            menuItem_notCp.addActionListener(this);
            menu_od.add(menuItem_notEv);
            menuItem_notEv.addActionListener(this);
            menubar.add(menu_od);
            menu_me.add(menuItem_menuRec);
            menuItem_menuRec.addActionListener(this);
            menu_me.add(menuItem_seeMenu);
            menuItem_seeMenu.addActionListener(this);
            menubar.add(menu_me);
            menu_ch.add(menuItem_seeCou);
            menuItem_seeCou.addActionListener(this);
            menu_ch.add(menuItem_seeDis);
            menuItem_seeDis.addActionListener(this);

        }
        this.setJMenuBar(menubar);
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label=new JLabel("您好!"+SystemUserManager.currentUser.getName());
        statusBar.add(label);
        this.getContentPane().add(statusBar,BorderLayout.SOUTH);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.menuItem_adDetail){
            FrmAdDetail dlg = new FrmAdDetail(this,"个人资料",true);
            dlg.setVisible(true);

        }

    }
}
