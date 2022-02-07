package rabbit_and_carrot;

import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Button;
import rabbit_and_carrot.Maingame;
import rabbit_and_carrot.Music;
public class SoundControl extends JFrame {

	boolean f1 = true;    //用于标记背景音乐的开关
	boolean f2 = true;    //用于标记音效的开关
	private JPanel contentPane;
	Music bgm ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SoundControl frame = new SoundControl();
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
	public SoundControl() {
		
		bgm= new Music("res/start.wav");
		bgm. continuousStart();
		Music soundm = new Music("res/音效.wav");
		//soundm.start();
		Music soundwin = new Music("res/win1.wav");
		//soundwin.start();
		Music soundlose = new Music("res/shibai1.wav");
		
		setUndecorated(true);
		setTitle("音效");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 488,379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel bgMusictitle = new JLabel("音效控制");
		bgMusictitle.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 16));
		bgMusictitle.setBounds(15, 10, 80, 32);
		contentPane.add(bgMusictitle);
		
		JLabel bgMusic = new JLabel("背景音乐");
		bgMusic.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 23));
		bgMusic.setBounds(218, 96, 100, 32);
		contentPane.add(bgMusic);
		
		JLabel sound = new JLabel("音效");
		sound.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 23));
		sound.setBounds(218, 150, 100, 24);
		contentPane.add(sound);
		
		 //关闭按钮及鼠标事件
		   JLabel close = new JLabel("");
		   close.setBounds(458, 10, 20, 20);
		   close.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					close.setBounds(458, 10, 20,25);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					close.setBounds(458, 10, 20, 20);
				}
			});
			String closepic = "res/close.png";
			Maingame.shink(closepic,close);
			contentPane.add(close);
			
     //最小化按钮及鼠标事件
			JLabel smallest = new JLabel("");
			smallest.setBounds(428, 10, 20, 20);
			smallest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(JFrame.ICONIFIED); 
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				smallest.setBounds(428, 10, 20,25);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				smallest.setBounds(428, 10, 20, 20);
			}
			});
			String smallestpic = "res/smallest.png";
			Maingame.shink(smallestpic,smallest);
			contentPane.add(smallest);	
		
		//控制背景音乐开关的鼠标事件
		JLabel bgmof = new JLabel("");
		String gon = "E:\\JAVA实训素材包\\soundo.png";
		String gclose = "E:\\JAVA实训素材包\\soundc.png";

		bgmof.addMouseListener(new MouseAdapter() {
			 //改变图标状态
			@Override
			public void mouseClicked(MouseEvent e) {
				if(f1==true){
					f1 = false;
					Maingame.shink(gclose,bgmof);
					bgm.continuousStop();
				}
				else{
					f1 = true;
					Maingame.shink(gon,bgmof);
					bgm = new Music("res/start.wav");
					bgm.continuousStart();
				}	
			}
		});
		
		bgmof.setBounds(359, 95, 60, 32);
		Maingame.shink(gon,bgmof);
		contentPane.add(bgmof);
		
		//设置音效开关的按钮
		JLabel soundof = new JLabel("");
		soundof.addMouseListener(new MouseAdapter() {
			//改变图标状态
			@Override
			public void mouseClicked(MouseEvent e) {
				if(f2==true){
					f2 = false;
					Maingame.shink(gclose,soundof);
					soundm.stop();
				}
				else{
					f2 = true;
					Maingame.shink(gon,soundof);
					soundm.start();
				}	
			}
		});
		soundof.setBounds(359, 150, 60, 32);
		Maingame.shink(gon, soundof);
		contentPane.add(soundof); 
		
		//设置背景图片（对图片进行强制缩放）
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 488, 379);
		String bgpic = "res/soundpic.jpg";
		ImageIcon icon = new ImageIcon(bgpic);
		icon=new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(),Image.SCALE_DEFAULT));
		background.setIcon(icon);
		contentPane.add(background);
	}
}
