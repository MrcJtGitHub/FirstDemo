package cn.tedu.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy {
	static final int STATUS_NORMAL = 0;
	static final int STATUS_EXPLODE = 1;
	static final int STATUS_DEAD = 2;
	
	BufferedImage[] imgs;
	BufferedImage img;
	int x;
	int y;
	int stepPx;
	int life;
	int curIndex;
	int damage;
	int score;
	int status = STATUS_NORMAL;
	
	public void step(){
		if(status == STATUS_NORMAL){
			y += stepPx;			
		} else if(status == STATUS_EXPLODE){
			if(GamePanel.counter % 6 == 0){
				img = imgs[++curIndex];
				if(curIndex >= imgs.length - 1){
					status = STATUS_DEAD;
				}
			}
		}
	}
	
	public boolean isOut(){
		return x <= -img.getWidth() || x >= 400 || y >= 654;
	}
	
	public void paint(Graphics g){
		g.drawImage(img, x, y, null);
	}
	
	public void init(){
		this.y = - img.getHeight();
		this.x = new Random().nextInt(400-img.getWidth());
	}
	
	public boolean hit(Bullet b){
		int r = img.getWidth() / 2;
		
		int x1 = x + img.getWidth() / 2;
		int y1 = y + img.getHeight() / 2;
	
		int dx = x1 - b.x;
		int dy = y1 - b.y;
		
		return Math.sqrt(dx*dx+dy*dy) <= r;
	}
	
	public boolean hit(Hero h){
		int r1 = img.getWidth() / 2;
		int r2 = h.img.getHeight() / 2;
		
		int x1 = x + img.getWidth() / 2;
		int y1 = y + img.getHeight() / 2;
		int x2 = h.x + h.img.getWidth() / 2;
		int y2 = h.y + h.img.getHeight() / 2;
		
		int dx = x1 - x2;
		int dy = y1 - y2;
		
		return Math.sqrt(dx*dx + dy*dy) <= r1 + r2;
	}
	
	public boolean isNormal(){
		return status == STATUS_NORMAL;
	}
	
	public boolean isExplode(){
		return status == STATUS_EXPLODE;
	}
	
	public boolean isDead(){
		return status == STATUS_DEAD;
	}
}
