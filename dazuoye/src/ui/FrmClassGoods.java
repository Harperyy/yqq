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
import java.awt.event.*;
import java.util.List;

public class FrmClassGoods extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JButton btn = new JButton("商品详情");
    private JButton btn1 = new JButton("推荐菜单");
    private JButton btn2 = new JButton("用户评价");

    private Object tblPlanTitle[]={"编号","名称","描述"};
    private Object tblPlanData[][];
    DefaultTableModel tabPlanModel=new DefaultTableModel();
    private JTable dataTablePlan=new JTable(tabPlanModel);
    private JLabel lab3 = new JLabel("请先登录");

    private Object tblStepTitle[]={"编号","名称","原价","会员价","编码"};
    private Object tblStepData[][];
    DefaultTableModel tabStepModel=new DefaultTableModel();
    private JTable dataTableStep=new JTable(tabStepModel);

    private BeanFreshType curPlan=null;
    java.util.List<BeanFreshType> allPlan=null;
    List<BeanFresh> planSteps=null;
    private void reloadPlanTable(){//这是测试数据，需要用实际数替换
        try {
            allPlan=(new FreshTypeManager()).loadAll();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblPlanData =  new Object[allPlan.size()][3];
        for(int i=0;i<allPlan.size();i++){
            tblPlanData[i][0]=allPlan.get(i).getFP_id();
            tblPlanData[i][1]=allPlan.get(i).getFP_name();
            tblPlanData[i][2]=allPlan.get(i).getFP_remark();
        }
        tabPlanModel.setDataVector(tblPlanData,tblPlanTitle);
        this.dataTablePlan.validate();
        this.dataTablePlan.repaint();
    }
    private void reloadPlanStepTabel(int planIdx){
        if(planIdx<0) return;

        try {
            planSteps=(new FreshManager()).loadByType(planIdx);
            System.out.println(planSteps.get(0).getFre_name());
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //System.out.println(planSteps.get(1));
        tblStepData =new Object[planSteps.size()][5];
        for(int i=0;i<planSteps.size();i++){
            tblStepData[i][0]=planSteps.get(i).getFre_id();
            tblStepData[i][1]=planSteps.get(i).getFre_name();
            tblStepData[i][2]=planSteps.get(i).getFre_price();
            tblStepData[i][3]=planSteps.get(i).getFre_vip_price();

            tblStepData[i][4]=planSteps.get(i).getFp_id();


        }

        tabStepModel.setDataVector(tblStepData,tblStepTitle);
        this.dataTableStep.validate();
        this.dataTableStep.repaint();
    }
    public FrmClassGoods(Frame f, String s, boolean b) {
        super(f, s, b);
        if(SystemUserManager.currentUser==null) {
            this.add(lab3);
        }
        else {
            toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar.add(btn);toolBar.add(btn1);toolBar.add(btn2);
            //toolBar.add(this.btnDelete);
            this.getContentPane().add(toolBar, BorderLayout.NORTH);
            this.getContentPane().add(new JScrollPane(this.dataTablePlan), BorderLayout.WEST);
            this.dataTablePlan.addMouseListener(new MouseAdapter(){


                @Override
                public void mouseClicked(MouseEvent e) {
                    int i=dataTablePlan.getSelectedRow();
                    if(i<0) {
                        return;
                    }
                    int id = (int) dataTablePlan.getValueAt(i,0);
                    reloadPlanStepTabel(id);
                }

            });
            this.reloadPlanTable();
            this.getContentPane().add(new JScrollPane(this.dataTableStep), BorderLayout.CENTER);

        }
        this.setSize(1000, 600);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btn.addActionListener(this);
        this.btn1.addActionListener(this);

        this.btn2.addActionListener(this);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
               // System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn){
            int i=this.dataTableStep.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int id=(int) dataTableStep.getValueAt(i,0);
            FrmGoodsDetail dlg=new FrmGoodsDetail(this,"商品详情",true,id);
            dlg.setVisible(true);
        }else if(e.getSource()==this.btn1){
            int i=this.dataTableStep.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int id=(int) dataTableStep.getValueAt(i,0);

            FrmMenuRec dlg = new FrmMenuRec(this,"推荐菜单",true,id);dlg.setVisible(true);
        }
        else if(e.getSource()==this.btn2){
            int i=this.dataTableStep.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int id=(int) dataTableStep.getValueAt(i,0);

            FrmAprRec dlg = new FrmAprRec(this,"用户评价",true,id);dlg.setVisible(true);
        }

    }
}
