package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
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
		jb1.setText("≥È»°");
		jb1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("clicked");
				System.out.println(jcb.getSelectedItem().toString());
			}});
		jb2=new JButton();
		jb2.setBounds(500, 40, 150, 40);
		jb2.setText("Õ£÷π");
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
		JTable jtable=new JTable();
		jtable.setBounds(450, 20, 1100, 500);
		jtable.setVisible(true);

		mp2.add(jtree);
		mp2.add(jtable);
		mp.add(mp1);
		mp.add(mp2);
		mf.add(mp);
		mf.show();
	}
}
