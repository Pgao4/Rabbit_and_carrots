package rabbit_and_carrot;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import rabbit_and_carrot.Maingame;
import javax.swing.ImageIcon;

public class Shop extends JPanel {
	private final JLabel lblNewLabel = new JLabel("New label");
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;

	/**
	 * Create the panel.
	 */
	public Shop() {
		setLayout(null);
		lblNewLabel.setIcon(new ImageIcon("D:\\eclipse\\workspace\\rabbit_and_carrot\\res\\gongneng\\\u5546\u5E97.jpg"));
		lblNewLabel.setBounds(0, -17, 900, 740);
		add(lblNewLabel);
		
		/*
		 * 商店的鼠标响应事件   
		 */
		lbl1 = new JLabel("New label");
		lbl1.setBounds(210, 510, 93, 91);
		add(lbl1);
		lbl1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 Hook.changespeedup();
                 setVisible(false);
               
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
			}
		});

		
		
		lbl2 = new JLabel("New label");
		lbl2.setBounds(384, 510, 93, 91);
		add(lbl2);
		
		lbl3 = new JLabel("New label");
		lbl3.setBounds(558, 510, 93, 91);
		add(lbl3);

	}
}
