package cn.tedu.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background {
	BufferedImage img = Main.background;
	int x1;
	int y1;
	int x2;
	int y2 = -852;
	int stepPx = 2;
	
	public void step(){
		y1 += stepPx;
		y2 += stepPx;
		
		if(y1 >= 852){
			y1 = -852;
		}
		
		if(y2 >= 852){
			y2 = -852;
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(img, x1, y1, null);
		g.drawImage(img, x2, y2, null);
	}
}
