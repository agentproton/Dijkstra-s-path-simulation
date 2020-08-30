package first.java;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import first.java.question1.edge;

import java.io.*;

import java.awt.TextField;
import java.awt.event.*;
import java.util.*;  

public class question1 implements ActionListener {
	 class edge{
		int weight;
		String fromvertex;
		String tovertex;
		edge(String a,String b,int c){
			fromvertex=a;
			tovertex=b;
			weight=c;
		}
		String getfrom() { return fromvertex;}
		String getto() { return tovertex;}
	}
	 class node{
		int xcord,ycord;
		String name;
		node(String a,int b,int c){
			name=a;
			xcord=b;
			ycord=c;
		}
		String getname() { return name;}
	}
	class sortbyname implements Comparator<node>{
		@Override
		public int compare(node o1, node o2) {
			// TODO Auto-generated method stub
			return o1.getname().compareTo(o2.getname());
		}
		
	}
	class sortbyedge implements Comparator<edge>{

		@Override
		public int compare(edge o1, edge o2) {
			// TODO Auto-generated method stub
			if(o1.getfrom().compareTo(o2.getfrom())>0)return 1;
			else if(o1.getfrom().compareTo(o2.getfrom())<0)  return -1;
			else {
				return o1.getto().compareTo(o2.getto());
			}
		}
		
	}
	JLabel jl1;
	ArrayList<edge> edgelist=new ArrayList<edge>();
    HashMap<String,ArrayList<edge>>hm=new HashMap<String,ArrayList<edge>>();
     HashSet<String>hs=new HashSet<String>();
    ArrayList<node> arraylist;
    JFrame jf;
     JButton addvertex,searchvertex,deletevertex,modifyvertex,addedge,searchedge,deleteedge,modifyedge,importfile,exportgraph,printpath,screenint;
    question1(){
    	 jf=new JFrame("GRAPH REPRESENTATION");
    	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	addvertex=new JButton("AddVertex");
    	searchvertex=new JButton("SEARCHvertex");
    	deletevertex=new JButton("DELETEvertex");
    	modifyvertex=new JButton("MODIFYvertex");
    	addedge =new JButton("ADDEdge");
    	deleteedge=new JButton("DELETEEdge");
    	modifyedge=new JButton("MODIFYedge");
    	searchedge=new JButton("SEARCHEdge");
    	importfile=new JButton("IMPORT FILE");
    	exportgraph=new JButton("EXPORT_GRAPH");
    	printpath=new JButton("Print path");
    	screenint=new JButton("Interact on screen");
    	printpath.setBounds(300,200,150,100);
    	importfile.setBounds(0,200,150,100);
    	exportgraph.setBounds(150,200,150,100);
    	addvertex.setBounds(0,0,150,100);
    	searchvertex.setBounds(150,0,150,100);
    	deletevertex.setBounds(300,0,150,100);
    	modifyvertex.setBounds(450,0,150,100);
    	addedge.setBounds(0,100,150,100);
    	searchedge.setBounds(150,100,150,100);
    	deleteedge.setBounds(300,100,150,100);
    	modifyedge.setBounds(450,100,150,100);
    	screenint.setBounds(0,300,150,150);
    	addvertex.addActionListener(this);
    	searchvertex.addActionListener(this);
    	deletevertex.addActionListener(this);
    	modifyvertex.addActionListener(this);
    	addedge.addActionListener(this);
    	searchedge.addActionListener(this);
    	deleteedge.addActionListener(this);
    	modifyedge.addActionListener(this);
    	importfile.addActionListener(this);
    	exportgraph.addActionListener(this);
    	printpath.addActionListener(this);
    	screenint.addActionListener(this);
    	jf.setSize(600,600);
    	jf.add(addvertex);
    	jf.add(searchvertex);
    	jf.add(deletevertex);
    	jf.add(modifyvertex);
    	jf.add(addedge);
    	jf.add(searchedge);
    	jf.add(deleteedge);
    	jf.add(modifyedge);
    	jf.add(importfile);
    	jf.add(exportgraph);
    	jf.add(printpath);
    	jf.add(screenint);
    	jf.setLayout(null);
    	jf.setVisible(true);
    	arraylist=new ArrayList<node>();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	if(e.getSource()==addvertex) {
		try {
		 String name=JOptionPane.showInputDialog(jf,"Enter name");
		 String a=JOptionPane.showInputDialog(jf,"Enter x co-ordinate");
		 String b=JOptionPane.showInputDialog(jf,"Enter y-co-ordinate");
		 try {
			 Integer.parseInt(a);
		    Integer.parseInt(b);
		    int c= Integer.parseInt(a);
		    int d=  Integer.parseInt(b);
		    node n1=new node(name,c,d);
		     hs.add(name);
		     //j1.setText(hs.size());
		     arraylist.add(n1);
			 
		 }catch(NumberFormatException e1) {
			 JOptionPane.showMessageDialog(jf,"Input mismatch,try again");  
		 }
		}
		catch(Exception e1){
   		 JOptionPane.showMessageDialog(jf,e1,"Alert",JOptionPane.WARNING_MESSAGE);
   	}
	     
	    }

	if(e.getSource()==searchvertex) {
		try {
		 String nam=JOptionPane.showInputDialog(jf,"Enter name");
		 int fl=0;
			// JOptionPane.showMessageDialog(jf,"the x co-ordinate is");
		 for(node ss:arraylist) {
				 //JOptionPane.showMessageDialog(jf,"the x co-ordinate is"+ss.xcord+"\n"+"the y co-ordiante"+ss.ycord);
			 if(ss.name.compareTo(nam)==0) {
					 fl=1;
					 JOptionPane.showMessageDialog(jf,"the x co-ordinate is- "+ss.xcord+"\n"+"the y co-ordiante- "+ss.ycord);
					 break;
				 }
			 }
			 if(fl==0) {
				 JOptionPane.showMessageDialog(jf,"Vertex Not Found","Alert",JOptionPane.WARNING_MESSAGE);
			 }
		}
		catch(Exception e1){
   		 JOptionPane.showMessageDialog(jf,e1,"Alert",JOptionPane.WARNING_MESSAGE);
   	}
		}
		if(e.getSource()==deletevertex) {
			String nam=JOptionPane.showInputDialog("Enter name");
			int fl=0;
			for(node ss:arraylist) {
				 if(ss.name.compareTo(nam)==0) {
					  arraylist.remove(ss);
					  fl=1;
					  JOptionPane.showMessageDialog(jf,"Successfully Deleted","Alert",JOptionPane.WARNING_MESSAGE);
					 break;
				 }
			 }
			if(fl==1) {
				Iterator<edge>it=edgelist.iterator();
				while(it.hasNext()==true) {
					edge ee= it.next();
					if(ee.fromvertex.compareTo(nam)==0||ee.tovertex.compareTo(nam)==0)
						it.remove();
				}
				ArrayList<edge>arr=null;
				arr=null;
				hm.put(nam,arr);
				for(String ss:hs) {
					arr=hm.get(ss);
					if(arr==null)
						continue;
					ArrayList<edge>ap=null;
					ap=arr;
					for(edge ee: arr) {
						if(ee.tovertex.compareTo(nam)==0)
							ap.remove(ee);
					}
					arr=ap;
					hm.put(ss,arr);
				}
				hs.remove(nam);}
			if(fl==0) {
				  JOptionPane.showMessageDialog(jf,"Vertex Not Found","Alert",JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource()==modifyvertex) {
			try {
			String nam=JOptionPane.showInputDialog("Enter name");
			 String a=JOptionPane.showInputDialog(jf,"Enter new x co-ordinate");
			 String b=JOptionPane.showInputDialog(jf,"Enter new y-co-ordinate");
			 try {
			 Integer.parseInt(a);
			 Integer.parseInt(b);
			 int c=Integer.parseInt(a);
			 int d=Integer.parseInt(b);
			 int fl=0;
			 for(node ss:arraylist) {
					
				 if(ss.name.compareTo(nam)==0) {
					  ss.xcord=c;
					  ss.ycord=d;
					  fl=1;
					  JOptionPane.showMessageDialog(jf,"Successfully updated","Alert",JOptionPane.WARNING_MESSAGE);
					 break;
				 }
			 }
			 if(fl==0) {
				  JOptionPane.showMessageDialog(jf,"Vertex Not Found","Alert",JOptionPane.WARNING_MESSAGE);
			}}
			 catch(NumberFormatException e1) {
				 JOptionPane.showMessageDialog(jf,"Input mismatch,try again");  
			 }
			}
			catch(Exception e1){
       		 JOptionPane.showMessageDialog(jf,e1,"Alert",JOptionPane.WARNING_MESSAGE);
       	}
		}
		if(e.getSource()==addedge) {
			try {
			 String name=JOptionPane.showInputDialog(jf,"Enter SOURCE Vertex");
			 String a=JOptionPane.showInputDialog(jf,"Enter DESTINATION vertex ");
			 String b=JOptionPane.showInputDialog(jf,"Enter weight of the path");
			 try {
			 Integer.parseInt(b);
			 int c=Integer.parseInt(b);
			 edge e1=new edge(name,a,c);
			 ArrayList<edge>arr;
			 if(hm.get(name)==null) {
				 arr=new ArrayList<edge>();
				 arr.add(e1);
				 hm.put(name,arr);
			 }
			 else {
				 arr=hm.get(name);
				 arr.add(e1);
				 hm.put(name,arr);
			 }
			 edgelist.add(e1);}
			 catch(NumberFormatException e1) {
				 JOptionPane.showMessageDialog(jf,"Input mismatch,try again");  
			 }
			}
			catch(Exception e1){
       		 JOptionPane.showMessageDialog(jf,e1,"Alert",JOptionPane.WARNING_MESSAGE);
       	}
			 
		}
		if(e.getSource()==searchedge) {
			try {
			 String name=JOptionPane.showInputDialog(jf,"Enter SOURCE Vertex");
			 String a=JOptionPane.showInputDialog(jf,"Enter DESTINATION vertex ");
			 int fl=0;
			 for(edge ee: edgelist) {
				 if(ee.fromvertex.compareTo(name)==0&&ee.tovertex.compareTo(a)==0) {
					 fl=1;
					 JOptionPane.showMessageDialog(jf,"The edge is present between "+ee.fromvertex+" and "+ee.tovertex+" and the weight of the path is "+ee.weight);
					 break;
				 }
			 }
			 if(fl==0) {
				 JOptionPane.showMessageDialog(jf,"Edge Not Found","Alert",JOptionPane.WARNING_MESSAGE);
			 }
			}
			catch(Exception e1){
       		 JOptionPane.showMessageDialog(jf,e1,"Alert",JOptionPane.WARNING_MESSAGE);
       	}
		}
		if(e.getSource()==deleteedge) {
			 String name=JOptionPane.showInputDialog(jf,"Enter SOURCE Vertex");
			 String a=JOptionPane.showInputDialog(jf,"Enter DESTINATION vertex ");
			 int fl=0;
			 for(edge ee: edgelist) {
				 if(ee.fromvertex.compareTo(name)==0&&ee.tovertex.compareTo(a)==0) {
					 edgelist.remove(ee);
					 ArrayList<edge>ar1=new ArrayList<edge>();
					
					 ar1=hm.get(name);
					 for(edge es:ar1) {
						 if(es.getto().compareTo(a)==0)
						 {
							ar1.remove(es);
							fl=1;
							 hm.put(name,ar1);
							 JOptionPane.showMessageDialog(jf,"Successfully Deleted","Alert",JOptionPane.WARNING_MESSAGE);
							 break;
						 }
					 }
					 if(fl==1)
						 break;
				 }
			 }
			if(fl==0) {
				  JOptionPane.showMessageDialog(jf,"edge Not Found","Alert",JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource()==modifyedge) {
			try {
			 String name=JOptionPane.showInputDialog(jf,"Enter SOURCE Vertex");
			 String a=JOptionPane.showInputDialog(jf,"Enter DESTINATION vertex ");
			 String c=JOptionPane.showInputDialog(jf,"Enter new weight");
			 Integer.parseInt(c);
			 int d=Integer.parseInt(c);
			 int fl=0;
			 for(edge ee: edgelist) {
				 if(ee.fromvertex.compareTo(name)==0&&ee.tovertex.compareTo(a)==0) {
					 ee.weight=d;
					 ArrayList<edge>ar1=new ArrayList<edge>();
					 ar1=hm.get(name);
					 for(edge es:ar1) {
						 if(es.getto().compareTo(a)==0)
						 {
							es.weight=d;
							 hm.put(name,ar1);
							 fl=1;
							 JOptionPane.showMessageDialog(jf,"Successfully Updated","Alert",JOptionPane.WARNING_MESSAGE);
							 break;
						 }
					 }
					if(fl==1)
						break;
				 }
			 }
			 if(fl==0) {
				 JOptionPane.showMessageDialog(jf,"edge Not Found","Alert",JOptionPane.WARNING_MESSAGE);
			 }
			 }
			 catch(Exception e1) {
				 JOptionPane.showMessageDialog(jf,"Input mismatch,try again");  
			 }
			 
		}
		if(e.getSource()==importfile) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int value = fileChooser.showOpenDialog(null);
            if (value == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Scanner sc = null;
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
            int totaledge,totalvertex;
            totalvertex = sc.nextInt();
            while(totalvertex>0) {
            	totalvertex--;
            	String name;
            	int a,b;
            	try {
            	name=sc.next();
            	a= sc.nextInt();
            	b= sc.nextInt();
            	node n1=new node(name,a,b);
            	hs.add(name);
            	arraylist.add(n1);}
            	catch(Exception e1){
           		 JOptionPane.showMessageDialog(jf,e1,"Alert",JOptionPane.WARNING_MESSAGE);
           	}
            }
            try {
            totaledge=sc.nextInt();
            while(totaledge>0) {
            	totaledge--;
            	String name,a;
            	int b;
            	try {
            	name=sc.next();
            	a= sc.next();
            	b= sc.nextInt();
            	edge n1=new edge(name,a,b);
            	 ArrayList<edge>arr;
            	 if(hm.get(name)==null) {
    				 arr=new ArrayList<edge>();
    				 arr.add(n1);
    				 hm.put(name,arr);
    			 }
    			 else {
    				 arr=hm.get(name);
    				 arr.add(n1);
    				 hm.put(name,arr);
    			 }
            	edgelist.add(n1);}
            	catch(Exception e1){
           		 JOptionPane.showMessageDialog(jf,e1,"Alert",JOptionPane.WARNING_MESSAGE);
           	}
            }
            }
        	catch(Exception e1){
       		 JOptionPane.showMessageDialog(jf,e1,"Alert",JOptionPane.WARNING_MESSAGE);
       	}
            
            }
            
		}
		if(e.getSource()==exportgraph) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int value = fileChooser.showOpenDialog(null);
            if (value == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
		           FileWriter fw=new FileWriter(file);  
		           PrintWriter pw = new PrintWriter(fw);
		           sortbyname s1=new sortbyname();
		           sortbyedge e1=new sortbyedge();
		           Collections.sort(arraylist,s1);
		           Collections.sort(edgelist,e1);
		           pw.printf("%d\r\n", arraylist.size());
		           for(node nn:arraylist) {
		        	   pw.printf("%s %d %d\r\n",nn.name,nn.xcord,nn.ycord );
		           }
		           pw.printf("%d\r\n", edgelist.size());
		           for(edge ee:edgelist) {
		        	   pw.printf("%s %s %d\r\n",ee.fromvertex,ee.tovertex,ee.weight);
		           }
		           fw.close();    
		          }catch(Exception e1){System.out.println(e1);}     
	    	}
		
    	}
	 if(e.getSource()==printpath) {
		 String name=JOptionPane.showInputDialog(jf,"Enter SOURCE Vertex");
		 String a=JOptionPane.showInputDialog(jf,"Enter DESTINATION vertex ");
		 if(name.compareTo(a)==0) {
			 JOptionPane.showMessageDialog(jf,"Path exists\n"+name);
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
                 if(hm.get(src)!=null)
        		  p = hm.get(src);
        		 for(edge ee:p) {
        			  String s1=ee.getto();
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
        		 JOptionPane.showMessageDialog(jf,"No path exists","Alert",JOptionPane.WARNING_MESSAGE);
        	}
        	else {
        		String pq="";
        		for(int i=st.size()-1;i>=0;--i) {
        			pq+=st.get(i);
        			pq+=" ";
        		}
        		 JOptionPane.showMessageDialog(jf,"Path exists\n"+pq);
        	}
	 }}
	 if(e.getSource()==screenint) {
		 JFrame frame = new JFrame();
          frame.add(new question3());
          frame.setSize(600, 600);
          frame.setVisible(true);
	 }
	}
	public static void main(String[] args) {
		new question1();
	}
}
