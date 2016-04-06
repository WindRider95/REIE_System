package view;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
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
		this.setLayout(null);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("./src/icon.png"));
	}
	@Override
	public void paint(Graphics g) { /* ÖØ»æº¯Êý */
	
	super.paint(g);

	System.out.println("paint");
	Image im=Toolkit.getDefaultToolkit().getImage("./src/bg1.jpg");
	im=im.getScaledInstance(1600, 800, Image.SCALE_DEFAULT);
	 MediaTracker   t   =   new   MediaTracker(this);   
	  t.addImage(im,   0);   
	  try {

	t.waitForAll();
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}   
	  Graphics2D g2=(Graphics2D)g;
	  AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
	    g2.setComposite(ac);
if(g2.drawImage(im, 0, 0,1600,800, null))
{
System.out.println("yes");
}

}
}
