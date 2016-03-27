package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class MyPanel1 extends JPanel{
	JPanel jp1;
	JPanel jp2;
	JComboBox jcb;
	JButton jb1;
	JButton jb2;
	MyPanel1(int w,int h)
	{
		super();
		this.setSize(w,h);
		//this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setLayout(null);
		jp1=new JPanel();
		jp1.setLayout(null);
		//jp1.setSize(w,h/2);
		jp1.setBounds(0, 0, w, h/2);
		//jp1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		jp2=new JPanel();
		jcb=new JComboBox();
		jcb.setEditable(true);
		jcb.setSelectedItem("");
		jcb.addItem("news.163.com");
		jcb.addItem("news.sina.com.cn");
		
	
		jcb.setBounds(40, 40, 250, 40);
		jp1.add(jcb);
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
		jp1.add(jb1);
		jb2=new JButton();

		jb2.setBounds(500, 40, 150, 40);
		jb2.setText("Õ£÷π");
		jp1.add(jb2);
		this.add(jp1);
		this.add(jp2);
	}
	
}
