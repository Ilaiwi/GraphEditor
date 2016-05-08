package project;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class GUI extends JFrame implements ActionListener, MouseListener , MouseMotionListener{
	private int nodeNum,from,to,toDelete,orgTemp;
	private Integer numTemp;
	//Map<String, JButton> buttonMap = new HashMap<String, JButton>();
	private Vector links;
	private Vector nodes;
	private Vector pair;
	private String num;
	private link linkTemp,pairTemp;
	private Point p,drag;
	private rectAndNum temp1,fromTemp,toTemp,dragNode,dragTemp,isTemp;
	private Rectangle temp;
	private boolean flag1=false,flag2=false,flag3=false;
	private panel paintPanel;
	private JPanel buttons;
	private JButton newNode , newLink , delete;
	public GUI(){
		super("Graph Editor");
		paintPanel=new panel();
		newNode = new JButton("Add New Node");
		newLink = new JButton("Add New Link");
		delete = new JButton("Delete Node");
		pair=new Vector();
		add(paintPanel);
		buttons = new JPanel();
		buttons.add(newNode);
		buttons.add(newLink);
		buttons.add(delete);
		nodes= new Vector();
		links=new Vector();
		add(buttons,BorderLayout.SOUTH);
		newNode.addActionListener(this);
		newLink.addActionListener(this);
		delete.addActionListener(this);
		paintPanel.addMouseListener(this);
		paintPanel.addMouseMotionListener(this);

	}
	public static void main(String args[]){
		GUI fr = new GUI();
		fr.setVisible(true);
		fr.setSize(500, 500);
		fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public boolean isNode(int x){
		if(nodes!=null){
		for(int i=0;i<nodes.size();i++){
			isTemp=(rectAndNum)nodes.get(i);
			if(isTemp.getNum()==x){
				return true;
			}
		}
		}
		else{
			return false;
		}
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==newNode){
			num=JOptionPane.showInputDialog(this, "Enter the node number");
			if(num==null){
				return;
			}
			try{
			nodeNum=Integer.parseInt(num);
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(this, "is not an Integer number", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			num=null;
			while(isNode(nodeNum)){
				JOptionPane.showMessageDialog(this, "this node already exist", "Error", JOptionPane.ERROR_MESSAGE);
				num=JOptionPane.showInputDialog(this, "Enter another node number");
				if(num==null){
					return;
				}
				try{
					nodeNum=Integer.parseInt(num);
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(this, "is not an Integer number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

				num=null;
		}
		flag1=true;
		//flag2=true;
		//paintPanel.setFlags(true,true,true);
		paintPanel.repaint();
		}
		if(arg0.getSource()==newLink){
			num=JOptionPane.showInputDialog(this, "Enter first node");
			if(num==null){
				return;
			}
			try{
				from=Integer.parseInt(num);
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(this, "is not an Integer number", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

			//from=Integer.parseInt(num);
			num=null;
			while(!isNode(from)){
				JOptionPane.showMessageDialog(this, "this node does not exist", "Error", JOptionPane.ERROR_MESSAGE);
				num=JOptionPane.showInputDialog(this, "Enter another node number");
				if(num==null){
					return;
				}
				try{
					from=Integer.parseInt(num);
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(this, "is not an Integer number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				//from=Integer.parseInt(num);
				num=null;
		}
			num=JOptionPane.showInputDialog(this, "Enter second node");
			if(num==null){
				return;
			}
			try{
				to=Integer.parseInt(num);
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(this, "is not an Integer number", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
		//	to=Integer.parseInt(num);
			while(!isNode(to)){
				JOptionPane.showMessageDialog(this, "this node does not exist", "Error", JOptionPane.ERROR_MESSAGE);
				num=JOptionPane.showInputDialog(this, "Enter another node number");
				if(num==null){
					return;
				}
				try{
					to=Integer.parseInt(num);
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(this, "is not an Integer number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				//to=Integer.parseInt(num);
				num=null;
		}

			pair.add(new link(from,to));
			links=creatLinks(pair);
			//paintPanel.setLinks(links);
			//paintPanel.repaint();
		}
		if(arg0.getSource()==delete){
			num=JOptionPane.showInputDialog(this, "Enter the number node to delete");
			if(num==null){
				return;
			}
			try{
				toDelete=Integer.parseInt(num);
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(this, "is not an Integer number", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			//toDelete=Integer.parseInt(num);
			num=null;
			while(!isNode(toDelete)){
				JOptionPane.showMessageDialog(this, "this node does not exist", "Error", JOptionPane.ERROR_MESSAGE);
				num=JOptionPane.showInputDialog(this, "Enter another node number");
				if(num==null){
					return;
				}
				try{
					toDelete=Integer.parseInt(num);
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(this, "is not an Integer number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				//toDelete=Integer.parseInt(num);
				num=null;
		}
			for (int i=0 ; i<nodes.size();i++){
				if(((rectAndNum)nodes.get(i)).getNum()==toDelete){
					nodes.removeElementAt(i);
					
					
									}
							}
			if(pair!=null){
				//System.out.println("here");
				for (int i=0 ; i<pair.size();i++){
					if(((link)pair.get(i)).to==toDelete){
						//System.out.println(((link)pair.get(i)).to);
						pair.removeElementAt(i);
						links=creatLinks(pair);
						//paintPanel.setLinks(links);

						}
			}
				for (int i=0 ; i<pair.size();i++){
					if(((link)pair.get(i)).from==toDelete){
						//System.out.println(((link)pair.get(i)).to);
						pair.removeElementAt(i);
						links=creatLinks(pair);
						//paintPanel.setLinks(links);

					}			}

			
		}}
		links=creatLinks(pair);
		paintPanel.setLinks(links);
		paintPanel.repaint();	
	}
	public Vector creatLinks(Vector x){
		Vector l = new Vector();
		for (int y=0 ; y<x.size();y++){
			linkTemp=(link)x.get(y);
		for (int i=0 ; i<nodes.size();i++){
			if(((rectAndNum)nodes.get(i)).getNum()==linkTemp.from){
				fromTemp=(rectAndNum)nodes.get(i);
			}
			if(((rectAndNum)nodes.get(i)).getNum()==linkTemp.to){
				toTemp=(rectAndNum)nodes.get(i);
			}
		}
		if(Math.abs(fromTemp.mid.y-toTemp.mid.y)<20){
			l.add(new Line(fromTemp.top.x,fromTemp.top.y,toTemp.top.x,toTemp.top.y));
		}
		else{

		if((fromTemp.mid.y)<toTemp.mid.y){
		l.add(new Line(fromTemp.bottom.x,fromTemp.bottom.y,toTemp.top.x,toTemp.top.y));}
		else{
			l.add(new Line(toTemp.bottom.x,toTemp.bottom.y,fromTemp.top.x,fromTemp.top.y));
		}}}
		
		return l;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Point t;
		t=e.getPoint();
		if(dragNode!=null&&numTemp!=null){
			dragNode.getRect().x=t.x;
			dragNode.getRect().y=t.y;
			dragNode.setPoints();
			nodes.set(numTemp, dragNode);
			paintPanel.setVector(nodes);
			links=creatLinks(pair);
			paintPanel.setLinks(links);
			paintPanel.repaint();	
		}
		
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		p=e.getPoint();
		temp=new Rectangle(p.x,p.y,20,20);
		temp1=new rectAndNum(temp,nodeNum);
		paintPanel.setFlags(flag1, flag2,flag3);
		paintPanel.setRectAndNum(temp1);
		paintPanel.repaint();
		//paintPanel.setOpaque(true);
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		flag2=true;
		
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		flag2=false;
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(nodes!=null&&SwingUtilities.isLeftMouseButton(arg0)){
			for (int i=0 ; i<nodes.size();i++){
				drag=arg0.getPoint();
				dragTemp=(rectAndNum)nodes.get(i);
				if(dragTemp.getRect().x<=drag.x&&(dragTemp.getRect().x+20)>drag.x){
					if(dragTemp.getRect().y<=drag.y&&(dragTemp.getRect().y+20)>drag.y){
						dragNode=dragTemp;
						numTemp=i;
					}
				}}
			dragTemp=null;
		}
		else if(arg0.isMetaDown()){
			num=JOptionPane.showInputDialog(this, "Enter the new Number");
			if(num==null){
				return;
			}
			try{
				nodeNum=Integer.parseInt(num);
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(this, "is not an Integer number", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			//nodeNum=Integer.parseInt(num);
			num=null;
			while(isNode(nodeNum)){
				JOptionPane.showMessageDialog(this, "this node already exist", "Error", JOptionPane.ERROR_MESSAGE);
				num=JOptionPane.showInputDialog(this, "Enter another node number");
				if(num==null){
					return;
				}
				try{
					nodeNum=Integer.parseInt(num);
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(this, "is not an Integer number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				//nodeNum=Integer.parseInt(num);
				num=null;
		}
			for (int i=0 ; i<nodes.size();i++){
				drag=arg0.getPoint();
				dragTemp=(rectAndNum)nodes.get(i);
				if(dragTemp.getRect().x<=drag.x&&(dragTemp.getRect().x+20)>drag.x){
					if(dragTemp.getRect().y<=drag.y&&(dragTemp.getRect().y+20)>drag.y){
						dragNode=dragTemp;
						numTemp=dragNode.getNum();
						orgTemp=i;
					}
				}}
			dragNode.setNum(nodeNum);
			nodes.set(orgTemp, dragNode);
			paintPanel.setVector(nodes);
			for(int i=0 ; pair.size()>i;i++){
				pairTemp=((link)pair.get(i));
				pairTemp.flag=false;
				pair.set(i, pairTemp);

			}
			for(int i=0 ; pair.size()>i;i++){
				if(((link)pair.get(i)).to==numTemp&&!(((link)pair.get(i)).flag)){
					pairTemp=((link)pair.get(i));
					pairTemp.to=nodeNum;
					pairTemp.flag=true;
					pair.set(i, pairTemp);
					pairTemp=null;
				}
				if(((link)pair.get(i)).from==numTemp&&!(((link)pair.get(i)).flag)){
					
					pairTemp=((link)pair.get(i));
					pairTemp.from=nodeNum;
					pairTemp.flag=true;
					pair.set(i, pairTemp);
					pairTemp=null;
				}
			}
			links=creatLinks(pair);
			paintPanel.setLinks(links);
			paintPanel.repaint();
			
		}
		if(SwingUtilities.isLeftMouseButton(arg0)){
			flag3=true;
			if(flag1&&flag2){
				nodes.add(temp1);
				paintPanel.setVector(nodes);
				flag1=false;
			}
	
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		dragNode=null;
		numTemp=null;
		flag3=false;
	}

}
