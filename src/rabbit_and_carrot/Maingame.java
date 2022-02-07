package rabbit_and_carrot;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class Maingame extends JFrame {
	
	private JPanel contentPane;
	private JPanel Pane=new JPanel();            //游戏菜单界面
	public static boolean flag = false;  
	JLabel setting = new JLabel();
	static CardLayout card = new CardLayout(0,0);
	static JPanel pan = new JPanel(card);
	static Stage stage;
	static StageP2 stage1;
	private int saveorder=0;
	private List<Mineral> savemineralList = new ArrayList<Mineral>();
	private int savescore=0;
	private double savetime=0;
    static final double TIME_STEP = 1.0; //单位事件步长
    static final double PERIOD = 20.0;//时间分成了20份
    
    Setting set ;
    public  static SoundControl m1;             //控制声效窗口
	Exit e1 ;                            //退出界面
	
	public Maingame() throws IOException {
		Shop sp = new Shop();
		sp.setBounds(0,0,900, 700);
		
		setTitle("Rabbit and Carrot");
        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pan.add(Pane,"p1");
        /*
        pan.add(sp,"p3");
        */
        setResizable(false);                              //不可最大化
		setUndecorated(true);
		
	    set = new Setting();
        m1 = new SoundControl();             //控制声效窗口
		e1 = new Exit();                             //退出界面
						              
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		setVisible(true);
		contentPane = new JPanel();
		Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Pane.setLayout(null);
		contentPane.setLayout(null);
		
		//游戏主标题
		JLabel title1 = new JLabel("Carrot And Rabit");
		title1.setForeground(new Color(0, 51, 102));
		title1.setBounds(418, 88, 440, 196);
		Pane.add(title1);
		title1.setFont(new Font("Aa芒果弟弟", Font.BOLD, 54));
		
		/*stage.smallest.addMouseListener(new MouseAdapter() {
      		@Override
      	public void mouseClicked(MouseEvent e) {	
      			setExtendedState(JFrame.ICONIFIED);
      	}
      	});*/
		//开始游戏的鼠标事件
		JLabel start = new JLabel("单人");
		start.addMouseListener(new MouseAdapter() {
			@Override
		public void mouseClicked(MouseEvent e) { 
			flag = false;
			try{
				stage = new Stage();
				Stage.score = 0;
		   }catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			pan.add(stage,"p2");
			login lg = new login();
			lg.setVisible(!flag);
			/*
			card.next(pan);
			stage.setFocusable(true);
			stage.requestFocusInWindow();//为窗口设置焦点 
		      */
		    stage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  super.mouseClicked(e);
                  //home键的鼠标点击事件
                  int x1=e.getX(), y1=e.getY();
                  if(x1>30&&x1<80 && y1>30 && y1<80){
                   	stage.pause();
                   	card.show(pan, "p1");;
                  }
                  //音效键的鼠标点击事件
                  int x2=e.getX(), y2=e.getY();
                  if(x2>766&&x2<797 && y2>10 && y2<41){
                    	stage.pause();
                      	m1.setVisible(true );
                   }
                  //设置键的鼠标点击事件
                  int x3=e.getX(), y3=e.getY();
                  if(x3>720&&x3<752 && y3>10 && y3<42){
                      	stage.pause();
                      	set.setVisible(true);
                  }
                  //关闭键的鼠标点击事件
                  else if(x3>846&&x3<873 && y3>14 && y3<41){
                      	stage.pause();
                       	e1.setVisible(true);                        
                  }
                  //最小化按钮鼠标点击事件
                  else if(x3>806&&x3<842 && y3>23 && y3<29){
                      	stage.pause();
                      	setExtendedState(JFrame.ICONIFIED);
                  }
                  //商店键的鼠标点击事件
                 int x=e.getX(), y=e.getY();
                 if(x>240&&x<290 && y>30 && y<80){
                       stage.pause();
                       Shop1 shop = new Shop1();
                       shop.setVisible(true);
                   }//存档功能
                 else if(x>100&&x<150 && y>30 && y<80){
                       	saveorder=stage.order;
                       	savescore=stage.score;
                       	savetime=stage.lifetime;
                       	savemineralList.addAll(stage.mineralList);
                 }
                 else if(x>170&&x<220 && y>30 && y<80){
                      	Rank rank = new Rank();
                       	rank.setVisible(true);
                 }
                 else if(x>30&&x<80 && y>30 && y<80){
                     	stage.pause();
                       	card.show(pan, "p1");
                 }
           }
       });
			stage.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e){
						super.keyPressed(e);
						switch(e.getKeyCode()){
						case KeyEvent.VK_SPACE :
							stage.hook.launch();
							if(m1.f2==true){
								Music soundm = new Music("res/音效.wav");
								soundm.start();
							}
							//System.out.println("game");//测试是否获取了键盘事件  
							break;
						case KeyEvent.VK_P:
							stage.pause();
							break;
						}
					}
				});
					        
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			start.setForeground(new Color(255, 255, 255));
			if(m1.f2==true){
				Music soundm = new Music("res/音效.wav");
				soundm.start();
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {
			start.setForeground(new Color(255, 255, 0));
		}
		});
		start.setForeground(new Color(255, 255, 0));
		start.setFont(new Font("Aa芒果弟弟", Font.PLAIN, 39));
		start.setBounds(550, 345, 96, 52);
		Pane.add(start);
		
		//主界面背景图片
		JLabel ground = new JLabel("");
		ground.setIcon(new ImageIcon("res/大象和小白兔.png"));
		ground.setBounds(-196, 0, 976, 700);
		Pane.add(ground);
		
		//双人模式的鼠标事件
		JLabel doublemode = new JLabel("双人");
		doublemode.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			doublemode.setForeground(new Color(255, 255, 255));
			if(m1.f2==true){
				Music soundm = new Music("res/音效.wav");
				soundm.start();
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {
			doublemode.setForeground(new Color(255, 255, 0));
		}
			@Override
			public void mouseClicked(MouseEvent e) {
				flag=false;
				stage1.score=0;
				try {
					stage1 = new StageP2();
				} catch (IOException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				} 
		        pan.add(stage1, "p3");
				card.show(pan,"p3");
				stage1.setFocusable(true);
				stage1.requestFocusInWindow();
		        stage1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        int x=e.getX(), y=e.getY();
                            if(x>240&&x<290 && y>30 && y<80){
                             //card.next(pan);
                            	stage1.pause();
                            	Shop2 shop = new Shop2();
                            	shop.setVisible(true);
                            }//存档功能
                            else if(x>100&&x<150 && y>30 && y<80){
                            	//Save.RankscoreWirte(Stage.score);
                            	saveorder=stage1.order;
                            	savescore=stage1.score;
                            }
                            else if(x>170&&x<220 && y>30 && y<80){
                            	Rank rank = new Rank();
                            	rank.setVisible(true);
                            }
                            else if(x>30&&x<80 && y>30 && y<80){
                            	stage1.pause();
                            	card.show(pan, "p1");
                            }
                          //音效键的鼠标点击事件
                            int x2=e.getX(), y2=e.getY();
                            if(x2>766&&x2<797 && y2>10 && y2<41){
                            	//System.out.println("yinxiao");
                            	stage1.pause();
                            	m1.setVisible(true );
                            }
                          //设置键的鼠标点击事件
                            int x3=e.getX(), y3=e.getY();
                            if(x3>720&&x3<752 && y3>10 && y3<42){
                            	stage1.pause();
                            	set.setVisible(true);
                            }
                            //关闭键的鼠标点击事件
                            else if(x3>846&&x3<873 && y3>14 && y3<41){
                            	stage1.pause();
                            	e1.setVisible(true);                        

                            }
                            //最小化按钮鼠标点击事件
                            else if(x3>806&&x3<842 && y3>23 && y3<29){
                            	stage1.pause();
                            	setExtendedState(JFrame.ICONIFIED);
                        }
               }
           });
				stage1.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent e){
							super.keyPressed(e);
							switch(e.getKeyCode()){
							case KeyEvent.VK_SPACE :
								stage1.hook1.launch();
								
								//System.out.println("game");//测试是否获取了键盘事件  
								break;
							case KeyEvent.VK_DOWN :
							
								stage1.hook2.launch();
								//System.out.println("game");//测试是否获取了键盘事件  
								break;
							case KeyEvent.VK_P:
								stage1.pause();
								break;
							}
						}
					});
				try {
					//stage.load(0);
					stage1.load(1);
					stage1.start();//控制钩子开始旋转
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		doublemode.setForeground(new Color(255, 255, 0));
		doublemode.setFont(new Font("Aa芒果弟弟", Font.BOLD, 39));
		doublemode.setBounds(690, 345, 100, 68);
		Pane.add(doublemode);
		//双人模式背景图
		String startbgName = "res/胡萝卜.gif";
		JLabel doublebg = new JLabel();
		doublebg.setSize(170, 170);
		doublebg.setLocation(650, 280);
		shink(startbgName,doublebg);
		Pane.add(doublebg);
		//继续游戏的鼠标事件
		JLabel goon = new JLabel("继续");
		goon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				goon.setForeground(new Color(255, 255, 255));
				if(m1.f2==true){
					Music soundm = new Music("res/音效.wav");
					soundm.start();
				}		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				goon.setForeground(new Color(255, 255, 0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				goon.setForeground(new Color(255, 255, 0));
				Maingame.card.next(Maingame.pan);
				Maingame.pan.setFocusable(true);
				
				Maingame.pan.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e){
						super.keyPressed(e);
						switch(e.getKeyCode()){
						case KeyEvent.VK_SPACE :
							stage.hook.launch();
							//System.out.println("一致性");

							break;
						case KeyEvent.VK_P:
							stage.pause();
							break;
						}
					}
				});
				Maingame.pan.setFocusable(true);
				Maingame.pan.requestFocusInWindow();//为窗口设置焦点 
				
				try {
					//stage.load(0);
					Maingame.stage.load(saveorder);
					stage.score=savescore;
					stage.lifetime=savetime;
					stage.mineralList.clear();
					stage.mineralList.addAll(savemineralList);
					Maingame.stage.start();//控制钩子开始旋转
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		});
		goon.setForeground(new Color(255, 255, 0));
		goon.setFont(new Font("Aa芒果弟弟", Font.BOLD, 39));
		goon.setBounds(550, 525, 100, 44);
		Pane.add(goon);
		
		//退出游戏标签鼠标事件
		JLabel exit = new JLabel("退出");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				e1.setVisible(true);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setForeground(new Color(255, 255, 255));
				if(m1.f2==true){
					Music soundm = new Music("res/音效.wav");
					soundm.start();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exit.setForeground(new Color(255, 255, 0));
			}
		});
		exit.setForeground(new Color(255, 255, 0));
		exit.setFont(new Font("Aa芒果弟弟", Font.BOLD, 39));
		exit.setBounds(690, 525, 100, 45); 
		Pane.add(exit);
		
		//单人模式背景图片
		JLabel singlebg = new JLabel("");
		singlebg.setBounds(509, 277, 170, 170);
		shink(startbgName,singlebg);
		Pane.add(singlebg);
		//退出游戏背景图片
		JLabel exitbg = new JLabel("");
		exitbg.setBounds(650, 450, 170, 170);
		shink(startbgName,exitbg);
		Pane.add(exitbg);
		
		//继续游戏背景图片
		JLabel goonbg = new JLabel("");
		goonbg.setBounds(509, 450, 170, 170);
		shink(startbgName,goonbg);
		Pane.add(goonbg);
		
		//设置关闭按钮及鼠标事件监听器
		JLabel close = new JLabel("");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				e1.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				close.setBounds(846, 19, 27,27);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				close.setBounds(846, 14, 27, 27);
			}
		});
		String closepic = "res/close.png";
		close.setBounds(846, 14, 27, 27);
		shink(closepic,close);
		Pane.add(close);
		
		//最小化的鼠标监听事件
		JLabel smallest = new JLabel("");
		smallest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				smallest.setBounds(806, 15, 32, 30);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				smallest.setBounds(806, 10, 32, 30);
			}
		});
		String smallestpic = "res/smallest.png";
		smallest.setBounds(806, 10, 32, 30);
		shink(smallestpic,smallest);
		Pane.add(smallest);
		
		//设置声效按钮及鼠标事件监听器
		JLabel sound = new JLabel("");
		sound.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m1.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sound.setBounds(766,15 , 30, 30);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sound.setBounds(766, 10, 30, 30);
			}
		});
		sound.setBounds(766, 10, 30, 30);
		String soundpic = "res/sound.png";
		shink(soundpic,sound);
		Pane.add(sound);
		//contentPane.add(Pane);
		this.getContentPane().add(pan);   
        this.setSize(300, 200);  
        this.setVisible(true);  
        
