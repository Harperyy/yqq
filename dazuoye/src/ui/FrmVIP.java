package ui;

import control.SystemUserManager;
import util.BaseException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class FrmVIP extends JDialog implements ActionListener  {
	private JPanel toolBar1 = new JPanel();
    private JPanel toolBar2 = new JPanel();
    private JPanel toolBar3 = new JPanel();
    private JPanel toolBar4 = new JPanel();
    private JButton btnCancel = new JButton("退出");
    private JButton btnBuy = new JButton("购买");
    private JRadioButton randioButton1=new JRadioButton("月卡");
    private JRadioButton randioButton2=new JRadioButton("季卡");
    private JRadioButton randioButton3=new JRadioButton("年卡");
    private ButtonGroup bg = new ButtonGroup();
    
    private int type = -1;
    
    public FrmVIP(Frame f, String s, boolean b) {
    	super(f, s, b);
        this.setLayout(new GridLayout(2,1));
        this.add(toolBar1);
        this.add(toolBar2);
        

        bg.add(randioButton1);
        bg.add(randioButton2);
        bg.add(randioButton3);
        
        toolBar1.add(randioButton1);
        toolBar1.add(randioButton2);
        toolBar1.add(randioButton3);
        
        toolBar2.add(btnCancel);
        toolBar2.add(btnBuy);
        
        this.setSize(320, 180);
        this.setLocation(200, 200);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
        
        btnBuy.addActionListener(this);
        btnCancel.addActionListener(this);
        randioButton1.addActionListener(this);
        randioButton2.addActionListener(this);
        randioButton3.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
        }
		else if(e.getSource()==this.btnBuy) {
			SystemUserManager sum=new SystemUserManager();
			try {
				sum.setVIP(type);
				this.setVisible(false);
			}catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误提示",JOptionPane.ERROR_MESSAGE);
            }

			
			
			
		}else if(e.getSource() == this.randioButton1){
            type = 0;
        }
        else if(e.getSource() == this.randioButton2){
            type = 1;
        }else if(e.getSource() == this.randioButton3){
            type = 2;
        }
        
		
	}
   
    
}
