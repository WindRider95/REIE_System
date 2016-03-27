package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;

public class main {
	MyFrame mf;
	MyPanel mp;
	MyPanel1 mp1;
	MyPanel2 mp2;
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
		mp1=new MyPanel1(1600,200);
		mp2=new MyPanel2(1600,600);
		mp.add(mp1);
		mp.add(mp2);
		mf.add(mp);
		mf.show();
	}
}
