package rabbit_and_carrot;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Shop2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop2 frame = new Shop2();
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
	public Shop2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(230, 13, 900, 700);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon("res\\gongneng\\\u52A0\u91D1\u5E01.png"));
		lbl2.setBounds(400, 474, 100, 100);
		lbl2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 if(StageP2.score>=500){
					 StageP2.score-=500;
					 StageP2.score=StageP2.score*2;}
                 setVisible(false);
                 Maingame.stage1.pause();
               
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
			}
		});
		contentPane.add(lbl2);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon("res\\gongneng\\\u52A0\u901F.png"));
		lbl1.setBounds(200, 474, 100, 100);
		lbl1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(StageP2.score>=400){
					Hook.changespeedup();
					StageP2.score-=400;
				}
                 setVisible(false);
                 Maingame.stage1.pause();
               
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
			}
		});
		
		JLabel lbl4 = new JLabel();
		String returnpic = "res/return.gif";
		lbl4.setBounds(0, 0, 80, 80);
		Maingame.shink(returnpic, lbl4);
		lbl4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
                 setVisible(false);
                 Maingame.stage1.pause();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
			}
		});
		contentPane.add(lbl4);
		contentPane.add(lbl1);
		
		JLabel lbl3 = new JLabel("");
		lbl3.setIcon(new ImageIcon("res\\gongneng\\\u52A0\u65F6\u95F4.png"));
		lbl3.setBounds(600, 474, 100, 100);
		lbl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 if(StageP2.score>=300){
					 StageP2.score-=300;
					 StageP2.lifetime=StageP2.lifetime*1.5;}
                 setVisible(false);
                 Maingame.stage1.pause();
               
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
			}
		});
		contentPane.add(lbl3);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("res\\gongneng\\\u5546\u5E97.jpg"));
		lblNewLabel.setBounds(0, 0, 900, 700);
		contentPane.add(lblNewLabel);
	}
}

