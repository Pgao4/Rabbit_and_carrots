package rabbit_and_carrot;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


public class Hook2 {
	private double sourceX; //悬挂点
    private double sourceY; //悬挂点
    private double theta=0.0; //角度
    private double d=0.0; //绳长
    final double r = 30.0; //钩子大小
    private double weight=800.0; //钩子本身的重量
    private Mineral mineral;//钩到的物体
    private static int speedup=0;

    HookState state; //钩子的行进状态
    enum HookState{WAIT, FORWARD, BACKWARD}
    
    int hookWaitDirection = 1; //控制钩子晃动的方向

    public Hook2(double width, double height){
        sourceX = width/2;
        sourceY = height; //需要根据背景调节到合适的起始高度

        state = HookState.WAIT;
    }
    public static void changespeedup(){
    	speedup+=20000;
    	
    }
    double getX(){
        return sourceX + d * Math.cos(theta);
    }

    double getY(){
        return sourceY + d * Math.sin(theta);
    }

    double getWeight(){
        return mineral == null ? weight : weight 
        		+ mineral.Density * mineral.Radius * mineral.Radius;
    }

    /*分别计算拉的速度和放的速度（放的速度偏慢，不能用钩子的重量来算）*/
    /*TODO 计算速度的参数可以调整*/
    double getPullVelocity(){
    	 return (40000.0+speedup) / getWeight();
    }    
    double getPushVelocity(){
    	return 20.0;
    }
    
    boolean hasMineral() {
    	return mineral != null;
    }

    /*逐个判断是否钩到物体*/
    /*TODO 钩子触发的范围可以调整*/
    boolean hookMineral(Mineral m){
        if(distance(getX(),getY(),m.x,m.y) < (/*r/2 +*/ m.Radius)){
            mineral = m;
            state = HookState.BACKWARD;
            return true;
        } else {
        	return false;
        }
    }
    
    /*逐个判断是否触碰炸弹*/
    boolean explodeBomb(Bomb b){
    	if(distance(getX(), getY(), b.x, b.y) < (r/2 + b.Radius)){
    		state = HookState.BACKWARD;
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /*每次时间循环时更新钩子位置和角度, 速度与钩子重量有关; 判断是否抓到矿物 */
    void refresh(StageP2 stagep2){
        switch (state){
            case WAIT:
            	
            	theta += hookWaitDirection * Math.PI / Maingame.PERIOD;
            	
            	
            	/*控制钩子的方向，到达边界转向*/
            	/*TODO 钩子晃动的范围可以调整*/
            	if (theta >= Math.PI * 19 / 20) {
            		hookWaitDirection = -1;
            	}
            	else if (theta <= Math.PI / 20) {
            		hookWaitDirection = 1;
            	}
                break;
                
            case FORWARD:
            	d += getPushVelocity();
            	
            	/*判断是否超出边界*/
            	if (getX() < 50 || getX() > 850 || getY() > 650) {
            		state = HookState.BACKWARD;
            		break;
            	}
            	
            	/*判断是否钩到物体*/
                for(int i=0; i<stagep2.mineralList.size(); i++){
                    Mineral testMineral = stagep2.mineralList.get(i);
                	if(hookMineral(testMineral)){
                    	testMineral.hooked(stagep2,i);
                    	break;
                    }
                }
                
                /*判断是否碰到炸弹*/
                for(int i=0, len = stagep2.bombList.size(); i<len; ++i) {
                	Bomb testBomb = stagep2.bombList.get(i);
                	if (explodeBomb(testBomb)) {
                		stagep2.bombList.remove(i);
                		testBomb.explodep2();
                		len = stagep2.bombList.size();
                		--i;
                	}
                }
                break;
                
            case BACKWARD:
            	d -= getPullVelocity();
            	
            	/*需要判断是超出边界造成的拉回还是钩到东西造成的拉回*/
            	if (mineral != null){
            		/*给钩到的东西加了个位移*/
            		/*TODO 加位移的多少可以调整*/
            		mineral.Refresh(getX() + r * Math.cos(theta), 
            				getY() + r * Math.sin(theta));
            	}
            	
            	/*到达后加分并回到等待状态*/
            	/*TODO 判断高分物体和低分物体的界限可以调整*/
            	if (d <= 0){
            		if (mineral != null) {
            			stagep2.score += mineral.Value;
            			
            			/*播放声音*/
            			String soundName; //指定播放声音文件名
            			/*if (mineral.Value < 50) {
            				soundName = "res/sounds/low-value.wav";
            			} else if (mineral.Value >= 300) {
            				soundName = "res/sounds/high-value.wav";
            			} else {
            				soundName = "res/sounds/normal-value.wav";
            			}*/
            			
            			/*在新线程中播放声音*/
            			/*Thread playSound = new Thread(new SoundPlayer(soundName)); 
        				playSound.start();*/
            			 mineral = null;
            		}
            		d = 0;
            		state = HookState.WAIT;
            	}
            	break;
        }
    }

    /*画线, 钩子, 钩到的物体*/
    void paint(Graphics g) throws IOException{
    	switch (state) {
    	case BACKWARD:
    		if (mineral != null){
    			mineral.Paint(g);	// 先画钩到的物体，再进入default画钩子和线
    		}    		
    	default:
    		/*画线*/
    		Graphics2D g2= (Graphics2D)g;
    		g2.setStroke(new BasicStroke(3.0f)); // 设置线条粗细
        	g2.drawLine((int)sourceX, (int)(sourceY), (int)getX(), (int)getY());
    		/*画钩子*/
    		//BufferedImage hookImage = ImageIO.read(new File("res/howtoplay_2.png"));
    		BufferedImage hookImage = ImageIO.read(new File("res/hook.png"));
        	BufferedImage rotatedImage = rotateImage(hookImage, theta);
        	g.drawImage(rotatedImage,
        			(int)(getX() - r), (int)(getY() - r), 2*(int)r, 2*(int)r, null);
    	}    	
    }

    void launch(){
        if(state==HookState.WAIT)
            state = HookState.FORWARD;
    }

    private static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
    
    /*旋转图片*/
    public static BufferedImage rotateImage(final BufferedImage bufferedimage,
            final double theta) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(theta, w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }    
}
