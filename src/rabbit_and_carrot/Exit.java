package rabbit_and_carrot;
import rabbit_and_carrot.Maingame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Exit extends JFrame {

	private JPanel contentPane;
	private JLabel yes = new JLabel("确定");
	private JLabel no = new JLabel("返回");
	private JLabel close = new JLabel("");
	private JLabel background = new JLabel("");
	private JLabel text = new JLabel("您确定要退出吗？？");
	private JLabel exitTitle = new JLabel("退出游戏");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exit frame = new Exit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public Exit() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 450, 390);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		   String name = "res/exitpic1.jpg";
		   ImageIcon bgpic = new ImageIcon(name);
			String closepic = "res/close.png";
			
			//确定退出按钮
			yes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
					yes.setBounds(255, 226, 106,50);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					yes.setBounds(255, 226, 106, 40);
				}
			});
			
			//关闭按钮及鼠标事件
			close.setBounds(415, 10, 20, 20);
			close.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
					close.setBounds(415, 15, 20,20);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					close.setBounds(415, 10, 20, 20);
				}
			});
			
			//设置背景图片
			background.setBounds(0, 0, 450, 390);
			bgpic=new ImageIcon(bgpic.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
			background.setIcon(bgpic);  
			background.setHorizontalAlignment(SwingConstants.CENTER);
			
			//Maingame.shink(name, background);
			
			Maingame.shink(closepic,close);
			contentPane.add(close);
			
			text.setFont(new Font("Aa芒果弟弟", Font.BOLD, 32));
			text.setBounds(100, 80, 350, 50);
			contentPane.add(text);
			yes.setHorizontalAlignment(SwingConstants.CENTER);
			yes.setFont(new Font("Aa芒果弟弟", Font.BOLD, 28));
			yes.setBounds(255, 226, 106, 40);
			contentPane.add(yes);
			
			//返回游戏按钮
			no.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
					no.setBounds(60, 226, 106,50);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					no.setBounds(60, 226, 106, 40);
				}
			});
			no.setHorizontalAlignment(SwingConstants.CENTER);
			no.setFont(new Font("Aa芒果弟弟", Font.BOLD, 28));
			no.setBounds(60, 226, 106, 40);
			contentPane.add(no);
			
			//小标题
			exitTitle.setFont(new Font("Aa芒果弟弟", Font.BOLD, 16));
			exitTitle.setBounds(5, 5, 70, 20);
			contentPane.add(exitTitle);
			contentPane.add(background);
		   
		  
		
	}
}
