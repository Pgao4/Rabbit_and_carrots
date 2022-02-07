package rabbit_and_carrot;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stage extends JPanel {
	double width = 900;
    double height = 700;
    private JLabel jbl=new JLabel();
    int order;//关数
    final int totalOrder = 4;
    Shop sp=new Shop();
    /*关卡生成加载的变量*/
    static double lifetime;
    double totaltime;
    int requireScore;
    List<Mineral> mineralList = new ArrayList<Mineral>();
    List<Bomb> bombList = new ArrayList<Bomb>();
    List<ExplodeEffect> explodeEffectList = new ArrayList<ExplodeEffect>();
    enum StageState {MENU, PLAYING, CONFIGURE, PAUSE, GAME_OVER,WIN}
    Exit e1 = new Exit();
    JLabel close = new JLabel();           //关闭按钮
 	JLabel smallest = new JLabel();        //最小化按钮
    JLabel setting = new JLabel();
    JLabel sound = new JLabel();
    StageState stageState;

    static int score=0;


    Hook hook = new Hook (width, 290);//初始化钩子
    
    static Timer timer;
   
    void load(int order) throws IOException {
    	int minercount=0;
        this.order = order;
        mineralList.clear();
        File filepath=new File("dat/stage"+order+".dat");
        InputStreamReader isr=new InputStreamReader(new FileInputStream(filepath));
        BufferedReader br=new BufferedReader(isr); //读入数据文件
        String linedata=br.readLine(); //读关卡时间和分数
        StringTokenizer st=new StringTokenizer(linedata,",");
        lifetime=Integer.parseInt(st.nextToken());
        totaltime=lifetime;
        requireScore=Integer.parseInt(st.nextToken());
        minercount=Integer.parseInt(st.nextToken());
        for(int i=0;i<minercount;i++){  //读矿物列表
        	linedata=br.readLine();
        	st=new StringTokenizer(linedata,",");  //格式为：矿物类型,x,y,r,value,density
        	String mineralType=st.nextToken();
        	switch(mineralType){  //根据不同的矿物类型建立不同对象
        		case "C":
        		{
                	double x=Double.parseDouble(st.nextToken());
                	double y=Double.parseDouble(st.nextToken());
                	double r=Double.parseDouble(st.nextToken());
                	int value=Integer.parseInt(st.nextToken());
        			mineralList.add(new Carrot(x,y,r,value));
        			break;
        		}
        		case "G":
        		{
                	double x=Double.parseDouble(st.nextToken());
                	double y=Double.parseDouble(st.nextToken());
                	double r=Double.parseDouble(st.nextToken());
                	int value=Integer.parseInt(st.nextToken());
        			mineralList.add(new GoldCarrot(x,y,r,value));
        			break;
        		}
        		case "B":
        		{
                	double x=Double.parseDouble(st.nextToken());
                	double y=Double.parseDouble(st.nextToken());
                	double r=Double.parseDouble(st.nextToken());
        			bombList.add(new Bomb(x,y,r,this));
        			break;
        		}
        		case "W":
        		{
        			double x=Double.parseDouble(st.nextToken());
                	double y=Double.parseDouble(st.nextToken());
                	double r=Double.parseDouble(st.nextToken());
                	int value=Integer.parseInt(st.nextToken());
                	int movingDirection=Integer.parseInt(st.nextToken());
                	double movingSpeed=Double.parseDouble(st.nextToken());
        			mineralList.add(new WalkingCarrot(x,y,r,value,
        					movingDirection,movingSpeed));
        			break;
        		}
        	
        	}
        }
        br.close();
        isr.close();
    }

    public Stage() throws IOException {
    	sp.setBounds(0,0,900, 700);
    	
        this.stageState=StageState.MENU;
        this.requestFocus();
    }

    void pause() {
        if (stageState == StageState.PLAYING)
            stageState = StageState.PAUSE;
        else if (stageState == StageState.PAUSE) {
            stageState = StageState.PLAYING;
        }
    }

    void configure() {
        if (stageState == StageState.CONFIGURE)
            stageState = StageState.PLAYING;
        else if (stageState == StageState.PLAYING)
            stageState = StageState.CONFIGURE;
    }

    void gameOver() {
        //score = 0;
        stageState = StageState.GAME_OVER;
        //System.out.println("lose");//调试
    }
    void win(){
    	stageState = StageState.WIN;
    	//System.out.println("win");//调试
    }
    void next() throws IOException{ 
        if(lifetime>0&&lifetime<=100){
        	
        } 
        if (score < requireScore) {
            timer.cancel();
            bombList.clear();
            gameOver();
        } else {
            order++;
            //System.out.println("gamenext");
            if (order < totalOrder) {
            	bombList.clear(); //有可能上一关的炸弹还没炸完
            	load(order);
            } else {
            	//order = 1;
            	//score = 0;
            	order--;
            	bombList.clear(); //有可能上一关的炸弹还没炸完
            	//load(order);
            	win();
            }
        }
    }
	void refresh() throws IOException {
        if (stageState != StageState.PLAYING) return;
        if ((mineralList.isEmpty() && ! hook.hasMineral())||lifetime <= 0) {
        	next();
        }
        lifetime--;
        hook.refresh(this);
        for (Mineral i : mineralList) {
        	/*对老鼠来说需要更新其位置*/
        	if (i instanceof WalkingCarrot) {
        		((WalkingCarrot)i).UpdateLocation();
        	}
        }
        repaint();
    }

    void start() {
        stageState = StageState.PLAYING;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
					refresh();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }, 0, 100);
    }
    Image cp=Toolkit.getDefaultToolkit().createImage("res/close.png");
    Image smp=Toolkit.getDefaultToolkit().createImage("res/smallest.png");
    Image setpic=Toolkit.getDefaultToolkit().createImage("res/setting.png");
    Image soundpic=Toolkit.getDefaultToolkit().createImage("res/sound.png");
    Image timeLineBg=Toolkit.getDefaultToolkit().createImage("res/timebg.png");
    Image timeLineGreen=Toolkit.getDefaultToolkit().createImage("res/timegood.png");
    Image timeLineRed=Toolkit.getDefaultToolkit().createImage("res/timepoor.png");
    Image home=Toolkit.getDefaultToolkit().createImage("res/home.png");
    Image gift=Toolkit.getDefaultToolkit().createImage("res/gift.png");
    Image gamebgPic=Toolkit.getDefaultToolkit().createImage("res/background.jpg");
    Image jiandan=Toolkit.getDefaultToolkit().createImage("res/简单.png");
    Image putong=Toolkit.getDefaultToolkit().createImage("res/普通.png");
    Image kunnan=Toolkit.getDefaultToolkit().createImage("res/挑战.png");
    Image jinbi=Toolkit.getDefaultToolkit().createImage("res/金币.png");
    Image qiandai=Toolkit.getDefaultToolkit().createImage("res/钱袋.png");
    Image cundang=Toolkit.getDefaultToolkit().createImage("res/gongneng/存档1.png");
    Image paihangbang=Toolkit.getDefaultToolkit().createImage("res/gongneng/排行榜1.png");
    Image shezhi=Toolkit.getDefaultToolkit().createImage("res/gongneng/设置1.png");
    Image jiemian1=Toolkit.getDefaultToolkit().createImage("res/gongneng/界面上.jpg");
    Image jiemian2=Toolkit.getDefaultToolkit().createImage("res/gongneng/界面下2.jpg");
    Image jiemian3=Toolkit.getDefaultToolkit().createImage("res/gongneng/界面下1.jpg");
    Image gameoverpic=Toolkit.getDefaultToolkit().createImage("res/gongneng/gameover.jpg");
    Image gamewinpic=Toolkit.getDefaultToolkit().createImage("res/gongneng/gamewin.jpg");
    Image shangdian=Toolkit.getDefaultToolkit().createImage("res/gongneng/商店.png");
    Image jiemian=Toolkit.getDefaultToolkit().createImage("res/gongneng/界面.png");
    Image zhuye=Toolkit.getDefaultToolkit().createImage("res/gongneng/主页.png");
    Image fanhui=Toolkit.getDefaultToolkit().createImage("res/return.gif");

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, (int)width, (int)height);
        double leftPercent=1.0;
		switch (stageState) {
            case PLAYING:
            case PAUSE:
            	//g.drawImage(jiemian1,0,0,(int)width,200,this);
            	switch(order){
            	case 1:g.drawImage(jiemian,0,0,(int)width,700,this);break;
            	case 2:g.drawImage(jiemian,0,0,(int)width,700,this);break;
            	}
            	g.drawImage(cp, 846, 14, 27, 27, this);     //关闭按钮
            	g.drawImage(smp, 806, 23, 36, 6, this);      //最小化按钮
            	g.drawImage(soundpic, 766, 10, 31, 31, this);     //音效按钮
            	g.drawImage(setpic, 720, 10, 32, 32, this);     //设置按钮

            	g.drawImage(timeLineBg,715,130,165,15,this);
            	/*
            	 * 三个功能按键
            	 */
            	g.drawImage(zhuye,30,30,50,50,this);
            	g.drawImage(cundang,100,30,50,50,this);
            	g.drawImage(paihangbang,170,30,50,50,this);
            	g.drawImage(shangdian,240,30,50,50,this);
            	//g.drawImage("",30,20,145,80,this);
            	g.setFont(new Font("Tahoma", Font.BOLD, 28));
            	g.setColor(Color.white);
            	g.drawString(""+requireScore,790,70);
            	g.drawString(""+score,790,110);
            	g.setFont(new Font("华文行楷", Font.BOLD, 28));
            	g.drawImage(jinbi,600, 30, 50, 50,this);
            	g.drawImage(qiandai,600, 85, 50, 50,this);
            	g.drawString("目标分数：",650,70);
            	g.drawString("当前分数：",650,110);
            	//前两秒钟不改变进度比例以避免进度条异常运动
            	if(totaltime-lifetime>5)
            		leftPercent=(1.0*lifetime)/(1.0*totaltime);
        		g.drawImage(timeLineRed,(int)(720+165*(1.0-leftPercent)),130,880,145,
								(int)((1.0-leftPercent)*timeLineGreen.getWidth(this)),0,(int)timeLineGreen.getWidth(this),(int)timeLineGreen.getHeight(this),this);
            	if((1.0*lifetime)/(1.0*totaltime)>=0.3)
            		g.drawImage(timeLineGreen,(int)(720+165*(1.0-leftPercent)),130,880,145,
            						(int)((1.0-leftPercent)*timeLineGreen.getWidth(this)),0,(int)timeLineGreen.getWidth(this),(int)timeLineGreen.getHeight(this),this);
            	g.setColor(Color.black);
            	try {
                	hook.paint(g);
                } catch (IOException error) {}
                for (Mineral m : mineralList) {
                    m.Paint(g);
                }
                for (Bomb b : bombList) {
                	b.paint(g);
                }
                for (int i=0, len = explodeEffectList.size(); i < len; ++i) {
                	if (explodeEffectList.get(i) != null) {
                    	explodeEffectList.get(i).paint(g);
                    	if (explodeEffectList.get(i).isEnd()) {
                        	explodeEffectList.remove(i);
                        	--i;
                        	--len;
                        }
                    }
                }           
                g.setColor(Color.red);
                break;
                
            case CONFIGURE:
                break;
                
            case GAME_OVER:
            	//绘制结束画面
            	Save.RankscoreWirte(Stage.score);
            	if(Save.isrank){
            		Save.RanknameWirte(login.name);
            	}
               	g.drawImage(gameoverpic,0,0,(int)width,(int)height,this);
            	g.drawImage(fanhui, 10, 20, 90, 80, this);
            	g.setFont(new Font("Aa芒果弟弟", Font.BOLD, 80));
            	g.setColor(Color.blue);
            	g.drawString(""+order,560,230);
            	//g.drawString(""+login.name,560,215);//测试使用
            	g.drawString(""+score,590,330);
                break;
               
            case WIN:
            	//绘制结束画面
            	Save.RankscoreWirte(Stage.score);
            	if(Save.isrank){
            		Save.RanknameWirte(login.name);
            	}
            	g.drawImage(gamewinpic,0,0,(int)width,(int)height,this);
            	g.drawImage(fanhui, 10, 20, 90, 80, this);
            	g.setFont(new Font("Aa芒果弟弟", Font.BOLD, 80));
            	g.setColor(Color.blue);
            	g.drawString(""+login.name,560,215);
            	g.drawString(""+score,590,315);
                

                break;
                
        }
    }

}