//设置图标鼠标事件 
      	
      	setting.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		set.setVisible(true);
      	}
      	@Override
      	public void mouseEntered(MouseEvent e) {
      		setting.setBounds(720, 15, 32, 32);
      	}
      	@Override
      	public void mouseExited(MouseEvent e) {
      		setting.setBounds(720, 10, 32, 32);
      	}
      	});
      	String setpic = "res/setting.png";
      	setting.setBounds(720, 10, 32, 32);
      	shink(setpic,setting);
      	Pane.add(setting);
		}
      
    
    public static void main(String[] args) throws IOException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        Maingame maingame = new Maingame();
			        maingame.setVisible(true);
			        maingame.setSize(900,700);
			        maingame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }


//将图片按label比例缩小的函数并设置成label背景的函数
		public static void shink(String bgname,JLabel label){
			//生成一个背景图片的对象
			ImageIcon bgpic = new ImageIcon(bgname);
			//获得背景图片的长宽
			int imgWidth=bgpic.getIconWidth();  
		    int imgHeight=bgpic.getIconHeight();  
		    //获得窗口的长宽
		    int conWidth=label.getWidth();  
		    int conHeight=label.getHeight();  
		    //按比例缩放后的图片大小
		    int reImgWidth;  
		    int reImgHeight;  
		    if(imgWidth/imgHeight>=conWidth/conHeight){  
		           if(imgWidth>conWidth){  
		               reImgWidth=conWidth;  
		               reImgHeight=imgHeight*reImgWidth/imgWidth;  
		           }else{  
		               reImgWidth=imgWidth;  
		               reImgHeight=imgHeight;  
		           }  
		        }
		       else{  
		           if(imgWidth>conWidth){  
		               reImgHeight=conHeight;  
		               reImgWidth=imgWidth*reImgHeight/imgHeight;  
		           }
		           else{  
		               reImgWidth=imgWidth;  
		               reImgHeight=imgHeight;   
		           }
		       }
		    //重新生成图片对象
		    bgpic = new ImageIcon(bgpic.getImage().getScaledInstance(reImgWidth, reImgHeight,Image.SCALE_SMOOTH ));
			label.setIcon(bgpic);
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
}
