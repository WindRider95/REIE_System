package view;

import java.awt.GridLayout;

import javax.swing.*;

public class MyPanel extends JPanel {
	MyPanel (int w,int h)
	{
		super();
		this.setSize(w,h);
		this.setLayout(new GridLayout(2,1));
	}
}
