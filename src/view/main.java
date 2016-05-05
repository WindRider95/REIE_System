package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import control.GetNews;
import dao.DAO;

public class main {
	MyFrame mf;
	MyPanel mp;
	JPanel mp1;
	static JPanel mp2;
	JPanel mp3;
	JComboBox jcb;
	JButton jb1;
	JButton jb2;
	static JTable jtable;
	static JTextField jeb1;
	static JTextField jeb2;
	static JTextField jeb3;
	static JTextField jeb4;
	static JScrollPane jsp;
	 static Object[][] newsInfo={{"","","",""}};
	static String oldvalue;
	static Boolean changed=false;
	static String tabletitle=null;
	public static JTextArea jta;
	public static void main(String[] args)
	{EventQueue.invokeLater(new Runnable() {
		public void run() {
		main m=new main();
		m.init();
		}});
	}
	private void init()
	{
		System.out.println("hello");
		try
		 {
		 UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		 }catch(Exception e)
		 {
		 e.printStackTrace();
		 }
		
		mf=new MyFrame(1600,800);
		mf.setResizable(false);
		mp=new MyPanel(1600,800);
		mp.setLayout(null);
		mp.setOpaque(false);
		Image im=Toolkit.getDefaultToolkit().getImage("./src/bg1.jpg");
		im=im.getScaledInstance(1600, 800, Image.SCALE_DEFAULT);
		ImageIcon bg1=new ImageIcon(im);
		JLabel jl=new JLabel(bg1);
		jl.setOpaque(true);
		jl.setBounds(0, 0, 1600, 800);
		jl.setOpaque(false);
		//mf.add(jl);
		mp1=new JPanel();
		
		mp1.setSize(1600,100);
		//this.setLayout(new FlowLayout(FlowLayout.LEFT));
		mp1.setLayout(null);

		jcb=new JComboBox();
		jcb.setEditable(true);
		jcb.setSelectedItem("");
		jcb.addItem("news.163.com");
		jcb.addItem("news.sina.com.cn");
		jcb.setBounds(40, 40, 250, 40);
		mp1.add(jcb);
		jb1=new JButton();	
		jb1.setBounds(320, 40, 150, 40);
		jb1.setText("抽取");
		jb1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("clicked");
				System.out.println(jcb.getSelectedItem().toString());
				if(jcb.getSelectedItem().toString().equals("news.sina.com.cn"))
				{
					System.out.println("yes");
					DAO d=new DAO();
					tabletitle=d.extandcreate();
					String arg[]=new String[1];
					arg[0]=tabletitle;
					GetNews.main(arg);
					GetNews g=new GetNews();
					g.start();
				}
			}});
		jb2=new JButton();
		jb2.setBounds(500, 40, 150, 40);
		jb2.setText("停止");
		jb2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		mp1.add(jb1);
		mp1.add(jb2);
		Font a=new Font("Courier",Font.BOLD,20);
		JLabel jl1=new JLabel("标题:");
		jl1.setFont(a);
		jl1.setBounds(700, 40, 60, 40);
		jeb1=new JTextField();
		jeb1.setBounds(750, 45, 150, 30);
		JLabel jl2=new JLabel("日期从:");
		jl2.setFont(a);
		jl2.setBounds(900, 40, 90, 40);
		JLabel jl3=new JLabel("到:");
		jl3.setFont(a);
		jl3.setBounds(1120, 40, 60, 40);
		 jeb2=new JTextField();
		jeb2.setBounds(970, 45, 150, 30);
		jeb3=new JTextField();
		jeb3.setBounds(1150, 45, 150, 30);
		JLabel jl4=new JLabel("来源:");
		jl4.setFont(a);
		jl4.setBounds(1300, 40, 60, 40);
		 jeb4=new JTextField();
		jeb4.setBounds(1350, 45, 150, 30);
		JButton jbs=new JButton("查询");
		jbs.setBounds(1500, 45, 75, 30);
		jbs.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				updateData();
			}});
		mp1.add(jl1);
		mp1.add(jeb1);
		mp1.add(jl2);
		mp1.add(jeb2);
		mp1.add(jl3);
		mp1.add(jeb3);
		mp1.add(jl4);
		mp1.add(jeb4);
		mp1.add(jbs);
		mp2=new JPanel();
		mp2.setBounds(0, 100, 1600, 600);
		
		mp2.setLayout(null);
		//todo: tree init
		DefaultMutableTreeNode tn=new DefaultMutableTreeNode("历史记录");
		DefaultMutableTreeNode tn2=new DefaultMutableTreeNode("news.sina.cn.com");
		tn.add(new DefaultMutableTreeNode("a"));
		tn.add(new DefaultMutableTreeNode("b"));
		tn2.add(new DefaultMutableTreeNode("b"));
		tn.add(tn2);
		JTree jtree=new JTree(tn);
		
		jtree.getSelectionModel().setSelectionMode(

				TreeSelectionModel.SINGLE_TREE_SELECTION);
		jtree.setBounds(20, 20,380,580);
		jtree.setVisible(true);
		
		jtable=inittable();
		 jsp= new JScrollPane(jtable);
		jsp.setBounds(450, 20, 1100, 580);
		jtable.setVisible(true);
		
		mp2.add(jtree);
		mp2.add(jsp);
		mp3=new JPanel();
		mp3.setBounds(0, 700, 1600, 100);
		mp3.setLayout(null);
		jta=new JTextArea();
		 JScrollPane jspa=new JScrollPane(jta);
		jspa.setBounds(13, 0, 1530, 60);
		jspa.setVisible(true);
		jta.setLineWrap(true);
		jta.setEditable(false);
		jta.setVisible(true);
		//jta.setText("fdsafewfw\n\n\n\n\n\njfdsljfsdf");
		jta.setFont(a);
	/*	JScrollPane jspb=new JScrollPane();
		jspb.setBounds(13, 0,1530, 60);
		jspb.add(jta);*/
		mp3.add(jspa);
		//mp3.add(jsb);
		mp.add(mp1);
		mp.add(mp2);
		mp.add(mp3);
		mf.add(mp);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.setVisible(true);
	}
	JTable inittable()
	{
		String[] Names={"标题","时间","来源","网址"};
		DAO d=new DAO();
		List<Map<String,String>> newslist=d.find("","","","","","newsinfo");
		newsInfo=new Object[newslist.size()][4];
		int c=0;
		Iterator i=newslist.iterator();
		while(i.hasNext())
		{
			Map<String,String> m=(Map<String, String>) i.next();
			newsInfo[c][0]=m.get("title");
			newsInfo[c][1]=m.get("time");
			newsInfo[c][2]=m.get("publish");
			newsInfo[c][3]=m.get("link");
			c++;
		}
		if(c==0)
		{newsInfo=new Object[][]{{"","","",""}};}
	    JTable jtb  =new JTable(newsInfo,Names);
	  //  jtb.setEnabled(false);
	    
	    jtb.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(jtb.isCellSelected(jtb.getSelectedRow(), jtb.getSelectedColumn())){
				     oldvalue=jtb.getValueAt(jtb.getSelectedRow(), jtb.getSelectedColumn()).toString();     
			}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				 
			}
	    });
	    jtb.getModel().addTableModelListener(new TableModelListener(){

			@Override
			public void tableChanged(TableModelEvent arg0) {
				// TODO Auto-generated method stub
				if(!changed){
				changed=true;
				jtb.setValueAt(oldvalue, arg0.getFirstRow(), arg0.getColumn());
				changed=false;
				}
				
			}}
	    		);
			return jtb;
	}
	static JTable settable()
	{	mp2.remove(jsp);
    String[] Names={"标题","时间","来源","网址"};
    JTable jtb  =new JTable(newsInfo,Names);
  //  jtb.setEnabled(false);
    
    jtb.addMouseListener(new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(jtb.isCellSelected(jtb.getSelectedRow(), jtb.getSelectedColumn())){
			     oldvalue=jtb.getValueAt(jtb.getSelectedRow(), jtb.getSelectedColumn()).toString();     
		}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			 
		}
    });
    jtb.getModel().addTableModelListener(new TableModelListener(){

		@Override
		public void tableChanged(TableModelEvent arg0) {
			// TODO Auto-generated method stub
			if(!changed){
			changed=true;
			jtb.setValueAt(oldvalue, arg0.getFirstRow(), arg0.getColumn());
			changed=false;
			}
			
		}}
    		);
    jtb.setVisible(true);
    jsp=new JScrollPane (jtb);
 	jsp.setBounds(450, 20, 1100, 580);
 	jsp.setVisible(true);
 	mp2.add(jsp);
    mp2.validate();
    mp2.repaint();
	return jtb;
	}
	public static void updateData()
	{
		DAO d=new DAO();
		List<Map<String,String>> newslist=d.find(jeb1.getText(),jeb4.getText(),jeb2.getText(),jeb3.getText(),"",tabletitle);
		newsInfo=new Object[newslist.size()][4];
		int c=0;
		Iterator i=newslist.iterator();
		while(i.hasNext())
		{
			Map<String,String> m=(Map<String, String>) i.next();
			newsInfo[c][0]=m.get("title");
			newsInfo[c][1]=m.get("time");
			newsInfo[c][2]=m.get("publish");
			newsInfo[c][3]=m.get("link");
			c++;
		}
		if(c==0)
		{newsInfo=new Object[][]{{"","","",""}};}
		jtable=settable();
	}
}
