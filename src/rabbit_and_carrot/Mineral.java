package rabbit_and_carrot;

import java.awt.*;

import javax.swing.ImageIcon;

public abstract class Mineral {
	double x;
	double y;
	double Radius;
	int Value;
	int Density;

	Mineral(double x,double y,double Radius,int Value,int Density){
		this.x=x;
		this.y=y;
		this.Radius=Radius;
		this.Value=Value;
		this.Density=Density;
	}
	abstract void Paint(Graphics graph);
	//更新物体位置的函数，被勾住后通过该函数使其位置保持同步
	void Refresh(double newX,double newY) {
		this.x=newX;
		this.y=newY;
	}
	//被勾到事件的地图更新等地图写完后再写
	void hooked(Stage stage,int i) {
		stage.mineralList.remove(i);
	} 
	void hooked(StageP2 stage,int i) {
	stage.mineralList.remove(i);
}
}
 


class Carrot extends Mineral{

	Carrot(double x,double y,double radius,int Value){
		super(x,y,radius,Value,5);
	}
	void Paint(Graphics graph) {
		Image icon = new ImageIcon("res/rabbit.png").getImage();
		graph.drawImage(icon, (int)(x-Radius+28), (int)(y-Radius), (int)(Radius), (int)(2*Radius), null);
	}
}

class GoldCarrot extends Mineral{
	GoldCarrot(double x,double y,double radius,int Value){
		super(x,y,radius,Value,7);
	}
	void Paint(Graphics graph) {
		Image icon = new ImageIcon("res/GoldCarrot.png").getImage();
		graph.drawImage(icon, (int)(x-2*Radius), (int)(y-2*Radius), (int)(4*Radius), (int)(4*Radius), null);
	}
}

class Stone extends Mineral{
	Stone(double x,double y,double radius,int Value){
		super(x,y,radius,Value,5);
	}
	void Paint(Graphics graph) {
		Image icon = new ImageIcon("").getImage();
		graph.drawImage(icon, (int)(x-Radius), (int)(y-Radius), (int)(2*Radius), (int)(2*Radius), null);
	}
}

class WalkingCarrot extends Mineral{
	int Direction;
	double Speed;
	int PaintCount;
	boolean isHooked;
	WalkingCarrot(double x,double y,double Radius,int Value,int Direction,double Speed){
		super(x,y,Radius,Value,2);
		this.Direction=Direction;
		this.Speed=Speed;
		this.PaintCount=0;
		this.isHooked=false;
	}
	void UpdateLocation() {
		x+=Direction*Speed;
		if(x<=100||x>=900) {
			this.Direction=-this.Direction;
		}//900仅为参考数值，根据游戏窗口大小进行调整
	}
	void hooked(Stage stage, int i){
    	stage.mineralList.remove(i);
    	isHooked = true;
    }
	void Paint(Graphics graph) {
		String suffix;
		String prefix;
		prefix = new String("res/pic/luobo");
		//prefix = new String("res/pic/luobo/carrot");
		if (Direction > 0) {
			suffix = new String("_right.png");
		} else {
			suffix = new String("_left.png");
		}
		Image icon = new ImageIcon(prefix+(PaintCount+1) + suffix).getImage();
		if(!isHooked) {
			//PaintCount+=Speed/31+1;
			//PaintCount=PaintCount%16;
			PaintCount+=Speed/62+1;
			PaintCount=PaintCount%4;
		}
		graph.drawImage(icon, (int)(x-0.8*Radius), (int)(y-Radius), (int)(Radius), (int)(2*Radius), null);
	}
}