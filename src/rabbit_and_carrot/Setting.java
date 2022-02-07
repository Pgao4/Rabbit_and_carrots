package rabbit_and_carrot;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
public class Setting extends JFrame {

	private JPanel contentPane;
	private JLabel settingbg1 = new JLabel();
	private JLabel settingbg2 = new JLabel();
	private JLabel settingbg3 = new JLabel();
	private JLabel settingbg4 = new JLabel();
	private JLabel settingbg5 = new JLabel();
	private DefaultListModel data =new DefaultListModel();
    private JList list=new JList(data);          //数据模型放在列表中
	//卡片布局管理器中的四个切换界面
	private JPanel setting = new JPanel();
	private JPanel about = new JPanel();
	private JPanel instrution = new JPanel();
	private JPanel chatrobot = new JPanel();
	private JPanel weather = new JPanel();
	//卡片布局管理器
	private CardLayout card = new CardLayout(0,0);
	private JPanel pan = new JPanel();
	

	private JLabel rule = new JLabel("游戏规则");
	private JLabel robot = new JLabel("小兔子菲菲");
	private JLabel aboutUS = new JLabel("关于我们");
	private JLabel weath = new JLabel("菲菲报天气");
    private JLabel close = new JLabel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Setting frame = new Setting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Setting() throws IOException {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 488, 379);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        
		//背景图片
		settingbg1.setBounds(0, 58, 490, 320);
		settingbg2.setBounds(0, 58, 490, 320);
		settingbg3.setBounds(0, 58, 490, 320);
		settingbg4.setBounds(0, 58, 490, 320);
		settingbg5.setBounds(0, 58, 490, 320);
        String setbgpic = "res/settingbg.png";
        Maingame.shink(setbgpic, settingbg1);
        Maingame.shink(setbgpic, settingbg2);
        Maingame.shink(setbgpic, settingbg3);
        Maingame.shink(setbgpic, settingbg4);
        Maingame.shink(setbgpic, settingbg5);
        setting.add(settingbg1);
        
      
      //改变Panel的原始布局
        setting.setLayout(null);
        instrution.setLayout(null);
        about.setLayout(null);
        chatrobot.setLayout(null);
        weather.setLayout(null);
        //设置panel大小
        setting.setBounds(0,40,490,400);
        pan.setBounds(0,40,490,400);
        instrution.setBounds(0,40,490,400);
        about.setBounds(0,40,490,400);
        chatrobot.setBounds(0, 40,490, 400);
        weather.setBounds(0, 40, 490, 400);
        //将pan加到卡片布局上
        pan.setLayout(card);
        contentPane.add(pan);
        //将三个界面加到卡片布局上
        pan.add(setting,"p1");
        pan.add(instrution,"p2");
        pan.add(about,"p3");
        pan.add(chatrobot, "p4");
        pan.add(weather, "p5");
        
       
        //游戏规则按钮鼠标事件
        rule.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		rule.setBounds(90, 46, 150, 60);        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
                rule.setBounds(90, 40, 150, 60);
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		card.show(pan, "p2");
        	}
        });
        rule.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 30));
        rule.setBounds(90, 40, 150, 60);
        setting.add(rule);
     
        //聊天机器人按钮鼠标事件
        robot.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		robot.setBounds(100, 96, 150, 60);        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		robot.setBounds(100, 90, 150, 60);
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		card.show(pan, "p4");
        	}
        });
        robot.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 30));
        robot.setBounds(100, 90, 150, 60);
        setting.add(robot);
        
        //关于我们按钮
        
        aboutUS.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 30));
        aboutUS.setBounds(270, 40, 160, 60);
        setting.add(aboutUS);
        //关于我们按钮鼠标事件
        aboutUS.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		aboutUS.setBounds(270, 46, 160, 60);        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		aboutUS.setBounds(270, 40, 160, 60);
        	}
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		card.show(pan, "p3");
        	}
        });
        card.show(pan, "p1");
        
        //天气预报
        weath.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		weath.setBounds(300, 96, 150, 60);        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		weath.setBounds(300, 90, 150, 60);
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		card.show(pan, "p5");
        	}
        });
        weath.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 30));
        weath.setBounds(300, 90, 150, 60);

        JLabel return4 = new JLabel("返回");
        return4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					card.show(pan, "p1");
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					return4.setBounds(200, 267, 180, 25);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					return4.setBounds(200, 260, 180, 25);
				}
			});
		        
        return4.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 30));
        return4.setBounds(200, 250, 67, 40);
        weather.add(return4);
        
        JTextField text2 = new JTextField("请用拼音输入您要查询的城市：");
        text2.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        text2.setBounds(30, 81, 360, 40);    
        weather.add(text2);
        
        JButton send2 = new JButton("发送");
        send2.setBounds(400, 81, 68, 40);
        send2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		String value = text2.getText().trim();
        	    String tq = null;
				String fl = null;
				String fx = null;
        	    JTextField anw = new JTextField("|天气："+tq+"   |风力："+fl+"   |风向："+fx);
                anw.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 18));
                anw.setBounds(30, 130, 360, 40);
                weather.add(anw);
        	}
        });
        
        weather.add(send2);
        weather.add(settingbg5);
        setting.add(weath);
  
        
        //游戏规则界面
        JLabel ruletitle = new JLabel("游戏规则");
        ruletitle.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 30));
        ruletitle.setBounds(180, 0, 128, 30);
        instrution.add(ruletitle);
        
        JLabel return1 = new JLabel("返回");
        return1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					card.show(pan, "p1");
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					return1.setBounds(200, 267, 180, 25);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					return1.setBounds(200, 260, 180, 25);
				}
			});
		        
        return1.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 30));
        return1.setBounds(200, 250, 67, 40);
        instrution.add(return1);
        
        JLabel Rule1 = new JLabel("本游戏是一个适合8-14岁青少年儿童以及");
        JLabel Rule2 = new JLabel("部分怀旧人群的益智小游戏，游戏玩家通过空");
        JLabel Rule3 = new JLabel("格键控制小兔子手中的钩子，帮助小兔子挖取");
        JLabel Rule4 = new JLabel("埋在土里萝卜，每种萝卜有不同的分数，只有");
        JLabel Rule5 = new JLabel("达到指定分数时才能过关。每一关结束后会有");
        JLabel Rule6 = new JLabel("商店出现，玩家可以用萝卜来购买道具。");

        Rule1.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        Rule2.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        Rule3.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        Rule4.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        Rule5.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        Rule6.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));

    	Rule1.setBounds(80, 40, 360, 30);
    	Rule2.setBounds(40, 70, 400, 30);
    	Rule3.setBounds(40, 100, 400, 30);
    	Rule4.setBounds(40, 130, 400, 30);
    	Rule5.setBounds(40, 160, 400, 30);
    	Rule6.setBounds(40, 190, 400, 30);

    	instrution.add(Rule1);
    	instrution.add(Rule2);
    	instrution.add(Rule3);
    	instrution.add(Rule4);
    	instrution.add(Rule5);
    	instrution.add(Rule6);
    	instrution.add(settingbg2);
    	
    	//关于我们界面
    	JLabel abouttitle = new JLabel("关于我们");
    	abouttitle.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 30));
    	abouttitle.setBounds(180, 0, 128, 25);
        about.add(abouttitle);
        
        JLabel return2 = new JLabel("返回");
        return2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					card.show(pan, "p1");
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					return2.setBounds(200, 267, 180, 25);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					return2.setBounds(200, 260, 180, 25);
				}
			});
		        
        return2.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 30));
        return2.setBounds(200, 250, 67, 40);
        about.add(return2);
                                  
        JLabel Rule7 = new JLabel("本项目组组名为“拔萝卜表演艺术团”，我");
        JLabel Rule8 = new JLabel("们小组里有大神，有菜鸟，但我们都在尽全力");
        JLabel Rule9 = new JLabel("去做好这个项目。希望大家能够喜欢我们的作");
        JLabel Rule10 = new JLabel("品，不喜欢的话试着去喜欢一下。目前作品还");
        JLabel Rule11 = new JLabel("存在一些小小的问题，看出问题的小伙伴请保");
        JLabel Rule12 = new JLabel("持沉默，因为沉默是金，希望你有钱。");

        Rule7.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        Rule8.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        Rule9.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        Rule10.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        Rule11.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        Rule12.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));

    	Rule7.setBounds(80, 40, 360, 30);
    	Rule8.setBounds(40, 70, 400, 30);
    	Rule9.setBounds(40, 100, 400, 30);
    	Rule10.setBounds(40, 130, 400, 30);
    	Rule11.setBounds(40, 160, 400, 30);
    	Rule12.setBounds(40, 190, 400, 30);

    	about.add(Rule7);
    	about.add(Rule8);
    	about.add(Rule9);
    	about.add(Rule10);
    	about.add(Rule11);
    	about.add(Rule12);
        about.add(settingbg3);

        //聊天小兔子界面
        JTextField text1 = new JTextField("快来和小兔子菲菲聊天吧！");
        text1.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
        text1.setBounds(30, 81, 360, 40);    
        chatrobot.add(text1);
      
        
        JButton send1 = new JButton("\u53D1\u9001");
        send1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		String value = text1.getText().trim();
        	    String anwser = null;
        	    JTextField anw = new JTextField(anwser);
                anw.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
                anw.setBounds(30, 130, 360, 40);
                chatrobot.add(anw);
        	}
        });
        send1.setFont(new Font("Aa芒果弟弟", Font.BOLD, 16));
        send1.setBounds(400, 81, 68, 40);
        chatrobot.add(send1);
        
        JLabel return3 = new JLabel("返回");
        return3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					card.show(pan, "p1");
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					return3.setBounds(200, 267, 68, 40);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					return3.setBounds(200, 260, 68, 40);
				}
			});
		        
        return3.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 30));
        return3.setBounds(200, 260, 68, 40);
        chatrobot.add(return3);
        chatrobot.add(settingbg4);
        
        //关闭按钮及响应事件
        close.setBounds(458, 10, 20, 20);
		   close.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					close.setBounds(458, 15, 20,20);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					close.setBounds(458, 10, 20, 20);
				}
			});
			String closepic = "res/close.png";
			Maingame.shink(closepic,close);
			contentPane.add(close);
			
			//设置窗口标题
			JLabel title = new JLabel("设置");
			title.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 20));
			title.setBounds(10, 5, 70, 30);
			contentPane.add(title);
			
	}
}

