package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class main {
	MyFrame mf;
	MyPanel mp;
	JPanel mp1;
	JPanel mp2;

	JComboBox jcb;
	JButton jb1;
	JButton jb2;
	JTable jtable;
	String oldvalue;
	Boolean changed=false;
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
		
		mp1.setSize(1600,200);
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
		mp2=new JPanel();
		mp2.setBounds(0, 200, 1600, 600);
		
		mp2.setLayout(null);
		//todo: tree init
		DefaultMutableTreeNode tn=new DefaultMutableTreeNode("fefe");
		tn.add(new DefaultMutableTreeNode("a"));
		tn.add(new DefaultMutableTreeNode("b"));
		JTree jtree=new JTree(tn);
		jtree.getSelectionModel().setSelectionMode(

				TreeSelectionModel.SINGLE_TREE_SELECTION);
		jtree.setBounds(20, 20,380,500);
		jtree.setVisible(true);
		
		jtable=settable();
		JScrollPane jsp= new JScrollPane(jtable);
		jsp.setBounds(450, 20, 1100, 500);
		jtable.setVisible(true);
		
		mp2.add(jtree);
		mp2.add(jsp);
		mp.add(mp1);
		mp.add(mp2);
		
		mf.add(mp);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.show();
	}
	JTable settable()
	{
		 Object[][] playerInfo={
                 {"阿呆",new Integer(66),new Integer(32),new Integer(98),new Boolean(false)},
                {"阿呆",new Integer(82),new Integer(69),new Integer(128),new Boolean(true)},
    };
    String[] Names={"标题","时间","来源","关键字","网址"};
    JTable jtb=new JTable(playerInfo,Names);
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
}
