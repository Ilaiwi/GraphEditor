package project;

import java.awt.Point;
import java.awt.Rectangle;


public class rectAndNum {
	private Rectangle rect;
	private int num;
	public Point top , bottom,mid;
	public rectAndNum(Rectangle x , int y){
		rect=x;
		num=y;
		top=new Point(rect.x+10,rect.y);
		bottom=new Point(rect.x+10,rect.y+20);
		mid=new Point(rect.x+10,rect.y+10);
	}
	public void setRectangle(Rectangle x){
		rect=x;
		top=new Point(rect.x+10,rect.y);
		bottom=new Point(rect.x+10,rect.y+20);
	}
	public void setNum(int x){
		num=x;
	}
	public Rectangle getRect(){
		return rect;
	}
	public int getNum(){
		return num;
	}
	public void setPoints(){
		top=new Point(rect.x+10,rect.y);
		bottom=new Point(rect.x+10,rect.y+20);
		mid=new Point(rect.x+10,rect.y+10);

	}

}
