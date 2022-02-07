package rabbit_and_carrot;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.util.*;
public class Rank extends JFrame {

	private JPanel contentPane;
	private JLabel close = new JLabel();
	private JLabel back = new JLabel("返回");
	private JLabel title1 = new JLabel("排行榜");
	private JLabel rankpic = new JLabel();
	private JLabel [] ranking = new JLabel[5] ;   
	private JLabel [] xin = new JLabel[3];
	private JLabel rankingbg = new JLabel();
	private JLabel [] name_score  = new JLabel[5];
	String name[] =Save.RanknameRead();
	String score[] =Save.RankscoreRead();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rank frame = new Rank();
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
	public Rank() {
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 130, 450, 500);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
	
		
		//将排名的Label数组加到面板上
		for(int i = 1;i <= 5;i++){
			ranking[i-1] = new JLabel("第 "+i+" 名:     ");
			ranking[i-1].setFont(new Font("Aa芒果弟弟", Font.PLAIN, 28));
			ranking[i-1].setBounds(100,40+i*40,150,50);
			//用户名和分数
			name_score[i-1] = new JLabel();
			name_score[i-1].setFont(new Font("Aa芒果弟弟", Font.PLAIN, 28));
			name_score[i-1].setBounds(200,40+i*40,200,50);
			name_score[i-1].setText(" "+name[i-1]+" "+score[i-1]);
			contentPane.add(name_score[i-1]);
			contentPane.add(ranking[i-1]);
		}
		String xinpic = "res/xin.gif";
		for(int i = 0;i<3;i ++){
			xin[i] = new JLabel();
			xin[i].setBounds(40, 80+i*40, 20*(3-i), 20*(3-i));
			Maingame.shink(xinpic, xin[i]);
			contentPane.add(xin[i]);
		}
		
		
		title1.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
		title1.setBounds(10, 5, 60,20);
	    contentPane.add(title1);
	        
        back.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					back.setBounds(220, 450, 60, 40);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					back.setBounds(220, 440, 60, 40);
				}
			});
		        
        back.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 30));
        back.setBounds(220, 440, 60,40);
        contentPane.add(back);
		
		//关闭按钮及响应事件
        close.setBounds(413, 10, 20, 20);
		close.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			setVisible(false);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			close.setBounds(413, 15, 20,20);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			close.setBounds(413, 10, 20, 20);
		}
		});
		String closepic = "res/close.png";
		contentPane.setLayout(null);
		Maingame.shink(closepic,close);
		contentPane.add(close);
		
		rankingbg.setBounds(0, 0, 450, 500);
		String rankingpic = "res/rankingpic.jpg";
		Maingame.shink(rankingpic,rankingbg);
		contentPane.add(rankingbg);
		
	}

}

