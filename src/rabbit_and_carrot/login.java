package rabbit_and_carrot;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import rabbit_and_carrot.Maingame;
public class login extends JFrame {

	private JPanel contentPane;
	private JTextField text;
    public static String name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 453, 232);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblid = new JLabel("\u8BF7\u8F93\u5165\u7528\u6237\u540D\uFF1A");
		lblid.setForeground(Color.BLUE);
		lblid.setFont(new Font("Aa芒果弟弟", Font.BOLD, 15));
		lblid.setBounds(81, 90, 119, 28);
		contentPane.add(lblid);
		
		JButton btn = new JButton("\u786E\u5B9A");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    name = text.getText().trim();
				//Save.RanknameWirte(name);
				Maingame.flag=true;
				dispose();
				if(Maingame.flag){
					Maingame.card.show(Maingame.pan,"p2");
					Maingame.stage.setFocusable(true);
					Maingame.stage.requestFocusInWindow();//为窗口设置焦点 
					try {
						//stage.load(0);
						Maingame.stage.load(1);
						Maingame.stage.start();//控制钩子开始旋转
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					}
			}
		});
		btn.setForeground(Color.BLUE);
		btn.setFont(new Font("Aa芒果弟弟", Font.BOLD, 15));
		btn.setBounds(162, 149, 113, 27);
		contentPane.add(btn);
		
		text = new JTextField();
		text.setBounds(200, 93, 128, 24);
		contentPane.add(text);
		text.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		String loginpic = "res/settingbg.png";
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 15));
		lblNewLabel.setBounds(0, -12, 453, 279);
		Maingame.shink(loginpic, lblNewLabel);
		contentPane.add(lblNewLabel);
	}
}

