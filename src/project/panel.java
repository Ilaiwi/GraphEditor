package project;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class panel extends JPanel {
	private Line lineTemp;
	private Vector nodes;
	private Vector links;
	boolean flag1=false,flag2=false,flag3=false;
	private int nodeNum,newNodeNum;
	private rectAndNum temp1, newRec1;
	private Rectangle temp,newRec;
	public boolean flag=false; 
	 public panel(){
		 this.setBackground(Color.white);
		 //this.addMouseListener(this);
		 //this.addMouseMotionListener(this);
		 //repaint();
	 }
	 public void setFlags(boolean fg1 , boolean fg2,boolean fg3){
		 flag1=fg1;
		 flag2=fg2;
		 flag3=fg3;
		// if(flag1&&flag2){
			// System.out.println("flag 1 flag 2");
		// }
		 repaint();
	 }
	 public void setRectAndNum(rectAndNum x){
		 temp1=x;
		 //repaint();
		 //if(temp!=null){
			// System.out.println("rect");
		 //}
	 }
	 public void setVector(Vector x){
		 nodes=x;
	 }
	 public void setLinks(Vector x){
		 links=x;
	 }
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.black);
		if(flag1&&flag2){
			//System.out.println("inside paint");
			temp=temp1.getRect();
			nodeNum=temp1.getNum();
			g.setColor(Color.red);
			g.drawRect(temp.x,temp.y,temp.height,temp.width);
			g.setColor(Color.black);
			g.drawString(""+nodeNum, temp.x+9,temp.y+14);
		}
		if(nodes!=null){
		for (int i=0 ; i<nodes.size();i++){
			newRec1=(rectAndNum) nodes.get(i);
			//System.out.println(i);
			newRec=newRec1.getRect();
			newNodeNum=newRec1.getNum();
			g.setColor(Color.yellow);
			g.fillRect(newRec.x, newRec.y, newRec.width, newRec.height);
			g.setColor(Color.red);
			g.drawRect(newRec.x, newRec.y, newRec.width, newRec.height);
			g.setColor(Color.black);
			g.drawString(""+newNodeNum, newRec.x+9,newRec.y+14);
	}	}
		if(links!=null){
			for (int i=0 ; i<links.size();i++){
				lineTemp=(Line)links.get(i);
				g.setColor(Color.blue);
				g.drawLine(lineTemp.x1, lineTemp.y1, lineTemp.x2, lineTemp.y2);
				}
			}
	}}

	