package rabbit_and_carrot;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Bomb {
	double x;
	double y;
	double Radius;

	Stage stage;
	StageP2 stagep2;
	List<Bomb> chainReaction =new ArrayList<Bomb>();
	boolean Mark_1;
	Bomb(double x, double y, double Radius, Stage stage) {
		this.x = x;
		this.y = y;
		this.Radius = Radius;
		this.stage = stage;
		this.Mark_1 = false;
	}
	Bomb(double x, double y, double Radius, StageP2 stagep2) {
		this.x = x;
		this.y = y;
		this.Radius = Radius;
		this.stagep2 = stagep2;
		this.Mark_1 = false;
	}
	
	void paint(Graphics graph) {
		Image icon = new ImageIcon("res/bomb.png").getImage();
		graph.drawImage(icon, (int)(x-Radius), (int)(y-Radius), (int)(2*Radius), (int)(2*Radius), null);
	}
	
	private static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
	
	void explode() {		
		
		for(int i=0,len=stage.mineralList.size();i<len;++i) {
			if(distance(stage.mineralList.get(i).x,stage.mineralList.get(i).y,x,y)<5*Radius) {
				stage.mineralList.remove(i);
				--len;
				--i;
			}
		}
		//声音播放需要另外添加音效文件,以及新的类
		for (int i=0, len = stage.bombList.size(); i<len; ++i) {
			Bomb testBomb = stage.bombList.get(i);
			if(distance(x,y,testBomb.x, testBomb.y) < (Radius + testBomb.Radius)
					&& testBomb.Mark_1 == false) {
				stage.bombList.remove(i);
				len = stage.bombList.size();
				--i;
				testBomb.Mark_1 = true;
				chainReaction.add(testBomb);
			}
		}
		for (int i=0; i<chainReaction.size(); ++i) {
			chainReaction.get(i).explode();
		}
		chainReaction.clear();
		stage.explodeEffectList.add(new ExplodeEffect(x, y, Radius));
		if(Maingame.m1.f2==true){
			Music soundm = new Music("res/Yinxiao.wav");
			soundm.start();
		}
	}
	void explodep2() {
		/*
		 * p2
		 */
		for(int i=0,len=stagep2.mineralList.size();i<len;++i) {
			if(distance(stagep2.mineralList.get(i).x,stagep2.mineralList.get(i).y,x,y)<5*Radius) {
				stagep2.mineralList.remove(i);
				--len;
				--i;
			}
		}
		for (int i=0, len = stagep2.bombList.size(); i<len; ++i) {
			Bomb testBomb = stagep2.bombList.get(i);
			if(distance(x,y,testBomb.x, testBomb.y) < (Radius + testBomb.Radius)
					&& testBomb.Mark_1 == false) {
				stagep2.bombList.remove(i);
				len = stagep2.bombList.size();
				--i;
				testBomb.Mark_1 = true;
				chainReaction.add(testBomb);
			}
		}
		for (int i=0; i<chainReaction.size(); ++i) {
			chainReaction.get(i).explodep2();
		}
		chainReaction.clear();
		stagep2.explodeEffectList.add(new ExplodeEffect(x, y, Radius));
		if(Maingame.m1.f2==true){
			Music soundm = new Music("res/Yinxiao.wav");
			soundm.start();
		}
	}
	
	
	
}

class ExplodeEffect{
	double x;
	double y;
	double Radius;
	int EffectCount;
	
	ExplodeEffect(double x,double y,double Radius){
		this.x=x;
		this.y=y;
		this.Radius=3*Radius;//爆炸范围可以调整
	}
	void paint(Graphics g) {
		Image icon = new ImageIcon("res/pic/bomb/bomb" + EffectCount + ".png").getImage();
		g.drawImage(icon, (int)(x-Radius), (int)(y-Radius), (int)(3*Radius), (int)(3*Radius), null);
		++this.EffectCount;
	}
	
	boolean isEnd() {
		return EffectCount==6;//数值等于爆炸效果图片数减一
	}
}