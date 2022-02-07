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
    int order;//����
    final int totalOrder = 4;
    Shop sp=new Shop();
    /*�ؿ����ɼ��صı���*/
    static double lifetime;
    double totaltime;
    int requireScore;
    List<Mineral> mineralList = new ArrayList<Mineral>();
    List<Bomb> bombList = new ArrayList<Bomb>();
    List<ExplodeEffect> explodeEffectList = new ArrayList<ExplodeEffect>();
    enum StageState {MENU, PLAYING, CONFIGURE, PAUSE, GAME_OVER,WIN}
    Exit e1 = new Exit();
    JLabel close = new JLabel();           //�رհ�ť
 	JLabel smallest = new JLabel();        //��С����ť
    JLabel setting = new JLabel();
    JLabel sound = new JLabel();
    StageState stageState;

    static int score=0;


    Hook hook = new Hook (width, 290);//��ʼ������
    
    static Timer timer;
   
    void load(int order) throws IOException {
    	int minercount=0;
        this.order = order;
        mineralList.clear();
        File filepath=new File("dat/stage"+order+".dat");
        InputStreamReader isr=new InputStreamReader(new FileInputStream(filepath));
        BufferedReader br=new BufferedReader(isr); //���������ļ�
        String linedata=br.readLine(); //���ؿ�ʱ��ͷ���
        StringTokenizer st=new StringTokenizer(linedata,",");
        lifetime=Integer.parseInt(st.nextToken());
        totaltime=lifetime;
        requireScore=Integer.parseInt(st.nextToken());
        minercount=Integer.parseInt(st.nextToken());
        for(int i=0;i<minercount;i++){  //�������б�
        	linedata=br.readLine();
        	st=new StringTokenizer(linedata,",");  //��ʽΪ����������,x,y,r,value,density
        	String mineralType=st.nextToken();
        	switch(mineralType){  //���ݲ�ͬ�Ŀ������ͽ�����ͬ����
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
        //System.out.println("lose");//����
    }
    void win(){
    	stageState = StageState.WIN;
    	//System.out.println("win");//����
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
            	bombList.clear(); //�п�����һ�ص�ը����ûը��
            	load(order);
            } else {
            	//order = 1;
            	//score = 0;
            	order--;
            	bombList.clear(); //�п�����һ�ص�ը����ûը��
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
        	/*��������˵��Ҫ������λ��*/
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
    Image jiandan=Toolkit.getDefaultToolkit().createImage("res/��.png");
    Image putong=Toolkit.getDefaultToolkit().createImage("res/��ͨ.png");
    Image kunnan=Toolkit.getDefaultToolkit().createImage("res/��ս.png");
    Image jinbi=Toolkit.getDefaultToolkit().createImage("res/���.png");
    Image qiandai=Toolkit.getDefaultToolkit().createImage("res/Ǯ��.png");
    Image cundang=Toolkit.getDefaultToolkit().createImage("res/gongneng/�浵1.png");
    Image paihangbang=Toolkit.getDefaultToolkit().createImage("res/gongneng/���а�1.png");
    Image shezhi=Toolkit.getDefaultToolkit().createImage("res/gongneng/����1.png");
    Image jiemian1=Toolkit.getDefaultToolkit().createImage("res/gongneng/������.jpg");
    Image jiemian2=Toolkit.getDefaultToolkit().createImage("res/gongneng/������2.jpg");
    Image jiemian3=Toolkit.getDefaultToolkit().createImage("res/gongneng/������1.jpg");
    Image gameoverpic=Toolkit.getDefaultToolkit().createImage("res/gongneng/gameover.jpg");
    Image gamewinpic=Toolkit.getDefaultToolkit().createImage("res/gongneng/gamewin.jpg");
    Image shangdian=Toolkit.getDefaultToolkit().createImage("res/gongneng/�̵�.png");
    Image jiemian=Toolkit.getDefaultToolkit().createImage("res/gongneng/����.png");
    Image zhuye=Toolkit.getDefaultToolkit().createImage("res/gongneng/��ҳ.png");
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
            	g.drawImage(cp, 846, 14, 27, 27, this);     //�رհ�ť
            	g.drawImage(smp, 806, 23, 36, 6, this);      //��С����ť
            	g.drawImage(soundpic, 766, 10, 31, 31, this);     //��Ч��ť
            	g.drawImage(setpic, 720, 10, 32, 32, this);     //���ð�ť

            	g.drawImage(timeLineBg,715,130,165,15,this);
            	/*
            	 * �������ܰ���
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
            	g.setFont(new Font("�����п�", Font.BOLD, 28));
            	g.drawImage(jinbi,600, 30, 50, 50,this);
            	g.drawImage(qiandai,600, 85, 50, 50,this);
            	g.drawString("Ŀ�������",650,70);
            	g.drawString("��ǰ������",650,110);
            	//ǰ�����Ӳ��ı���ȱ����Ա���������쳣�˶�
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
            	//���ƽ�������
            	Save.RankscoreWirte(Stage.score);
            	if(Save.isrank){
            		Save.RanknameWirte(login.name);
            	}
               	g.drawImage(gameoverpic,0,0,(int)width,(int)height,this);
            	g.drawImage(fanhui, 10, 20, 90, 80, this);
            	g.setFont(new Font("Aaâ���ܵ�", Font.BOLD, 80));
            	g.setColor(Color.blue);
            	g.drawString(""+order,560,230);
            	//g.drawString(""+login.name,560,215);//����ʹ��
            	g.drawString(""+score,590,330);
                break;
               
            case WIN:
            	//���ƽ�������
            	Save.RankscoreWirte(Stage.score);
            	if(Save.isrank){
            		Save.RanknameWirte(login.name);
            	}
            	g.drawImage(gamewinpic,0,0,(int)width,(int)height,this);
            	g.drawImage(fanhui, 10, 20, 90, 80, this);
            	g.setFont(new Font("Aaâ���ܵ�", Font.BOLD, 80));
            	g.setColor(Color.blue);
            	g.drawString(""+login.name,560,215);
            	g.drawString(""+score,590,315);
                

                break;
                
        }
    }

}
