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


public class Hook {
	private double sourceX; //���ҵ�
    private double sourceY; //���ҵ�
    private double theta=0.0; //�Ƕ�
    private double d=0.0; //����
    final double r = 30.0; //���Ӵ�С
    private double weight=800.0; //���ӱ��������
    private Mineral mineral;//����������
    private static int speedup=0;

    HookState state; //���ӵ��н�״̬
    enum HookState{WAIT, FORWARD, BACKWARD}
    
    int hookWaitDirection = 1; //���ƹ��ӻζ��ķ���

    public Hook(double width, double height){
        sourceX = width/2;
        sourceY = height; //��Ҫ���ݱ������ڵ����ʵ���ʼ�߶�

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

    /*�ֱ���������ٶȺͷŵ��ٶȣ��ŵ��ٶ�ƫ���������ù��ӵ��������㣩*/
    /*TODO �����ٶȵĲ������Ե���*/
    double getPullVelocity(){
    	 return (40000.0+speedup) / getWeight();
    }    
    double getPushVelocity(){
    	return 20.0;
    }
    
    boolean hasMineral() {
    	return mineral != null;
    }

    /*����ж��Ƿ񹳵�����*/
    /*TODO ���Ӵ����ķ�Χ���Ե���*/
    boolean hookMineral(Mineral m){
        if(distance(getX(),getY(),m.x,m.y) < (/*r/2 +*/ m.Radius)){
            mineral = m;
            state = HookState.BACKWARD;
            return true;
        } else {
        	return false;
        }
    }
    
    /*����ж��Ƿ���ը��*/
    boolean explodeBomb(Bomb b){
    	if(distance(getX(), getY(), b.x, b.y) < (r/2 + b.Radius)){
    		state = HookState.BACKWARD;
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /*ÿ��ʱ��ѭ��ʱ���¹���λ�úͽǶ�, �ٶ��빳�������й�; �ж��Ƿ�ץ������ */
    void refresh(Stage stage){
        switch (state){
            case WAIT:
            	theta += hookWaitDirection * Math.PI / Maingame.PERIOD;
            	
            	/*���ƹ��ӵķ��򣬵���߽�ת��*/
            	/*TODO ���ӻζ��ķ�Χ���Ե���*/
            	if (theta >= Math.PI * 19 / 20) {
            		hookWaitDirection = -1;
            	}
            	else if (theta <= Math.PI / 20) {
            		hookWaitDirection = 1;
            	}
                break;
                
            case FORWARD:
            	d += getPushVelocity();
            	
            	/*�ж��Ƿ񳬳��߽�*/
            	if (getX() < 50 || getX() > 850 || getY() > 650) {
            		state = HookState.BACKWARD;
            		break;
            	}
            	
            	/*�ж��Ƿ񹳵�����*/
                for(int i=0; i<stage.mineralList.size(); i++){
                    Mineral testMineral = stage.mineralList.get(i);
                	if(hookMineral(testMineral)){
                    	testMineral.hooked(stage,i);
                    	break;
                    }
                }
                
                /*�ж��Ƿ�����ը��*/
                for(int i=0, len = stage.bombList.size(); i<len; ++i) {
                	Bomb testBomb = stage.bombList.get(i);
                	if (explodeBomb(testBomb)) {
                		stage.bombList.remove(i);
                		testBomb.explode();
                		len = stage.bombList.size();
                		--i;
                	}
                }
                break;
                
            case BACKWARD:
            	d -= getPullVelocity();
            	
            	/*��Ҫ�ж��ǳ����߽���ɵ����ػ��ǹ���������ɵ�����*/
            	if (mineral != null){
            		/*�������Ķ������˸�λ��*/
            		/*TODO ��λ�ƵĶ��ٿ��Ե���*/
            		mineral.Refresh(getX() + r * Math.cos(theta), 
            				getY() + r * Math.sin(theta));
            	}
            	
            	/*�����ӷֲ��ص��ȴ�״̬*/
            	/*TODO �жϸ߷�����͵ͷ�����Ľ��޿��Ե���*/
            	if (d <= 0){
            		if (mineral != null) {
            			stage.score += mineral.Value;
            			
            			/*��������*/
            			String soundName; //ָ�����������ļ���
            			
            			 mineral = null;
            		}
            		d = 0;
            		state = HookState.WAIT;
            	}
            	break;
        }
    }

    /*����, ����, ����������*/
    void paint(Graphics g) throws IOException{
    	switch (state) {
    	case BACKWARD:
    		if (mineral != null){
    			mineral.Paint(g);	// �Ȼ����������壬�ٽ���default�����Ӻ���
    		}    		
    	default:
    		/*����*/
    		Graphics2D g2= (Graphics2D)g;
    		g2.setStroke(new BasicStroke(3.0f)); // ����������ϸ
        	g2.drawLine((int)sourceX, (int)(sourceY), (int)getX(), (int)getY());
    		/*������*/
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
    
    /*��תͼƬ*/
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

