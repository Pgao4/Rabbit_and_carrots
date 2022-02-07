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

public class Shop1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop1 frame = new Shop1();
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
	public Shop1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(230, 13, 900, 700);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon("res/加金币.png"));
		lbl2.setBounds(400, 474, 100, 100);
		lbl2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 if(Stage.score>=500){
					 Stage.score-=500;
					 Stage.score=Stage.score*2;}
                 setVisible(false);
                 Maingame.stage.pause();
               
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
		lbl1.setIcon(new ImageIcon("res/加速.png"));
		lbl1.setBounds(200, 474, 100, 100);
		lbl1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Stage.score>=400){
					Hook.changespeedup();
					Stage.score-=400;
				}
                 setVisible(false);
                 Maingame.stage.pause();
               
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
                 Maingame.stage.pause();
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
		lbl3.setIcon(new ImageIcon("res/加时间.png"));
		lbl3.setBounds(600, 474, 100, 100);
		lbl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 if(Stage.score>=300){
					 Stage.score-=300;
					 Stage.lifetime=Stage.lifetime*1.5;}
                 setVisible(false);
                 Maingame.stage.pause();
               
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
		lblNewLabel.setIcon(new ImageIcon("res/商店.jpg"));
		lblNewLabel.setBounds(0, 0, 900, 700);
		contentPane.add(lblNewLabel);
	}
}
