package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class MyFrame extends JFrame{
	MyFrame(int w,int h)
	{
		super();
		this.setSize(w,h);
		this.setTitle("REIE_SYSTEM");
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();
		this.setLocation(width/2-800, height/2-400);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("./src/icon.png"));
	}
}
