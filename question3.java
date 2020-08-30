package first.java;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import javax.swing.*;

import first.java.question1.edge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class question3 extends JPanel implements MouseListener,MouseMotionListener,ActionListener{
     ArrayList<Point> points;
     class edge{
    	 Point p1;
    	 int weight;
    	 edge(Point p,int a){
    		 p1=p;
    		 weight=a;
    	 }
     }
     HashMap<String,Point>mp=new HashMap<String,Point>();
     HashMap<String,ArrayList<edge>> hm1=new HashMap<String,ArrayList<edge>>();
     HashMap<Point,String> pm=new   HashMap<Point,String>();
     HashSet<String>hs=new HashSet<String>();
     ArrayList<String>sh=new ArrayList<String>();
     JTextField j1;
     JButton jb,jb1,jb2,printpath,deledg,modifyedge;
     int vertex=0,edg=0,delete=0,print=0,edgedelete=0,pathp=0,modify=0;
     HashMap<Point,ArrayList<Point>>hm=new   HashMap<Point,ArrayList<Point>>();
     int sxpoint,sypoint,expoint,eypoint;
     static JFrame frame;
    question3() {
        points = new ArrayList<Point>();
        setBackground(Color.WHITE);
        jb=new JButton("Add vertex");
        jb1=new JButton("Add Edge");
        jb2=new JButton("Delete Vertex");
        deledg=new JButton("Delete edge");
        printpath=new JButton("Print Path");
        modifyedge=new JButton("Modify edge");
        jb.setBounds(0, 0, 100,50);
        jb1.setBounds(100,0,100,50);
        jb2.setBounds(200,0,200,50);
        printpath.setBounds(400,0,100,50);
        deledg.setBounds(500,0,100,50);
        modifyedge.setBounds(600,0,200,50);
        jb.addActionListener(this);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        printpath.addActionListener(this);
        deledg.addActionListener(this);
        modifyedge.addActionListener(this);
        addMouseListener(this);
        add(jb);
        add(jb1);
        add(jb2);
        add(printpath);
        add(deledg);
        add(modifyedge);
    	setLayout(null);
      
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
      //  Graphics2D g1 = (Graphics2D) g;
        g2.setColor(Color.red);
      //  g1.setColor(Color.BLACK);
        for (Point point : points) {
        	g2.drawString(pm.get(point),point.x,point.y+30);
            g2.fillOval(point.x, point.y, 20, 20);
        }
        g2.setColor(Color.BLUE);
        for(Point p: points) {
     	   ArrayList<Point>pp;
     	   pp=hm.get(p);
     	   if(pp==null)
     		   continue;
     	   else {
     		   for(Point pq:pp) {
     			   int a=(int)p.getX();
     			   int b=(int)p.getY();
     			   int c=(int)pq.getX();
     			   int d=(int)pq.getY();
     			  int k=(a+c)/2;
     			  int h=(b+d)/2;
     			  Line2D l=new Line2D.Float(p, pq);
     			 g2.setStroke(new BasicStroke(3));
     			  g2.draw(l);
     			  ArrayList<edge>ww;
     			  int qw = 0;
     			  ww=hm1.get(pm.get(p));
     			  for(edge ee:ww) {
     				  if(ee.p1.equals(pq)==true) {
     					  qw=ee.weight;
     				  }
     			  }
     			  String str=Integer.toString(qw);
     			  g2.drawString(str,k,h+15);
     		   }
     	   }
        }
        if(pathp==1) {
        	g2.setColor(Color.green);
        	for(int i=sh.size()-1;i>0;--i) {
        		Point p1= mp.get(sh.get(i));
        		Point p2=mp.get(sh.get(i-1));
        		 Line2D l=new Line2D.Float(p1, p2);
     			 g2.setStroke(new BasicStroke(3));
     			  g2.draw(l);
        	}
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame();
                frame.add(new question3());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 800);
                frame.setVisible(true);
            }
    });
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		
		 int  x=e.getX();
         int  y=e.getY();
         Point p1=new Point(x,y);
         int  fl=0;
         for(Point p: points) {
         	if(x<(p.getX()+30)&&x>(p.getX()-30)&&y<(p.getY()+30)&&y>(p.getY()-30)) {
         		fl=1;
         		  int a=JOptionPane.showConfirmDialog(frame,"Do you want to delete?");  
         		  if(a==JOptionPane.YES_OPTION&&delete==1){  
         			 for(Point ps: points) {
         		     	   ArrayList<Point>pp;
         		     	   pp=hm.get(ps);
         		     	   if(pp==null)
         		     		   continue;
         		     	   else {
         		     		   for(@SuppressWarnings("unused") Point pq:pp) {
         		     			   if(pq.equals(p)) {
         		     				   pp.remove(pq);
         		     			   }
         		     		   }
         		     	   }
         		        }
         			  points.remove(p);
         			  repaint();
         		  }  
         		  break;
         	}		
         }
         if(fl==0&&vertex==1) {
         points.add(p1);
         String name=JOptionPane.showInputDialog(frame,"Enter Name");
         if(hs.contains(name)==false) {
         hs.add(name);
         mp.put(name,p1);
         pm.put(p1,name);
         repaint();}
         }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb) {
			vertex=1;
			edg=0;
			delete=0;
			edgedelete=0;
			pathp=0;
			modify=0;
		}
		if(e.getSource()==jb1) {
			vertex=0;
			edg=1;
			delete=0;
			edgedelete=0;
			pathp=0;
			modify=0;
		}
		if(e.getSource()==jb2) {
			vertex=0;
			edg=0;
			delete=1;
			edgedelete=0;
			pathp=0;
			modify=0;
		}
		if(e.getSource()==deledg) {
			vertex=0;
			edg=0;
			delete=0;
			edgedelete=1;
			pathp=0;
			modify=0;
		}
		if(e.getSource()==modifyedge) {
			vertex=0;
			edg=0;
			delete=0;
			edgedelete=0;
			pathp=0;
			modify=1;
		}
		if(e.getSource()==printpath) {
			pathp=1;
			String name=JOptionPane.showInputDialog(frame,"Enter SOURCE Vertex");
			 String a=JOptionPane.showInputDialog(frame,"Enter DESTINATION vertex ");
			 if(name.compareTo(a)==0) {
				 JOptionPane.showMessageDialog(frame,"Path exists\n"+name);
			 }
			 else {
			 HashMap<String,Integer> dmax=new HashMap<String,Integer>();
	         HashMap<String,Integer> vis=new HashMap<String,Integer>();
	         HashMap<String,String> par1=new HashMap<String,String>();
	        	 for(String ss: hs){
	                 dmax.put(ss,100000000);
	                 vis.put(ss,0);
	                 par1.put(ss,null);
	             }
	        	 vis.put(name,1);
	        	 dmax.put(name,0);
	        	 String src=name;
	        	 for( @SuppressWarnings("unused") String ss:hs) {
	        		 ArrayList<edge> p = null;
	                 if(hm1.get(src)!=null)
	        		  p = hm1.get(src);
	        		 for(edge ee:p) {
	        			  String s1=pm.get(ee.p1);
	                      if(dmax.get(s1)>(dmax.get(src)+ee.weight)){
	                          int d=dmax.get(src)+ee.weight;
	                          dmax.put(s1,d);
	                          par1.put(s1,src);
	                      }
	        		 }
	        		 int mn=100000000;
	                 for(String sq: hs){
	                     if(mn>dmax.get(sq)&&vis.get(sq)!=1){
	                         mn=dmax.get(sq);
	                         src=sq;
	                     }
	                 }
	                 vis.put(src,1);
	        	 }
	        	 String sw=a;
	        	 int fl=0;
	        	 ArrayList<String>st=new ArrayList<String>();
	        	while(sw!=null) {
	        		if(sw.compareTo(name)==0)
	        			fl=1;
	        		st.add(sw);
	        		sw=par1.get(sw);
	        	}
	        	if(fl==0) {
	        		 JOptionPane.showMessageDialog(frame,"No path exists","Alert",JOptionPane.WARNING_MESSAGE);
	        	}
	        	else {
	        		sh=st;
	        		String pq="";
	        		for(int i=st.size()-1;i>=0;--i) {
	        			pq+=st.get(i);
	        			pq+=" ";
	        			
	        		}
	        		 JOptionPane.showMessageDialog(frame,"Path exists\n"+pq);
	        		 repaint();
	        	}
	        	
		 }
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		 int  x=e.getX();
         int  y=e.getY();
         Point p1=new Point(x,y);
         int  fl=0;
         for(Point p: points) {
         	if(x<(p.getX()+30)&&x>(p.getX()-30)&&y<(p.getY()+30)&&y>(p.getY()-30)) {
                    expoint=p.x;
                    eypoint=p.y;
                    break;
         		  }  
         		 
         	}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		 int  x=e.getX();
         int  y=e.getY();
         Point p1=new Point(x,y);
         int  fl=0;
         for(Point p: points) {
         	if(x<(p.getX()+30)&&x>(p.getX()-30)&&y<(p.getY()+30)&&y>(p.getY()-30)) {
                    sxpoint=(int) p.getX();
                    sypoint=(int) p.getY();
                    break;
         		  }  
         		 
         	}		
         }

	@Override
	public void mouseReleased(MouseEvent e) {
		if(edg==1) {
		 int  x=e.getX();
         int  y=e.getY();
         Point p1=new Point(x,y);
         int  fl=0;
         for(Point p: points) {
         	if(x<(p.getX()+30)&&x>(p.getX()-30)&&y<(p.getY()+30)&&y>(p.getY()-30)) {
         		  expoint=(int) p.getX();
                  eypoint=(int) p.getY();
                    break;
         		  }  
         		 
         	}
         Point p=new Point(sxpoint,sypoint);
		   Point p11=new Point(expoint,eypoint);
		   ArrayList<Point> ar;
		   ArrayList<Point>ar1;
		   ar=hm.get(p);
		   ar1=hm.get(p11);
		   String name=null;
		   if(ar==null) {
			   ar=new ArrayList<Point>();
			   if(p.equals(p11)==false) {
			   name=JOptionPane.showInputDialog(frame,"Enter weight");   
			   int cd=Integer.parseInt(name);
			   String ss=pm.get(p);
			   ArrayList<edge>arr;
			   arr=hm1.get(ss);
			   if(arr==null) {
				   arr=new ArrayList<edge>();
				   edge e1=new edge(p11,cd);
				   arr.add(e1);
				   hm1.put(ss,arr);
			   }
			   else {
				   edge e1=new edge(p11,cd);
				   arr.add(e1);
				   hm1.put(ss,arr);
			   }
			   ar.add(p11);
			   hm.put(p,ar);}
		   }
		   else {
			   if(ar.contains(p11)==false&&p.equals(p11)==false) {
			   ar.add(p11);
			   hm.put(p,ar);
			    name=JOptionPane.showInputDialog(frame,"Enter weight");   
			   int cd=Integer.parseInt(name);
			   String ss=pm.get(p);
			   ArrayList<edge>arr;
			   arr=hm1.get(ss);
			   if(arr==null) {
				   arr=new ArrayList<edge>();
				   edge e1=new edge(p11,cd);
				   arr.add(e1);
				   hm1.put(ss,arr);
			   }
			   else {
				   edge e1=new edge(p11,cd);
				   arr.add(e1);
				   hm1.put(ss,arr);
			   }
			   }
		   }
			repaint();}
		if(edgedelete==1) {
			 int  x=e.getX();
	         int  y=e.getY();
	         Point p1=new Point(x,y);
	         int  fl=0;
	         for(Point p: points) {
	         	if(x<(p.getX()+30)&&x>(p.getX()-30)&&y<(p.getY()+30)&&y>(p.getY()-30)) {
	         		  expoint=(int) p.getX();
	                  eypoint=(int) p.getY();
	                    break;
	         		  }  
	         		 
	         	}
	         Point p=new Point(sxpoint,sypoint);
			 Point p11=new Point(expoint,eypoint);
		     	   ArrayList<Point>pp;
		     	   pp=hm.get(p);
		     		   for(Point pq:pp) {
		     			   if(pq.equals(p11)==true) {
		     				   pp.remove(pq);
		     			   }
		     		   }
		     		   hm.put(p,pp);
		     		   repaint();
		}
		if(modify==1) {
			 int  x=e.getX();
	         int  y=e.getY();
	         
	         Point p1=new Point(x,y);
	         int  fl=0;
	         for(Point p: points) {
	         	if(x<(p.getX()+30)&&x>(p.getX()-30)&&y<(p.getY()+30)&&y>(p.getY()-30)) {
	         		  expoint=(int) p.getX();
	                  eypoint=(int) p.getY();
	                    break;
	         		  }  
	         		 
	         	}
	         Point p=new Point(sxpoint,sypoint);
			 Point p11=new Point(expoint,eypoint);
			 Point aq = null;
			 int fl1=0;
		     	   ArrayList<Point>pp;
		     	   pp=hm.get(p);
		     		   for(Point pq:pp) {
		     			   if(pq.equals(p11)==true) {
		     				   aq=pq;
		     				   fl1=1;
		     				   break;
		     			   }
		     		   }
		     		   if(fl1==1) {
		        String name;
		       String src=pm.get(p);
		       ArrayList <edge>wr;
		       wr=hm1.get(src);
		       for(edge ee:wr) {
		    	   if(ee.p1.equals(aq)) {
		    		   name=JOptionPane.showInputDialog(frame,"Enter new weight");
		    		  int a=Integer.parseInt(name);
		    		  ee.weight=a;
		    		  break;
		    	   }
		       }
		       hm1.put(src,wr);
		       repaint();
		     		   }
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}