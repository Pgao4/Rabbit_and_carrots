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

	boolean f1 = true;    //���ڱ�Ǳ������ֵĿ���
	boolean f2 = true;    //���ڱ����Ч�Ŀ���
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
		Music soundm = new Music("res/��Ч.wav");
		//soundm.start();
		Music soundwin = new Music("res/win1.wav");
		//soundwin.start();
		Music soundlose = new Music("res/shibai1.wav");
		
		setUndecorated(true);
		setTitle("��Ч");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 488,379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel bgMusictitle = new JLabel("��Ч����");
		bgMusictitle.setFont(new Font("Aaâ���ܵ�", Font.PLAIN, 16));
		bgMusictitle.setBounds(15, 10, 80, 32);
		contentPane.add(bgMusictitle);
		
		JLabel bgMusic = new JLabel("��������");
		bgMusic.setFont(new Font("Aaâ���ܵ�", Font.PLAIN, 23));
		bgMusic.setBounds(218, 96, 100, 32);
		contentPane.add(bgMusic);
		
		JLabel sound = new JLabel("��Ч");
		sound.setFont(new Font("Aaâ���ܵ�", Font.PLAIN, 23));
		sound.setBounds(218, 150, 100, 24);
		contentPane.add(sound);
		
		 //�رհ�ť������¼�
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
			
     //��С����ť������¼�
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
		
		//���Ʊ������ֿ��ص�����¼�
		JLabel bgmof = new JLabel("");
		String gon = "E:\\JAVAʵѵ�زİ�\\soundo.png";
		String gclose = "E:\\JAVAʵѵ�زİ�\\soundc.png";

		bgmof.addMouseListener(new MouseAdapter() {
			 //�ı�ͼ��״̬
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
		
		//������Ч���صİ�ť
		JLabel soundof = new JLabel("");
		soundof.addMouseListener(new MouseAdapter() {
			//�ı�ͼ��״̬
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
		
		//���ñ���ͼƬ����ͼƬ����ǿ�����ţ�
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 488, 379);
		String bgpic = "res/soundpic.jpg";
		ImageIcon icon = new ImageIcon(bgpic);
		icon=new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(),Image.SCALE_DEFAULT));
		background.setIcon(icon);
		contentPane.add(background);
	}
}
